package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName:String?) : Pair<String?, String?>{

        val parts: List<String>? = fullName?.split(" ")

        val firstName: String?
        val lastName: String?

        if (parts?.getOrNull(0).isNullOrBlank()){
            firstName = "null"
        } else firstName = parts?.getOrNull(0)

        if (parts?.getOrNull(1).isNullOrBlank()){
            lastName = "null"
        } else lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        if (firstName?.isNotBlank() == true && lastName?.isNotBlank() == true) {
            return "${firstName[0].toUpperCase()}${lastName[0].toUpperCase()}"
        } else if (firstName?.isNotBlank() == true) {
            return "${firstName[0].toUpperCase()}"
        } else if (lastName?.isNotBlank() == true) {
            return "${lastName[0].toUpperCase()}"
        } else {
            return null
        }

    }

    fun transliteration(payload: String, divider: String = " "): String {

        var nick = payload.replace("а".toRegex(),"a")
            nick = nick.replace("б".toRegex(),"b")
            nick = nick.replace("в".toRegex(), "v")
            nick = nick.replace("г".toRegex(), "g")
            nick = nick.replace("д".toRegex(), "d")
            nick = nick.replace("е".toRegex(), "e")
            nick = nick.replace("ё".toRegex(), "e")
            nick = nick.replace("ж".toRegex(), "zh")
            nick = nick.replace("з".toRegex(), "z")
            nick = nick.replace("и".toRegex(), "i")
            nick = nick.replace("й".toRegex(), "i")
            nick = nick.replace("к".toRegex(), "k")
            nick = nick.replace("л".toRegex(), "l")
            nick = nick.replace("м".toRegex(), "m")
            nick = nick.replace("н".toRegex(), "n")
            nick = nick.replace("о".toRegex(), "o")
            nick = nick.replace("п".toRegex(), "p")
            nick = nick.replace("р".toRegex(), "r")
            nick = nick.replace("с".toRegex(), "s")
            nick = nick.replace("т".toRegex(), "t")
            nick = nick.replace("у".toRegex(), "u")
            nick = nick.replace("ф".toRegex(), "f")
            nick = nick.replace("х".toRegex(), "h")
            nick = nick.replace("ц".toRegex(), "c")
            nick = nick.replace("ч".toRegex(), "ch")
            nick = nick.replace("ш".toRegex(), "sh")
            nick = nick.replace("щ".toRegex(), "sh'")
            nick = nick.replace("ъ".toRegex(), "")
            nick = nick.replace("ы".toRegex(), "i")
            nick = nick.replace("ь".toRegex(), "")
            nick = nick.replace("э".toRegex(), "e")
            nick = nick.replace("ю".toRegex(), "yu")
            nick = nick.replace("я".toRegex(), "ya")

            nick = nick.replace("А".toRegex(),"A")
            nick = nick.replace("Б".toRegex(),"B")
            nick = nick.replace("В".toRegex(), "V")
            nick = nick.replace("Г".toRegex(), "G")
            nick = nick.replace("Д".toRegex(), "D")
            nick = nick.replace("Е".toRegex(), "E")
            nick = nick.replace("Ё".toRegex(), "E")
            nick = nick.replace("Ж".toRegex(), "ZH")
            nick = nick.replace("З".toRegex(), "Z")
            nick = nick.replace("И".toRegex(), "I")
            nick = nick.replace("Й".toRegex(), "I")
            nick = nick.replace("К".toRegex(), "K")
            nick = nick.replace("Л".toRegex(), "L")
            nick = nick.replace("М".toRegex(), "M")
            nick = nick.replace("Н".toRegex(), "N")
            nick = nick.replace("О".toRegex(), "O")
            nick = nick.replace("П".toRegex(), "P")
            nick = nick.replace("Р".toRegex(), "R")
            nick = nick.replace("С".toRegex(), "S")
            nick = nick.replace("Т".toRegex(), "T")
            nick = nick.replace("У".toRegex(), "U")
            nick = nick.replace("Ф".toRegex(), "F")
            nick = nick.replace("Х".toRegex(), "H")
            nick = nick.replace("Ц".toRegex(), "C")
            nick = nick.replace("Ч".toRegex(), "CH")
            nick = nick.replace("Ш".toRegex(), "SH")
            nick = nick.replace("Щ".toRegex(), "SH'")
            nick = nick.replace("Ъ".toRegex(), "")
            nick = nick.replace("Ы".toRegex(), "I")
            nick = nick.replace("Ь".toRegex(), "")
            nick = nick.replace("Э".toRegex(), "E")
            nick = nick.replace("Ю".toRegex(), "YU")
            nick = nick.replace("Я".toRegex(), "YA")

            nick = nick.replace(" ".toRegex(), divider)

        return nick
    }
}