class  TuringMachineFuncionak {

    private var tape: MutableList<Char> = mutableListOf()
    private var headPosition: Int = 0
    private var currentState: String = "q0"

    fun process(input: String): Boolean {
        // Limpa a fita e inicia com dois espaços em branco
        tape.addAll(input.toList())

        while (true) {
            val currentSymbol = tape[headPosition]

            when (currentState) {
                "q0" -> {
                    when (currentSymbol) {
                        'a' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q1"
                        }
                        'b' -> {
                            tape[headPosition] = 'Y'
                            moveRight()
                            currentState = "q2"
                        }
                        ' ' -> return true // Aceitação imediata se a entrada estiver vazia
                        else -> return false // Rejeita se o primeiro símbolo não for 'a' ou 'b'
                    }
                }
                "q1" -> {
                    when (currentSymbol) {
                        'a' -> moveRight()
                        'b' -> {
                            tape[headPosition] = 'Y'
                            moveRight()
                            currentState = "q2"
                        }
                        ' ' -> currentState = "q_reject"
                        else -> return false // Rejeita para outros símbolos em q1
                    }
                }
                "q2" -> {
                    when (currentSymbol) {
                        'a' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q3"
                        }
                        'b' -> moveRight()
                        ' ' -> currentState = "q_reject"
                        else -> return false // Rejeita para outros símbolos em q2
                    }
                }
                "q3" -> {
                    when (currentSymbol) {
                        'a' -> moveRight()
                        'b' -> {
                            tape[headPosition] = 'Y'
                            moveRight()
                            currentState = "q4"
                        }
                        ' ' -> currentState = "q_reject"
                        else -> return false // Rejeita para outros símbolos em q3
                    }
                }
                "q4" -> {
                    when (currentSymbol) {
                        'a' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q5"
                        }
                        'b' -> moveRight()
                        ' ' -> currentState = "q_reject"
                        else -> return false // Rejeita para outros símbolos em q4
                    }
                }
                "q5" -> {
                    when (currentSymbol) {
                        'a' -> moveRight()
                        'b' -> {
                            tape[headPosition] = 'Y'
                            moveRight()
                            currentState = "q6"
                        }
                        ' ' -> currentState = "q_accept"
                        else -> return false // Rejeita para outros símbolos em q5
                    }
                }
                "q6" -> {
                    when (currentSymbol) {
                        'a' -> moveRight()
                        'b' -> moveRight()
                        ' ' -> currentState = "q_accept"
                        else -> return false // Rejeita para outros símbolos em q6
                    }
                }
                "q_accept" -> {
                    return currentSymbol == ' ' // Aceita imediatamente se encontrar espaço em branco
                }
                "q_reject" -> return false // Estado de rejeição
            }

            if (currentState == "q_accept" && currentSymbol == ' ') {
                return true // Aceitação final se chegou ao final da fita
            }
        }
    }

    private fun moveLeft() {
        headPosition--
    }

    private fun moveRight() {
        headPosition++
        if (headPosition == tape.size) {
            tape.add(' ')
        }
    }
}

fun main() {
    val turingMachine = TuringMachineFuncionak()
    val input = "aabb"
    val accepted = turingMachine.process(input)
    if (accepted) {
        println("A cadeia '$input' foi aceita.")
    } else {
        println("A cadeia '$input' foi rejeitada.")
    }
}