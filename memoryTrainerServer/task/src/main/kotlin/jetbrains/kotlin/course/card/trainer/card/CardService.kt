package jetbrains.kotlin.course.card.trainer.card

import jetbrains.kotlin.course.card.trainer.util.countries
import org.springframework.stereotype.Service

@Service
class CardService {

    companion object {
        val randomCardGenerator = CardSequenceGenerator {
            countries.map { Card(Front(it.key), Back(it.value)) }.shuffled()
        }

        private fun generateNewCardsSequence(): MutableList<Card> = randomCardGenerator.generateCards().toMutableList()

        var cards: MutableList<Card> = generateNewCardsSequence()
    }

    fun getNextCard(): Card {
        require(cards.isNotEmpty()) { "Cards cannot be empty" }

        return cards.removeFirst()
    }

    fun startNewGame(): Card {
        cards = generateNewCardsSequence()

        return getNextCard()
    }
}



