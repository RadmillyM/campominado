fun criaMenu(): String
{
    return ("\n Bem Vindo ao Campo DEISIado \n \n 1 - Novo Jogo \n 2 - Ler Jogo \n 0 - Sair \n")
}

fun retornaMenu(respostaMenu : String): String{
    when(respostaMenu){
        "1"-> return  ("${criaLegenda()}")
        "2"->  return (" NÃO IMPLEMENTADO \n ${criaMenu()}")
        "0"->  return " "
        else -> return ("Resposta Inválida. \n ${criaMenu()}")
    }
}

fun validanome(nome: String?) : Boolean {

    if (nome.isNullOrBlank()){
        return false
    } else {
        if ( nome.length<7) {
            return false
             } else{
                if (temMaiuscula(nome)&& temEspaco(nome)== true) {
                     return true
                }
                     return false
            }

        }
    }


fun ondeTemEspaco(nome: String?) : Int {
    if (nome.isNullOrBlank()) {
        return -1
    }
    var contadorPosicao = 0
    var posicaoEspaco = 0

    while (contadorPosicao < nome.length ) {
        if (nome[contadorPosicao] == ' ') {
            posicaoEspaco = contadorPosicao
            return posicaoEspaco
        } else {
            contadorPosicao++
        }
    }
    return -1
}

fun temMaiuscula(nome: String?) : Boolean {
    if (nome.isNullOrBlank()) {
        return false
    }
    val temnomeMaiusculo = nome[0].isUpperCase()
    val temApelidoMaiusculo = nome[ondeTemEspaco(nome)+1].isUpperCase()

    if (temApelidoMaiusculo && temnomeMaiusculo == true){
        return true
    }
    return false
}

fun temEspaco(nome: String?) : Boolean {
    if (nome.isNullOrBlank()) {
        return false
    }

    var contadorPosicao = 0
    var contadorEspaco = 0

    while (contadorPosicao < nome.length && contadorEspaco<=1 ) {
        if (nome[contadorPosicao] == ' ') {
            contadorPosicao++
            contadorEspaco++
        }else{
            contadorPosicao++
        }
    }
    if (contadorEspaco == 1){
        return true
    }
    return false
}

fun lerNome(): String? {
    var nome: String?
    do {
        println("Introduza o seu nome:")
        nome = readLine()

        if (validanome(nome)==false) {
            println("Nome inválido, tenta outra vez.\n")
        }

    } while (validanome(nome)==false)

    return criaLegenda()

}

fun validaNumeroDeMinas(numLines: Int,numColumns: Int,numMines: Int): Boolean{

    val casaVazias : Int = (numLines * numColumns) - 2

    if (numMines <= 0 || casaVazias < numMines || numLines < 1 || numColumns < 1) {
        return false
    }
    return true
}

fun calculaNumeroDeMinas(numLines: Int,numColumns: Int): Int? {
    if (numLines < 1 || numColumns < 1) {
        return null
    }
    val casaVazias : Int = (numLines * numColumns) - 2

    var minas=0
    if (casaVazias in 14..79) {
        when (casaVazias) {
            in 14..20 -> {
                minas = 6
            }
            in 21..40 -> {
                minas = 9
            }
            in 41..60 -> {
                minas = 12
            }
            in 61..79 -> {
                minas = 19
            }

        }
    }
    else {
        return null
    }
    return (minas)

}
fun pedeLegenda(): Boolean{

    println("Mostrar legenda (s/n)?")
         val respostaLegenda= readln()

    if (respostaLegenda.isNullOrBlank()){
        return false
    }

    if (respostaLegenda!=="n"){
             return false
    }else{
        if (respostaLegenda!=="N"){
             return false
        }else{
            if (respostaLegenda!=="s"){
                return true
            }else{
                if (respostaLegenda!=="S"){
                    return true
                }
            }
        }
    }

    return false
}
fun confirmacaoLegenda(){

    if (pedeLegenda()==true){
        return pedeLinhas()
    } else{
        pedeLinhas()
    }
}

fun criaLegenda(numColumns: Int):String?{
    if (pedeLegenda()== true){
        return pedeLinhas()
    }
    var aux = 0
    var textoApoio : String
    val alfabeto  ="A B C D E F G H I J K L M N O P Q R S T U V X Z"
    var auxiliar = numColumns
    var textoFinalLegenda = ""

    if (numColumns < 1) {
        return null.toString()
    }

    do{
        textoApoio = alfabeto[aux].toString()

        textoFinalLegenda += if (auxiliar!=1) {
            "$textoApoio   "
        } else {
            textoApoio
        }

        aux+=2

        auxiliar--
    }while (auxiliar!=0)

    return textoFinalLegenda
}

fun pedeLinhas(): String? {
    println("Quantas linhas?")
        var numLines=readln().toInt()

    while (numLines!==1){
        println("Resposta invalida.")

        println("Quantas linhas?")

         numLines=readln().toInt()
    }

    return quantasColunas()
}

fun quantasColunas(): String?{
    println("Quantas linhas?")
    var numLines=readln().toInt()

    while(numLines<1){
        println("Resposta invalida.")
        println("Quantas linhas?")
        numLines=readln().toInt()
    }
    return ????
}
fun criaTerreno(numColumns: Int, numLines: Int, numMines: Int, legenda : Boolean = true): String {

    var resultado = ""

    if (legenda){
        val legend = criaLegenda(numColumns)
        if (legend != null){
            resultado += legend +"\n"
        }
    }
    return resultado
}

fun main() {
    println(criaMenu())
     val respostaMenu=readln()
        println("${retornaMenu(respostaMenu)}")


}
