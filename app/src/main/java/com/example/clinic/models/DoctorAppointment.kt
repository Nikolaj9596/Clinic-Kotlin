package com.example.clinic.models

import java.time.LocalDateTime

class DoctorAppointment(
    val id: Int,
    val doctor: Doctor,
    val client: Client,
    val startDateAppointment: LocalDateTime,
    val endDateAppointment: LocalDateTime,
    val created: LocalDateTime
)
