import axios from 'axios';

// Obtener la URL del API Gateway desde las variables de entorno
const API_GATEWAY_URL = process.env.NEXT_PUBLIC_API_GATEWAY_URL;

// Crear una instancia de Axios
const api = axios.create({
  baseURL: API_GATEWAY_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para añadir el token JWT a cada solicitud
api.interceptors.request.use(
  (config) => {
    // Obtener el token del localStorage (o de donde lo almacenes)
    const token = localStorage.getItem('accessToken');

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Interceptor para manejar errores de respuesta (ej. 401 Unauthorized)
api.interceptors.response.use(
  (response) => response,
  (error) => {
    // Si la respuesta es 401 Unauthorized, podrías redirigir al login
    if (error.response && error.response.status === 401) {
      console.error('Unauthorized request. Redirecting to login...');
      // Implementar una lógica para limpiar el token y redirigir
      localStorage.removeItem('accessToken'); window.location.href = '/auth/login';
    }
    return Promise.reject(error);
  }
);

export default api;
