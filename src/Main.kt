import kotlin.math.max
import kotlin.math.min
import java.io.File
import java.io.IOException

const val JOGADOR = "J"
const val FINAL = "f"
const val MINA = "*"
const val VAZIO = " "

//private const val MENU =
      //      "\nBem vindo ao Campo DEISIado\n\n1 - Novo Jogo\n2 - Ler Jogo\n0 - Sair\n"


fun criaMenu(): String {
    return             "\nBem vindo ao Campo DEISIado\n\n1 - Novo Jogo\n2 - Ler Jogo\n0 - Sair\n"
}

//constante feita para a string de resposta inválida
private const val RESPOSTA_INVALIDA = "Resposta invalida."

private const val NAO_IMPLEMENTADO = "NÃO IMPLEMENTADO"

/*fun retornaMenu(respostaMenu : String): String{

    if(respostaMenu == "1") {
        return ("Novo jogo") // retirei porque precisa de parametro para correr
    }else if (respostaMenu == "2") {
        return ("$NAO_IMPLEMENTADO") // validar o qq faz
    }else if (respostaMenu =="0") {
        return " "
    }else return ("$RESPOSTA_INVALIDA")
}*/

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
fun lerNome() : String? {
    var nome: String?
    do {
        println("Introduz o nome do jogador")
        nome = readLine()

        if (!validaNome(nome)) {
           println(RESPOSTA_INVALIDA)
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

fun calculaNumeroDeMinas(numLines: Int, numColumns: Int): Int? {
    if (numLines < 1 || numColumns < 1) return null

    val casaVazias = numLines * numColumns - 2
    if (casaVazias <= 0) return null

    return if (casaVazias == 1) {
        1
    } else if (casaVazias in 2..5) {
        2
    } else if (casaVazias in 6..10) {
        3
    } else if (casaVazias in 11..20) {
        6
    } else if (casaVazias in 21..50) {
        10
    } else if (casaVazias >= 51) {   // equivalente ao teu "51.."
        15
    } else {
        null
    }
}

fun pedeLegenda(): Boolean{

    println("Mostrar legenda (s/n)?")
         var respostaLegenda= readln()

    while (respostaLegenda.isNullOrBlank() || (respostaLegenda != "s" && respostaLegenda != "S"
                && respostaLegenda != "n" && respostaLegenda != "N")){
        println("Resposta inválida. Mostrar legenda (s/n)?")
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
    while (true) {
        println("Quantas linhas?")
        val input = readLine()
        val numLines = input?.toIntOrNull()

        if (numLines == null || numLines < 1) { //  !== erro sintaxe
            println(RESPOSTA_INVALIDA)

        } else {

            return numLines.toString() //aqui deve devolver o número de linhas escolinhas pelo utilizador
        }
    }
}
fun quantasColunas(): String? {
    while (true) {
        println("Quantas colunas?")
        val input = readLine()
        var numColunas = input?.toIntOrNull()

        if (numColunas == null || numColunas < 1) {
            println(RESPOSTA_INVALIDA)

        } else {
            return numColunas.toString() //aqui deve devolver o numero de colunas escolinhas pelo utilizador
        }
    }
}
fun criaTerreno(numLines: Int, numColumns: Int, numMines: Int, legenda : Boolean = true): String {

    if (!validaNumeroDeMinas(numLines, numColumns, numMines)) return ""

    var resultado = ""

    if (legenda) {
        resultado += "    " + (criaLegenda(numColumns) ?: "") + "    \n"
    }

    val total = numLines * numColumns
    var posicao = 0
    var mines = 0
    var linha = 1

    while (linha <= numLines) {

        if (legenda){
            resultado += " $linha  "
        }
        else resultado += " "

        var coluna = 1
        while (coluna <= numColumns) {

            val letra =
                if (posicao == 0) {
                    'J'
                }
                else if (posicao == total - 1){
                    'f'
                }
                else if (mines < numMines) {
                    mines++
                    '*'
                }
                else ' '

            resultado += letra

            if (coluna < numColumns) {
                resultado += " | "
            }

            posicao++
            coluna++
        }

        if (legenda) {
            resultado += "    "
        }
        else {
            resultado += " "
        }

        if (linha < numLines){
            resultado += "\n"
        }

        linha++
    }

    return resultado
    }
fun pedeMinas(numLines: Int, numColumns: Int): Int{
    println("Quantas minas (ou enter para o valor por omissao)?")
    var inputMinas = readLine()

    var numMines: Int

    if (inputMinas.isNullOrBlank()) {
        numMines = calculaNumeroDeMinas(numLines, numColumns) ?: 1
    } else {
        numMines = inputMinas.toInt()
        while (!validaNumeroDeMinas(numLines, numColumns, numMines)) {
            println("Número de minas inválido.")
            println("Quantas minas (ou enter para o valor por omissao)?")
            inputMinas = readLine()
            numMines = if (inputMinas.isNullOrBlank()){
                calculaNumeroDeMinas(numLines, numColumns) ?: 1
            }else {
                inputMinas.toInt()
            }
        }
    }

    return numMines

}


//parte 2

fun obtemCoordenadas(input: String?): Pair<Int, Int>? {
    if (input == null || input.length != 2) return null

    val charLinha = input[0]
    val charColuna = input[1].uppercaseChar()

    if (!charLinha.isDigit()) return null

    val linha = charLinha - '1'
    if (linha !in 0..8) return null

    val coluna = when (charColuna) {
        'A' -> 0
        'B' -> 1
        'C' -> 2
        'D' -> 3
        'E' -> 4
        'F' -> 5
        'G' -> 6
        'H' -> 7
        'I' -> 8
        else -> return null
    }

    return Pair(linha, coluna)
}
fun validaMovimentoJogador(origem: Pair<Int, Int>, destino: Pair<Int, Int>): Boolean {
    val origemX = origem.first
    val origemY = origem.second

    val destinoX = destino.first
    val destinoY = destino.second

    if ((destinoX >= origemX-1 && destinoX <=origemX+1) && (destinoY >= origemY-1 && destinoY <= origemY+1)) {
        return true
    }
    return false
}
fun validaCoordenadasDentroTerreno(coordenada: Pair<Int, Int>?, numLinhas: Int, numColunas: Int): Boolean {
    if (coordenada == null) return false
    val (linha, coluna) = coordenada
    return linha in 0 until numLinhas && coluna in 0 until numColunas
}

fun quadradoAVoltaDoPonto(linha: Int,coluna: Int,numLinhas: Int,numColunas: Int): Pair<Pair<Int, Int>, Pair<Int, Int>> {

        var linhaMin = linha - 1
        var linhaMax = linha + 1
        var colunaMin = coluna - 1
        var colunaMax = coluna + 1

        if (linhaMin < 0) linhaMin = 0
        if (colunaMin < 0) colunaMin = 0
        if (linhaMax >= numLinhas) linhaMax = numLinhas - 1
        if (colunaMax >= numColunas) colunaMax = numColunas - 1

        return Pair(Pair(linhaMin, colunaMin), Pair(linhaMax, colunaMax))
}

fun contaMinasPerto(terreno: Array<Array<Pair<String, Boolean>>>, linha: Int, coluna: Int): Int {
    var num = 0

    for (linhas:Int  in linha-1..coluna+1) {
        if (linhas <= terreno.size-1 && linhas >=0) {
            for (colunas: Int in linha - 1..coluna + 1) {
                if (colunas <= terreno[linhas].size - 1 && colunas >= 0) {
                    if (terreno[linhas][colunas].first == "*") {
                        num++
                    }
                }
            }
        }
    }

    return num
}

fun geraMatrizTerreno(numLinhas: Int,numColunas: Int,numMinas: Int): Array<Array<Pair<String, Boolean>>> {

        if (numLinhas < 1 || numColunas < 1 || numMinas < 0) {
            return emptyArray()
        }

        val terreno = Array(numLinhas) {
            Array(numColunas) {
                Pair(VAZIO, false)
            }
        }

        terreno[0][0] = Pair(JOGADOR, true)

        terreno[numLinhas - 1][numColunas - 1] = Pair(FINAL, true)

        var minasColocadas = 0

        val maxMinasPossiveis = numLinhas * numColunas - 2
        if (numMinas > maxMinasPossiveis) {
            return emptyArray()
        }

        while (minasColocadas < numMinas) {
            val linhaAleatoria = (0 until numLinhas).random()
            val colunaAleatoria = (0 until numColunas).random()

            val eCasaDoJogador = (linhaAleatoria == 0 && colunaAleatoria == 0)
            val eCasaFinal = (linhaAleatoria == numLinhas - 1 && colunaAleatoria == numColunas - 1)

            if (eCasaDoJogador || eCasaFinal) {
                continue
            }

            if (terreno[linhaAleatoria][colunaAleatoria].first != MINA) {
                terreno[linhaAleatoria][colunaAleatoria] = Pair(MINA, false)
                minasColocadas++
            }
        }

        return terreno
    }

fun celulaTemNumeroMinasVisivel(terreno: Array<Array<Pair<String, Boolean>>>, linha: Int, coluna: Int): Boolean {
    return false
}
fun escondeMatriz(terreno: Array<Array<Pair<String, Boolean>>>) {

}
fun preencheNumMinasNoTerreno(terreno: Array<Array<Pair<String, Boolean>>>) {
    var nrBombas : Int
    if (terreno.isEmpty()) {
        return
    }

    for (linhas in terreno.indices) {
        for (colunas in 0 until terreno[linhas].size) {
            if (terreno[linhas][colunas].first == " ") {
                nrBombas = contaMinasPerto(terreno,linhas,colunas)

                if (nrBombas!=0) {
                    terreno[linhas][colunas] = Pair(nrBombas.toString(), false)
                }
                else {
                    if (!terreno[linhas][colunas].second) {
                        terreno[linhas][colunas] = Pair(" ", false)
                    }
                    else {
                        terreno[linhas][colunas] = Pair(" ", true)

                    }
                }
            }
        }
    }
}
fun revelaMatriz(terreno: Array<Array<Pair<String, Boolean>>>, linha: Int, coluna: Int) {

}

fun criaTerreno(
    terreno: Array<Array<Pair<String, Boolean>>>,
    mostraLegenda: Boolean = true,
    mostraTudo: Boolean = false
): String {
    if (terreno.isEmpty() || terreno[0].isEmpty()) return ""

    val numLinhas = terreno.size
    val numColunas = terreno[0].size
    val sb = StringBuilder()

    fun colLetter(i: Int) = ('A'.code + i).toChar()

    if (mostraLegenda) {
        sb.append("   ")
        for (c in 0 until numColunas) {
            sb.append(colLetter(c))
            if (c < numColunas - 1) sb.append(' ')
        }
        sb.append('\n')
    }

    for (r in 0 until numLinhas) {
        if (mostraLegenda) sb.append(String.format("%2d ", r + 1))
        for (c in 0 until numColunas) {
            val (v, vis) = terreno[r][c]
            val show = mostraTudo || vis
            sb.append(if (show) v else " ")
            if (c < numColunas - 1) sb.append(' ')
        }
        if (r < numLinhas - 1) sb.append('\n')
    }

    return sb.toString()
}


fun lerFicheiroJogo(nomeFicheiro: String, numLinhasEsperadas: Int, numColunasEsperadas: Int): Array<Array<Pair<String, Boolean>>>? {
    val ficheiro = File(nomeFicheiro)
    if (!ficheiro.exists()) {
        return null
    }

    val conteudo = ficheiro.readText()

    // apanhar só caracteres relevantes do terreno
    val celulasChars = mutableListOf<Char>()
    for (ch in conteudo) {
        if (ch == 'J' || ch == 'f' || ch == '*' || ch == ' ') {
            celulasChars.add(ch)
        }
    }

    val totalEsperado = numLinhasEsperadas * numColunasEsperadas
    if (numLinhasEsperadas <= 0 || numColunasEsperadas <= 0) return null
    if (celulasChars.size < totalEsperado) return null

    val terreno = Array(numLinhasEsperadas) {
        Array(numColunasEsperadas) { Pair(VAZIO, false) }
    }

    var indice = 0
    for (linha in 0 until numLinhasEsperadas) {
        for (coluna in 0 until numColunasEsperadas) {
            val ch = celulasChars[indice++]
            val valor = ch.toString()
            val visivel = (valor == JOGADOR || valor == FINAL)
            terreno[linha][coluna] = Pair(valor, visivel)
        }
    }

    return terreno
}
fun validaTerreno(terreno: Array<Array<Pair<String, Boolean>>>?): Boolean {
    return false
}

fun main() {

    var opcao: String

    do {
        println(criaMenu())
        opcao = readln()

        if (opcao != "0" && opcao != "1" && opcao != "2") {
            println(RESPOSTA_INVALIDA)
        }
    } while (opcao != "0" && opcao != "1" && opcao != "2")

    when (opcao) {
        "0" -> return

        "2" -> {
            lerNome()
            val mostraLegenda = pedeLegenda()
            val numLines = pedeLinhas()!!.toInt()
            val numColumns = quantasColunas()!!.toInt()

            println("Qual o ficheiro de jogo a carregar?")
            val nomeFicheiro = readLine()?.trim().orEmpty()

            val terreno = lerFicheiroJogo(nomeFicheiro, numLines, numColumns)

            if (!validaTerreno(terreno)) {

                println("Terreno de jogo invalido")

            } else {
                val mostraLegenda = pedeLegenda()
                val terrenoTexto = criaTerreno(terreno!!, mostraLegenda, mostraTudo = false)
                println(terrenoTexto)

            }
        }

        "1"-> {
            lerNome()
            val mostraLegenda = pedeLegenda()
            val numLines = pedeLinhas()!!.toInt()
            val numColumns = quantasColunas()!!.toInt()
            val numMines = pedeMinas(numLines, numColumns)

            println(criaTerreno(numLines, numColumns, numMines, mostraLegenda))
        }
    }
}