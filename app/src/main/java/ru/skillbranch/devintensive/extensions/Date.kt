package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diffInSeconds = (this.time - date.time) / SECOND
    val diffInMinutes = (this.time - date.time) / MINUTE
    val diffInHours = (this.time - date.time) / HOUR
    val diffInDays = (this.time - date.time) / DAY

    return if (diffInSeconds in -1L downTo -59L) {
        "${TimeUnits.SECOND.plural(diffInSeconds.toInt().absoluteValue)} назад"
    } else if (diffInMinutes in -1L downTo -59L) {
        "${TimeUnits.MINUTE.plural(diffInMinutes.toInt().absoluteValue)} назад"
    } else if (diffInHours in -1L downTo -23L) {
        "${TimeUnits.HOUR.plural(diffInHours.toInt().absoluteValue)} назад"
    } else if (diffInDays in -1L downTo -365L) {
        "${TimeUnits.DAY.plural(diffInDays.toInt().absoluteValue)} назад"
    } else if (diffInDays <= -366) {
        "более года назад"
    } else if (diffInSeconds in 59L downTo 1L) {
        "через ${TimeUnits.SECOND.plural(diffInSeconds.toInt())}"
    } else if (diffInMinutes in 59L downTo 1L) {
        "через ${TimeUnits.MINUTE.plural(diffInMinutes.toInt().absoluteValue)}"
    } else if (diffInHours in 23L downTo 1L) {
        "через ${TimeUnits.HOUR.plural(diffInHours.toInt().absoluteValue)}"
    } else if (diffInDays in 365L downTo 1L) {
        "через ${TimeUnits.DAY.plural(diffInDays.toInt().absoluteValue)}"
    } else if (diffInDays >= 366) {
        "более чем через год"
    } else ""
}

enum class TimeUnits : Plurable{
    SECOND {
        override fun plural(value: Int): String{
            return when(value%10) {
                1 -> "$value секунду"
                in 4 downTo 2 -> "$value секунды"
                else -> "$value секунд"
            }
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            return when(value%10) {
                1 -> "$value минуту"
                in 4 downTo 2 -> "$value минуты"
                else -> "$value минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            return when(value%10) {
                1 -> "$value час"
                in 4 downTo 2 -> "$value часа"
                else -> "$value часов"
            }
        }
    },
    DAY {
        override fun plural(value: Int): String {
            return when(value%10) {
                1 -> "$value день"
                in 4 downTo 2 -> "$value дня"
                else -> "$value дней"
            }
        }
    }
}

interface Plurable{
    fun plural(value: Int): String
}