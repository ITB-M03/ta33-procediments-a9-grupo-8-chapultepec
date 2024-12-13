package utilities
import java.text.DecimalFormat
import java.util.*

/**
 *Apertura de Scanner
 * @author Angel Sardinha
 * @return Devuelve el scanner para usarlo en el resto del programa
 */
fun abrirScanner() : Scanner {
    var scan = Scanner(System.`in`).useLocale(Locale.UK)

    return scan
}

/**
 *Cierre del Scanner
 * @author Angel Sardinha
 * @param scan --> la funcion que contiene el scanner
 */
fun cerrarScanner(scan : Scanner) {
    scan.close()
}

/**
 * Lectura de la Linea
 * @author Angel Sardinha
 * @param msg --> Mensaje para indicar al usuario que hacer
 * @param scan --> Funccion de scanner
 * @return Retorna un string
 */
fun leerLinea(msg : String, scan : Scanner) : String {
    print(msg)
    var titulo = scan.nextLine()

    return titulo
}
/**
 * Lectura de la Int
 * @author Angel Sardinha
 * @param msg --> Mensaje para indicar al usuario que hacer
 * @param scan --> Funccion de scanner
 */
fun leerInt(msg : String, scan : Scanner) : Int {
    print(msg)
    var numero = scan.nextInt()

    return numero
}

/**
 * Prin menaje
 * @author Iván Salamanca
 * @param msg --> Mensaje a imprimir
 */
fun printlnMSG (msg: String) {
    println(msg)
}


/**
 * Lectura de un numero
 * @author Angel Sardinha
 * @param msg --> Mensaje que se mostrara por pantalla
 * @param scan --> Funcion de scanner
 * @return Retorna un numero entero
 */
fun pedirNumerito(msg : String, scan : Scanner) : Int {
    var numerito : Int

    print(msg)
    numerito = scan.nextInt()

    return numerito
}

/**
 * Muestra un mensaje por pantalla sin un salto de linea
 * @author Angel Sardinha
 * @param msg --> Mensaje que se mostrara en pantalla
 */
fun mostrarMensajeSinSalto(msg : String) {
    print(msg)
}

/**
 * Mostrar el resultado con un Int
 * @author Iván Salamanca
 * @param msg --> Mensaje para mostrar
 * @param resultado --> Resultado para mostrar
 */
fun showResulString (msg: String, resultado: String) {
    println("$msg $resultado")
}

/**
 * pedir nnumerito Doouble
 * @author Iván Salamanca
 * @param msg --> Mensaje para pedir numero
 * @param scan --> Herramienta
 * @return devuelve el numero obtenido
 */
fun pedirNumeritoDouble ( msg: String, scan: Scanner): Double {
    var resultado : Double
    print(msg)
    resultado = scan.nextDouble()
    return resultado
}

fun restaDosElemetosDouble (num1: Double, num2:Double): Double{
    var result = 0.0
    result = num1-num2
    return result
}

/**
 * Esto nos permite imprimir los precios que son decimales solo con dos numeros decimaoes y no tropecientos
 * @author Iván Salamanca
 * @param numeroDouble --> Es el numeeo que hay que acortar
 * @return --> Devuelve el numero a modo de string para imprimir por pantlla
 */
fun imprimirDosDecimales(numeroDouble: Double): String{
    val df = DecimalFormat("0.00")
    return df.format(numeroDouble)
}