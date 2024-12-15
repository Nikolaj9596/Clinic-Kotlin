package com.example.clinic.models

import java.util.Date

data class Client(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val dateBirthday: Date,
    val address: String,
    val created: Date
)

