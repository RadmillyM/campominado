import kotlin.math.max
import kotlin.math.min
import java.io.File
import kotlin.random.Random


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

private const val MOVIMENTO_INVALIDO = "Movimento invalido."


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
        val numColunas = input?.toIntOrNull()

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
    val diferencaLinhas = kotlin.math.abs(destino.first - origem.first)
    val diferencaColunas = kotlin.math.abs(destino.second - origem.second)

    if (diferencaLinhas == 0 && diferencaColunas == 0) return false
    return diferencaLinhas <= 2 && diferencaColunas <= 2
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

fun contaMinasPerto(
    terreno: Array<Array<Pair<String, Boolean>>>,
    linha: Int,
    coluna: Int
): Int {
    val numLinhas = terreno.size
    val numColunas = terreno[0].size
    val quadrado = quadradoAVoltaDoPonto(linha, coluna, numLinhas, numColunas)

    val cantoSupEsq = quadrado.first
    val cantoInfDir = quadrado.second

    var contadorMinas = 0

    var linhaAtual = cantoSupEsq.first
    while (linhaAtual <= cantoInfDir.first) {
        var colunaAtual = cantoSupEsq.second
        while (colunaAtual <= cantoInfDir.second) {
            if (!(linhaAtual == linha && colunaAtual == coluna)) {
                if (terreno[linhaAtual][colunaAtual].first == MINA) {
                    contadorMinas++
                }
            }
            colunaAtual++
        }
        linhaAtual++
    }

    return contadorMinas
}


fun geraMatrizTerreno(numLinhas: Int, numColunas: Int, numMinas: Int): Array<Array<Pair<String, Boolean>>> {

    if (numLinhas < 1 || numColunas < 1 || numMinas < 0) return emptyArray()

    val totalCelulas = numLinhas * numColunas
    val maxMinas = totalCelulas - 2
    if (numMinas > maxMinas) return emptyArray()

    val terreno = Array(numLinhas) { Array(numColunas) { Pair(VAZIO, false) } }

    terreno[0][0] = Pair(JOGADOR, true)
    terreno[numLinhas - 1][numColunas - 1] = Pair(FINAL, true)

    // índices lineares válidos: 1 .. total-2 (exclui 0 e total-1)
    val posicoes = IntArray(maxMinas)
    var indicePos = 0
    var indiceLinear = 1
    while (indiceLinear <= totalCelulas - 2) {
        posicoes[indicePos] = indiceLinear
        indicePos++
        indiceLinear++
    }

    // baralhar Fisher-Yates
    var i = posicoes.size - 1
    while (i > 0) {
        val j = Random.nextInt(i + 1)
        val tmp = posicoes[i]
        posicoes[i] = posicoes[j]
        posicoes[j] = tmp
        i--
    }

    var minasColocadas = 0
    while (minasColocadas < numMinas) {
        val idx = posicoes[minasColocadas]
        val linhaMina = idx / numColunas
        val colunaMina = idx % numColunas
        terreno[linhaMina][colunaMina] = Pair(MINA, false)
        minasColocadas++
    }

    return terreno
}

fun celulaTemNumeroMinasVisivel(terreno: Array<Array<Pair<String, Boolean>>>, linha: Int, coluna: Int): Boolean {
    if (terreno.isEmpty() || terreno[0].isEmpty()) return false
    if (linha !in terreno.indices) return false
    if (coluna !in terreno[0].indices) return false

    val (valor, visivel) = terreno[linha][coluna]
    if (!visivel) return false
    if (valor.length != 1) return false

    val caracter = valor[0]
    return caracter in '1'..'8'
}

fun escondeMatriz(terreno: Array<Array<Pair<String, Boolean>>>) {
    if (terreno.isEmpty() || terreno[0].isEmpty()) return

    var linhaAtual = 0
    while (linhaAtual < terreno.size) {
        var colunaAtual = 0
        while (colunaAtual < terreno[linhaAtual].size) {
            val valor = terreno[linhaAtual][colunaAtual].first
            val deveFicarVisivel = (valor == JOGADOR || valor == FINAL)
            terreno[linhaAtual][colunaAtual] = Pair(valor, deveFicarVisivel)
            colunaAtual++
        }
        linhaAtual++
    }
}

