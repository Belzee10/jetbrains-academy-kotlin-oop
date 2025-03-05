package jetbrains.kotlin.course.codenames.keyCard

import jetbrains.kotlin.course.codenames.utils.Utils

enum class KeyCardType(val number: Int) {
    Pink(8),
    Violet(9),
    Gray(7),
    Black(1)
}

@JvmInline
value class  KeyCardCell(val type: KeyCardType)

data class KeyCard(val cells: List<KeyCardCell> = Utils.uniqueKeyCardGenerator.generateData())
