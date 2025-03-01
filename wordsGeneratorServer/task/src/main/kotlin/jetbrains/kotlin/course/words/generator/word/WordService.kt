package jetbrains.kotlin.course.words.generator.word

import jetbrains.kotlin.course.words.generator.util.words
import org.springframework.stereotype.Service

@Service
class WordService {
    companion object {
        val numberOfWords: Int = words.size
        val previousWords: MutableMap<String, MutableList<Word>> = mutableMapOf()
    }

    fun generateNextWord(): Word {
        if (words.isEmpty()) error("Words list cannot be empty")

        return Word(words.removeFirst())
    }

    fun isValidWord(keyWord: String, newWord: String): Boolean {
        if (newWord.isEmpty()) return false

        val newWordGrouped = newWord.groupingBy { it }.eachCount()
        val keyWordGrouped = keyWord.groupingBy { it }.eachCount()

        if(keyWordGrouped.any { (newWordGrouped[it.key] ?: 0) > it.value }) return false

        if (!keyWord.contains(newWord)) return false

        return true
    }

    fun isNewWord(keyWord: String, newWord: String): Boolean {
        if (!previousWords.contains(keyWord)) {
            previousWords.putIfAbsent(keyWord, mutableListOf(Word(newWord)))
            return true
        }

        if ((previousWords[keyWord])!!.contains(Word(newWord))) return false
        previousWords[keyWord]?.add(Word(newWord))
        return true
    }
}