fun preencheNumMinasNoTerreno(terreno: Array<Array<Pair<String, Boolean>>>) {
    if (terreno.isEmpty() || terreno[0].isEmpty()) return

    val numLinhas = terreno.size
    val numColunas = terreno[0].size

    var l = 0
    while (l < numLinhas) {
        var c = 0
        while (c < numColunas) {
            val valor = terreno[l][c].first

            if (valor == VAZIO) {
                val minas = contaMinasPerto(terreno, l, c)
                val novo = if (minas == 0) VAZIO else minas.toString()
                terreno[l][c] = Pair(novo, false) // mantém false
            } else if (valor == MINA) {
                terreno[l][c] = Pair(MINA, false) // mantém false
            } else if (valor == JOGADOR) {
                terreno[l][c] = Pair(JOGADOR, true)
            } else if (valor == FINAL) {
                terreno[l][c] = Pair(FINAL, true)
            } else {

                terreno[l][c] = Pair(valor, false)
            }

            c++
        }
        l++
    }

    terreno[0][0] = Pair(JOGADOR, true)
    terreno[numLinhas - 1][numColunas - 1] = Pair(FINAL, true)
}


fun revelaMatriz(terreno: Array<Array<Pair<String, Boolean>>>, linha: Int, coluna: Int) {
    if (terreno.isEmpty() || terreno[0].isEmpty()) return

    val numLinhas = terreno.size
    val numColunas = terreno[0].size
    if (linha !in 0 until numLinhas || coluna !in 0 until numColunas) return

    val quad = quadradoAVoltaDoPonto(linha, coluna, numLinhas, numColunas)
    val (minL, minC) = quad.first
    val (maxL, maxC) = quad.second

    var l = minL
    while (l <= maxL) {
        var c = minC
        while (c <= maxC) {
            val valor = terreno[l][c].first
            val v = terreno[l][c].first
            if (v.length == 1 && v[0] in '1'..'8') {
                terreno[l][c] = Pair(v, true)
            }
            c++
        }
        l++
    }

    terreno[0][0] = Pair(JOGADOR, true)
    terreno[numLinhas - 1][numColunas - 1] = Pair(FINAL, true)
}


fun criaTerreno(terreno: Array<Array<Pair<String, Boolean>>>, mostraLegenda: Boolean = true, mostraTudo: Boolean = false): String {

    if (terreno.isEmpty() || terreno[0].isEmpty()) return ""

    val numLinhas = terreno.size
    val numColunas = terreno[0].size
    val textoTerreno = StringBuilder()

    if (mostraLegenda) {
        textoTerreno.append("    ")
        textoTerreno.append(criaLegenda(numColunas) ?: "")
        textoTerreno.append("    \n")
    }

    var linhaAtual = 0
    while (linhaAtual < numLinhas) {
        if (mostraLegenda) {
            textoTerreno.append(" ${linhaAtual + 1}  ")
        } else {
            textoTerreno.append(" ")
        }

        var colunaAtual = 0
        while (colunaAtual < numColunas) {
            val celula = terreno[linhaAtual][colunaAtual]
            val visivel = mostraTudo || celula.second
            val simbolo = if (visivel) celula.first else VAZIO

            textoTerreno.append(simbolo)

            if (colunaAtual < numColunas - 1) {
                textoTerreno.append(" | ")
            } else {
                if (mostraLegenda) textoTerreno.append("    ") else textoTerreno.append(" ")
            }

            colunaAtual++
        }

        if (linhaAtual < numLinhas - 1) {
            textoTerreno.append("\n")
            if (mostraLegenda) textoTerreno.append("   ")

            var colunaSep = 0
            while (colunaSep < numColunas) {
                textoTerreno.append("---")
                if (colunaSep < numColunas - 1) textoTerreno.append("+")
                colunaSep++
            }

            if (mostraLegenda) textoTerreno.append("   ")
            textoTerreno.append("\n")
        }

        linhaAtual++
    }

    return if (mostraLegenda) {
        textoTerreno.toString() + "\n"
    } else {
        textoTerreno.toString()
    }
}


