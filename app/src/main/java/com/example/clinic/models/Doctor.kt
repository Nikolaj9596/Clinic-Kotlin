package com.example.clinic.models

import java.util.Date

data class Doctor(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val profession: Profession,
    val dateStartWork: Date,
    val created: Date,
    val avatar: String
)
