package org.example.controllers
import utilities.*
import java.text.DecimalFormat
import java.util.Scanner

var dineroExistentes = listOf(0.05, 0.10, 0.20, 0.50, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0)

var billetesSeleccionados = mutableListOf<String>()
var precioBilletesSeleccionados = mutableListOf<Double>()
var precioFinal =0.0
var contador = 0


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
 * Punto de entrada principal del programa.
 * Inicializa el scanner y la lista de billetes y ejecuta el bucle principal.
 * @author Angel Sardinha
 */
fun main() {
    val scan = abrirScanner()
    val listaBilletes = definirLista()
    startProgram(listaBilletes, scan)
    cerrarScanner(scan)
}

/**
 * Bucle principal para la interacción con el usuario.
 * @author Angel Sardinha
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param scan -> Instancia de Scanner para leer la entrada del usuario.
 */
fun startProgram(listabilletes: MutableList<billetes>, scan : Scanner){
    var num = 0
    val password = 4321
    var precioTotal : Double = 0.0
    var seguir = true

    while(seguir == true && contador<3){
        var billete = menu(scan) // Pedir tipo Billete
        var zona = elegirZona(listabilletes, scan, billete) //Pedir zona de billete
        precioTotal += buscarPrecio(listabilletes, zona, billete) //Calcular Precio
        seguir = masBilletes(scan) //Quieres mas billetes?
    }
    precioFinal = precioTotal //Almacenamos para el tiquet
    procederPago(scan, precioTotal)
}

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
 * Permite al usuario elegir una zona para el billete seleccionado
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
 * Pregunra a, usuario si quiere comprar mas billetes.
 * @author Angel Sardinha & Iván Salamanca
 * @param scan --> Instancia de Scanner para leer la entrada del usuario.
 * @return --> devuelve si quier mas billetes o no
 */
fun masBilletes(scan : Scanner, ) : Boolean {
    var siONo : String
    var result = false
    // Obtener info teclado
    siONo = leerLinea("Le gustaría seguir comprando?[S/N] ",scan).uppercase()
    //analisis data recibido
    if(siONo == "S"){
        result = true
    }
    else if(siONo == "N"){
         result = false
    }
    else printlnMSG("Esto no es una opción válida")
    return result
}

/**
 * Calcula el precio de un billete según la zona seleccionada.
 * @author Angel Sardinha
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param opcionZone -> Zona seleccionada por el usuario.
 * @param opcionesTargeta -> Opción del billete seleccionada por el usuario.
 * @return El precio del billete ajustado por zona como un valor Double.
 */
fun buscarPrecio(listabilletes: MutableList<billetes>, opcionZone : Int, opcionesTargeta : Int) : Double{
    var precioNuevo : Double
    var indice = opcionesTargeta
    // Valor a multiplicar por cada zona
    var zonaDos = 1.3125
    var zonaTres = 1.8443
    var DOS = 2
    var TRES = 3

    //Calculo
    precioNuevo = listabilletes[indice].precio
    if(opcionZone == DOS){//para zona 2
        precioNuevo *= zonaDos
    }
    else if(opcionZone == TRES){ // Para zona 3
        precioNuevo *= zonaTres
    }
    else precioNuevo = precioNuevo // Para zona 1
    precioBilletesSeleccionados.add(precioNuevo) // Guardamos para el tiket
    contador++

    return precioNuevo
}

/**
 * Muestra el billete y la zona seleccionada.
 * @author Angel Sardinha
 * @param listabilletes -> Lista mutable de billetes disponibles.
 * @param opcionZona -> Zona seleccionada por el usuario.
 * @param opciones -> Opción del billete seleccionada por el usuario.
 */
fun mostrarBillete(listabilletes: MutableList<billetes>, opcionZona : Int, opciones : Int){
    var tipoBillete = listabilletes[opciones].billete
    billetesSeleccionados.add("$tipoBillete - Zona $opcionZona")// Guardamos para el tiket
    println("Usted ha elegido $tipoBillete, Zona $opcionZona")
}




