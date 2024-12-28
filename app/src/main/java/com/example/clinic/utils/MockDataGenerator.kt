package com.example.clinic.utils

import java.text.SimpleDateFormat
import java.util.*
import com.example.clinic.models.Client
import com.example.clinic.models.Diagnosis
import com.example.clinic.models.DiagnosisStatus
import com.example.clinic.models.Doctor
import com.example.clinic.models.Appointment
import com.example.clinic.models.Profession
import java.time.LocalDateTime
import java.util.Date

object MockDataGenerator {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val clients = mutableListOf(
        Client(1, "Иван", "Иванов", "Иванович", dateFormat.parse("1985-05-10") ?: Date(), "ул. Ленина, 1", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
        Client(2, "Мария", "Петрова", "Сергеевна", dateFormat.parse("1985-05-10") ?: Date(), "ул. Пушкина, 10", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
        Client(3, "Валерия", "Петрова", "Сергеевна", dateFormat.parse("1985-05-10") ?: Date(), "ул. Пушкина, 10", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
        Client(4, "Антонина", "Ванина", "Сергеевна", dateFormat.parse("1985-05-10") ?: Date(), "ул. Пушкина, 10", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380")
    )


    fun getClients(): List<Client> {
        return clients
    }

    fun addClient(client: Client) {
        clients.add(client)
    }

    fun updateClient(updatedClient: Client) {
        val index = clients.indexOfFirst { it.id == updatedClient.id }
        if (index != -1) {
            clients[index] = updatedClient
        }
    }

    fun deleteClient(clientId: Int) {
        clients.removeAll { it.id == clientId }
    }
    private val profession = mutableListOf(
        Profession(1, "Терапевт"),
        Profession(2, "Хирург"),
        Profession(3, "Стомотолог"),
        Profession(4, "Окулист"),
    )

    fun getProfessions(): List<Profession> {
        return profession
    }
    private val doctors = mutableListOf(
        Doctor(1, "Петр", "Смирнов", "Викторович", Profession(1, "Терапевт"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
        Doctor(2, "Анна", "Кузнецова", "Александровна", Profession(2, "Хирург"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380")
    )

    fun getDoctors(): List<Doctor> {
        return doctors
    }

    fun addDoctor(doctor: Doctor) {
        doctors.add(doctor)
    }

    fun updateDoctor(updatedDoctor: Doctor) {
        val index = doctors.indexOfFirst { it.id == updatedDoctor.id }
        if (index != -1) {
            doctors[index] = updatedDoctor
        }
    }

    fun deleteDoctor(doctorId: Int) {
        doctors.removeAll { it.id == doctorId }
    }

    private val diagnoses = mutableListOf<Diagnosis>(
        Diagnosis(1, "Насморк", "Какое-то описание", Client(1, "Иван", "Иванов", "Иванович", dateFormat.parse("1985-05-10") ?: Date(), "ул. Ленина, 1", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"), Doctor(1, "Петр", "Смирнов", "Викторович", Profession(1, "Терапевт"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"), DiagnosisStatus.ACTIVE, LocalDateTime.now())
    )

    fun addDiagnosis(diagnosis: Diagnosis) {
        diagnoses.add(diagnosis)
    }

    fun updateDiagnosis(updatedDiagnosis: Diagnosis) {
        val index = diagnoses.indexOfFirst { it.id == updatedDiagnosis.id }
        if (index != -1) {
            diagnoses[index] = updatedDiagnosis
        }
    }

    fun deleteDiagnosis(diagnosisId: Int) {
        diagnoses.removeAll { it.id == diagnosisId }
    }

    fun getDiagnosisById(diagnosisId: Int): Diagnosis? {
        return diagnoses.find { it.id == diagnosisId }
    }

    fun getAllDiagnoses(): List<Diagnosis> {
        return diagnoses.toList()
    }

    // Appointments

    private val appointments = mutableListOf<Appointment>(
        Appointment(
            id = 1,
            doctor = Doctor(1, "Петр", "Смирнов", "Викторович", Profession(1, "Терапевт"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            client = Client(1, "Иван", "Иванов", "Иванович", Date(1985, 5, 10), "ул. Ленина, 1", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            startDate = LocalDateTime.of(2024, 12, 10, 14, 30),
            endDate = LocalDateTime.of(2024, 12, 10, 15, 30),
            created = LocalDateTime.of(2024, 1, 15, 10, 0)
        ),
        Appointment(
            id = 2,
            doctor = Doctor(2, "Анна", "Кузнецова", "Александровна", Profession(2, "Хирург"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            client = Client(2, "Анна", "Петрова", "Сергеевна", Date(1990, 8, 15), "ул. Советская, 5", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            startDate = LocalDateTime.of(2025, 1, 12, 9, 30),
            endDate = LocalDateTime.of(2025, 1, 12, 11, 0),
            created = LocalDateTime.of(2023, 12, 20, 15, 0)
        ),
        Appointment(
            id = 3,
            doctor = Doctor(1, "Петр", "Смирнов", "Викторович", Profession(1, "Терапевт"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            client = Client(1, "Иван", "Иванов", "Иванович", Date(1985, 5, 10), "ул. Ленина, 1", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            startDate = LocalDateTime.of(2024, 11, 1, 10, 0),
            endDate = LocalDateTime.of(2024, 11, 1, 12, 0),
            created = LocalDateTime.of(2023, 12, 1, 14, 0)
        ),
        Appointment(
            id = 4,
            doctor = Doctor(2, "Анна", "Кузнецова", "Александровна", Profession(2, "Хирург"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            client = Client(2, "Анна", "Петрова", "Сергеевна", Date(1990, 8, 15), "ул. Советская, 5", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            startDate = LocalDateTime.of(2024, 12, 20, 8, 0),
            endDate = LocalDateTime.of(2024, 12, 20, 9, 30),
            created = LocalDateTime.of(2023, 11, 15, 10, 30)
        ),
        Appointment(
            id = 5,
            doctor = Doctor(1, "Петр", "Смирнов", "Викторович", Profession(1, "Терапевт"), Date(), Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            client = Client(1, "Иван", "Иванов", "Иванович", Date(1985, 5, 10), "ул. Ленина, 1", Date(), "https://img.freepik.com/free-photo/view-3d-cool-modern-bird_23-2150946449.jpg?t=st=1734292766~exp=1734296366~hmac=5f9a25581ab2e189925684283d1097f2d194c06ce896c644a08c85ff1d10267c&w=1380"),
            startDate = LocalDateTime.of(2024, 10, 15, 14, 30),
            endDate = LocalDateTime.of(2024, 10, 15, 15, 30),
            created = LocalDateTime.of(2023, 10, 1, 9, 0)
        )
    )

    fun addAppointment(appointment: Appointment) {
        appointments.add(appointment)
    }

    fun updateAppointment(updatedAppointment: Appointment) {
        val index = appointments.indexOfFirst { it.id == updatedAppointment.id }
        if (index != -1) {
            appointments[index] = updatedAppointment
        }
    }

    fun deleteAppointment(appointmentId: Int) {
        appointments.removeAll { it.id == appointmentId }
    }

    fun getAppointmentById(appointmentId: Int): Appointment? {
        return appointments.find { it.id == appointmentId }
    }

    fun getAllAppointments(): List<Appointment> {
        return appointments.toList()
    }

    fun getAppointmentsByDoctor(doctorId: Int): List<Appointment> {
        return appointments.filter { it.doctor.id == doctorId }
    }

    fun getAppointmentsByClient(clientId: Int): List<Appointment> {
        return appointments.filter { it.client.id == clientId }
    }
}
