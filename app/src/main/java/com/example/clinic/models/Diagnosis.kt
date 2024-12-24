package com.example.clinic.models

import java.time.LocalDateTime

data class Diagnosis(
    val id: Int,
    val name: String,
    val description: String,
    val client: Client,
    val doctor: Doctor,
    val status: DiagnosisStatus,
    val created: LocalDateTime
)

enum class DiagnosisStatus {
    ACTIVE,
    INACTIVE
}
