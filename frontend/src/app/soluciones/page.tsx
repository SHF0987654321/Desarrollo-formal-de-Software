"use client"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import {
  ArrowLeft,
  ArrowRight,
  BarChart3,
  Building2,
  ChevronRight,
  Clock,
  CreditCard,
  Eye,
  Globe,
  LineChart,
  PieChart,
  RefreshCw,
  ShoppingCart,
  TrendingUp,
  Users,
  Mail,
  Phone,
  MapPin,
  Facebook,
  Twitter,
  Linkedin,
} from "lucide-react"
import Image from "next/image"
import Link from "next/link"

export default function SolutionsPage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50">
      {/* Header */}
      <header className="bg-white/95 backdrop-blur-sm border-b border-slate-200 sticky top-0 z-50 transition-all duration-300">
        <div className="container mx-auto px-4 py-4">
          <div className="flex items-center justify-between">
            {/* Back Button */}
            <Link
              href="/"
              className="flex items-center justify-center w-10 h-10 rounded-full hover:bg-slate-100 transition-colors group"
            >
              <ArrowLeft className="w-5 h-5 text-slate-600 group-hover:text-slate-800" />
            </Link>

            {/* Logo */}
            <div className="flex items-center space-x-3">
              <div className="w-10 h-10 relative">
                <Image src="/owl-logo.png" alt="OHO Logo" fill className="object-contain" />
              </div>
              <span className="text-2xl font-bold bg-gradient-to-r from-slate-800 to-blue-600 bg-clip-text text-transparent">
                OHO
              </span>
            </div>

            {/* Spacer for centering */}
            <div className="w-10"></div>
          </div>
        </div>
      </header>

      {/* Hero Section */}
      <section className="py-16 lg:py-24 relative overflow-hidden">
        <div className="absolute inset-0 bg-gradient-to-br from-blue-600/5 to-slate-600/5"></div>
        <div className="container mx-auto px-4 relative">
          <div className="max-w-4xl mx-auto text-center">
            <div className="mb-8 inline-flex items-center px-4 py-2 bg-blue-100 text-blue-700 rounded-full text-sm font-medium">
              <Eye className="w-4 h-4 mr-2" />
              Soluciones de Inteligencia de Mercado
            </div>

            <h1 className="text-4xl lg:text-6xl font-bold text-slate-800 mb-6 leading-tight">
              Potencia tu Estrategia con{" "}
              <span className="bg-gradient-to-r from-blue-600 to-emerald-600 bg-clip-text text-transparent">OHO</span>
            </h1>

            <p className="text-xl text-slate-600 mb-10 max-w-3xl mx-auto leading-relaxed">
              Descubre cómo nuestras soluciones de inteligencia de mercado transforman datos complejos en decisiones
              estratégicas que impulsan tu crecimiento y rentabilidad.
            </p>
          </div>
        </div>
      </section>

      {/* Solutions Section */}
      <section className="py-16 lg:py-24">
        <div className="container mx-auto px-4">
          <div className="max-w-6xl mx-auto">
            <div className="text-center mb-16">
              <h2 className="text-3xl lg:text-4xl font-bold text-slate-800 mb-6">Nuestras Soluciones Integrales</h2>
              <p className="text-xl text-slate-600 max-w-3xl mx-auto">
                Cada solución está diseñada para abordar aspectos críticos de tu estrategia de precios y posicionamiento
                en el mercado.
              </p>
              <div className="w-24 h-1 bg-gradient-to-r from-blue-600 to-emerald-600 mx-auto mt-6"></div>
            </div>

            {/* Price Tracker Solution */}
            <div className="mb-24">
              <Card className="overflow-hidden border-0 shadow-xl bg-white/90 backdrop-blur-sm hover:shadow-2xl transition-all duration-500">
                <div className="grid md:grid-cols-2 gap-6">
                  <div className="p-8 lg:p-10">
                    <CardHeader className="p-0 mb-6">
                      <div className="w-16 h-16 bg-gradient-to-br from-blue-600 to-blue-700 rounded-2xl flex items-center justify-center mb-6">
                        <Eye className="w-8 h-8 text-white" />
                      </div>
                      <CardTitle className="text-3xl font-bold text-slate-800">Rastreador de Precios</CardTitle>
                      <CardDescription className="text-lg text-slate-600 mt-3">
                        Monitoreo constante de precios de la competencia en diversas plataformas, proporcionando datos
                        en tiempo real para mantener tu ventaja competitiva.
                      </CardDescription>
                    </CardHeader>
                    <CardContent className="p-0">
                      <div className="space-y-6">
                        <div className="bg-slate-50 rounded-xl p-6">
                          <h4 className="font-semibold text-slate-800 mb-4">Características Principales</h4>
                          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <RefreshCw className="w-4 h-4 text-blue-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Actualización en Tiempo Real</h5>
                                <p className="text-sm text-slate-600">Datos actualizados cada 15 minutos</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <Globe className="w-4 h-4 text-blue-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Cobertura Global</h5>
                                <p className="text-sm text-slate-600">Monitoreo en más de 50 países</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <ShoppingCart className="w-4 h-4 text-blue-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Múltiples Plataformas</h5>
                                <p className="text-sm text-slate-600">E-commerce, retail y distribuidores</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <Clock className="w-4 h-4 text-blue-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Historial Completo</h5>
                                <p className="text-sm text-slate-600">Datos históricos de hasta 5 años</p>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div className="flex flex-wrap gap-3">
                          <div className="px-4 py-2 bg-slate-100 rounded-full text-sm font-medium text-slate-700">
                            Amazon
                          </div>
                          <div className="px-4 py-2 bg-slate-100 rounded-full text-sm font-medium text-slate-700">
                            Walmart
                          </div>
                          <div className="px-4 py-2 bg-slate-100 rounded-full text-sm font-medium text-slate-700">
                            eBay
                          </div>
                          <div className="px-4 py-2 bg-slate-100 rounded-full text-sm font-medium text-slate-700">
                            Alibaba
                          </div>
                          <div className="px-4 py-2 bg-slate-100 rounded-full text-sm font-medium text-slate-700">
                            +45 más
                          </div>
                        </div>
                      </div>
                    </CardContent>
                    <CardFooter className="p-0 mt-8">
                      <Button className="bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white shadow-lg hover:shadow-xl transition-all duration-300">
                        Más Información
                        <ChevronRight className="w-4 h-4 ml-2" />
                      </Button>
                    </CardFooter>
                  </div>
                  <div className="bg-gradient-to-br from-blue-50 to-slate-100 p-8 flex items-center justify-center">
                    <div className="relative w-full h-[300px] lg:h-[400px]">
                      <div className="absolute inset-0 flex items-center justify-center">
                        <div className="w-full max-w-md bg-white rounded-xl shadow-lg p-6 border border-slate-200">
                          <div className="flex items-center justify-between mb-4">
                            <h5 className="font-semibold text-slate-800">Monitor de Precios</h5>
                            <div className="text-xs px-2 py-1 bg-green-100 text-green-700 rounded-full">
                              Actualizado
                            </div>
                          </div>
                          <div className="space-y-4">
                            <div className="flex items-center justify-between py-2 border-b border-slate-100">
                              <div className="flex items-center gap-2">
                                <div className="w-8 h-8 bg-blue-100 rounded-full"></div>
                                <span className="text-sm font-medium">Competidor A</span>
                              </div>
                              <div className="text-sm font-semibold">$129.99</div>
                            </div>
                            <div className="flex items-center justify-between py-2 border-b border-slate-100">
                              <div className="flex items-center gap-2">
                                <div className="w-8 h-8 bg-emerald-100 rounded-full"></div>
                                <span className="text-sm font-medium">Tu Precio</span>
                              </div>
                              <div className="text-sm font-semibold text-emerald-600">$119.99</div>
                            </div>
                            <div className="flex items-center justify-between py-2 border-b border-slate-100">
                              <div className="flex items-center gap-2">
                                <div className="w-8 h-8 bg-amber-100 rounded-full"></div>
                                <span className="text-sm font-medium">Competidor B</span>
                              </div>
                              <div className="text-sm font-semibold">$134.99</div>
                            </div>
                            <div className="flex items-center justify-between py-2">
                              <div className="flex items-center gap-2">
                                <div className="w-8 h-8 bg-slate-100 rounded-full"></div>
                                <span className="text-sm font-medium">Competidor C</span>
                              </div>
                              <div className="text-sm font-semibold">$124.99</div>
                            </div>
                          </div>
                          <div className="mt-6 pt-4 border-t border-slate-100">
                            <div className="flex items-center justify-between">
                              <span className="text-xs text-slate-500">Última actualización: hace 5 min</span>
                              <button className="text-xs text-blue-600 font-medium">Ver detalles</button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </Card>
            </div>

            {/* Market Analysis Solution */}
            <div className="mb-24">
              <Card className="overflow-hidden border-0 shadow-xl bg-white/90 backdrop-blur-sm hover:shadow-2xl transition-all duration-500">
                <div className="grid md:grid-cols-2 gap-6">
                  <div className="bg-gradient-to-br from-emerald-50 to-slate-100 p-8 flex items-center justify-center order-last md:order-first">
                    <div className="relative w-full h-[300px] lg:h-[400px]">
                      <div className="absolute inset-0 flex items-center justify-center">
                        <div className="w-full max-w-md bg-white rounded-xl shadow-lg p-6 border border-slate-200">
                          <div className="flex items-center justify-between mb-6">
                            <h5 className="font-semibold text-slate-800">Análisis de Tendencias</h5>
                            <div className="text-xs px-2 py-1 bg-blue-100 text-blue-700 rounded-full">Predictivo</div>
                          </div>
                          <div className="space-y-6">
                            <div>
                              <div className="flex items-center justify-between mb-2">
                                <span className="text-sm text-slate-600">Tendencia de Precios</span>
                                <span className="text-xs font-medium text-emerald-600">+4.2%</span>
                              </div>
                              <div className="h-2 bg-slate-100 rounded-full overflow-hidden">
                                <div className="h-full bg-emerald-500 rounded-full" style={{ width: "65%" }}></div>
                              </div>
                            </div>
                            <div>
                              <div className="flex items-center justify-between mb-2">
                                <span className="text-sm text-slate-600">Demanda Proyectada</span>
                                <span className="text-xs font-medium text-blue-600">+12.8%</span>
                              </div>
                              <div className="h-2 bg-slate-100 rounded-full overflow-hidden">
                                <div className="h-full bg-blue-500 rounded-full" style={{ width: "78%" }}></div>
                              </div>
                            </div>
                            <div>
                              <div className="flex items-center justify-between mb-2">
                                <span className="text-sm text-slate-600">Competitividad</span>
                                <span className="text-xs font-medium text-amber-600">Media-Alta</span>
                              </div>
                              <div className="h-2 bg-slate-100 rounded-full overflow-hidden">
                                <div className="h-full bg-amber-500 rounded-full" style={{ width: "45%" }}></div>
                              </div>
                            </div>
                            <div className="pt-4 border-t border-slate-100">
                              <div className="flex items-center gap-2">
                                <LineChart className="w-4 h-4 text-emerald-600" />
                                <span className="text-sm font-medium">Recomendación:</span>
                                <span className="text-sm text-slate-700">Incremento gradual de precios</span>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="p-8 lg:p-10">
                    <CardHeader className="p-0 mb-6">
                      <div className="w-16 h-16 bg-gradient-to-br from-emerald-600 to-emerald-700 rounded-2xl flex items-center justify-center mb-6">
                        <TrendingUp className="w-8 h-8 text-white" />
                      </div>
                      <CardTitle className="text-3xl font-bold text-slate-800">Análisis de Mercado</CardTitle>
                      <CardDescription className="text-lg text-slate-600 mt-3">
                        Transformación de datos en insights accionables para estrategias de precios competitivas, con
                        análisis predictivos y recomendaciones personalizadas.
                      </CardDescription>
                    </CardHeader>
                    <CardContent className="p-0">
                      <div className="space-y-6">
                        <div className="bg-slate-50 rounded-xl p-6">
                          <h4 className="font-semibold text-slate-800 mb-4">Tipos de Análisis</h4>
                          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-emerald-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <LineChart className="w-4 h-4 text-emerald-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Análisis Predictivo</h5>
                                <p className="text-sm text-slate-600">Precisión del 92% a 30 días</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-emerald-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <PieChart className="w-4 h-4 text-emerald-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Segmentación</h5>
                                <p className="text-sm text-slate-600">Análisis por categoría y región</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-emerald-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <Users className="w-4 h-4 text-emerald-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Comportamiento</h5>
                                <p className="text-sm text-slate-600">Patrones de compra y elasticidad</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-emerald-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <Building2 className="w-4 h-4 text-emerald-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Competitivo</h5>
                                <p className="text-sm text-slate-600">Benchmarking y posicionamiento</p>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div className="bg-emerald-50 rounded-xl p-6">
                          <div className="flex items-center gap-3 mb-4">
                            <div className="w-10 h-10 bg-emerald-100 rounded-full flex items-center justify-center">
                              <span className="text-emerald-700 font-bold">92%</span>
                            </div>
                            <h4 className="font-semibold text-slate-800">Precisión Promedio</h4>
                          </div>
                          <p className="text-sm text-slate-600">
                            Nuestros modelos predictivos alcanzan una precisión promedio del 92% en proyecciones a 30
                            días, superando el estándar de la industria del 78%.
                          </p>
                        </div>
                      </div>
                    </CardContent>
                    <CardFooter className="p-0 mt-8">
                      <Button className="bg-gradient-to-r from-emerald-600 to-emerald-700 hover:from-emerald-700 hover:to-emerald-800 text-white shadow-lg hover:shadow-xl transition-all duration-300">
                        Ver Casos de Éxito
                        <ChevronRight className="w-4 h-4 ml-2" />
                      </Button>
                    </CardFooter>
                  </div>
                </div>
              </Card>
            </div>

            {/* Financial Analysis Solution */}
            <div className="mb-16">
              <Card className="overflow-hidden border-0 shadow-xl bg-white/90 backdrop-blur-sm hover:shadow-2xl transition-all duration-500">
                <div className="grid md:grid-cols-2 gap-6">
                  <div className="p-8 lg:p-10">
                    <CardHeader className="p-0 mb-6">
                      <div className="w-16 h-16 bg-gradient-to-br from-amber-600 to-amber-700 rounded-2xl flex items-center justify-center mb-6">
                        <BarChart3 className="w-8 h-8 text-white" />
                      </div>
                      <CardTitle className="text-3xl font-bold text-slate-800">Análisis Financiero</CardTitle>
                      <CardDescription className="text-lg text-slate-600 mt-3">
                        Evaluación de rentabilidad y simulación de escenarios para optimizar tus márgenes, con
                        proyecciones financieras avanzadas.
                      </CardDescription>
                    </CardHeader>
                    <CardContent className="p-0">
                      <div className="space-y-6">
                        <div className="bg-slate-50 rounded-xl p-6">
                          <h4 className="font-semibold text-slate-800 mb-4">Métricas Financieras Clave</h4>
                          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-amber-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <CreditCard className="w-4 h-4 text-amber-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Margen de Contribución</h5>
                                <p className="text-sm text-slate-600">Análisis por producto y canal</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-amber-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <LineChart className="w-4 h-4 text-amber-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Punto de Equilibrio</h5>
                                <p className="text-sm text-slate-600">Cálculo dinámico por escenario</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-amber-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <TrendingUp className="w-4 h-4 text-amber-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">ROI Proyectado</h5>
                                <p className="text-sm text-slate-600">Estimaciones a 3, 6 y 12 meses</p>
                              </div>
                            </div>
                            <div className="flex items-start gap-3">
                              <div className="w-8 h-8 bg-amber-100 rounded-full flex items-center justify-center flex-shrink-0">
                                <BarChart3 className="w-4 h-4 text-amber-700" />
                              </div>
                              <div>
                                <h5 className="font-medium text-slate-800">Elasticidad de Precios</h5>
                                <p className="text-sm text-slate-600">Impacto en demanda y rentabilidad</p>
                              </div>
                            </div>
                          </div>
                        </div>

                        <div className="bg-amber-50 rounded-xl p-6">
                          <h4 className="font-semibold text-slate-800 mb-4">Integraciones Disponibles</h4>
                          <div className="flex flex-wrap gap-3">
                            <div className="px-4 py-2 bg-white rounded-full text-sm font-medium text-slate-700 shadow-sm">
                              QuickBooks
                            </div>
                            <div className="px-4 py-2 bg-white rounded-full text-sm font-medium text-slate-700 shadow-sm">
                              SAP
                            </div>
                            <div className="px-4 py-2 bg-white rounded-full text-sm font-medium text-slate-700 shadow-sm">
                              Xero
                            </div>
                            <div className="px-4 py-2 bg-white rounded-full text-sm font-medium text-slate-700 shadow-sm">
                              Oracle
                            </div>
                            <div className="px-4 py-2 bg-white rounded-full text-sm font-medium text-slate-700 shadow-sm">
                              +12 más
                            </div>
                          </div>
                        </div>
                      </div>
                    </CardContent>
                    <CardFooter className="p-0 mt-8">
                      <Button className="bg-gradient-to-r from-amber-600 to-amber-700 hover:from-amber-700 hover:to-amber-800 text-white shadow-lg hover:shadow-xl transition-all duration-300">
                        Ver Precios
                        <ChevronRight className="w-4 h-4 ml-2" />
                      </Button>
                    </CardFooter>
                  </div>
                  <div className="bg-gradient-to-br from-amber-50 to-slate-100 p-8 flex items-center justify-center">
                    <div className="relative w-full h-[300px] lg:h-[400px]">
                      <div className="absolute inset-0 flex items-center justify-center">
                        <div className="w-full max-w-md bg-white rounded-xl shadow-lg p-6 border border-slate-200">
                          <div className="flex items-center justify-between mb-6">
                            <h5 className="font-semibold text-slate-800">Simulador de Escenarios</h5>
                            <div className="text-xs px-2 py-1 bg-amber-100 text-amber-700 rounded-full">Proyección</div>
                          </div>
                          <div className="space-y-6">
                            <div className="grid grid-cols-2 gap-4">
                              <div className="bg-slate-50 p-3 rounded-lg">
                                <div className="text-xs text-slate-500 mb-1">Precio Actual</div>
                                <div className="text-lg font-semibold">$89.99</div>
                              </div>
                              <div className="bg-amber-50 p-3 rounded-lg">
                                <div className="text-xs text-slate-500 mb-1">Precio Óptimo</div>
                                <div className="text-lg font-semibold text-amber-700">$99.99</div>
                              </div>
                            </div>
                            <div className="space-y-3">
                              <div>
                                <div className="flex items-center justify-between mb-1">
                                  <span className="text-xs text-slate-500">Margen Actual</span>
                                  <span className="text-xs font-medium">32%</span>
                                </div>
                                <div className="h-1.5 bg-slate-100 rounded-full overflow-hidden">
                                  <div className="h-full bg-slate-400 rounded-full" style={{ width: "32%" }}></div>
                                </div>
                              </div>
                              <div>
                                <div className="flex items-center justify-between mb-1">
                                  <span className="text-xs text-slate-500">Margen Proyectado</span>
                                  <span className="text-xs font-medium text-amber-700">41%</span>
                                </div>
                                <div className="h-1.5 bg-slate-100 rounded-full overflow-hidden">
                                  <div className="h-full bg-amber-500 rounded-full" style={{ width: "41%" }}></div>
                                </div>
                              </div>
                              <div>
                                <div className="flex items-center justify-between mb-1">
                                  <span className="text-xs text-slate-500">Impacto en Ventas</span>
                                  <span className="text-xs font-medium text-amber-700">-3%</span>
                                </div>
                                <div className="h-1.5 bg-slate-100 rounded-full overflow-hidden">
                                  <div
                                    className="h-full bg-amber-300 rounded-full"
                                    style={{ width: "97%", marginLeft: "3%" }}
                                  ></div>
                                </div>
                              </div>
                            </div>
                            <div className="pt-4 border-t border-slate-100">
                              <div className="flex items-center justify-between">
                                <div className="flex items-center gap-2">
                                  <TrendingUp className="w-4 h-4 text-emerald-600" />
                                  <span className="text-sm font-medium text-emerald-600">+8.5% Rentabilidad</span>
                                </div>
                                <button className="text-xs text-amber-600 font-medium">Ajustar Variables</button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </Card>
            </div>
          </div>
        </div>
      </section>

      {/* Call to Action */}
      <section className="py-16 lg:py-24 bg-gradient-to-br from-slate-900 to-blue-900 text-white">
        <div className="container mx-auto px-4">
          <div className="max-w-4xl mx-auto text-center">
            <h2 className="text-3xl lg:text-5xl font-bold mb-6">¿Listo para transformar tu estrategia de precios?</h2>
            <p className="text-xl text-blue-100 mb-10 max-w-3xl mx-auto">
              Únete a cientos de empresas que ya están aprovechando el poder de la inteligencia de mercado con OHO.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Button
                size="lg"
                className="bg-gradient-to-r from-blue-500 to-blue-600 hover:from-blue-600 hover:to-blue-700 text-white shadow-lg hover:shadow-xl transition-all duration-300 px-8 py-6 text-lg"
              >
                Contactar a Ventas
                <ArrowRight className="w-5 h-5 ml-2" />
              </Button>
              <Button
                size="lg"
                variant="outline"
                className="border-2 border-blue-400 text-white hover:bg-blue-800/30 px-8 py-6 text-lg"
              >
                Solicitar Demo
              </Button>
            </div>
            <div className="mt-12 grid grid-cols-2 md:grid-cols-4 gap-8">
              <div className="text-center">
                <div className="text-3xl lg:text-4xl font-bold text-white mb-2">500+</div>
                <p className="text-blue-200">Clientes Activos</p>
              </div>
              <div className="text-center">
                <div className="text-3xl lg:text-4xl font-bold text-white mb-2">50M+</div>
                <p className="text-blue-200">Productos Monitoreados</p>
              </div>
              <div className="text-center">
                <div className="text-3xl lg:text-4xl font-bold text-white mb-2">92%</div>
                <p className="text-blue-200">Precisión Predictiva</p>
              </div>
              <div className="text-center">
                <div className="text-3xl lg:text-4xl font-bold text-white mb-2">15%</div>
                <p className="text-blue-200">Aumento Promedio en Rentabilidad</p>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-slate-900 text-white py-16">
        <div className="container mx-auto px-4">
          <div className="grid md:grid-cols-4 gap-8 mb-12">
            {/* Logo and Description */}
            <div className="md:col-span-2">
              <div className="flex items-center space-x-3 mb-6">
                <div className="w-10 h-10 relative">
                  <Image
                    src="/owl-logo.png"
                    alt="OHO Logo"
                    fill
                  />
                </div>
                <span className="text-2xl font-bold">OHO</span>
              </div>
              <p className="text-slate-300 leading-relaxed max-w-md">
                Transformando datos de mercado en decisiones inteligentes. La sabiduría del búho al servicio de tu
                estrategia comercial.
              </p>
            </div>

            {/* Contact Info */}
            <div>
              <h4 className="text-lg font-semibold mb-6">Contacto</h4>
              <div className="space-y-4">
                <div className="flex items-center space-x-3">
                  <Mail className="w-5 h-5 text-blue-400" />
                  <span className="text-slate-300">contacto@oho.com</span>
                </div>
                <div className="flex items-center space-x-3">
                  <Phone className="w-5 h-5 text-blue-400" />
                  <span className="text-slate-300">+1 (555) 123-4567</span>
                </div>
                <div className="flex items-center space-x-3">
                  <MapPin className="w-5 h-5 text-blue-400" />
                  <span className="text-slate-300">Ciudad, País</span>
                </div>
              </div>
            </div>

            {/* Social Links */}
            <div>
              <h4 className="text-lg font-semibold mb-6">Síguenos</h4>
              <div className="flex space-x-4">
                <Link
                  href="#"
                  className="w-10 h-10 bg-slate-800 rounded-full flex items-center justify-center hover:bg-blue-600 transition-colors"
                >
                  <Facebook className="w-5 h-5" />
                </Link>
                <Link
                  href="#"
                  className="w-10 h-10 bg-slate-800 rounded-full flex items-center justify-center hover:bg-blue-600 transition-colors"
                >
                  <Twitter className="w-5 h-5" />
                </Link>
                <Link
                  href="#"
                  className="w-10 h-10 bg-slate-800 rounded-full flex items-center justify-center hover:bg-blue-600 transition-colors"
                >
                  <Linkedin className="w-5 h-5" />
                </Link>
              </div>
            </div>
          </div>

          {/* Bottom Bar */}
          <div className="border-t border-slate-700 pt-8">
            <div className="flex flex-col md:flex-row justify-between items-center space-y-4 md:space-y-0">
              <p className="text-slate-400 text-sm">© {new Date().getFullYear()} OHO. Todos los derechos reservados.</p>
              <div className="flex space-x-6 text-sm">
                <Link href="#" className="text-slate-400 hover:text-white transition-colors">
                  Términos de Servicio
                </Link>
                <Link href="#" className="text-slate-400 hover:text-white transition-colors">
                  Política de Privacidad
                </Link>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </div>
  )
}
