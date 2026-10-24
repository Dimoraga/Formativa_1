//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    while(true) {

        println("Por favor, ingresa tu nombre: ")
        val nombreCliente = readln()
        Thread.sleep(1000) // se incorpora una pequeña pausa de un segundo

        println("Por favor, ingresa tu dirección: ")
        val direccion = readln()
        Thread.sleep(1000)

        val telefono = leerTelefono()
        println("Telefono registrado de manera exitosa.")
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