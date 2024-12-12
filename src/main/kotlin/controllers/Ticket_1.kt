package org.example.controllers

import utilities.*
import java.util.Scanner

/**
 * Clase de datos para representar los billetes
 */
data class Billetes(
    val billete: String,
    val precio: Double
)

/**
 * Función para definir una lista mutable de billetes
 */
fun definirLista(): MutableList<Billetes> {
    val listabilletes = mutableListOf<Billetes>()
    listabilletes.add(Billetes("Billete sencillo", 2.40))
    listabilletes.add(Billetes("TCasual", 11.35))
    listabilletes.add(Billetes("TUsual", 40.00))
    listabilletes.add(Billetes("TFamiliar", 10.00))
    listabilletes.add(Billetes("TJove", 80.00))

    return listabilletes
}

/**
 * Función para mostrar un menú
 */
fun menu(scan: Scanner): Int {
    mostrarMensajeSinSalto(
        """
        ------------------------------
                Buenas tardes
        0. Billete sencillo
        1. TCasual 
        2. TUsual
        3. TFamiliar
        4. TJove
    """.trimIndent()
    )
    return pedirNumerito("Dígame la opción del billete que desea: ", scan)
}

/**
 * Función para elegir una zona
 */
fun elegirZona(listabilletes: MutableList<Billetes>, scan: Scanner, opciones: Int): Int {
    val opcionZona = pedirNumerito("¿A qué zona le gustaría viajar? ", scan)
    mostrarPrecio(listabilletes, opcionZona, opciones)
    buclePedirMasBilletes(scan, listabilletes, opciones, opcionZona)
    return opcionZona
}

/**
 * Función para pedir más billetes en un bucle
 */
fun buclePedirMasBilletes(
    scan: Scanner,
    listabilletes: MutableList<Billetes>,
    opciones: Int,
    opcionesZona: Int
): Double {
    var contador = 0
    var precioActual = 0.0

    while (contador < 3) {
        val siONo = leerLinea("¿Le gustaría seguir comprando? (S/N): ", scan).uppercase()
        if (siONo == "S") {
            precioActual += buscarPrecio(listabilletes, opciones, opcionesZona)
            val opciones2 = menu(scan)
            elegirZona(listabilletes, scan, opciones2)
            contador++
        } else if (siONo == "N") {
            contador = 3
        }
    }
    return precioActual
}

/**
 * Función para buscar el precio según la zona
 */
fun buscarPrecio(listabilletes: MutableList<Billetes>, opcionZona: Int, opciones: Int): Double {
    val zonaDos = 1.3125
    val zonaTres = 1.8443
    val dos = 2
    val tres = 3

    var precioNuevo = listabilletes[opciones].precio
    if (opcionZona == dos) {
        precioNuevo *= zonaDos
    } else if (opcionZona == tres) {
        precioNuevo *= zonaTres
    }
    return precioNuevo
}

/**
 * Función para mostrar el precio elegido
 */
fun mostrarPrecio(listabilletes: MutableList<Billetes>, opcionZona: Int, opciones: Int) {
    val tipoBillete = listabilletes[opciones]
    println("Usted ha elegido ${tipoBillete.billete}, zona $opcionZona. Precio: ${tipoBillete.precio}")
}

/**
 * Bucle principal
 */
fun bucle(listabilletes: MutableList<Billetes>, scan: Scanner) {
    val password = 4321
    var num = 0

    while (num != password) {
        val opcion = menu(scan)
        elegirZona(listabilletes, scan, opcion)
    }
}

/**
 * Función principal
 */
fun main() {
    val scan = abrirScanner()
    val listaBilletes = definirLista()
    bucle(listaBilletes, scan)
    cerrarScanner(scan)
}