package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*


data class User
    // Primary constructor
    (
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {
    // Variable
    var introBit: String

    // Secondary constructor 1
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    // Secondary constructor 2
    constructor(id: String) : this(id, "John", "Doe")

    // Initialization block
    init {
        introBit = getIntro()

        println(
            "It's Alive!!! \n" +
                    if (lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!\n" +
                            getIntro()
        )
    }

    // Class function
    private fun getIntro() = """
        tu tu tu tururu !!!
        tu ru ru ru-ru ...
        
        tu tu ru tuuuuuu !!!
        tu tu ru ruuuuuu ...
        ${"\n\n\n"}
        $firstName $lastName    
    """.trimIndent()

    // Another class function
    fun printMe() = println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline    
        """.trimIndent())

    companion object Factory{
        private var lastId: Int = -1
        fun makeUser(fullname: String?): User{
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullname)

            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName
            )
        }
    }

    private constructor(builder: Builder) : this(
        builder.id,
        builder.firstName,
        builder.lastName,
        builder.avatar,
        builder.rating,
        builder.respect,
        builder.lastVisit,
        builder.isOnline
    )

    class Builder {
        var id: String = ""
            private set
        var firstName: String? = ""
            private set
        var lastName: String? = ""
            private set
        var avatar: String? = ""
            private set
        var rating: Int = 0
            private set
        var respect: Int = 0
            private set
        var lastVisit: Date = Date()
            private set
        var isOnline: Boolean = false
            private set

        fun id(id: String) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun avatar(avatar: String) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }

        fun build() = User(this)
    }
}