class  TuringMachineFuncionak {

    private var tape: MutableList<Char> = mutableListOf()
    private var headPosition: Int = 1
    private var currentState: String = "q0"

    fun process(input: String): Boolean {
        // Limpa a fita e inicia com dois espaços em branco
        tape.add(' ')
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
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q3"
                        }
                        'X' -> {
                            tape[headPosition] = 'X'
                            moveLeft()
                            currentState = "q0"
                        }
                        ' ' ->{
                            tape[headPosition] = ' '
                            moveRight()
                            currentState = "q5"
                        } // Aceitação imediata se a entrada estiver vazia
                        else -> return false // Rejeita se o primeiro símbolo não for 'a' ou 'b'
                    }
                }
                "q1" -> {
                    when (currentSymbol) {
                        'a' -> {
                            tape[headPosition] = 'a'
                            moveRight()
                            currentState = "q1"
                        }
                        'X' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q1"
                        }
                        'b'->{
                            tape[headPosition] = 'X'
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
                            tape[headPosition] = 'a'
                            moveLeft()
                            currentState = "q2"
                        }
                        'b' -> {
                            tape[headPosition] = 'b'
                            moveLeft()
                            currentState = "q2"
                        }
                        'X' -> {
                            tape[headPosition] = 'X'
                            moveLeft()
                            currentState = "q0"
                        }
                        ' ' -> {
                            tape[headPosition] = ' '
                            moveLeft()
                            currentState = "q4"
                        }
                        else -> return false // Rejeita para outros símbolos em q2
                    }
                }
                "q3" -> {
                    when (currentSymbol) {
                        'a' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q2"
                        }
                        'b' -> {
                            tape[headPosition] = 'b'
                            moveRight()
                            currentState = "q3"
                        }
                        'X' ->{
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q3"
                        }
                        ' ' -> {
                            currentState = "q2"
                        }
                        else -> return false // Rejeita para outros símbolos em q3
                    }
                }
                "q4" -> {
                    when (currentSymbol) {
                        'X' -> {
                            tape[headPosition] = 'X'
                            moveLeft()
                            currentState = "q4"
                        }
                        ' ' -> {
                            tape[headPosition] = ' '
                            moveRight()
                            currentState = "q_accept"
                        }
                        else -> return false // Rejeita para outros símbolos em q4
                    }
                }

                "q5" -> {
                    when (currentSymbol) {
                        'X' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q5"
                        }
                        'a' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q1"
                        }
                        'b' -> {
                            tape[headPosition] = 'X'
                            moveRight()
                            currentState = "q3"
                        }
                        else -> return false // Rejeita para outros símbolos em q4
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
    val input = "abab"
    val accepted = turingMachine.process(input)
    if (accepted) {
        println("A cadeia '$input' foi aceita.")
    } else {
        println("A cadeia '$input' foi rejeitada.")
    }
}