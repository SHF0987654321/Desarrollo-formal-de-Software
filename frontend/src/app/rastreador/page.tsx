"use client"

import type React from "react"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { Badge } from "@/components/ui/badge"
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu"
import {
  ArrowLeft,
  PlusCircle,
  LinkIcon,
  Tag,
  LineChartIcon as ChartLine,
  Edit,
  Bell,
  Trash2,
  PauseCircle,
  PlayCircle,
  ArrowUp,
  ArrowDown,
  Minus,
  MoreHorizontal,
  ExternalLink,
  TrendingUp,
} from "lucide-react"
import Image from "next/image"
import Link from "next/link"

// Datos simulados
const mockProducts = [
  {
    id: 1,
    name: "iPhone 15 Pro 128GB",
    platform: "Amazon",
    currentPrice: 1199.99,
    lastChange: "down",
    changeAmount: 50.0,
    status: "active",
    image: "/placeholder.svg?height=40&width=40",
    url: "https://amazon.com/iphone-15-pro",
    lastUpdated: "Hace 2 horas",
  },
  {
    id: 2,
    name: "MacBook Air M2",
    platform: "Apple Store",
    currentPrice: 1399.0,
    lastChange: "up",
    changeAmount: 100.0,
    status: "active",
    image: "/placeholder.svg?height=40&width=40",
    url: "https://apple.com/macbook-air",
    lastUpdated: "Hace 1 hora",
  },
  {
    id: 3,
    name: "Sony WH-1000XM5",
    platform: "PC Componentes",
    currentPrice: 329.99,
    lastChange: "none",
    changeAmount: 0,
    status: "paused",
    image: "/placeholder.svg?height=40&width=40",
    url: "https://pccomponentes.com/sony-headphones",
    lastUpdated: "Hace 6 horas",
  },
  {
    id: 4,
    name: "Samsung Galaxy S24 Ultra",
    platform: "MediaMarkt",
    currentPrice: 0,
    lastChange: "none",
    changeAmount: 0,
    status: "error",
    image: "/placeholder.svg?height=40&width=40",
    url: "https://mediamarkt.es/samsung-galaxy",
    lastUpdated: "Error hace 3 horas",
  },
  {
    id: 5,
    name: "Nintendo Switch OLED",
    platform: "El Corte Inglés",
    currentPrice: 349.95,
    lastChange: "down",
    changeAmount: 20.05,
    status: "active",
    image: "/placeholder.svg?height=40&width=40",
    url: "https://elcorteingles.es/nintendo-switch",
    lastUpdated: "Hace 30 min",
  },
]

