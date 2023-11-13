class State(val name: String, val isAccepting: Boolean = false, val isRejecting: Boolean = false) {
    private val transitions = mutableMapOf<Char, Transition>()

    fun addTransition(symbol: Char, writeSymbol: Char, moveDirection: MoveDirection, nextState: State) {
        transitions[symbol] = Transition(writeSymbol, moveDirection, nextState)
    }

    fun getTransition(symbol: Char): Transition? = transitions[symbol]

}