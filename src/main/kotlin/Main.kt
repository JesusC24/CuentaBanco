object CuentaBancaria {
    var saldo:Float
    init {
        saldo = 203.5F
    }
    fun opera(dinero: Float, funcion:(Float, Float) -> Float) {
        saldo = funcion(saldo, dinero)
    }
}

fun main() {
    println("Hola mundo, bienvenido al banco Portada Alta")
    while(true) {
        mostrarMenu()
        when (readLine()) {
            "1" -> mostrarSaldo()
            "2" -> ingresarDinero()
            "3" -> sacarDinero()
            "4" -> break
            else -> println("\nHas introducido una opción incorrecta\n")
        }
    }
}

fun mostrarMenu() {
    println("Ingresa el numero de alguna de las opciones")
    println("1.- Ver saldo")
    println("2.- Ingresar saldo")
    println("3.- Sacar dinero")
    println("4.- Salir")
    print("Opcion: ")
}

fun mostrarSaldo() = println("\nEl saldo acutual de la cuenta es ${CuentaBancaria.saldo}\n")

fun ingresarDinero() {
    print("\nDime la cantidad a ingresar: ")

    when (val dinero = readLine()?.toFloatOrNull()) {
        null -> print("\nNo has introducido ninguna cantidad")
        in 0F..999F ->  CuentaBancaria.opera(dinero) {x, y -> x+y}
        in 1000F..3000F -> {
            CuentaBancaria.opera(dinero) {x, y -> x+y}
            print("\nSe informará a hacienda de esta operación")
        }
        else -> print("\nLa cantidad introducida es mayor que la permitida")
    }
    mostrarSaldo()
}

fun sacarDinero() {
    print("\nDime la cantidad a retirar: ")
    val dinero = readLine()?.toFloatOrNull()
    when {
        dinero == null -> println("\nHas introducido un valor erróneo")
        (CuentaBancaria.saldo - dinero >= 0) -> CuentaBancaria.opera(dinero) {x, y -> x-y}
        else ->  println("\nEL saldo no puede quedarse en negativo")
    }
    mostrarSaldo()
}