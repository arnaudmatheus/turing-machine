class LanguageStatesBuilder {
    private val states = mutableListOf<State>()

    fun build(): List<State> {
        createState("q0")
        createState("q1")
        createState("q2")
        createState("q3")
        createState("q4")
        createState("q5")
        createState("q_accept", isAccepting = true)
        createState("q_reject", isRejecting = true)

        configureStates()
        return states
    }

    private fun createState(name: String, isAccepting: Boolean = false, isRejecting: Boolean = false): State {
        val state = State(name, isAccepting, isRejecting)
        states.add(state)
        return state
    }

    private fun configureStates() {
        states[0].addTransition('a', 'X', MoveDirection.RIGHT, states[1])
        states[0].addTransition('b', 'X', MoveDirection.RIGHT, states[3])
        states[0].addTransition('X', 'X', MoveDirection.LEFT, states[0])
        states[0].addTransition(' ', ' ', MoveDirection.RIGHT, states[5])
        states[1].addTransition('a', 'a', MoveDirection.RIGHT, states[1])
        states[1].addTransition('X', 'X', MoveDirection.RIGHT, states[1])
        states[1].addTransition('b', 'X', MoveDirection.RIGHT, states[2])
        states[1].addTransition(' ', ' ', MoveDirection.LEFT, states[7])
        states[2].addTransition('a', 'a', MoveDirection.LEFT, states[2])
        states[2].addTransition('b', 'b', MoveDirection.LEFT, states[2])
        states[2].addTransition('X', 'X', MoveDirection.LEFT, states[0])
        states[2].addTransition(' ', ' ', MoveDirection.LEFT, states[4])
        states[3].addTransition('a', 'X', MoveDirection.RIGHT, states[2])
        states[3].addTransition('b', 'b', MoveDirection.RIGHT, states[3])
        states[3].addTransition('X', 'X', MoveDirection.RIGHT, states[3])
        states[3].addTransition(' ', ' ', MoveDirection.RIGHT, states[7])
        states[4].addTransition('X', 'X', MoveDirection.LEFT, states[4])
        states[4].addTransition(' ', ' ', MoveDirection.RIGHT, states[6])
        states[5].addTransition('X', 'X', MoveDirection.RIGHT, states[5])
        states[5].addTransition('a', 'X', MoveDirection.RIGHT, states[1])
        states[5].addTransition('b', 'X', MoveDirection.RIGHT, states[3])

    }
}