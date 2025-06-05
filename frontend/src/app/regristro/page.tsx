"use client"

import type React from "react"
import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Checkbox } from "@/components/ui/checkbox"
import { Card, CardContent, CardHeader } from "@/components/ui/card"
import { ArrowLeft, Eye, EyeOff, Check, X } from "lucide-react"
import Image from "next/image"
import Link from "next/link"
import { useRouter } from "next/navigation" // Importa useRouter de next/navigation

import api from "../../services/api" // Importa tu cliente API

export default function RegisterPage() {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: "",
    confirmPassword: "",
    acceptTerms: false,
  })

  const [showPassword, setShowPassword] = useState(false)
  const [showConfirmPassword, setShowConfirmPassword] = useState(false)
  const [errors, setErrors] = useState<Record<string, string>>({})
  const [isLoading, setIsLoading] = useState(false)
  const [apiError, setApiError] = useState<string | null>(null) // Para errores de la API
  const [apiSuccess, setApiSuccess] = useState<string | null>(null) // Para mensajes de éxito de la API
  const router = useRouter() // Inicializa useRouter

  // Password requirements validation
  const passwordRequirements = {
    length: formData.password.length >= 8,
    uppercase: /[A-Z]/.test(formData.password),
    lowercase: /[a-z]/.test(formData.password),
    number: /\d/.test(formData.password),
    special: /[!@#$%^&*(),.?":{}|<>]/.test(formData.password),
  }

  const isPasswordValid = Object.values(passwordRequirements).every(Boolean)

  const handleInputChange = (field: string, value: string | boolean) => {
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

    if (!formData.fullName.trim()) {
      newErrors.fullName = "El nombre completo es requerido"
    }

    if (!formData.email.trim()) {
      newErrors.email = "El correo electrónico es requerido"
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      newErrors.email = "Ingresa un correo electrónico válido"
    }

    if (!formData.password) {
      newErrors.password = "La contraseña es requerida"
    } else if (!isPasswordValid) {
      newErrors.password = "La contraseña no cumple con los requisitos"
    }

    if (!formData.confirmPassword) {
      newErrors.confirmPassword = "Confirma tu contraseña"
    } else if (formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = "Las contraseñas no coinciden"
    }

    if (!formData.acceptTerms) {
      newErrors.acceptTerms = "Debes aceptar los términos y condiciones"
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
      const response = await api.post("/auth/register", { // Endpoint: /api/auth/register
        nombre: formData.fullName, // Mapea a 'nombre' en el backend DTO
        correoElectronico: formData.email, // Mapea a 'correoElectronico' en el backend DTO
        contrasena: formData.password, // Mapea a 'contrasena' en el backend DTO
      })

      setApiSuccess("¡Registro exitoso! Ahora puedes iniciar sesión.")
      console.log("Registro exitoso:", response.data)
      // Redirigir al login después de un registro exitoso
      router.push("/inicio") // Ajusta la ruta a tu página de login
    } catch (err: any) {
      console.error("Error durante el registro:", err)
      if (err.response) {
        // Errores de validación de Spring Boot (MethodArgumentNotValidException)
        if (err.response.status === 400 && typeof err.response.data === 'object' && !err.response.data.message) {
          const validationErrors = Object.values(err.response.data).map((msg: any) => msg).join(', ');
          setApiError(`Errores de validación: ${validationErrors}`);
        } else {
          // Otros errores del backend (ej. BadRequestException, ResourceNotFoundException)
          setApiError(err.response.data.message || 'Ocurrió un error inesperado al registrarse.');
        }
      } else {
        setApiError('Error de red o servidor no disponible. Inténtalo de nuevo.');
      }
    } finally {
      setIsLoading(false)
    }
  }

  const handleSocialRegister = (provider: string) => {
    console.log(`Register with ${provider}`)
    // Implementar lógica para registro social
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50 flex flex-col">
      {/* Header */}
      <header className="bg-white/95 backdrop-blur-sm border-b border-slate-200">
        <div className="container mx-auto px-4 py-4">
          <div className="flex items-center justify-between">
            {/* Back Arrow */}
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
            <h1 className="text-3xl font-bold text-slate-800 mb-2">Crea tu cuenta OHO</h1>
            <p className="text-slate-600">Únete a la plataforma de inteligencia de mercado</p>
          </div>

          {/* Registration Form */}
          <Card className="shadow-xl border-0 bg-white/80 backdrop-blur-sm">
            <CardHeader className="pb-4">
              <div className="text-center">
                <h2 className="text-xl font-semibold text-slate-800">Registro Individual</h2>
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

                {/* Full Name */}
                <div className="space-y-2">
                  <Label htmlFor="fullName" className="text-slate-700 font-medium">
                    Nombre completo
                  </Label>
                  <Input
                    id="fullName"
                    type="text"
                    placeholder="Ingresa tu nombre completo"
                    value={formData.fullName}
                    onChange={(e) => handleInputChange("fullName", e.target.value)}
                    className={`transition-all duration-200 ${
                      errors.fullName
                        ? "border-red-500 focus:border-red-500 focus:ring-red-500/20"
                        : "border-slate-300 focus:border-blue-500 focus:ring-blue-500/20"
                    }`}
                  />
                  {errors.fullName && (
                    <p className="text-sm text-red-600 flex items-center gap-1">
                      <X className="w-4 h-4" />
                      {errors.fullName}
                    </p>
                  )}
                </div>

                {/* Email */}
                <div className="space-y-2">
                  <Label htmlFor="email" className="text-slate-700 font-medium">
                    Correo electrónico
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
                  <Label htmlFor="password" className="text-slate-700 font-medium">
                    Contraseña
                  </Label>
                  <div className="relative">
                    <Input
                      id="password"
                      type={showPassword ? "text" : "password"}
                      placeholder="Crea una contraseña segura"
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

                  {/* Password Requirements */}
                  {formData.password && (
                    <div className="bg-slate-50 rounded-lg p-3 space-y-2">
                      <p className="text-sm font-medium text-slate-700 mb-2">Requisitos de contraseña:</p>
                      <div className="grid grid-cols-1 gap-1 text-xs">
                        <div
                          className={`flex items-center gap-2 ${passwordRequirements.length ? "text-green-600" : "text-slate-500"}`}
                        >
                          {passwordRequirements.length ? <Check className="w-3 h-3" /> : <X className="w-3 h-3" />}
                          Mínimo 8 caracteres
                        </div>
                        <div
                          className={`flex items-center gap-2 ${passwordRequirements.uppercase ? "text-green-600" : "text-slate-500"}`}
                        >
                          {passwordRequirements.uppercase ? <Check className="w-3 h-3" /> : <X className="w-3 h-3" />}
                          Al menos una mayúscula
                        </div>
                        <div
                          className={`flex items-center gap-2 ${passwordRequirements.lowercase ? "text-green-600" : "text-slate-500"}`}
                        >
                          {passwordRequirements.lowercase ? <Check className="w-3 h-3" /> : <X className="w-3 h-3" />}
                          Al menos una minúscula
                        </div>
                        <div
                          className={`flex items-center gap-2 ${passwordRequirements.number ? "text-green-600" : "text-slate-500"}`}
                        >
                          {passwordRequirements.number ? <Check className="w-3 h-3" /> : <X className="w-3 h-3" />}
                          Al menos un número
                        </div>
                        <div
                          className={`flex items-center gap-2 ${passwordRequirements.special ? "text-green-600" : "text-slate-500"}`}
                        >
                          {passwordRequirements.special ? <Check className="w-3 h-3" /> : <X className="w-3 h-3" />}
                          Al menos un carácter especial (!, @, #, etc.)
                        </div>
                      </div>
                    </div>
                  )}

                  {errors.password && (
                    <p className="text-sm text-red-600 flex items-center gap-1">
                      <X className="w-4 h-4" />
                      {errors.password}
                    </p>
                  )}
                </div>

                {/* Confirm Password */}
                <div className="space-y-2">
                  <Label htmlFor="confirmPassword" className="text-slate-700 font-medium">
                    Confirmar contraseña
                  </Label>
                  <div className="relative">
                    <Input
                      id="confirmPassword"
                      type={showConfirmPassword ? "text" : "password"}
                      placeholder="Confirma tu contraseña"
                      value={formData.confirmPassword}
                      onChange={(e) => handleInputChange("confirmPassword", e.target.value)}
                      className={`pr-10 transition-all duration-200 ${
                        errors.confirmPassword
                          ? "border-red-500 focus:border-red-500 focus:ring-red-500/20"
                          : "border-slate-300 focus:border-blue-500 focus:ring-blue-500/20"
                      }`}
                    />
                    <button
                      type="button"
                      onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                      className="absolute right-3 top-1/2 -translate-y-1/2 text-slate-500 hover:text-slate-700"
                    >
                      {showConfirmPassword ? <EyeOff className="w-4 h-4" /> : <Eye className="w-4 h-4" />}
                    </button>
                  </div>
                  {errors.confirmPassword && (
                    <p className="text-sm text-red-600 flex items-center gap-1">
                      <X className="w-4 h-4" />
                      {errors.confirmPassword}
                    </p>
                  )}
                </div>

                {/* Terms and Conditions */}
                <div className="space-y-2">
                  <div className="flex items-start space-x-3">
                    <Checkbox
                      id="acceptTerms"
                      checked={formData.acceptTerms}
                      onCheckedChange={(checked) => handleInputChange("acceptTerms", checked as boolean)}
                      className="mt-1"
                    />
                    <Label htmlFor="acceptTerms" className="text-sm text-slate-600 leading-relaxed cursor-pointer">
                      Acepto los{" "}
                      <Link href="/terms" className="text-blue-600 hover:text-blue-700 underline">
                        términos y condiciones
                      </Link>{" "}
                      y la{" "}
                      <Link href="/privacy" className="text-blue-600 hover:text-blue-700 underline">
                        política de privacidad
                      </Link>
                    </Label>
                  </div>
                  {errors.acceptTerms && (
                    <p className="text-sm text-red-600 flex items-center gap-1">
                      <X className="w-4 h-4" />
                      {errors.acceptTerms}
                    </p>
                  )}
                </div>

                {/* Register Button */}
                <Button
                  type="submit"
                  disabled={isLoading}
                  className="w-full bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white shadow-lg hover:shadow-xl transition-all duration-300 py-3"
                >
                  {isLoading ? "Creando cuenta..." : "Registrarse"}
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

              {/* Social Registration */}
              <div className="space-y-3">
                <Button
                  type="button"
                  variant="outline"
                  onClick={() => handleSocialRegister("Google")}
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
                  Registrarse con Google
                </Button>

                <Button
                  type="button"
                  variant="outline"
                  onClick={() => handleSocialRegister("Microsoft")}
                  className="w-full border-slate-300 hover:bg-slate-50 transition-colors py-3"
                >
                  <svg className="w-5 h-5 mr-3" viewBox="0 0 24 24">
                    <path fill="#F25022" d="M1 1h10v10H1z" />
                    <path fill="#00A4EF" d="M13 1h10v10H13z" />
                    <path fill="#7FBA00" d="M1 13h10v10H1z" />
                    <path fill="#FFB900" d="M13 13h10v10H13z" />
                  </svg>
                  Registrarse con Microsoft
                </Button>
              </div>

              {/* Login Link */}
              <div className="text-center">
                <p className="text-sm text-slate-600">
                  ¿Ya tienes una cuenta?{" "}
                  <Link href="/inicio" className="text-blue-600 hover:text-blue-700 font-medium underline">
                    Inicia sesión
                  </Link>
                </p>
              </div>
            </CardContent>
          </Card>
        </div>
      </main>
    </div>
  )
}