/**
 * Pedir y comprobar el importe que entra si esta en la lista de monedas y billetes acceptados
 * @author Iván Salamanca
 * @param scan --> Herramienta que nos permite escanear el numero
 * @return --> El valor Obteidp despues de comprobarse.
 */
fun pedirCash(scan: Scanner): Double {
    var result: Double
    var isValid = false

    // Repetir hasta que se introduzca un billete o moneda válida
    do {
        result = pedirNumeritoDouble("Introduzca un billete o moneda: ", scan)

        // Verificar si el valor está en la lista de billetes o monedas válidos
        if (dineroExistentes.contains(result)) {
            isValid = true
        } else {
            printlnMSG("No ha introducido un billete o moneda legal en España")
            printlnMSG("Estas son las opciones: ${dineroExistentes}")
        }
    } while (isValid == false)

    return result
}

/**
 * Inicio del pago donde se gestiona el pago en si, el tiquet y el cambio
 * @author Iván Salamanca
 * @param scan --> Permite escanear los billetes y las respuestas de el usuario
 * @param precio --> Es el precio a pagar que ira descendiendo
 */
fun procederPago (scan: Scanner, precio: Double) {
    var precioRestante = precio
    // Se ejecuta hasta que quede todo pagado
    printlnMSG("*----------------------------Pago----------------------------*")
    while (precioRestante > 0.0) {
        printlnMSG("Su importe a pagar es de ${imprimirPrecio(precioRestante)} €")
        var cash = pedirCash(scan)
        precioRestante = restaDosElemetosDouble(precioRestante,cash)
    }
    gestionCambio(precioRestante)//se gestiona el cambio en caso de que haya o muestra 0.00
    tiquetQuieres(scan) // Preguntar si quiere tiquet e imprimirlo en caso Si
    //vaciado billetes antiguos
    billetesSeleccionados.clear()
    precioBilletesSeleccionados.clear()
    printlnMSG("Disfruta de tu viaje, si el tren llega claro ;)")
}

/**
 * Gestiona el cambio en caso de que se necesite devolver algo y se mosrtaria el resultado
 * @author Iván Salamanca
 * @param precioRestante --> Este precio o bien es 0.0 o tiene el resultado del cambio
 */
fun gestionCambio (precioRestante: Double){
    var precio = precioRestante
    if (precio < 0.0) {
        precio = precio * -1
    }
    printlnMSG("Su cambio es de : ${imprimirPrecio(precio)} €")

}

/**
 * Pregunta al usuario si quiere iimprimir el tiquet
 * @author Iván Salamanca
 * @param scan -> Nos permite leer la respuesta del usuario
 */
fun tiquetQuieres(scan:Scanner){
    var tiquetSiNo : String
    scan.nextLine()
    tiquetSiNo = leerLinea("Le gustaría Recibir el tiket?[S/N] ",scan).uppercase()
    if (tiquetSiNo == "S"){
        imprimirTiquet()
    }

}

/**
 * Imprime el tiquet completo con los datos que se han ido guardando durante el programa
 * @author Iván Salamanca
 */
fun imprimirTiquet(){
    printlnMSG("*--------------------* Su Tiquet *--------------------*")
    for (x in 0 until  billetesSeleccionados.size){
        mostrarMensajeSinSalto("    ${billetesSeleccionados[x]}")
        mostrarMensajeSinSalto("  Precio: ${imprimirPrecio(precioBilletesSeleccionados[x])}€")
        printlnMSG("")
    }
    printlnMSG("*-----------------------------------------------------*")
    printlnMSG("         Importe total ---> ${imprimirPrecio(precioFinal)}€")
    printlnMSG("*-----------------------------------------------------*")
}

/**
 * Esto nos permite imprimir los precios que son decimales solo con dos numeros decimaoes y no tropecientos
 * @author Iván Salamanca
 * @param precio --> Es el precio que hay que acortar
 * @return --> Devuelve el numero a modo de string para imprimir por pantlla
 */
fun imprimirPrecio(precio: Double): String{
    val df = DecimalFormat("0.00")
    return df.format(precio)
}
