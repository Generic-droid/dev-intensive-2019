package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String{
    val str= this.trimEnd()

    return if (str.length > value) {
        this.substring(0, value).plus("...")
    }  else str
}

fun String.stripHtml(): String {

    return this
        .replace("<(/?[^>]+)>".toRegex(), "")
        .replace("\\s+".toRegex(), " ")
        .replace("&.*?;".toRegex(), "").trim()
}