export default function PriceTrackerPage() {
  const [isDialogOpen, setIsDialogOpen] = useState(false)
  const [formData, setFormData] = useState({
    url: "",
    name: "",
  })

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    console.log("Añadir producto:", formData)
    setIsDialogOpen(false)
    setFormData({ url: "", name: "" })
  }

  const getStatusBadge = (status: string) => {
    switch (status) {
      case "active":
        return <Badge className="bg-emerald-100 text-emerald-700 hover:bg-emerald-200">Activo</Badge>
      case "paused":
        return <Badge className="bg-amber-100 text-amber-700 hover:bg-amber-200">Pausado</Badge>
      case "error":
        return <Badge className="bg-red-100 text-red-700 hover:bg-red-200">Error</Badge>
      default:
        return <Badge variant="secondary">Desconocido</Badge>
    }
  }

  const getPriceChangeIndicator = (change: string, amount: number) => {
    if (change === "up") {
      return (
        <div className="flex items-center gap-1 text-red-600">
          <ArrowUp className="w-4 h-4" />
          <span className="text-sm">+€{amount.toFixed(2)}</span>
        </div>
      )
    } else if (change === "down") {
      return (
        <div className="flex items-center gap-1 text-emerald-600">
          <ArrowDown className="w-4 h-4" />
          <span className="text-sm">-€{amount.toFixed(2)}</span>
        </div>
      )
    } else {
      return (
        <div className="flex items-center gap-1 text-slate-500">
          <Minus className="w-4 h-4" />
          <span className="text-sm">Sin cambios</span>
        </div>
      )
    }
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50">
      {/* Header */}
      <header className="bg-white/95 backdrop-blur-sm border-b border-slate-200 sticky top-0 z-50">
        <div className="container mx-auto px-4 py-4">
          <div className="flex items-center justify-between">
            {/* Back Button */}
            <Link
              href="/soluciones"
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
      <main className="container mx-auto px-4 py-8">
        {/* Page Title */}
        <div className="text-center mb-8">
          <h1 className="text-4xl font-bold text-slate-800 mb-2">Mi Rastreador de Precios</h1>
          <p className="text-slate-600">Monitorea los precios de tus productos favoritos en tiempo real</p>
        </div>

        {/* Add Product Section */}
        <div className="mb-8">
          <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
            <DialogTrigger asChild>
              <Button className="bg-gradient-to-r from-emerald-600 to-teal-700 hover:from-emerald-700 hover:to-teal-800 text-white shadow-lg hover:shadow-xl transition-all duration-300">
                <PlusCircle className="w-5 h-5 mr-2" />
                Añadir Nuevo Producto
              </Button>
            </DialogTrigger>
            <DialogContent className="bg-white/90 backdrop-blur-md border border-slate-200 shadow-2xl">
              <DialogHeader>
                <DialogTitle className="text-2xl font-bold text-slate-800">Añadir Producto a Rastrear</DialogTitle>
                <DialogDescription className="text-slate-600">
                  Ingresa la URL del producto que deseas monitorear y comenzaremos a rastrear sus cambios de precio.
                </DialogDescription>
              </DialogHeader>
              <form onSubmit={handleSubmit} className="space-y-6 mt-4">
                <div className="space-y-2">
                  <Label htmlFor="url" className="text-slate-700 font-medium">
                    URL del Producto *
                  </Label>
                  <div className="relative">
                    <LinkIcon className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-slate-500" />
                    <Input
                      id="url"
                      type="url"
                      placeholder="https://ejemplo.com/producto"
                      value={formData.url}
                      onChange={(e) => setFormData({ ...formData, url: e.target.value })}
                      className="pl-10 border-slate-300 focus:border-emerald-500 focus:ring-emerald-500/20"
                      required
                    />
                  </div>
                </div>

                <div className="space-y-2">
                  <Label htmlFor="name" className="text-slate-700 font-medium">
                    Nombre del Producto (Opcional)
                  </Label>
                  <div className="relative">
                    <Tag className="absolute left-3 top-1/2 transform -translate-y-1/2 w-4 h-4 text-slate-500" />
                    <Input
                      id="name"
                      type="text"
                      placeholder="Nombre personalizado para el producto"
                      value={formData.name}
                      onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                      className="pl-10 border-slate-300 focus:border-emerald-500 focus:ring-emerald-500/20"
                    />
                  </div>
                </div>

                <Button
                  type="submit"
                  className="w-full bg-gradient-to-r from-emerald-600 to-teal-700 hover:from-emerald-700 hover:to-teal-800 text-white"
                >
                  <TrendingUp className="w-4 h-4 mr-2" />
                  Rastrear Producto
                </Button>
              </form>
            </DialogContent>
          </Dialog>
        </div>

        {/* Products Table */}
        <Card className="shadow-lg bg-white/80 backdrop-blur-sm border-0">
          <CardHeader>
            <CardTitle className="text-2xl font-bold text-slate-800 flex items-center gap-2">
              <ChartLine className="w-6 h-6 text-emerald-600" />
              Productos Rastreados ({mockProducts.length})
            </CardTitle>
          </CardHeader>
          <CardContent>
            {mockProducts.length > 0 ? (
              <div className="overflow-x-auto">
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead className="font-semibold text-slate-700">Producto</TableHead>
                      <TableHead className="font-semibold text-slate-700">Plataforma</TableHead>
                      <TableHead className="font-semibold text-slate-700">Precio Actual</TableHead>
                      <TableHead className="font-semibold text-slate-700">Último Cambio</TableHead>
                      <TableHead className="font-semibold text-slate-700">Estado</TableHead>
                      <TableHead className="font-semibold text-slate-700">Acciones</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {mockProducts.map((product) => (
                      <TableRow key={product.id} className="hover:bg-slate-50/50 transition-colors">
                        <TableCell>
                          <div className="flex items-center gap-3">
                            <Image
                              src={product.image || "/placeholder.svg"}
                              alt={product.name}
                              width={40}
                              height={40}
                              className="rounded-lg border border-slate-200"
                            />
                            <div>
                              <Link
                                href={`/dashboard/rastreador/${product.id}`}
                                className="font-medium text-slate-800 hover:text-emerald-600 transition-colors"
                              >
                                {product.name}
                              </Link>
                              <p className="text-sm text-slate-500">{product.lastUpdated}</p>
                            </div>
                          </div>
                        </TableCell>
                        <TableCell>
                          <div className="flex items-center gap-2">
                            <span className="font-medium text-slate-700">{product.platform}</span>
                            <Link href={product.url} target="_blank" rel="noopener noreferrer">
                              <ExternalLink className="w-4 h-4 text-slate-400 hover:text-emerald-600 transition-colors" />
                            </Link>
                          </div>
                        </TableCell>
                        <TableCell>
                          {product.status === "error" ? (
                            <span className="text-red-600 font-medium">Error</span>
                          ) : (
                            <span className="font-bold text-slate-800 text-lg">€{product.currentPrice.toFixed(2)}</span>
                          )}
                        </TableCell>
                        <TableCell>{getPriceChangeIndicator(product.lastChange, product.changeAmount)}</TableCell>
                        <TableCell>{getStatusBadge(product.status)}</TableCell>
                        <TableCell>
                          <DropdownMenu>
                            <DropdownMenuTrigger asChild>
                              <Button variant="ghost" size="icon" className="hover:bg-slate-100">
                                <MoreHorizontal className="w-4 h-4" />
                              </Button>
                            </DropdownMenuTrigger>
                            <DropdownMenuContent align="end" className="w-48">
                              <DropdownMenuItem asChild>
                                <Link href={`/dashboard/rastreador/${product.id}`} className="flex items-center gap-2">
                                  <ChartLine className="w-4 h-4" />
                                  Ver Gráfico
                                </Link>
                              </DropdownMenuItem>
                              <DropdownMenuItem className="flex items-center gap-2">
                                <Edit className="w-4 h-4" />
                                Editar Producto
                              </DropdownMenuItem>
                              <DropdownMenuItem className="flex items-center gap-2">
                                <Bell className="w-4 h-4" />
                                Configurar Alertas
                              </DropdownMenuItem>
                              <DropdownMenuItem className="flex items-center gap-2">
                                {product.status === "paused" ? (
                                  <>
                                    <PlayCircle className="w-4 h-4" />
                                    Reanudar Rastreo
                                  </>
                                ) : (
                                  <>
                                    <PauseCircle className="w-4 h-4" />
                                    Pausar Rastreo
                                  </>
                                )}
                              </DropdownMenuItem>
                              <DropdownMenuItem className="flex items-center gap-2 text-red-600">
                                <Trash2 className="w-4 h-4" />
                                Eliminar
                              </DropdownMenuItem>
                            </DropdownMenuContent>
                          </DropdownMenu>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </div>
            ) : (
              <div className="text-center py-12">
                <ChartLine className="w-16 h-16 text-slate-300 mx-auto mb-4" />
                <h3 className="text-xl font-semibold text-slate-600 mb-2">No hay productos rastreados</h3>
                <p className="text-slate-500 mb-6">
                  Comienza añadiendo tu primer producto para monitorear sus cambios de precio.
                </p>
                <Button
                  onClick={() => setIsDialogOpen(true)}
                  className="bg-gradient-to-r from-emerald-600 to-teal-700 hover:from-emerald-700 hover:to-teal-800 text-white"
                >
                  <PlusCircle className="w-4 h-4 mr-2" />
                  Añadir Primer Producto
                </Button>
              </div>
            )}
          </CardContent>
        </Card>

        {/* Stats Summary */}
        {mockProducts.length > 0 && (
          <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mt-8">
            <Card className="bg-white/60 backdrop-blur-sm border-0 shadow-md">
              <CardContent className="p-6 text-center">
                <div className="text-2xl font-bold text-emerald-600 mb-1">
                  {mockProducts.filter((p) => p.status === "active").length}
                </div>
                <div className="text-sm text-slate-600">Productos Activos</div>
              </CardContent>
            </Card>
            <Card className="bg-white/60 backdrop-blur-sm border-0 shadow-md">
              <CardContent className="p-6 text-center">
                <div className="text-2xl font-bold text-red-600 mb-1">
                  {mockProducts.filter((p) => p.lastChange === "up").length}
                </div>
                <div className="text-sm text-slate-600">Precios Subieron</div>
              </CardContent>
            </Card>
            <Card className="bg-white/60 backdrop-blur-sm border-0 shadow-md">
              <CardContent className="p-6 text-center">
                <div className="text-2xl font-bold text-emerald-600 mb-1">
                  {mockProducts.filter((p) => p.lastChange === "down").length}
                </div>
                <div className="text-sm text-slate-600">Precios Bajaron</div>
              </CardContent>
            </Card>
            <Card className="bg-white/60 backdrop-blur-sm border-0 shadow-md">
              <CardContent className="p-6 text-center">
                <div className="text-2xl font-bold text-slate-600 mb-1">
                  €{mockProducts.reduce((sum, p) => sum + (p.status !== "error" ? p.changeAmount : 0), 0).toFixed(2)}
                </div>
                <div className="text-sm text-slate-600">Ahorro Total</div>
              </CardContent>
            </Card>
          </div>
        )}
      </main>
    </div>
  )
}
