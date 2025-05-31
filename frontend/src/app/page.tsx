import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import {
  Eye,
  TrendingUp,
  BarChart3,
  Users,
  Mail,
  MapPin,
  Phone,
  Facebook,
  Twitter,
  Linkedin,
  ChevronDown,
} from "lucide-react"
import Image from "next/image"
import Link from "next/link"

export default function Component() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50">
      {/* Header */}
      <header className="bg-white/95 backdrop-blur-sm border-b border-slate-200 sticky top-0 z-50 transition-all duration-300">
        <div className="container mx-auto px-4 py-4">
          <div className="flex items-center justify-between">
            {/* Logo */}
            <div className="flex items-center space-x-3">
              <div className="w-10 h-10 relative">
                <Image src="/owl-logo.png" alt="OHO Logo" fill className="object-contain" />
              </div>
              <span className="text-2xl font-bold bg-gradient-to-r from-slate-800 to-blue-600 bg-clip-text text-transparent">
                OHO
              </span>
            </div>

            {/* Navigation */}
            <nav className="hidden md:flex items-center space-x-8">
              {/* Enlaces con href a los IDs de las secciones */}
              <Link href="#inicio" className="text-slate-600 hover:text-blue-600 transition-colors font-medium">
                Inicio
              </Link>
              <Link href="#servicios" className="text-slate-600 hover:text-blue-600 transition-colors font-medium">
                Servicios
              </Link>
              <Link href="#sobre-nosotros" className="text-slate-600 hover:text-blue-600 transition-colors font-medium">
                Sobre Nosotros
              </Link>
              <Link href="#contacto" className="text-slate-600 hover:text-blue-600 transition-colors font-medium">
                Contacto
              </Link>
            </nav>

            {/* Auth Buttons */}
            <div className="flex items-center space-x-3">
              {/* Estos enlaces deberían llevar a las páginas de login/register */}
              <Button asChild variant="ghost" className="text-slate-600 hover:text-blue-600 hover:bg-blue-50">
                <Link href="/tipo-inicio-sesion">Iniciar Sesión</Link>
              </Button>
              <Button asChild className="bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white shadow-lg hover:shadow-xl transition-all duration-300">
                <Link href="/tipo-registro">Registrarse</Link>
              </Button>
            </div>
          </div>
        </div>
      </header>

      {/* Hero Section - ID: inicio */}
      <section id="inicio" className="py-20 lg:py-32 relative overflow-hidden">
        <div className="absolute inset-0 bg-gradient-to-br from-blue-600/5 to-slate-600/5"></div>
        <div className="container mx-auto px-4 relative">
          <div className="max-w-4xl mx-auto text-center">
            <div className="mb-8 inline-flex items-center px-4 py-2 bg-blue-100 text-blue-700 rounded-full text-sm font-medium">
              <Eye className="w-4 h-4 mr-2" />
              Inteligencia de Mercado Avanzada
            </div>

            <h1 className="text-4xl lg:text-6xl font-bold text-slate-800 mb-6 leading-tight">
              OHO: Tu Visión Estratégica del{" "}
              <span className="bg-gradient-to-r from-blue-600 to-emerald-600 bg-clip-text text-transparent">
                Mercado
              </span>
            </h1>

            <p className="text-xl lg:text-2xl text-slate-600 mb-8 font-light">
              Desbloqueando la sabiduría del mercado para decisiones de precios inteligentes.
            </p>

            <p className="text-lg text-slate-600 mb-10 max-w-3xl mx-auto leading-relaxed">
              OHO es una plataforma innovadora que monitorea, analiza y transforma datos de precios en insights
              accionables, permitiéndote tomar decisiones estratégicas con la precisión y sabiduría del búho.
            </p>

            <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Link href="/soluciones" passHref>
              <Button
                size="lg"
                className="bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white shadow-lg hover:shadow-xl transition-all duration-300 px-8 py-3"
              >
                Descubre Más
                <ChevronDown className="w-5 h-5 ml-2" />
              </Button>
            </Link>
              <Button
                size="lg"
                variant="outline"
                className="border-2 border-blue-200 text-blue-700 hover:bg-blue-50 px-8 py-3"
              >
                Explora Nuestras Soluciones
              </Button>
            </div>
          </div>
        </div>
      </section>

      {/* Philosophy Section - No tiene enlace directo en el menú, pero está entre Inicio y Servicios */}
      <section className="py-20 bg-white">
        <div className="container mx-auto px-4">
          <div className="max-w-6xl mx-auto">
            <div className="text-center mb-16">
              <h2 className="text-3xl lg:text-4xl font-bold text-slate-800 mb-6">
                Nuestra Filosofía: La Sabiduría del Búho en Cada Decisión
              </h2>
              <div className="w-24 h-1 bg-gradient-to-r from-blue-600 to-emerald-600 mx-auto"></div>
            </div>

            <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
              <Card className="group hover:shadow-xl transition-all duration-300 border-0 shadow-lg bg-gradient-to-br from-blue-50 to-white">
                <CardContent className="p-8 text-center">
                  <div className="w-16 h-16 bg-gradient-to-br from-blue-600 to-blue-700 rounded-full flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform duration-300">
                    <Eye className="w-8 h-8 text-white" />
                  </div>
                  <h3 className="text-xl font-bold text-slate-800 mb-4">Visión Aguda</h3>
                  <p className="text-slate-600 leading-relaxed">
                    Como el búho, OHO posee una visión aguda, escaneando el vasto bosque del mercado para detectar cada
                    movimiento de precios, cada tendencia oculta.
                  </p>
                </CardContent>
              </Card>

              <Card className="group hover:shadow-xl transition-all duration-300 border-0 shadow-lg bg-gradient-to-br from-slate-50 to-white">
                <CardContent className="p-8 text-center">
                  <div className="w-16 h-16 bg-gradient-to-br from-slate-600 to-slate-700 rounded-full flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform duration-300">
                    <TrendingUp className="w-8 h-8 text-white" />
                  </div>
                  <h3 className="text-xl font-bold text-slate-800 mb-4">Análisis Silencioso</h3>
                  <p className="text-slate-600 leading-relaxed">
                    Nuestra plataforma opera con la precisión silenciosa del vuelo del búho, analizando grandes
                    volúmenes de datos sin interrupciones, ofreciendo insights claros y sin ruido.
                  </p>
                </CardContent>
              </Card>

              <Card className="group hover:shadow-xl transition-all duration-300 border-0 shadow-lg bg-gradient-to-br from-emerald-50 to-white">
                <CardContent className="p-8 text-center">
                  <div className="w-16 h-16 bg-gradient-to-br from-emerald-600 to-emerald-700 rounded-full flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform duration-300">
                    <BarChart3 className="w-8 h-8 text-white" />
                  </div>
                  <h3 className="text-xl font-bold text-slate-800 mb-4">Sabiduría Nocturna</h3>
                  <p className="text-slate-600 leading-relaxed">
                    Aprovechamos la 'sabiduría nocturna' del mercado, revelando patrones y oportunidades que otros no
                    ven, permitiéndote tomar decisiones informadas a cualquier hora.
                  </p>
                </CardContent>
              </Card>

              <Card className="group hover:shadow-xl transition-all duration-300 border-0 shadow-lg bg-gradient-to-br from-amber-50 to-white">
                <CardContent className="p-8 text-center">
                  <div className="w-16 h-16 bg-gradient-to-br from-amber-600 to-amber-700 rounded-full flex items-center justify-center mx-auto mb-6 group-hover:scale-110 transition-transform duration-300">
                    <Users className="w-8 h-8 text-white" />
                  </div>
                  <h3 className="text-xl font-bold text-slate-800 mb-4">Estrategia Precisa</h3>
                  <p className="text-slate-600 leading-relaxed">
                    Cada recomendación de precio en OHO es el resultado de una estrategia meticulosa, como la caza
                    precisa del búho, asegurando que cada movimiento sea un paso hacia tu éxito.
                  </p>
                </CardContent>
              </Card>
            </div>
          </div>
        </div>
      </section>

      {/* Services Section - ID: servicios */}
      <section id="servicios" className="py-20 bg-gradient-to-br from-slate-50 to-blue-50">
        <div className="container mx-auto px-4">
          <div className="max-w-6xl mx-auto">
            <div className="text-center mb-16">
              <h2 className="text-3xl lg:text-4xl font-bold text-slate-800 mb-6">
                Nuestros Módulos Clave de Inteligencia
              </h2>
              <p className="text-xl text-slate-600 max-w-3xl mx-auto">
                Descubre las herramientas que transformarán tu estrategia de precios
              </p>
            </div>

            <div className="grid lg:grid-cols-3 gap-8">
              <Card className="group hover:shadow-2xl transition-all duration-500 border-0 shadow-lg overflow-hidden">
                <CardContent className="p-8">
                  <div className="w-14 h-14 bg-gradient-to-br from-blue-600 to-blue-700 rounded-lg flex items-center justify-center mb-6 group-hover:scale-110 transition-transform duration-300">
                    <Eye className="w-7 h-7 text-white" />
                  </div>
                  <h3 className="text-2xl font-bold text-slate-800 mb-4">Rastreador de Precios</h3>
                  <p className="text-slate-600 leading-relaxed mb-6">
                    Monitoreo constante de precios de la competencia en diversas plataformas, proporcionando datos en
                    tiempo real para mantener tu ventaja competitiva.
                  </p>
                  <div className="flex items-center text-blue-600 font-semibold group-hover:text-blue-700 transition-colors">
                    Disponible ahora
                    <ChevronDown className="w-4 h-4 ml-2 rotate-[-90deg]" />
                  </div>
                </CardContent>
              </Card>

              <Card className="group hover:shadow-2xl transition-all duration-500 border-0 shadow-lg overflow-hidden">
                <CardContent className="p-8">
                  <div className="w-14 h-14 bg-gradient-to-br from-emerald-600 to-emerald-700 rounded-lg flex items-center justify-center mb-6 group-hover:scale-110 transition-transform duration-300">
                    <TrendingUp className="w-7 h-7 text-white" />
                  </div>
                  <h3 className="text-2xl font-bold text-slate-800 mb-4">Análisis de Mercado</h3>
                  <p className="text-slate-600 leading-relaxed mb-6">
                    Transformación de datos en insights accionables para estrategias de precios competitivas, con
                    análisis predictivos y recomendaciones personalizadas.
                  </p>
                  <div className="flex items-center text-emerald-600 font-semibold group-hover:text-emerald-700 transition-colors">
                    Disponible ahora
                    <ChevronDown className="w-4 h-4 ml-2 rotate-[-90deg]" />
                  </div>
                </CardContent>
              </Card>

              <Card className="group hover:shadow-2xl transition-all duration-500 border-0 shadow-lg overflow-hidden">
                <CardContent className="p-8">
                  <div className="w-14 h-14 bg-gradient-to-br from-amber-600 to-amber-700 rounded-lg flex items-center justify-center mb-6 group-hover:scale-110 transition-transform duration-300">
                    <BarChart3 className="w-7 h-7 text-white" />
                  </div>
                  <h3 className="text-2xl font-bold text-slate-800 mb-4">Análisis Financiero</h3>
                  <p className="text-slate-600 leading-relaxed mb-6">
                    Evaluación de rentabilidad y simulación de escenarios para optimizar tus márgenes, con proyecciones
                    financieras avanzadas.
                  </p>
                  <div className="flex items-center text-amber-600 font-semibold group-hover:text-amber-700 transition-colors">
                    Próximamente
                    <ChevronDown className="w-4 h-4 ml-2 rotate-[-90deg]" />
                  </div>
                </CardContent>
              </Card>
            </div>
          </div>
        </div>
      </section>

      {/* Team Section - ID: sobre-nosotros */}
      <section id="sobre-nosotros" className="py-20 bg-white">
        <div className="container mx-auto px-4">
          <div className="max-w-4xl mx-auto text-center">
            <h2 className="text-3xl lg:text-4xl font-bold text-slate-800 mb-6">Nuestro Equipo</h2>
            <div className="w-24 h-1 bg-gradient-to-r from-blue-600 to-emerald-600 mx-auto mb-8"></div>

            <p className="text-lg text-slate-600 leading-relaxed mb-12">
              Detrás de OHO hay un equipo apasionado de expertos en inteligencia de mercado, análisis de datos y
              tecnología. Combinamos años de experiencia en el sector con una visión innovadora para crear soluciones
              que realmente transformen la manera en que las empresas entienden y responden al mercado.
            </p>

            <div className="bg-gradient-to-br from-blue-50 to-slate-50 rounded-2xl p-8 lg:p-12">
              <div className="flex items-center justify-center mb-6">
                <div className="w-20 h-20 bg-gradient-to-br from-blue-600 to-emerald-600 rounded-full flex items-center justify-center">
                  <Users className="w-10 h-10 text-white" />
                </div>
              </div>
              <h3 className="text-2xl font-bold text-slate-800 mb-4">Experiencia y Pasión Unidas</h3>
              <p className="text-slate-600 leading-relaxed max-w-2xl mx-auto">
                Nuestro equipo multidisciplinario está comprometido con la excelencia, la innovación continua y el éxito
                de nuestros clientes. Cada miembro aporta una perspectiva única que enriquece nuestra plataforma y
                fortalece nuestra misión de democratizar la inteligencia de mercado.
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Footer - ID: contacto */}
      <footer id="contacto" className="bg-slate-900 text-white py-16">
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