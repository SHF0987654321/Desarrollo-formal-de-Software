"use client"

import type React from "react"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Card, CardContent, CardHeader } from "@/components/ui/card"
import { Alert, AlertDescription } from "@/components/ui/alert"
import { ArrowLeft, X, Mail, CheckCircle, AlertCircle } from "lucide-react"
import Image from "next/image"
import Link from "next/link"

import api from "../../services/api" // Importa tu cliente API

export default function ForgotPasswordPage() {
  const [email, setEmail] = useState("")
  const [errors, setErrors] = useState<Record<string, string>>({})
  const [isLoading, setIsLoading] = useState(false)
  const [isSuccess, setIsSuccess] = useState(false)
  const [apiError, setApiError] = useState<string | null>(null) // Para errores de la API

  const handleInputChange = (value: string) => {
    setEmail(value)
    // Clear error when user starts typing
    if (errors.email) {
      setErrors({})
    }
    // Clear success/error states when user starts typing again
    if (isSuccess || apiError) { // Usa apiError en lugar de showError
      setIsSuccess(false)
      setApiError(null)
    }
  }

  const validateForm = () => {
    const newErrors: Record<string, string> = {}

    if (!email.trim()) {
      newErrors.email = "El correo electrónico es requerido"
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      newErrors.email = "Ingresa un correo electrónico válido"
    }

    setErrors(newErrors)
    return Object.keys(newErrors).length === 0
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()

    if (!validateForm()) {
      setApiError("Por favor, ingresa un correo electrónico válido.") // Mensaje de error general de validación
      return
    }

    setIsLoading(true)
    setApiError(null) // Limpiar errores anteriores de la API

    try {
      // Llamada real a la API del backend
      // El endpoint es /api/auth/forgot-password y espera el email en el cuerpo de la solicitud
      await api.post("/auth/forgot-password", email, { // Envía el email directamente como cuerpo de la solicitud
        headers: {
          'Content-Type': 'text/plain' // Asegúrate de que el backend espere text/plain para el email directo
        }
      });

      setIsSuccess(true)
      console.log("Password reset email sent to:", email)
    } catch (err: any) {
      console.error("Error sending reset email:", err);
      if (err.response) {
        // Asumiendo que el backend devuelve un objeto de error con un campo 'message'
        setApiError(err.response.data.message || 'No pudimos enviar el correo. Inténtalo de nuevo.');
      } else {
        setApiError('Error de red o servidor no disponible. Inténtalo de nuevo.');
      }
    } finally {
      setIsLoading(false)
    }
  }

  const handleResendEmail = () => {
    setIsSuccess(false)
    handleSubmit({ preventDefault: () => {} } as React.FormEvent)
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50 flex flex-col">
      {/* Header */}
      <header className="bg-white/95 backdrop-blur-sm border-b border-slate-200">
        <div className="container mx-auto px-4 py-4">
          <div className="flex items-center justify-between">
            {/* Back Arrow */}
            <Link
              href="/inicio" // Asegúrate de que esta URL sea correcta para tu página de login
              className="flex items-center justify-center w-10 h-10 rounded-full hover:bg-slate-100 transition-colors group"
            >
              <ArrowLeft className="w-5 h-5 text-slate-600 group-hover:text-cyan-700" />
            </Link>

            {/* Logo */}
            <div className="flex items-center space-x-3">
              <div className="w-8 h-8 relative">
                <Image src="/owl-logo.png" alt="OHO Logo" fill className="object-contain" />
              </div>
              <span className="text-xl font-bold bg-gradient-to-r from-slate-800 to-cyan-600 bg-clip-text text-transparent">
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
            <div className="w-16 h-16 bg-gradient-to-br from-cyan-600 to-emerald-600 rounded-full flex items-center justify-center mx-auto mb-4">
              <Mail className="w-8 h-8 text-white" />
            </div>
            <h1 className="text-3xl font-bold text-slate-800 mb-2">¿Olvidaste tu Contraseña?</h1>
            <p className="text-slate-600">No te preocupes, te ayudamos a recuperarla</p>
          </div>

          {/* Recovery Form */}
          <Card className="shadow-xl border-0 bg-white/80 backdrop-blur-sm">
            <CardHeader className="pb-4">
              <div className="text-center">
                <h2 className="text-xl font-semibold text-slate-800">Recuperar Contraseña</h2>
              </div>
            </CardHeader>
            <CardContent className="space-y-6">
              {/* API Success State */}
              {isSuccess && (
                <Alert className="border-green-200 bg-green-50">
                  <CheckCircle className="h-4 w-4 text-green-600" />
                  <AlertDescription className="text-green-800">
                    <div className="space-y-2">
                      <p className="font-medium">¡Correo enviado exitosamente!</p>
                      <p className="text-sm">
                        Hemos enviado un enlace de recuperación a <strong>{email}</strong>. Revisa tu bandeja de entrada
                        y sigue las instrucciones.
                      </p>
                      <p className="text-sm text-green-700">
                        Si no recibes el correo en unos minutos, revisa tu carpeta de spam.
                      </p>
                    </div>
                  </AlertDescription>
                </Alert>
              )}

              {/* API Error State */}
              {apiError && ( // Usa apiError aquí
                <Alert className="border-red-200 bg-red-50">
                  <AlertCircle className="h-4 w-4 text-red-600" />
                  <AlertDescription className="text-red-800">
                    <div className="space-y-2">
                      <p className="font-medium">Error:</p>
                      <p className="text-sm">
                        {apiError}
                      </p>
                      <p className="text-sm text-red-700">
                        Verifica que el correo sea correcto o{" "}
                        <Link href="/registro" className="text-red-700 underline font-medium">
                          crea una cuenta nueva
                        </Link>
                        .
                      </p>
                    </div>
                  </AlertDescription>
                </Alert>
              )}

              {/* Form */}
              {!isSuccess && (
                <>
                  <div className="bg-cyan-50 rounded-lg p-4 border border-cyan-200">
                    <p className="text-sm text-cyan-800 leading-relaxed">
                      <Mail className="w-4 h-4 inline mr-2" />
                      Ingresa tu correo electrónico y te enviaremos un enlace seguro para restablecer tu contraseña. El
                      enlace será válido por 24 horas.
                    </p>
                  </div>

                  <form onSubmit={handleSubmit} className="space-y-6">
                    {/* Email */}
                    <div className="space-y-2">
                      <Label htmlFor="email" className="text-slate-700 font-medium">
                        Correo Electrónico
                      </Label>
                      <Input
                        id="email"
                        type="email"
                        placeholder="tu@email.com"
                        value={email}
                        onChange={(e) => handleInputChange(e.target.value)}
                        className={`transition-all duration-200 ${
                          errors.email
                            ? "border-red-500 focus:border-red-500 focus:ring-red-500/20"
                            : "border-slate-300 focus:border-cyan-500 focus:ring-cyan-500/20"
                        }`}
                        disabled={isLoading}
                      />
                      {errors.email && (
                        <p className="text-sm text-red-600 flex items-center gap-1">
                          <X className="w-4 h-4" />
                          {errors.email}
                        </p>
                      )}
                    </div>

                    {/* Submit Button */}
                    <Button
                      type="submit"
                      disabled={isLoading}
                      className="w-full bg-gradient-to-r from-cyan-600 to-cyan-700 hover:from-cyan-700 hover:to-cyan-800 text-white shadow-lg hover:shadow-xl transition-all duration-300 py-3"
                    >
                      {isLoading ? (
                        <div className="flex items-center gap-2">
                          <div className="w-4 h-4 border-2 border-white border-t-transparent rounded-full animate-spin"></div>
                          Enviando enlace...
                        </div>
                      ) : (
                        "Enviar Enlace de Recuperación"
                      )}
                    </Button>
                  </form>
                </>
              )}

              {/* Success Actions */}
              {isSuccess && (
                <div className="space-y-3">
                  <Button
                    onClick={handleResendEmail}
                    variant="outline"
                    disabled={isLoading}
                    className="w-full border-slate-300 hover:bg-slate-50 transition-colors"
                  >
                    {isLoading ? "Reenviando..." : "Reenviar Correo"}
                  </Button>
                </div>
              )}

              {/* Back to Login */}
              <div className="text-center">
                <Link
                  href="/inicio" // Asegúrate de que esta URL sea correcta para tu página de login
                  className="inline-flex items-center gap-2 text-cyan-600 hover:text-cyan-700 font-medium underline transition-colors"
                >
                  <ArrowLeft className="w-4 h-4" />
                  Volver a Iniciar Sesión
                </Link>
              </div>

              {/* Additional Help */}
              <div className="text-center pt-4 border-t border-slate-100">
                <p className="text-xs text-slate-500 mb-2">¿Sigues teniendo problemas?</p>
                <div className="flex justify-center space-x-4 text-xs">
                  <Link href="/support" className="text-cyan-600 hover:text-cyan-700 underline">
                    Contactar Soporte
                  </Link>
                  <Link href="/faq" className="text-cyan-600 hover:text-cyan-700 underline">
                    Preguntas Frecuentes
                  </Link>
                </div>
              </div>
            </CardContent>
          </Card>

          {/* Security Notice */}
          <div className="mt-6 text-center">
            <div className="bg-cyan-100 rounded-lg p-4">
              <p className="text-xs text-slate-600 leading-relaxed">
                <strong>Nota de Seguridad:</strong> Por tu seguridad, el enlace de recuperación expirará en 24 horas. Si
                no solicitaste este cambio, puedes ignorar este mensaje de forma segura.
              </p>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}