import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TuringMachineTest : StringSpec({

    val turingMachine = TuringMachine()

    "Aceita cadeias válidas" {
        val validInputs = listOf("aabb", "abab", "baba", "aabbab")
        validInputs.forEach {
            turingMachine.process(it) shouldBe true
        }
    }

    "Rejeita cadeias inválidas" {
        val invalidInputs = listOf("ababa", "abbba", "baaab")
        invalidInputs.forEach {
            turingMachine.process(it) shouldBe false
        }
    }
})
