package com.example.clinic.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinic.models.Doctor
import com.example.clinic.utils.MockDataGenerator

@Composable
fun DoctorsScreen() {
    val doctors = MockDataGenerator.getDoctors()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(doctors) { doctor ->
            DoctorRow(doctor)
        }
    }
}

@Composable
fun DoctorRow(doctor: Doctor) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "${doctor.firstName} ${doctor.lastName}", style = MaterialTheme.typography.titleMedium)
        Text(text = doctor.profession.name, style = MaterialTheme.typography.bodyMedium)
    }
}
