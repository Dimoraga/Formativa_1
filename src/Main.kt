
// Sistema sencillo para la venta de pizzas

fun main() {

    println(":::: Bienvenido a la Pizzaria Il Nono:::::::")

    while(true) {

        println("Por favor, ingresa tu nombre: ")
        val nombreCliente = readln()
        Thread.sleep(1000) // se incorpora una pequeña pausa de un segundo

        println("Por favor, ingresa tu dirección: ")
        val direccion = readln()
        Thread.sleep(1000)

        val telefono = leerTelefono()
        println("Teléfono registrado de manera exitosa.")
        Thread.sleep(2000) // se incorpora pausa de dos segundos

        println("Confirmación. Los datos ingresados son los siguientes:  ")
        Thread.sleep(1000)

        println(
            "Nombre: $nombreCliente\n" +
                    "Dirección: $direccion\n" +
                    "Teléfono: $telefono"
        )
        Thread.sleep(1000)

        println("¿Son correctos los datos ingresados? Escribe SI o NO.")

        val respuesta = readln().trim().uppercase()

        if (respuesta == "SI") {
            println("Datos guardados correctamente.")
            break

        } else {
        println("Volvamos a ingresar los datos nuevamente\n")
        }
    }

    Thread.sleep(1000)
    val nombrePizza = leerPizza()

    Thread.sleep(1000)
    val cantidadPizzas = leerCantidad()

    val precios = mapOf(
        "Margarita" to 10000,
        "Pepperoni" to 12000,
        "Cuatro Quesos" to 15000,
        "Napolitana" to 11000
    )
    var totalPedido = precios[nombrePizza]!! * cantidadPizzas
    var descuento = "Sin descuento"

    // Aplica descuento de 10% si pedido supera las 3 pizzas
    if (cantidadPizzas > 3 ) {
        val totalPedido = totalPedido * 90/100
        descuento = "10%"

    }

    // Resumen del pedido

    println("RESUMEN DEL PEDIDO: ")
    Thread.sleep(1000)
    println("Tipo de pizza: $nombrePizza\n" +
            "Cantidad: $cantidadPizzas\n" +
            "Descuento: 10%" +
            "Total a pagar: $$totalPedido")



}

fun leerTelefono(): String {
    while (true){
        println("Por favor, ingresa tu número de contacto: ")
        val telefono= readln()

        when {
            telefono.length > 9 -> println("Número telefónico demasiado largo, no debe sobrepasar los nueve dígitos")
            telefono.length < 9 -> println("Número telefónico muy corto, debe tener nueve dígitos.")
            !telefono.all { it.isDigit() } -> println("Solo se permiten números")
            else -> return telefono
        }
    }
}


fun leerPizza(): String {
    while(true) {
        println("Seleccione la pizza que desee: ")
        println("1. Margarita")
        println("2. Pepperoni")
        println("3. Cuatro Quesos")
        println("4. Napolitana")

        when(readln().trim()) {
             "1" -> return "Margarita"
             "2" -> return "Pepperoni"
             "3" -> return "Cuatro Quesos"
             "4" -> return "Napolitana"
            else -> println("Opción no válida, elige un número del 1 al 4.\n")
        }

    }
}

fun leerCantidad(): Int{
    while(true){
        println("¿Cuántas pizzas quiere?")
        val cantidad = readln().trim().toIntOrNull()

        if (cantidad == null || cantidad <= 0){
            println("Ingresa un número válido mayor a 0.\n")
        } else {
            return cantidad
        }
    }
}

