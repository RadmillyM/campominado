import kotlin.system.exitProcess

fun criaMenu(): String {
    return ("\nBem vindo ao Campo DEISIado\n" +
            "1 - Novo Jogo\n" +
            "2 - Ler Jogo\n" +
            "0 - Sair\n")
}
//constante feita para a string de resposta inválida
private const val RESPOSTA_INVALIDA = "Resposta invalida"

fun retornaMenu(respostaMenu : String): String{

    if(respostaMenu == "1") {
        return ("Novo jogo") // retirei porque precisa de parametro para correr
    }else if (respostaMenu == "2") {
        return ("NÃO IMPLEMENTADO") // validar o qq faz
    }else if (respostaMenu =="0") {
        return ""
    }else return ("$RESPOSTA_INVALIDA.")
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
    if (nome.isNullOrBlank()) return false

    val pos = ondeTemEspaco(nome)
    if (pos == -1 || pos == nome.length - 1) return false

    val nomeUpper = nome[0].isUpperCase()
    val apelidoApper = nome[pos + 1].isUpperCase()

    return nomeUpper && apelidoApper
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
fun lerNome() : String? {
    var nome: String?
    do {
        print("Introduza o seu nome:")
        nome = readLine()

        if (!validaNome(nome)) {
            print("Nome inválido, tenta outra vez.\n")
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

    print("Mostrar legenda (s/n)?")
         var respostaLegenda= readln()

    while (respostaLegenda.isNullOrBlank() || (respostaLegenda != "s" && respostaLegenda != "S" && respostaLegenda != "n" && respostaLegenda != "N")){ // assim o ciclo garante que vai estar sempre a pedir a legenda sempre que for a resposta errada
        print("Resposta inválida. Mostrar legenda (s/n)?")
        respostaLegenda = readln()
    }
    if (respostaLegenda=="s" || respostaLegenda=="S"){
        return true
    }else (respostaLegenda == "n" || respostaLegenda == "N")
    return false
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
    print("Quantas linhas?")
        var numLines=readln().toInt()

    while (numLines < 1){ //  !== erro sintaxe
        print("$RESPOSTA_INVALIDA.")

        print("Quantas linhas?")

         numLines=readln().toInt()
    }

    return numLines.toString() //aqui deve devolver o número de linhas escolinhas pelo utilizador
}
fun quantasColunas(): String?{
    print("Quantas colunas?")
    var numColunas=readln().toInt()

    while(numColunas<1){
        print("$RESPOSTA_INVALIDA.")
        print("Quantas colunas?")
        numColunas=readln().toInt()
    }
    return numColunas.toString() //aqui deve devolver o numero de colunas escolinhas pelo utilizador
}
fun criaTerreno(numColumns: Int, numLines: Int, numMines: Int, legenda : Boolean = true): String {

    if (!validaNumeroDeMinas(numLines, numColumns, numMines)) return ""

    var resultado = ""

    if (legenda) {
        resultado += "    " + (criaLegenda(numColumns) ?: "") + "    \n"
    }

    val total = numLines * numColumns
    var pos = 0
    var mines = 0
    var line = 1

    while (line <= numLines) {

        if (legenda) resultado += " $line  "
        else resultado += " "

        var col = 1
        while (col <= numColumns) {

            val ch =
                if (pos == 0) 'J'
                else if (pos == total - 1) 'f'
                else if (mines < numMines) { mines++; '*' }
                else ' '

            resultado += ch

            if (col < numColumns) resultado += " | "

            pos++
            col++
        }

        if (legenda) resultado += "    "
        else resultado += " "

        if (line < numLines) resultado += "\n"

        line++
    }

    return resultado
    }


fun main() {

    var opcao = readln()

    do {
        print(criaMenu())
        opcao = readln()
        if (opcao != "0" && opcao != "1" && opcao != "2") {
            print(RESPOSTA_INVALIDA + "\n")
        }
    } while (opcao != "0" && opcao != "1" && opcao != "2")
    if (opcao == "0"){
        return
    }
    if (opcao == "2"){
        print ("NÃO IMPLEMENTADO")
        return
    }

    if (opcao != "1" ) {
        print(retornaMenu(opcao))
    } else {
        val nome = lerNome()
        val mostraLegenda = pedeLegenda()

        val numLines = pedeLinhas()!!.toInt()
        val numColumns = quantasColunas()!!.toInt()

        print("Quantas minas?")
        var inputMinas = readLine()
        var numMines: Int

        if (inputMinas.isNullOrBlank()) {
            val calc = calculaNumeroDeMinas(numLines, numColumns)
            numMines = if (calc == null) 1 else calc
        } else {
            numMines = inputMinas.toInt()

            while (!validaNumeroDeMinas(numLines, numColumns, numMines)) {
                print("Número de minas inválido.")
                print("Quantas minas? (ENTER para automático)")
                inputMinas = readLine()

                if (inputMinas.isNullOrBlank()) {
                    val calc2 = calculaNumeroDeMinas(numLines, numColumns)
                    numMines = if (calc2 == null) 1 else calc2
                } else {
                    numMines = inputMinas.toInt()
                }
            }
        }
    }
}
