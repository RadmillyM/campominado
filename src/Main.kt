fun criaMenu(): String {
    return ("\n Bem Vindo ao Campo DEISIado \n \n 1 - Novo Jogo \n 2 - Ler Jogo \n 0 - Sair \n")
}
fun retornaMenu(respostaMenu : String): String{

    if(respostaMenu == "1") {
        return ("Novo jogo") // retirei porque precisa de parametro para correr
    }else if (respostaMenu == "2") {
        return (" NÃO IMPLEMENTADO \n ${criaMenu()}") // validar o qq faz
    }else if (respostaMenu =="0") {
        return " "
    }else return ("Resposta Inválida. \n ${criaMenu()}")
}
fun validanome(nome: String?) : Boolean {

    if (nome.isNullOrBlank()){
        return false
    } else {
        if ( nome.length<7) {
            return false
             } else {
            if (temMaiuscula(nome) && temEspaco(nome) == true) {
                return true
            }
        }
    }
    return false
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

        if (!validanome(nome)) {
            println("Nome inválido, tenta outra vez.\n")
        }

    } while (!validanome(nome))

    return nome //retorna nome

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
         var respostaLegenda= readln()

    while (respostaLegenda.isNullOrBlank()){ // assim o ciclo garante que vai estar sempre a pedir a legenda sempre que for a resposta errada
        println("Resposta inválida. Mostrar legenda (s/n)?")
        respostaLegenda = readln()
    }

    if (respostaLegenda!=="n" || respostaLegenda!=="N"){
             return false
    }else{
        if (respostaLegenda!=="s" || respostaLegenda!=="S"){
             return true
        }
    }

    return false
}
fun confirmacaoLegenda(){

    if (pedeLegenda()==true){
        pedeLinhas()
    } else{
        pedeLinhas()
    }
}
fun criaLegenda(numColumns: Int):String?{
   // if (pedeLegenda()== true){ return pedeLinhas() }
    var aux = 0
    var textoApoio : String
    val alfabeto  ="A B C D E F G H I J K L M N O P Q R S T U V X Z"
    var auxiliar = numColumns
    var textoFinalLegenda = ""

    if (numColumns < 1) {
        return ""
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

    while (numLines != 1){ //  !== erro sintaxe
        println("Resposta invalida.")

        println("Quantas linhas?")

         numLines=readln().toInt()
    }

    return numLines.toString() //aqui deve devolver o numero de linhas escolinhas pelo utilizador
}
fun quantasColunas(): String?{
    println("Quantas colunas?")
    var numColunas=readln().toInt()

    while(numColunas<1){
        println("Resposta invalida.")
        println("Quantas colunas?")
        numColunas=readln().toInt()
    }
    return numColunas.toString() //aqui deve devolver o numero de colunas escolinhas pelo utilizador
}
fun criaTerreno(numColumns: Int, numLines: Int, numMines: Int, legenda : Boolean = true): String {

        if (!validaNumeroDeMinas(numLines, numColumns, numMines)) {
            return ""
        }

        var resultado = ""

        if (legenda) {
            resultado += criaLegenda(numColumns) + "\n"
        }

        val totalPosicoes = numLines * numColumns
        var i = 0
        var colAtual = 0
        var minasColocadas = 0

        while (i < totalPosicoes) {

            var simbolo = '.'

            if (i == 0) {
                simbolo = 'J'
            } else if (i == totalPosicoes - 1) {
                simbolo = 'F'
            } else {
                if (minasColocadas < numMines) {
                    simbolo = '*'
                    minasColocadas = minasColocadas + 1
                }
            }

            resultado += simbolo

            i = i + 1
            colAtual = colAtual + 1

            if (colAtual < numColumns && i < totalPosicoes) {
                resultado += " "
            }

            if (colAtual == numColumns && i < totalPosicoes) {
                resultado += "\n"
                colAtual = 0
            }
        }

        return resultado
    }
fun main() {
    println(criaMenu()) // ver como fazer print das funções sem print no main ? confirmar no enunciado
    val opcao = readln()

    if (opcao != "1") {     // logica mal implementada, menu não fica em loop em caso de resposta errada. do while ?
        println(retornaMenu(opcao))
    } else {
        val nome = lerNome()
        val mostraLegenda = pedeLegenda()

        val numLines = pedeLinhas()!!.toInt()
        val numColumns = quantasColunas()!!.toInt()

        println("Quantas minas?")
        var inputMinas = readLine()
        var numMines: Int

        if (inputMinas.isNullOrBlank()) {
            val calc = calculaNumeroDeMinas(numLines, numColumns)
            numMines = if (calc == null) 1 else calc
        } else {
            numMines = inputMinas.toInt()

            while (!validaNumeroDeMinas(numLines, numColumns, numMines)) {
                println("Número de minas inválido.")
                println("Quantas minas? (ENTER para automático)")
                inputMinas = readLine()

                if (inputMinas.isNullOrBlank()) {
                    val calc2 = calculaNumeroDeMinas(numLines, numColumns)
                    numMines = if (calc2 == null) 1 else calc2
                } else {
                    numMines = inputMinas.toInt()
                }
            }
        }

        if (nome != null) {
            println("Olá $nome")
        }

        println(criaTerreno(numColumns, numLines, numMines, mostraLegenda))
  /*  } else if (opcao == "2"){

    }else if (opcao == "0"){

    }else {
        println("Resposta Inválida.")
    }*/
    }
}
