import java.io.File

class Cine {
    private val nFilas = 10
    private val nAsientos = 15
    private val nombreCine = "Cine Club Lumiere"
    private var cine: Array<Array<Int>>

    init {
        cine = Array(nFilas) { Array(nAsientos) { 0 } }
        leerVentasDesdeArchivo()
    }

    fun mostrarPantallaDelCine() {
        println("-------------------------")
        println(nombreCine)
        println("fila: número")
        println("asiento: número")
        println("-------------------------")

        for (fila in 0 until nFilas) {
            for (asiento in 0 until nAsientos) {
                val estado = if (cine[fila][asiento] == 0) "__" else "XX"
                print("$estado  ")
            }
            println()
        }
    }

    fun venderEntrada(fila: Int, asiento: Int): Boolean {
        if (fila in 1..nFilas && asiento in 1..nAsientos) {
            if (cine[fila - 1][asiento - 1] == 0) {
                cine[fila - 1][asiento - 1] = 1
                return true
            }
        }
        return false
    }

    fun devolverEntrada(fila: Int, asiento: Int): Boolean {
        if (fila in 1..nFilas && asiento in 1..nAsientos) {
            if (cine[fila - 1][asiento - 1] == 1) {
                cine[fila - 1][asiento - 1] = 0
                return true
            }
        }
        return false
    }

    fun leerVentasDesdeArchivo() {
        val archivo = File("entradasVendidas.txt")
        if (archivo.exists()) {
            val ventas = archivo.readLines()
            for (venta in ventas) {
                val datos = venta.split(":")
                if (datos.size == 2) {
                    val fila = datos[0].toIntOrNull()
                    val asiento = datos[1].toIntOrNull()
                    if (fila != null && asiento != null) {
                        if (fila in 1..nFilas && asiento in 1..nAsientos) {
                            cine[fila - 1][asiento - 1] = 1
                        }
                    }
                }
            }
        } else {
            archivo.createNewFile()
        }
    }

    fun guardarVentasEnArchivo() {
        val archivo = File("entradasVendidas.txt")
        val ventas = mutableListOf<String>()
        for (fila in 0 until nFilas) {
            for (asiento in 0 until nAsientos) {
                if (cine[fila][asiento] == 1) {
                    val venta = Butaca(fila + 1, asiento + 1).toString()
                    ventas.add(venta)
                }
            }
        }
        archivo.writeText(ventas.joinToString("\n"))
    }
}