fun lerFicheiroJogo(
    nomeFicheiro: String,
    numLinhasEsperadas: Int,
    numColunasEsperadas: Int
): Array<Array<Pair<String, Boolean>>>? {

    if (numLinhasEsperadas <= 0 || numColunasEsperadas <= 0) return null

    val ficheiro = File(nomeFicheiro)
    if (!ficheiro.exists()) return null

    val linhas = ficheiro.readLines()

    val terreno = Array(numLinhasEsperadas) {
        Array(numColunasEsperadas) { Pair(VAZIO, false) }
    }

    fun extraiSimbolo(segmentoOriginal: String, primeiraColuna: Boolean): String {
        var segmento = segmentoOriginal

        if (primeiraColuna) {
            segmento = segmento.trimStart()
            var i = 0
            while (i < segmento.length && segmento[i].isDigit()) i++
            segmento = segmento.substring(i).trimStart()
        }

        segmento = segmento.trim()

        // prioridade: J / f / * (mesmo que haja dígitos noutro sítio)
        if (segmento.contains('J')) return JOGADOR
        if (segmento.contains('f')) return FINAL
        if (segmento.contains('*')) return MINA

        var k = 0
        while (k < segmento.length) {
            val ch = segmento[k]
            if (ch in '1'..'8') return ch.toString()
            k++
        }

        return VAZIO
    }

    var linhaTerreno = 0
    var idxLinha = 0

    while (idxLinha < linhas.size && linhaTerreno < numLinhasEsperadas) {
        val linhaTexto = linhas[idxLinha]

        // linha de tabuleiro tem '|' e NÃO é separador ---+---
        if (linhaTexto.contains('|') && !linhaTexto.contains("---")) {

            var colunaAtual = 0
            var inicio = 0
            var posBarra = linhaTexto.indexOf('|', inicio)

            // colunas 0..numColunas-2
            while (colunaAtual < numColunasEsperadas - 1) {
                if (posBarra == -1) return null

                val segmento = linhaTexto.substring(inicio, posBarra)
                val simbolo = extraiSimbolo(segmento, colunaAtual == 0)
                val visivel = (simbolo == JOGADOR || simbolo == FINAL)

                terreno[linhaTerreno][colunaAtual] = Pair(simbolo, visivel)

                colunaAtual++
                inicio = posBarra + 1
                posBarra = linhaTexto.indexOf('|', inicio)
            }

            val segmentoFinal = linhaTexto.substring(inicio)
            val simboloFinal = extraiSimbolo(segmentoFinal, false)
            val visivelFinal = (simboloFinal == JOGADOR || simboloFinal == FINAL)
            terreno[linhaTerreno][numColunasEsperadas - 1] = Pair(simboloFinal, visivelFinal)

            linhaTerreno++
        }

        idxLinha++
    }

    if (linhaTerreno != numLinhasEsperadas) return null
    return terreno
}

fun validaTerreno(terreno: Array<Array<Pair<String, Boolean>>>?): Boolean {
    if (terreno == null) return false
    if (terreno.isEmpty() || terreno[0].isEmpty()) return false

    val numLinhas = terreno.size
    val numColunas = terreno[0].size
    var linhaAtual = 0

    while (linhaAtual < numLinhas) {
        if (terreno[linhaAtual].size != numColunas) return false
        linhaAtual++
    }

    if (terreno[0][0].first != JOGADOR) return false
    if (terreno[numLinhas - 1][numColunas - 1].first != FINAL) return false

    if (!terreno[0][0].second) return false
    if (!terreno[numLinhas - 1][numColunas - 1].second) return false
    
    var contaJ = 0
    var contaF = 0

    linhaAtual = 0
    while (linhaAtual < numLinhas) {
        var colunaAtual = 0
        while (colunaAtual < numColunas) {
            val valor = terreno[linhaAtual][colunaAtual].first

            if (valor == JOGADOR) contaJ++
            if (valor == FINAL) contaF++

            val simboloValido =
                valor == VAZIO ||
                        valor == MINA ||
                        valor == JOGADOR ||
                        valor == FINAL ||
                        (valor.length == 1 && valor[0] in '1'..'8')

            if (!simboloValido) return false

            colunaAtual++
        }
        linhaAtual++
    }

    return contaJ == 1 && contaF == 1
}

