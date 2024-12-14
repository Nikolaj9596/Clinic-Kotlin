package com.example.clinic.utils

import java.text.SimpleDateFormat
import java.util.*
import com.example.clinic.models.Client
import com.example.clinic.models.Doctor
import com.example.clinic.models.Profession
import java.util.Date

object MockDataGenerator {

    fun getClients(): List<Client> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Определяем формат даты

        return listOf(
            Client(1, "Иван", "Иванов", "Иванович", dateFormat.parse("1990-01-01") ?: Date(), "ул. Ленина, 1", Date()),
            Client(2, "Мария", "Петрова", "Сергеевна", dateFormat.parse("1985-05-10") ?: Date(), "ул. Пушкина, 10", Date())
        )
    }

    fun getDoctors(): List<Doctor> {
        return listOf(
            Doctor(1, "Петр", "Смирнов", "Викторович", Profession(1, "Терапевт"), Date(), Date()),
            Doctor(2, "Анна", "Кузнецова", "Александровна", Profession(2, "Хирург"), Date(), Date())
        )
    }
}
