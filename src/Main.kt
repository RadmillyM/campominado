fun criaMenu(): String {
    return ("Bem vindo ao Campo DEISIado\n1 - Novo Jogo\n2 - Ler Jogo\n0 - Sair")
}
//constante feita para a string de resposta inválida
private const val RESPOSTA_INVALIDA = "Resposta invalida"

fun retornaMenu(respostaMenu : String): String{

    if(respostaMenu == "1") {
        return ("Novo jogo") // retirei porque precisa de parametro para correr
    }else if (respostaMenu == "2") {
        return ("NÃO IMPLEMENTADO") // validar o qq faz
    }else if (respostaMenu =="0") {
        return " "
    }else return ("Resposta invalida.")
}
fun validaNome(nome: String?, minSize: Int =4) : Boolean {
//usa do retorno da ondeTemEspaco e temMaiuscula para ferificar se o nome é válido
    if (nome.isNullOrBlank()){
        return false
    } else {
        if ( nome.length<minSize) {
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
    // verifica se há espaços, para sabermos se há sobrenome
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
    // Verifica se há maiusculas
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

        if (!validaNome(nome)) {
            println("Nome inválido, tenta outra vez.\n")
        }

    } while (!validaNome(nome))

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

fun criaLegenda(numColumns: Int):String?{

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
        println("$RESPOSTA_INVALIDA.")

        println("Quantas linhas?")

         numLines=readln().toInt()
    }

    return numLines.toString() //aqui deve devolver o número de linhas escolinhas pelo utilizador
}

fun quantasColunas(): String?{
    println("Quantas colunas?")
    var numColunas=readln().toInt()

    while(numColunas<1){
        println("$RESPOSTA_INVALIDA.")
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
        var contadorCriaterreno = 0
        var colAtual = 0
        var minasColocadas = 0


        while (contadorCriaterreno < totalPosicoes) {

            var simbolo = '.'

            if (contadorCriaterreno == 0) {
                simbolo = 'J'
            } else if (contadorCriaterreno == totalPosicoes - 1) {
                simbolo = 'F'
            } else {
                if (minasColocadas < numMines) {
                    simbolo = '*'
                    minasColocadas = minasColocadas + 1
                }
            }

            resultado += simbolo

            contadorCriaterreno = contadorCriaterreno + 1
            colAtual = colAtual + 1

            if (colAtual < numColumns && contadorCriaterreno < totalPosicoes) {
                resultado += " "
            }

            if (colAtual == numColumns && contadorCriaterreno < totalPosicoes) {
                resultado += "\n"
                colAtual = 0
            }
        }

        return resultado
    }


fun main() {
    println(criaMenu()) // ver como fazer print das funções sem print no main ? confirmar no enunciado
    var opcao = readln()

    while (opcao != "0" && opcao != "1"){

        println(retornaMenu(opcao))
        println(criaMenu())
        opcao= readln()

    }

    if (opcao != "1" ) {
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

        println(criaTerreno(numColumns, numLines, numMines, mostraLegenda))
  /*  } else if (opcao == "2"){

    }else if (opcao == "0"){

    }else {
        println("Resposta Inválida.")
    }*/
    }
}
