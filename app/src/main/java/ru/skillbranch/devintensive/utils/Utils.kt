package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = if (parts?.getOrNull(0).isNullOrBlank()) null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1).isNullOrBlank()) null else parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val parts: List<String> =
            if (payload.contains(" ")) payload.split(" ") else payload.plus(" ").split(" ")

        val transliterationDictionary = mapOf(
            "а" to "a", "б" to "b",
            "в" to "v", "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh",
            "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l", "м" to "m",
            "н" to "n", "о" to "o", "п" to "p", "р" to "r", "с" to "s", "т" to "t",
            "у" to "u", "ф" to "f", "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh",
            "щ" to "sh'", "ъ" to "", "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu",
            "я" to "ya"
        )

        var firstName = ""
        var lastName = ""

        parts[0].forEach { firstName = firstName.plus(transliterationDictionary[it.lowercase()] ?: it )}
        parts[1].forEach { lastName = lastName.plus(transliterationDictionary[it.lowercase()] ?: it )}

        return firstName.replaceFirstChar { it.uppercase() } + divider + lastName.replaceFirstChar { it.uppercase() }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val parts: List<String?> = listOf(firstName, lastName)

        return when {
            parts[0].isNullOrBlank() -> if (parts[1].isNullOrBlank()) null else parts[1]?.first()
                ?.uppercase()
            parts[1].isNullOrBlank() -> if (parts[0].isNullOrBlank()) null else parts[0]?.first()
                ?.uppercase()
            else -> "${parts[0]?.first()?.uppercase()}${parts[1]?.first()?.uppercase()}"
        }
    }
}