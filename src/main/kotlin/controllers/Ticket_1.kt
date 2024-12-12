package org.example.controllers
import utilities.*
import java.util.Scanner

/**
 *
 */
data class billetes(
    val billete : String,
    val precio : Double
)

/**
 *
 */
fun definirLista(billetes: billetes) : MutableList<billetes>{
    var listabilletes = mutableListOf<billetes>()
    listabilletes.add(billetes("billete sencillo", 2.40))
    listabilletes.add(billetes("TCasual", 11.35))
    listabilletes.add(billetes("TUsual", 40.00))
    listabilletes.add(billetes("TFamiliar", 10.00))
    listabilletes.add(billetes("TJove", 80.00))

    return listabilletes
}

/**
 *
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
 *
 */
fun elegirZona(listabilletes: MutableList<billetes>, scan : Scanner, opciones : Int) : Int {
    var opcionZona : Int

    opcionZona = pedirNumerito("A que zona le gustaría viajar? ", scan)
    mostrarPrecio(listabilletes, opcionZona, opciones)

    return opcionZona
}

/**
 *
 */
fun buclePedirMasBilletes(scan : Scanner, listabilletes: MutableList<billetes>, opciones: Int, opcionesZone : Int) : Double {
    var contador = 0
    var siONo : String
    var precioActual : Double = 0.0

    while (contador < 3){
        siONo = leerLinea("Le gustaría seguir comprando? ",scan).uppercase()
        if(siONo == "S"){
            precioActual += buscarPrecio(listabilletes, opciones, opcionesZone)
            var opciones2 = menu(scan)
            elegirZona(listabilletes, scan, opciones2)
            contador++
        }
        else if(siONo == "N"){
            contador = 3
        }
    }
    return precioActual
}

/**
 *
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
 *
 */
fun mostrarPrecio(listabilletes: MutableList<billetes>, opcionZona : Int, opciones : Int){
    var tipoBillete = listabilletes[opciones]

    println("Usted ha elegido ${tipoBillete}, $opcionZona")
}

/**
 *
 */
fun main() {
   var scan = abrirScanner()

    var opcion = menu(scan)

   cerrarScanner(scan)
}