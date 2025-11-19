
fun criaMenu(): String
{
 return (" 1 - Novo Jogo \n 2 - Ler Jogo \n 0 - Sair")
}

fun voltaMenu(respostaMenu : String): String{
    when(respostaMenu){
        "1"-> return  (" 1 ")
        "2"->  return (" NÃO IMPLEMENTADO \n ${criaMenu()}")
        "0"->  return " "
        else -> return ("Resposta Inválida.")
    }
}

//fun validaNome(){
//    return
//}

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

//fun criaLegenda(){
//    return
//}

fun main() {
}


