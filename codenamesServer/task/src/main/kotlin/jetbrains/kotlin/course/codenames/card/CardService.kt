package jetbrains.kotlin.course.codenames.card

import jetbrains.kotlin.course.codenames.utils.Utils
import jetbrains.kotlin.course.codenames.utils.words
import org.springframework.stereotype.Service

@Service
class CardService {
    fun generateWordsCards(): List<Card> {
        require(words.size >= Utils.TOTAL_NUMBER) { "TOTAL_NUMBER is bigger than the amount of words" }

        val shuffledWords = words.shuffled()

        val wordsList = shuffledWords.take(Utils.TOTAL_NUMBER)

        words = shuffledWords.drop(Utils.TOTAL_NUMBER)

        return wordsList.map { Card(WordCardData(it), CardState.Front) }
    }
}
