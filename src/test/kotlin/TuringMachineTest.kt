import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TuringMachineTest : StringSpec({

    val turingMachine = TuringMachineFuncionak()

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

    "Aceita ou rejeita outras cadeias" {
        val otherInputs = listOf("aaabbb", "bbbaaa", "ab", "ba", "aaaaa", "bbbbb", "ababab", "bababa")
        otherInputs.forEach {
            turingMachine.process(it) shouldBe it.length % 2 shouldBe  0
        }
    }

    // Adicione mais casos de teste conforme necessário
})
