package com.example.clinic.viewmodels
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.clinic.models.Doctor
import com.example.clinic.utils.MockDataGenerator

class DoctorsViewModel : ViewModel() {
    private val _doctors = mutableStateOf(MockDataGenerator.getDoctors())
    val doctors: State<List<Doctor>> = _doctors

    fun addDoctor(doctor: Doctor) {
        MockDataGenerator.addDoctor(doctor)
        _doctors.value = MockDataGenerator.getDoctors()
    }

    fun updateDoctor(doctor: Doctor) {
        MockDataGenerator.updateDoctor(doctor)
        _doctors.value = MockDataGenerator.getDoctors()
    }

    fun deleteDoctor(doctorId: Int) {
        MockDataGenerator.deleteDoctor(doctorId)
        _doctors.value = MockDataGenerator.getDoctors()
    }
}
