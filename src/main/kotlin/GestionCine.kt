
class GestionCine {
    private val cine: Cine

    init {
        cine = Cine()
    }

    fun mostrarMenu() {
        var opcion: Int

        do {
            println("1. Mostrar butacas")
            println("2. Comprar entrada")
            println("3. Devolver una entrada")
            println("4. Salir")
            println("-------------------------")

            opcion = readLine()?.toIntOrNull() ?: 0

            when (opcion) {
                1 -> cine.mostrarPantallaDelCine()
                2 -> comprarEntrada()
                3 -> devolverEntrada()
                4 -> salir()
                else -> println("Opción inválida. Inténtalo de nuevo.")
            }
        } while (opcion != 4)
    }

    private fun comprarEntrada() {
        println("Introduce la fila:")
        val fila = readLine()?.toIntOrNull()

        println("Introduce el asiento:")
        val asiento = readLine()?.toIntOrNull()

        if (fila != null && asiento != null) {
            val exito = cine.venderEntrada(fila, asiento)
            if (exito) {
                println("Entrada comprada: ${Butaca(fila, asiento)}")
            } else {
                println("Lo sentimos, esa entrada ya está ocupada.")
            }
        } else {
            println("Entrada inválida. Inténtalo de nuevo.")
        }
    }

    private fun devolverEntrada() {
        println("Introduce la fila:")
        val fila = readLine()?.toIntOrNull()

        println("Introduce el asiento:")
        val asiento = readLine()?.toIntOrNull()

        if (fila != null && asiento != null) {
            val exito = cine.devolverEntrada(fila, asiento)
            if (exito) {
                println("Entrada devuelta: ${Butaca(fila, asiento)}")
            } else {
                println("No se encontró ninguna entrada vendida en esa ubicación.")
            }
        } else {
            println("Entrada inválida. Inténtalo de nuevo.")
        }
    }

    private fun salir() {
        cine.guardarVentasEnArchivo()
        println("¡Hasta luego!")
    }
}