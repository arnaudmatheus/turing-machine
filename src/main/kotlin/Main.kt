fun main() {
    val turingMachine = TuringMachine()
    val input = "abb"
    val accepted = turingMachine.process(input)
    if (accepted) {
        println("A cadeia '$input' foi aceita.")
    } else {
        println("A cadeia '$input' foi rejeitada.")
    }
}