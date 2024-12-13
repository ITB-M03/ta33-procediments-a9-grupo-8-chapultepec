package org.example.controllers
import utilities.*
import java.util.Scanner

var dineroExistentes = listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0 )

/**
 * Representa los billetes disponibles con su nombre y precio.
 * @author Angel Sardinha
 * @param billete -> Nombre del billete.
 * @param precio -> Precio del billete.
 */
data class billetes(
    val billete : String,
    val precio : Double
)

/**
 * Define una lista mutable de billetes con sus precios iniciales.
 * @author Angel Sardinha
 * @return Una lista mutable de objetos billetes.
 */
fun definirLista() : MutableList<billetes>{
    var listabilletes = mutableListOf<billetes>()
    listabilletes.add(billetes("billete sencillo", 2.40))
    listabilletes.add(billetes("TCasual", 11.35))
    listabilletes.add(billetes("TUsual", 40.00))
    listabilletes.add(billetes("TFamiliar", 10.00))
    listabilletes.add(billetes("TJove", 80.00))

    return listabilletes
}

/**
 * Muestra un menú de opciones para elegir billetes y devuelve la opción seleccionada.
 * @author Angel Sardinha
 * @param scan -> Instancia de Scanner para leer la entrada del usuario.
 * @return La opción seleccionada por el usuario como un número entero.
 */
fun menu(scan : Scanner) : Int {
    var opciones : Int

    mostrarMensajeSinSalto("""
        ------------------------------
                Buenas tardes
        0. Billete sencillo
        1. TCasual 
        2. TUsual
        3. TFamiliar
        4. TJove 
        
    """.trimIndent())
    opciones = pedirNumerito("Digame la opcion del billete que desea: ", scan)

    return opciones
}

/**
 * Permite al usuario elegir una zona y muestra el precio del billete seleccionado.
 * @author Angel Sardinha
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param scan -> Instancia de Scanner para leer la entrada del usuario.
 * @param opciones -> Opción del billete seleccionada por el usuario.
 * @return La zona seleccionada por el usuario como un número entero.
 */
fun elegirZona(listabilletes: MutableList<billetes>, scan : Scanner, opciones : Int) : Int {
    var opcionZona : Int

    mostrarMensajeSinSalto("""
        --------------------------
        Las zonas a eleguir son: 
        1. Zona 1 
        2. Zona 2
        3. Zona 3
        
    """.trimIndent())
    opcionZona = pedirNumerito("A que zona le gustaría viajar? ", scan)
    mostrarBillete(listabilletes, opcionZona, opciones)
    scan.nextLine()

    return opcionZona
}

/**
 * Permite al usuario comprar billetes adicionales en un bucle hasta un máximo de tres intentos.
 * @author Angel Sardinha
 * @param scan -> Instancia de Scanner para leer la entrada del usuario.
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param opciones -> Opción del billete seleccionada por el usuario.
 * @param opcionesZone -> Zona seleccionada por el usuario.
 * @return El precio acumulado de los billetes comprados como un valor Double.
 */
fun buclePedirMasBilletes(scan : Scanner, listabilletes: MutableList<billetes>, opciones: Int, opcionesZone : Int, precioInicial : Double) : Double {
    var contador = 0
    var siONo : String
    var precioActual = precioInicial

    while (contador < 3){
        siONo = leerLinea("Le gustaría seguir comprando?[S/N] ",scan).uppercase()
        if(siONo == "S"){
            precioActual += buscarPrecio(listabilletes, opciones, opcionesZone)
            var opciones2 = menu(scan)
            elegirZona(listabilletes, scan, opciones2)
            contador++
        }
        else if(siONo == "N"){
            contador = 3
        }
        else printlnMSG("Esto no es una opción válida")    }

    return precioActual
}

/**
 * Calcula el precio de un billete según la zona seleccionada.
 * @author Angel Sardinha
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param opcionZone -> Zona seleccionada por el usuario.
 * @param opciones -> Opción del billete seleccionada por el usuario.
 * @return El precio del billete ajustado por zona como un valor Double.
 */
fun buscarPrecio(listabilletes: MutableList<billetes>, opcionZone : Int, opciones : Int) : Double{
    var precioNuevo : Double
    var indice = opciones
    var zonaDos = 1.3125
    var zonaTres = 1.8443
    var dos = 2
    var tres = 3

    precioNuevo = listabilletes[indice].precio
    if(opcionZone == dos){
        precioNuevo *= zonaDos
    }
    else if(opcionZone == tres){
        precioNuevo *= zonaTres
    }
    else precioNuevo = precioNuevo

    return precioNuevo
}

/**
 * Muestra el precio del billete y la zona seleccionada.
 * @author Angel Sardinha
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param opcionZona -> Zona seleccionada por el usuario.
 * @param opciones -> Opción del billete seleccionada por el usuario.
 */
fun mostrarBillete(listabilletes: MutableList<billetes>, opcionZona : Int, opciones : Int){
    var tipoBillete = listabilletes[opciones].billete

    println("Usted ha elegido $tipoBillete, Zona $opcionZona")
}

/**
 * Bucle principal para la interacción con el usuario.
 * @author Angel Sardinha
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param scan -> Instancia de Scanner para leer la entrada del usuario.
 */
fun bucle(listabilletes: MutableList<billetes>, scan : Scanner){
    var num = 0
    val password = 4321
    var precioTotal : Double

    while(num != password){
        var opcion = menu(scan)
        var zona = elegirZona(listabilletes, scan, opcion)
        precioTotal = buscarPrecio(listabilletes, zona, opcion)
        precioTotal = buclePedirMasBilletes(scan, listabilletes, opcion, zona, precioTotal)
        restarPrecio(scan, precioTotal)
    }
}

/**
 * Punto de entrada principal del programa.
 * Inicializa el scanner y la lista de billetes y ejecuta el bucle principal.
 * @author Angel Sardinha
 */
fun main() {
    val scan = abrirScanner()

    val listaBilletes = definirLista()

    bucle(listaBilletes, scan)

    cerrarScanner(scan)
}


/**
 * Pedir y comprobar el importe que entra si esta en la lista de cash
 * @author Iván Salamanca
 * @param scan --> Herramienta que nos permite escanear el numero
 * @return el valor Obteidp despues de comprobarse.
 */
fun pedirCash(scan:Scanner) : Double {
    var dineros = 0.0
    var result = pedirNumeritoDouble("Introduzca un billete o modeda: ", scan)
    for (x in 0 until dineroExistentes.size) {
        if (dineroExistentes[x]== result) dineros = result
    }
    if (dineros == 0.0) {
        printlnMSG("No ha introducido un billete o moneda legal en España")
        pedirCash(scan)
    }
    return dineros
}

fun restarPrecio (scan: Scanner, precio: Double) {
    var precioRestante = precio
    while (precioRestante > 0.0) {
        printlnMSG("Su importe a pagar es de $precioRestante")
        var cash = pedirCash(scan)
        precioRestante = restaDosElemetosDouble(precio,cash)
    }
    gestionCambio(precioRestante)
    printlnMSG("Disfruta de tu viaje, si el tren llega claro ;)")
}


fun gestionCambio (precioRestante: Double){
    var precio = precioRestante
    if (precio < 0.0) {
        precio = precio * -1
    }
    printlnMSG("Su cambio es de : $precio")

}


