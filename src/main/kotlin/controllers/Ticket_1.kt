package org.example.controllers
import utilities.*
import java.util.Scanner

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

    opcionZona = pedirNumerito("A que zona le gustaría viajar? ", scan)
    mostrarPrecio(listabilletes, opcionZona, opciones)
    buclePedirMasBilletes(scan, listabilletes, opciones, opcionZona)

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
fun mostrarPrecio(listabilletes: MutableList<billetes>, opcionZona : Int, opciones : Int){
    var tipoBillete = listabilletes[opciones]

    println("Usted ha elegido ${tipoBillete}, $opcionZona")
}

/**
 * @author Angel Sardinha
 * Bucle principal para la interacción con el usuario.
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param scan -> Instancia de Scanner para leer la entrada del usuario.
 */
fun bucle(listabilletes: MutableList<billetes>, scan : Scanner){
    var num = 0
    val password = 4321

    while(num != password){
        var opcion = menu(scan)
        elegirZona(listabilletes, scan, opcion)
    }
}

/**
 * @author Angel Sardinha
 * Punto de entrada principal del programa.
 * Inicializa el scanner y la lista de billetes y ejecuta el bucle principal.
 */
fun main() {
    val scan = abrirScanner()

    val listaBilletes = definirLista()

    bucle(listaBilletes, scan)

    cerrarScanner(scan)
}
