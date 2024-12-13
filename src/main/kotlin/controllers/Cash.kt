package controllers
import utilities.*
import java.util.Scanner


var dineroExistentes = listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0 )

fun main (){
    var scan = abrirScanner()

    print("Introduce el importe: ")
    var precio = scan.nextDouble()
    restarPrecio(scan, precio)
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


