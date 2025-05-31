"use client"

import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { User, Building2, ArrowRight, CheckCircle } from "lucide-react"
import Image from "next/image"
import Link from "next/link"

export default function AccountTypePage() {
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
        <div className="w-full max-w-4xl">
          {/* Title Section */}
          <div className="text-center mb-12">
            <h1 className="text-4xl lg:text-5xl font-bold text-slate-800 mb-4">¿Cómo quieres registrarte?</h1>
            <p className="text-xl text-slate-600 max-w-2xl mx-auto">
              Elige el tipo de cuenta que mejor se adapte a tus necesidades y comienza a aprovechar la inteligencia de
              mercado de OHO
            </p>
          </div>

          {/* Account Type Options */}
          <div className="grid md:grid-cols-2 gap-8 mb-12">
            {/* Individual Account */}
            <Link href="/tipo-registro/registro-individual" className="group">
              <Card className="h-full border-0 shadow-lg hover:shadow-2xl transition-all duration-300 group-hover:scale-105 bg-white/80 backdrop-blur-sm overflow-hidden">
                <CardContent className="p-8 h-full flex flex-col">
                  {/* Icon */}
                  <div className="w-20 h-20 bg-gradient-to-br from-blue-600 to-blue-700 rounded-2xl flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform duration-300">
                    <User className="w-10 h-10 text-white" />
                  </div>

                  {/* Title */}
                  <h2 className="text-2xl font-bold text-slate-800 text-center mb-4">Usuario Individual</h2>

                  {/* Description */}
                  <p className="text-slate-600 text-center mb-6 leading-relaxed flex-grow">
                    Perfecto para emprendedores, freelancers y profesionales que buscan insights de mercado para sus
                    proyectos personales o pequeños negocios.
                  </p>

                  {/* Features */}
                  <div className="space-y-3 mb-8">
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Acceso a herramientas básicas de análisis</span>
                    </div>
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Monitoreo de hasta 50 productos</span>
                    </div>
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Reportes mensuales automatizados</span>
                    </div>
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Soporte por email</span>
                    </div>
                  </div>

                  {/* CTA Button */}
                  <Button className="w-full bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white shadow-lg group-hover:shadow-xl transition-all duration-300">
                    Registrarse como Individual
                    <ArrowRight className="w-4 h-4 ml-2 group-hover:translate-x-1 transition-transform" />
                  </Button>

                  {/* Pricing Hint */}
                  <p className="text-center text-sm text-slate-500 mt-3">Desde $29/mes</p>
                </CardContent>
              </Card>
            </Link>

            {/* Business Account */}
            <Link href="/tipo-registro/registro-empresarial" className="group">
              <Card className="h-full border-0 shadow-lg hover:shadow-2xl transition-all duration-300 group-hover:scale-105 bg-white/80 backdrop-blur-sm overflow-hidden relative">
                {/* Popular Badge */}
                <div className="absolute top-4 right-4 bg-gradient-to-r from-emerald-600 to-emerald-700 text-white text-xs font-bold px-3 py-1 rounded-full">
                  Más Popular
                </div>

                <CardContent className="p-8 h-full flex flex-col">
                  {/* Icon */}
                  <div className="w-20 h-20 bg-gradient-to-br from-emerald-600 to-emerald-700 rounded-2xl flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform duration-300">
                    <Building2 className="w-10 h-10 text-white" />
                  </div>

                  {/* Title */}
                  <h2 className="text-2xl font-bold text-slate-800 text-center mb-4">Cuenta Empresarial</h2>

                  {/* Description */}
                  <p className="text-slate-600 text-center mb-6 leading-relaxed flex-grow">
                    Ideal para empresas, equipos y organizaciones que necesitan análisis avanzados de mercado y
                    colaboración en tiempo real.
                  </p>

                  {/* Features */}
                  <div className="space-y-3 mb-8">
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Análisis avanzado y personalizado</span>
                    </div>
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Monitoreo ilimitado de productos</span>
                    </div>
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Reportes en tiempo real y personalizados</span>
                    </div>
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Soporte prioritario 24/7</span>
                    </div>
                    <div className="flex items-center gap-3 text-sm text-slate-600">
                      <CheckCircle className="w-4 h-4 text-green-600 flex-shrink-0" />
                      <span>Gestión de equipos y permisos</span>
                    </div>
                  </div>

                  {/* CTA Button */}
                  <Button className="w-full bg-gradient-to-r from-emerald-600 to-emerald-700 hover:from-emerald-700 hover:to-emerald-800 text-white shadow-lg group-hover:shadow-xl transition-all duration-300">
                    Registrarse como Empresa
                    <ArrowRight className="w-4 h-4 ml-2 group-hover:translate-x-1 transition-transform" />
                  </Button>

                  {/* Pricing Hint */}
                  <p className="text-center text-sm text-slate-500 mt-3">Desde $99/mes</p>
                </CardContent>
              </Card>
            </Link>
          </div>

          {/* Comparison Table */}
          <div className="bg-white/60 backdrop-blur-sm rounded-2xl p-8 mb-12 border border-slate-200">
            <h3 className="text-2xl font-bold text-slate-800 text-center mb-8">Comparación Rápida</h3>
            <div className="grid md:grid-cols-3 gap-6 text-center">
              <div>
                <h4 className="font-semibold text-slate-700 mb-2">Características</h4>
                <div className="space-y-2 text-sm text-slate-600">
                  <p>Monitoreo de productos</p>
                  <p>Reportes automatizados</p>
                  <p>Análisis predictivo</p>
                  <p>Soporte técnico</p>
                  <p>Gestión de equipos</p>
                </div>
              </div>
              <div>
                <h4 className="font-semibold text-blue-700 mb-2">Individual</h4>
                <div className="space-y-2 text-sm">
                  <p>Hasta 50 productos</p>
                  <p>Mensuales</p>
                  <p>Básico</p>
                  <p>Email</p>
                  <p>No incluido</p>
                </div>
              </div>
              <div>
                <h4 className="font-semibold text-emerald-700 mb-2">Empresarial</h4>
                <div className="space-y-2 text-sm">
                  <p>Ilimitado</p>
                  <p>Tiempo real</p>
                  <p>Avanzado + IA</p>
                  <p>24/7 Prioritario</p>
                  <p>Completa</p>
                </div>
              </div>
            </div>
          </div>

          {/* Login Link */}
          <div className="text-center">
            <p className="text-slate-600 mb-4">
              ¿Ya tienes una cuenta?{" "}
              <Link href="/tipo-inicio-sesion" className="text-blue-600 hover:text-blue-700 font-medium underline">
                Inicia sesión aquí
              </Link>
            </p>

            {/* Additional Help */}
            <div className="flex justify-center space-x-6 text-sm text-slate-500">
              <Link href="/pricing" className="hover:text-slate-700 underline">
                Ver Precios Detallados
              </Link>
              <Link href="/features" className="hover:text-slate-700 underline">
                Comparar Características
              </Link>
              <Link href="/contact" className="hover:text-slate-700 underline">
                Contactar Ventas
              </Link>
            </div>
          </div>
        </div>
      </main>

      {/* Footer */}
      <footer className="bg-white/80 backdrop-blur-sm border-t border-slate-200 py-6">
        <div className="container mx-auto px-4 text-center">
          <p className="text-sm text-slate-500">
            ¿Necesitas ayuda para decidir?{" "}
            <Link href="/contact" className="text-blue-600 hover:text-blue-700 underline">
              Habla con nuestro equipo
            </Link>
          </p>
        </div>
      </footer>
    </div>
  )
}
