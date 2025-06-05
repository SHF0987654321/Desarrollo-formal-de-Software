"use client"

import type React from "react"
import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Card, CardContent, CardHeader } from "@/components/ui/card"
import { ArrowLeft, Eye, EyeOff, X, LogIn } from "lucide-react"
import Image from "next/image"
import Link from "next/link"
import { useRouter } from "next/navigation" // Importa useRouter de next/navigation

import api from "../../services/api" // Importa tu cliente API

export default function LoginPage() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  })

  const [showPassword, setShowPassword] = useState(false)
  const [errors, setErrors] = useState<Record<string, string>>({})
  const [isLoading, setIsLoading] = useState(false)
  const [apiError, setApiError] = useState<string | null>(null) // Para errores de la API
  const [apiSuccess, setApiSuccess] = useState<string | null>(null) // Para mensajes de éxito de la API
  const router = useRouter() // Inicializa useRouter

  const handleInputChange = (field: string, value: string) => {
    setFormData((prev) => ({ ...prev, [field]: value }))
    // Clear error when user starts typing
    if (errors[field]) {
      setErrors((prev) => ({ ...prev, [field]: "" }))
    }
    setApiError(null) // Limpiar errores de API al cambiar input
    setApiSuccess(null) // Limpiar éxito de API al cambiar input
  }

  const validateForm = () => {
    const newErrors: Record<string, string> = {}

    if (!formData.email.trim()) {
      newErrors.email = "El correo electrónico es requerido"
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      newErrors.email = "Ingresa un correo electrónico válido"
    }

    if (!formData.password) {
      newErrors.password = "La contraseña es requerida"
    }

    setErrors(newErrors)
    return Object.keys(newErrors).length === 0
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()

    if (!validateForm()) {
      setApiError("Por favor, corrige los errores del formulario.")
      return
    }

    setIsLoading(true)
    setApiError(null)
    setApiSuccess(null)

    try {
      const response = await api.post("/auth/login", { // Endpoint: /api/auth/login
        correoElectronico: formData.email, // Mapea a 'correoElectronico' en el backend DTO
        contrasena: formData.password, // Mapea a 'contrasena' en el backend DTO
      })

      // Almacenar el token JWT (y quizás el refresh token y el userId)
      localStorage.setItem('accessToken', response.data.accessToken);
      localStorage.setItem('userId', response.data.idUsuario); // Asume que el backend devuelve idUsuario
      localStorage.setItem('refreshToken', response.data.refreshToken); // Implementar refesh token si es necesario

      setApiSuccess("¡Inicio de sesión exitoso! Redirigiendo...")
      console.log("Inicio de sesión exitoso:", response.data)
      
      // Redirigir al dashboard o a la página principal
      router.push('/rastreador') // Ajusta la ruta a tu página principal/dashboard
    } catch (err: any) {
      console.error("Error durante el inicio de sesión:", err)
      if (err.response) {
        // Errores de validación de Spring Boot (MethodArgumentNotValidException)
        if (err.response.status === 400 && typeof err.response.data === 'object' && !err.response.data.message) {
          const validationErrors = Object.values(err.response.data).map((msg: any) => msg).join(', ');
          setApiError(`Errores de validación: ${validationErrors}`);
        } else {
          // Otros errores del backend (ej. BadRequestException, UnauthorizedException)
          setApiError(err.response.data.message || 'Credenciales inválidas. Inténtalo de nuevo.');
        }
      } else {
        setApiError('Error de red o servidor no disponible. Inténtalo de nuevo.');
      }
    } finally {
      setIsLoading(false)
    }
  }

  const handleSocialLogin = (provider: string) => {
    console.log(`Login with ${provider}`)
    // Implementar lógica para inicio de sesión social
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50 flex flex-col">
      {/* Header */}
      <header className="bg-white/95 backdrop-blur-sm border-b border-slate-200">
        <div className="container mx-auto px-4 py-4">
          <div className="flex items-center justify-between">
            {/* Back Arrow (Optional) */}
            <Link
              href="/app"
              className="flex items-center justify-center w-10 h-10 rounded-full hover:bg-slate-100 transition-colors group"
            >
              <ArrowLeft className="w-5 h-5 text-slate-600 group-hover:text-slate-800" />
            </Link>

            {/* Logo */}
            <div className="flex items-center space-x-3">
              <div className="w-8 h-8 relative">
                <Image src="/owl-logo.png" alt="OHO Logo" fill className="object-contain" />
              </div>
              <span className="text-xl font-bold bg-gradient-to-r from-slate-800 to-blue-600 bg-clip-text text-transparent">
                OHO
              </span>
            </div>

            {/* Spacer for centering */}
            <div className="w-10"></div>
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="flex-1 flex items-center justify-center px-4 py-8">
        <div className="w-full max-w-md">
          {/* Title */}
          <div className="text-center mb-8">
            <div className="w-16 h-16 bg-gradient-to-br from-blue-600 to-blue-700 rounded-full flex items-center justify-center mx-auto mb-4">
              <LogIn className="w-8 h-8 text-white" />
            </div>
            <h1 className="text-3xl font-bold text-slate-800 mb-2">Inicia Sesión en OHO</h1>
            <p className="text-slate-600">Accede a tu plataforma de inteligencia de mercado</p>
          </div>

          {/* Login Form */}
          <Card className="shadow-xl border-0 bg-white/80 backdrop-blur-sm">
            <CardHeader className="pb-4">
              <div className="text-center">
                <h2 className="text-xl font-semibold text-slate-800">Bienvenido de vuelta</h2>
              </div>
            </CardHeader>
            <CardContent className="space-y-6">
              <form onSubmit={handleSubmit} className="space-y-6">
                {apiError && (
                  <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                    <strong className="font-bold">Error: </strong>
                    <span className="block sm:inline">{apiError}</span>
                  </div>
                )}
                {apiSuccess && (
                  <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative" role="alert">
                    <strong className="font-bold">Éxito: </strong>
                    <span className="block sm:inline">{apiSuccess}</span>
                  </div>
                )}

                {/* Email */}
                <div className="space-y-2">
                  <Label htmlFor="email" className="text-slate-700 font-medium">
                    Correo Electrónico
                  </Label>
                  <Input
                    id="email"
                    type="email"
                    placeholder="tu@email.com"
                    value={formData.email}
                    onChange={(e) => handleInputChange("email", e.target.value)}
                    className={`transition-all duration-200 ${
                      errors.email
                        ? "border-red-500 focus:border-red-500 focus:ring-red-500/20"
                        : "border-slate-300 focus:border-blue-500 focus:ring-blue-500/20"
                    }`}
                  />
                  {errors.email && (
                    <p className="text-sm text-red-600 flex items-center gap-1">
                      <X className="w-4 h-4" />
                      {errors.email}
                    </p>
                  )}
                </div>

                {/* Password */}
                <div className="space-y-2">
                  <div className="flex items-center justify-between">
                    <Label htmlFor="password" className="text-slate-700 font-medium">
                      Contraseña
                    </Label>
                    <Link
                      href="/recuperar-clave"
                      className="text-sm text-blue-600 hover:text-blue-700 font-medium underline"
                    >
                      ¿Olvidaste tu contraseña?
                    </Link>
                  </div>
                  <div className="relative">
                    <Input
                      id="password"
                      type={showPassword ? "text" : "password"}
                      placeholder="Ingresa tu contraseña"
                      value={formData.password}
                      onChange={(e) => handleInputChange("password", e.target.value)}
                      className={`pr-10 transition-all duration-200 ${
                        errors.password
                          ? "border-red-500 focus:border-red-500 focus:ring-red-500/20"
                          : "border-slate-300 focus:border-blue-500 focus:ring-blue-500/20"
                      }`}
                    />
                    <button
                      type="button"
                      onClick={() => setShowPassword(!showPassword)}
                      className="absolute right-3 top-1/2 -translate-y-1/2 text-slate-500 hover:text-slate-700"
                    >
                      {showPassword ? <EyeOff className="w-4 h-4" /> : <Eye className="w-4 h-4" />}
                    </button>
                  </div>
                  {errors.password && (
                    <p className="text-sm text-red-600 flex items-center gap-1">
                      <X className="w-4 h-4" />
                      {errors.password}
                    </p>
                  )}
                </div>

                {/* Login Button */}
                <Button
                  type="submit"
                  disabled={isLoading}
                  className="w-full bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white shadow-lg hover:shadow-xl transition-all duration-300 py-3"
                >
                  {isLoading ? "Iniciando sesión..." : "Iniciar Sesión"}
                </Button>
              </form>

              {/* Divider */}
              <div className="relative">
                <div className="absolute inset-0 flex items-center">
                  <div className="w-full border-t border-slate-200"></div>
                </div>
                <div className="relative flex justify-center text-sm">
                  <span className="px-4 bg-white text-slate-500">O continúa con</span>
                </div>
              </div>

              {/* Social Login */}
              <div className="space-y-3">
                <Button
                  type="button"
                  variant="outline"
                  onClick={() => handleSocialLogin("Google")}
                  className="w-full border-slate-300 hover:bg-slate-50 transition-colors py-3"
                >
                  <svg className="w-5 h-5 mr-3" viewBox="0 0 24 24">
                    <path
                      fill="#4285F4"
                      d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
                    />
                    <path
                      fill="#34A853"
                      d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
                    />
                    <path
                      fill="#FBBC05"
                      d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
                    />
                    <path
                      fill="#EA4335"
                      d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
                    />
                  </svg>
                  Iniciar Sesión con Google
                </Button>

                <Button
                  type="button"
                  variant="outline"
                  onClick={() => handleSocialLogin("Microsoft")}
                  className="w-full border-slate-300 hover:bg-slate-50 transition-colors py-3"
                >
                  <svg className="w-5 h-5 mr-3" viewBox="0 0 24 24">
                    <path fill="#F25022" d="M1 1h10v10H1z" />
                    <path fill="#00A4EF" d="M13 1h10v10H13z" />
                    <path fill="#7FBA00" d="M1 13h10v10H1z" />
                    <path fill="#FFB900" d="M13 13h10v10H13z" />
                  </svg>
                  Iniciar Sesión con Microsoft
                </Button>
              </div>

              {/* Register Link */}
              <div className="text-center">
                <p className="text-sm text-slate-600">
                  ¿No tienes una cuenta?{" "}
                  <Link href="/registro" className="text-blue-600 hover:text-blue-700 font-medium underline">
                    Regístrate aquí
                  </Link>
                </p>
              </div>

              {/* Additional Help */}
              <div className="text-center pt-4 border-t border-slate-100">
                <p className="text-xs text-slate-500 mb-2">¿Necesitas ayuda?</p>
                <div className="flex justify-center space-x-4 text-xs">
                  <Link href="/support" className="text-blue-600 hover:text-blue-700 underline">
                    Soporte
                  </Link>
                  <Link href="/contact" className="text-blue-600 hover:text-blue-700 underline">
                    Contacto
                  </Link>
                </div>
              </div>
            </CardContent>
          </Card>

          {/* Security Notice */}
          <div className="mt-6 text-center">
            <p className="text-xs text-slate-500 leading-relaxed">
              Al iniciar sesión, aceptas nuestros{" "}
              <Link href="/terms" className="text-blue-600 hover:text-blue-700 underline">
                términos de servicio
              </Link>{" "}
              y{" "}
              <Link href="/privacy" className="text-blue-600 hover:text-blue-700 underline">
                política de privacidad
              </Link>
              .
            </p>
          </div>
        </div>
      </main>
    </div>
  )
}
