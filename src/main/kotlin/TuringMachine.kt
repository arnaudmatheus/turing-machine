class TuringMachine {

    private var tape: MutableList<Char> = mutableListOf()
    private var headPosition: Int = 1
    private var currentState: String = "q0"

    private fun clear() {
        tape.clear()
        headPosition = 1
        currentState = "q0"
    }

    fun process(input: String): Boolean {
        val languageStates = LanguageStatesBuilder().build()
        clear()
        tape.add(' ')
        tape.addAll(input.toList())

        while (true) {
            val currentSymbol = tape[headPosition]
            val transition =
                languageStates.first { it.name == currentState }.getTransition(currentSymbol) ?: return false
            tape[headPosition] = transition.writeSymbol
            when (transition.moveDirection) {
                MoveDirection.LEFT -> moveLeft()
                MoveDirection.RIGHT -> moveRight()
            }

            print("\n Simbolo: $currentSymbol Estado atual: $currentState \n")
            print(
                "Transição: $currentSymbol -> ${transition.writeSymbol}, ${transition.moveDirection}, " +
                        "${transition.nextState.name} | "
            )
            currentState = transition.nextState.name
            if (currentState == "q_accept") {
                return true
            }
            if (currentState == "q_reject") {
                return false
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