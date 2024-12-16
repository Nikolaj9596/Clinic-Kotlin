package com.example.clinic.ui.main.doctors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.clinic.R
import com.example.clinic.models.Client
import com.example.clinic.models.Doctor
import com.example.clinic.utils.MockDataGenerator

@Composable
fun DoctorsScreen(navController: NavController, onDelete: (Int) -> Unit) {
    val doctors = MockDataGenerator.getDoctors()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(doctors) { doctor ->
                DoctorRow(doctor = doctor, onClick = {
                    navController.navigate("client_details/${doctor.id}")
                }, navController=navController, onDelete=onDelete)
            }
        }

        FloatingActionButton(
            onClick = {navController.navigate("doctor_form")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFF6A1B9A)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Добавить Врача",
                tint = Color.White
            )
        }
    }
}

@Composable
fun DoctorRow(doctor: Doctor, navController: NavController, onClick: () -> Unit, onDelete: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box( // Используем Box для добавления градиента на фон
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC)) // Градиент от фиолетового к розовому
                    )
                )
                .padding(10.dp) // Внутренние отступы
        )
        {Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // Аватарка клиента (если это URL, то загружаем с помощью Coil)
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.Gray, shape = CircleShape)
                    .align(Alignment.CenterVertically)
            ) {
                val painter = rememberImagePainter(
                    data = doctor.avatar,
                    builder = {
                        crossfade(true)  // Анимация при загрузке
                        placeholder(R.drawable.ic_avatar)  // Используем локальное изображение как плейсхолдер
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .fillMaxSize()
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .border(2.dp, Color.LightGray, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Информация о клиенте в блоке с закругленными углами
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
                    .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large)
                    .padding(12.dp)
            ) {
                Text(
                    text = "${doctor.firstName} ${doctor.lastName}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.Cyan, Color.Blue),
                            ),
                            shape = RoundedCornerShape(12.dp) // Скругленные углы
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp) // Внутренние отступы
                ) {
                    Text(
                        text = doctor.profession.name,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White, // Белый цвет текста
                            fontStyle = FontStyle.Italic // Курсив
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Кнопки редактирования и удаления
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                IconButton(onClick = {navController.navigate("doctor_form/${doctor.id}")
                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Редактировать"
                    )
                }
                IconButton(onClick = {onDelete(doctor.id) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Удалить",
                        tint = Color.Red
                    )
                }
            }
        }}
    }
}