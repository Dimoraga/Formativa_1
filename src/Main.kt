
// Sistema sencillo para la venta de pizzas

fun main() {

    println(":::: Bienvenido a la Pizzaria Il Nono:::::::")

    var nombreCliente = ""
    var direccion = ""
    var telefono = ""

    while(true) {

        println("Por favor, ingresa tu nombre: ")
        nombreCliente = readln()
        Thread.sleep(1000) // se incorpora una pequeña pausa de un segundo

        println("Por favor, ingresa tu dirección: ")
        direccion = readln()


        Thread.sleep(1000)

        telefono = leerTelefono()
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

    val repartidor = asignarRepartidor()

    Thread.sleep(1000)
    var nombrePizza : String

    Thread.sleep(1000)
    var cantidadPizzas : Int

    while(true){
        nombrePizza = leerPizza()
        cantidadPizzas = leerCantidad()

        val faltantes = ingredientesFaltantes(nombrePizza, cantidadPizzas)

        if (faltantes.isEmpty()){
            break
        }

        println("Lo sentimos, no hay stock suficiente de: ${faltantes.joinToString(", ")}")
        println("Elige otra pizza o una cantidad menor.")
    }


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
        totalPedido = totalPedido * 90/100
        descuento = "10%"

    }

    // Resumen final del pedido
    println("*******************************")
    println("*******************************")
    println("RESUMEN DEL PEDIDO: ")
    Thread.sleep(1000)
    println("Nombre cliente: $nombreCliente\n" +
            "Teléfono cliente: $telefono\n" +
            "Dirección cliente: $direccion\n" +
            "Tipo de pizza: $nombrePizza\n" +
            "Cantidad: $cantidadPizzas\n" +
            "Descuento: $descuento\n" +
            "Total a pagar: $$totalPedido")

    descontarStock(nombrePizza, cantidadPizzas)
    Thread.sleep(1000)
    println("¡Pedido en preparación!")
    println("Te garantizamos que tu pedido llegará en menos de 40 minutos.")

    println("El nombre del repartidor es: $repartidor")

    println("::::: MUCHAS GRACIAS POR SU COMPRA ::::::::")
    println(":::::::::::::::::::::::::::::::::::::::::::")

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

// Stock disponible (masa en unidades, el resto en gramos)
val stock = mutableMapOf(
    "Masa" to 20,
    "Salsa de tomate" to 2000,
    "Mozzarella" to 3000,
    "Pepperoni" to 600,
    "Tomate" to 1000,
    "Gorgonzola" to 500,
    "Parmesano" to 500,
    "Provolone" to 500,
    "Aceitunas" to 300
)

// Ingredientes que usa UNA pizza de cada tipo
val recetas = mapOf(
    "Margarita" to mapOf("Masa" to 1, "Salsa de tomate" to 80, "Mozzarella" to 120, "Tomate" to 50),
    "Pepperoni" to mapOf("Masa" to 1, "Salsa de tomate" to 80, "Mozzarella" to 120, "Pepperoni" to 60),
    "Cuatro Quesos" to mapOf("Masa" to 1, "Mozzarella" to 100, "Gorgonzola" to 50, "Parmesano" to 40, "Provolone" to 50),
    "Napolitana" to mapOf("Masa" to 1, "Salsa de tomate" to 80, "Mozzarella" to 100, "Tomate" to 60, "Aceitunas" to 30)
)

fun ingredientesFaltantes(pizza: String, cantidad: Int): List<String> {
    val faltantes = mutableListOf<String>()

    for ((ingrediente, porPizza) in recetas.getValue(pizza)) {
        val necesario = porPizza * cantidad
        val disponible = stock.getValue(ingrediente)

        if (disponible < necesario) {
            faltantes.add(ingrediente)
        }
    }
    return faltantes
}

// lógica que descuenta el stock
fun descontarStock(pizza: String, cantidad: Int) {
    for ((ingrediente, porPizza) in recetas.getValue(pizza)) {
        stock[ingrediente] = stock.getValue(ingrediente) - porPizza * cantidad
    }
}

// asignar repartidor

fun asignarRepartidor(): String  {

    while(true) {

        println("Por favor, selecciona tu comuna de la lista: ¡¡¡ Recuerda que solamente repartimos en la ciudad de Santiago.")
        println("1. La Florida, Puente Alto, Macul, Peñalolén, La Granja y San Ramón")
        println("2. La Reina, Providencia, Las Condes, Vitacura, Lo Barnechea")
        println("3. Santiago, Pedro Aguirre Cerda, San Miguel, Recoleta, Independencia, Huechuraba, Estación Central")
        println("4. Maipú, Cerrillos, Lo Prado, Lo Espejo ")
        println("5. San Bernardo, El Bosque, La Cisterna, La Pintana.")
        println("6. Quilicura, Pudahuel, Quinta Normal, Cerro Navia.")

        when (readln().trim()) {
            "1" -> return "Marcelo Guerra"
            "2" -> return "Gonzalo Espinosa"
            "3" -> return "María José Valenzuela"
            "4" -> return "Bryan Jiménez"
            "5" -> return "Pablo Soto"
            "6" -> return "Ximena Salvatierra"
            else -> println("Opción no válida, elige un número del 1 al 6\n")
        }
    }

}