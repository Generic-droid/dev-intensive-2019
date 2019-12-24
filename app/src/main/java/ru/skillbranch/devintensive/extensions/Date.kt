package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add (value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
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

fun Date.humanizeDiff(date: Date = Date()): String?{

    val time  = date.time - this.time

    if (time / SECOND in 0 .. 1) return "только что"
    if (time / SECOND in 2 .. 45) return "несколько секунд назад"
    if (time / SECOND in 46 .. 75) return "минуту назад"

    if (time <= (46 * MINUTE) && time >= (76 * SECOND)){
        val absTime = Math.abs(time / MINUTE)

        if(absTime %10 in 0 .. 1 && absTime %100 != 11L){
            return "$absTime минуту назад"

        } else if (absTime %10 in 2 .. 4  && (absTime %100 < 10 || absTime %100 >= 20)){
            return "$absTime минуты назад"

        } else return "$absTime минут назад"
    }

    if (time / MINUTE in 46 .. 75) return "час назад"

    if (time > (75 * MINUTE) && time < (22 * HOUR)){
        val absTime = Math.abs(time / HOUR)

        if(absTime %10 in 0 .. 1 && absTime %100 != 11L){
            return "$absTime час назад"

        } else if (absTime %10 in 2 .. 4  && (absTime %100 < 10 || absTime %100 >= 20)){
            return "$absTime часа назад"

        } else return "$absTime часов назад"
    }

    if (time > 22 * HOUR && time < 26 * HOUR) return "день назад"

    if (time > (26 * HOUR) && time < (360 * DAY)){
        val absTime = Math.abs(time / DAY)

        if(absTime %10 in 0 .. 1 && absTime %100 != 11L){
            return "$absTime день назад"

        } else if (absTime %10 in 2 .. 4  && (absTime %100 < 10 || absTime %100 >= 20)){
            return "$absTime дня назад"

        } else return "$absTime дней назад"
    }

    if (time < 0 && time >= (- 59 * MINUTE)){
        val absTime = Math.abs(time / MINUTE)

        if(absTime %10 in 0 .. 1 && absTime %100 != 11L){
            return "через $absTime минуту"

        } else if (absTime %10 in 2 .. 4  && (absTime %100 < 10 || absTime %100 >= 20)){
            return "через $absTime минуты"

        } else return "через $absTime минут"
    }

    if (time < (-59 * MINUTE) && time >= (- 23 * HOUR)){
        val absTime = Math.abs(time / HOUR)

        if(absTime %10 in 0 .. 1 && absTime %100 != 11L){
            return "через $absTime час"

        } else if (absTime %10 in 2 .. 4  && (absTime %100 < 10 || absTime %100 >= 20)){
            return "через $absTime часа"

            } else return "через $absTime часов"
    }

    if (time <= (-24 * HOUR) && time >= (- 360 * DAY)){
        val absTime = Math.abs(time / DAY)

        if(absTime %10 in 0 .. 1 && absTime %100 != 11L){
            return "через $absTime день"

        } else if (absTime %10 in 2 .. 4  && (absTime %100 < 10 || absTime %100 >= 20)){
            return "через $absTime дня"

        } else return "через $absTime дней"
    }

    if (time > 360 * DAY) return "более года назад"
    if (time < -360 * DAY) return "более чем через год"

    return "..."

}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}