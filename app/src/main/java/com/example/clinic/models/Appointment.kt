package com.example.clinic.models

import java.time.LocalDateTime

class Appointment(
    val id: Int,
    val doctor: Doctor,
    val client: Client,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val created: LocalDateTime
)