fun encontraJogador(terreno: Array<Array<Pair<String, Boolean>>>): Pair<Int, Int>? {
    var linhaAtual = 0
    while (linhaAtual < terreno.size) {
        var caracter = 0
        while (caracter < terreno[0].size) {
            if (terreno[linhaAtual][caracter].first == JOGADOR) return Pair(linhaAtual, caracter)
            caracter++
        }
        linhaAtual++
    }
    return null
}

fun executaJogo(terreno: Array<Array<Pair<String, Boolean>>>, mostraLegenda: Boolean) {
    if (terreno.isEmpty() || terreno[0].isEmpty()) return

    val numLinhas = terreno.size
    val numColunas = terreno[0].size
    var mostrarTudoNoProximoCiclo = false
    escondeMatriz(terreno)

    var valorSobJogador = VAZIO

    while (true) {
        val mostraTudoAgora = mostrarTudoNoProximoCiclo
        mostrarTudoNoProximoCiclo = false
        print(criaTerreno(terreno, mostraLegenda, mostraTudo = mostraTudoAgora))
        println("Introduz a celula destino (ex: 2D)")

        val input = readLine()?.trim().orEmpty()
        if (input == "sair"){
            return
        }else if (input.lowercase() == "abracadabra") {
            mostrarTudoNoProximoCiclo = true

        } else {

            val destino = obtemCoordenadas(input)
            val destinoValido = validaCoordenadasDentroTerreno(destino, numLinhas, numColunas)

            if (!destinoValido) {
                println(RESPOSTA_INVALIDA)
            } else {
                val origem = encontraJogador(terreno)
                val movimentoValido = origem != null && validaMovimentoJogador(origem, destino!!)

                if (!movimentoValido) {
                    println(MOVIMENTO_INVALIDO)
                } else {
                    val (linhaOrigem, colunaOrigem) = origem!!
                    val (linhaDestino, colunaDestino) = destino!!

                    terreno[linhaOrigem][colunaOrigem] = Pair(valorSobJogador, true)

                    val valorDestino = terreno[linhaDestino][colunaDestino].first

                    if (valorDestino == MINA) {
                        terreno[linhaDestino][colunaDestino] = Pair(MINA, true)
                        print(criaTerreno(terreno, mostraLegenda, mostraTudo = true))
                        println("Perdeste o jogo!")
                        return
                    }

                    if (valorDestino == FINAL) {
                        terreno[linhaDestino][colunaDestino] = Pair(FINAL, true)
                        print(criaTerreno(terreno, mostraLegenda, mostraTudo = true))
                        return
                    }

                    valorSobJogador = valorDestino

                    terreno[linhaDestino][colunaDestino] = Pair(JOGADOR, true)

                    revelaMatriz(terreno, linhaDestino, colunaDestino)

                    terreno[numLinhas - 1][numColunas - 1] = Pair(FINAL, true)
                }
            }
        }
    }
}

fun main() {

    var opcao: String

    do {
        println(criaMenu())
        opcao = readln()?.trim().orEmpty()

        if (opcao != "0" && opcao != "1" && opcao != "2") {
            println(RESPOSTA_INVALIDA)
        }
    } while (opcao != "0" && opcao != "1" && opcao != "2")

    when (opcao) {
        "0" -> return

        "2" -> {
            var terreno: Array<Array<Pair<String, Boolean>>>? = null
            lerNome()
            val mostraLegenda = pedeLegenda()
            val numLines = pedeLinhas()!!.toInt()
            val numColumns = quantasColunas()!!.toInt()

            println("Qual o ficheiro de jogo a carregar?")
            val nomeFicheiro = readLine()?.trim().orEmpty()
            terreno = lerFicheiroJogo(nomeFicheiro, numLines, numColumns)
            if (!validaTerreno(terreno)) {

                println("Terreno de jogo invalido\n")

            } else {
                preencheNumMinasNoTerreno(terreno!!)
                executaJogo(terreno, mostraLegenda)
            }
        }

        "1"-> {
            lerNome()
            val mostraLegenda = pedeLegenda()
            val numLinhas = pedeLinhas()!!.toInt()
            val numColunas = quantasColunas()!!.toInt()
            val numMinas = pedeMinas(numLinhas, numColunas)
            val terreno = geraMatrizTerreno(numLinhas, numColunas, numMinas)
            preencheNumMinasNoTerreno(terreno)
            executaJogo(terreno, mostraLegenda)
        }
    }
}