package controllers
import utilities.*
import java.util.Scanner

var dineroExistentes = listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0 )


fun main (){
    var scan = abrirScanner()

    print("Introduce el importe: ")
    var precio = scan.nextDouble()
    while (precio != 0.0) {
        printlnMSG("Su importe a pagar es de $precio")
        var cash = pedirCash(scan)
        restaPrecio(precio,cash)
    }
    println("Ya esta todo pagado")
}

fun pedirCash(scan:Scanner) : Double {
    var dineros = 0.0
    var result = pedirNumeritoDouble("Introduzca un billete o modeda: ", scan)
    for (x in 0..dineroExistentes) {
        if numero==
    }
    return dineros
}

fun restaPrecio(precio: Double, cash: Double) :Double{
    var result : Double
    result = precio - cash
    return result
}


