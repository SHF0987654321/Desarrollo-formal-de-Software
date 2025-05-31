"use client"

import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { User, Building2, ArrowRight, LogIn } from "lucide-react"
import Image from "next/image"
import Link from "next/link"

export default function LoginTypePage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50 flex flex-col">
      {/* Header */}
      <header className="bg-white/95 backdrop-blur-sm border-b border-slate-200">
        <div className="container mx-auto px-4 py-6">
          <div className="flex justify-center">
            {/* Logo */}
            <div className="flex items-center space-x-3">
              <div className="w-10 h-10 relative">
                <Image src="/owl-logo.png" alt="OHO Logo" fill className="object-contain" />
              </div>
              <span className="text-2xl font-bold bg-gradient-to-r from-slate-800 to-blue-600 bg-clip-text text-transparent">
                OHO
              </span>
            </div>
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="flex-1 flex items-center justify-center px-4 py-12">
        <div className="w-full max-w-3xl">
          {/* Title Section */}
          <div className="text-center mb-12">
            <div className="w-20 h-20 bg-gradient-to-br from-blue-600 to-blue-700 rounded-full flex items-center justify-center mx-auto mb-6">
              <LogIn className="w-10 h-10 text-white" />
            </div>
            <h1 className="text-4xl lg:text-5xl font-bold text-slate-800 mb-4">¿Cómo quieres iniciar sesión?</h1>
            <p className="text-xl text-slate-600 max-w-2xl mx-auto">
              Selecciona el tipo de cuenta con la que deseas acceder a tu plataforma de inteligencia de mercado
            </p>
          </div>

          {/* Login Type Options */}
          <div className="grid md:grid-cols-2 gap-8 mb-12">
            {/* Individual Login */}
            <Link href="tipo-inicio-sesion/inicio-sesion-individual" className="group">
              <Card className="h-full border-0 shadow-lg hover:shadow-2xl transition-all duration-300 group-hover:scale-105 bg-white/80 backdrop-blur-sm overflow-hidden">
                <CardContent className="p-8 h-full flex flex-col items-center text-center">
                  {/* Icon */}
                  <div className="w-24 h-24 bg-gradient-to-br from-blue-600 to-blue-700 rounded-2xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform duration-300">
                    <User className="w-12 h-12 text-white" />
                  </div>

                  {/* Title */}
                  <h2 className="text-2xl font-bold text-slate-800 mb-4">Usuario Individual</h2>

                  {/* Description */}
                  <p className="text-slate-600 mb-8 leading-relaxed flex-grow">
                    Accede a tu cuenta personal y gestiona tus proyectos de análisis de mercado de forma individual.
                  </p>

                  {/* Features */}
                  <div className="space-y-2 mb-8 text-sm text-slate-600">
                    <p>• Dashboard personalizado</p>
                    <p>• Análisis de productos individuales</p>
                    <p>• Reportes básicos</p>
                  </div>

                  {/* CTA Button */}
                  <Button className="w-full bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white shadow-lg group-hover:shadow-xl transition-all duration-300">
                    Iniciar Sesión Individual
                    <ArrowRight className="w-4 h-4 ml-2 group-hover:translate-x-1 transition-transform" />
                  </Button>
                </CardContent>
              </Card>
            </Link>

            {/* Business Login */}
            <Link href="tipo-inicio-sesion/inicio-sesion-empresarial" className="group">
              <Card className="h-full border-0 shadow-lg hover:shadow-2xl transition-all duration-300 group-hover:scale-105 bg-white/80 backdrop-blur-sm overflow-hidden">
                <CardContent className="p-8 h-full flex flex-col items-center text-center">
                  {/* Icon */}
                  <div className="w-24 h-24 bg-gradient-to-br from-emerald-600 to-emerald-700 rounded-2xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform duration-300">
                    <Building2 className="w-12 h-12 text-white" />
                  </div>

                  {/* Title */}
                  <h2 className="text-2xl font-bold text-slate-800 mb-4">Cuenta Empresarial</h2>

                  {/* Description */}
                  <p className="text-slate-600 mb-8 leading-relaxed flex-grow">
                    Accede al panel empresarial con herramientas avanzadas y gestión de equipos para tu organización.
                  </p>

                  {/* Features */}
                  <div className="space-y-2 mb-8 text-sm text-slate-600">
                    <p>• Panel de administración</p>
                    <p>• Análisis empresarial avanzado</p>
                    <p>• Gestión de equipos y permisos</p>
                  </div>

                  {/* CTA Button */}
                  <Button className="w-full bg-gradient-to-r from-emerald-600 to-emerald-700 hover:from-emerald-700 hover:to-emerald-800 text-white shadow-lg group-hover:shadow-xl transition-all duration-300">
                    Iniciar Sesión Empresarial
                    <ArrowRight className="w-4 h-4 ml-2 group-hover:translate-x-1 transition-transform" />
                  </Button>
                </CardContent>
              </Card>
            </Link>
          </div>

          {/* Alternative Access */}
          <div className="bg-white/60 backdrop-blur-sm rounded-2xl p-6 mb-8 border border-slate-200">
            <div className="text-center">
              <h3 className="text-lg font-semibold text-slate-800 mb-3">¿No estás seguro de tu tipo de cuenta?</h3>
              <p className="text-slate-600 mb-4">
                Puedes usar el acceso general y te dirigiremos automáticamente según tu cuenta registrada.
              </p>
              <Link href="/login">
                <Button variant="outline" className="border-slate-300 hover:bg-slate-50 transition-colors">
                  Acceso General
                  <ArrowRight className="w-4 h-4 ml-2" />
                </Button>
              </Link>
            </div>
          </div>

          {/* Register Link */}
          <div className="text-center">
            <p className="text-slate-600 mb-4">
              ¿No tienes una cuenta?{" "}
              <Link href="/tipo-registro" className="text-blue-600 hover:text-blue-700 font-medium underline">
                Regístrate aquí
              </Link>
            </p>

            {/* Additional Help */}
            <div className="flex justify-center space-x-6 text-sm text-slate-500">
              <Link href="/forgot-password" className="hover:text-slate-700 underline">
                ¿Olvidaste tu contraseña?
              </Link>
              <Link href="/support" className="hover:text-slate-700 underline">
                Soporte Técnico
              </Link>
              <Link href="/contact" className="hover:text-slate-700 underline">
                Contacto
              </Link>
            </div>
          </div>
        </div>
      </main>

      {/* Security Notice */}
      <footer className="bg-white/80 backdrop-blur-sm border-t border-slate-200 py-6">
        <div className="container mx-auto px-4 text-center">
          <div className="flex items-center justify-center gap-2 text-sm text-slate-500">
            <div className="w-2 h-2 bg-green-500 rounded-full"></div>
            <span>Conexión segura SSL • Tus datos están protegidos</span>
          </div>
        </div>
      </footer>
    </div>
  )
}
