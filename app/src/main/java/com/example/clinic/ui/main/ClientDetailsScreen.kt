import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.clinic.R
import com.example.clinic.ui.main.ClientsScreen
import com.example.clinic.utils.MockDataGenerator

@Composable
fun ClientDetailsScreen(clientId: Int, onEdit: (Int) -> Unit, onDelete: (Int) -> Unit) {
    val client = MockDataGenerator.getClients().find { it.id == clientId }

    client?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Аватар
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Растягиваем контейнер по ширине
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center // Центрируем содержимое
            ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = if (it.avatar.isNotEmpty()) {
                        rememberAsyncImagePainter(it.avatar) // Если есть ссылка на аватар
                    } else {
                        painterResource(id = R.drawable.ic_avatar) // Стандартная аватарка
                    },
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .fillMaxSize()
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .align(Alignment.Center)
                        .border(2.dp, Color.LightGray, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }}

            Spacer(modifier = Modifier.height(16.dp))

            // Блок с информацией
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "${it.lastName} ${it.firstName} ${it.middleName} ",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF4A148C)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Дата рождения: ${SimpleDateFormat("dd MMMM yyyy").format(it.dateBirthday)}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Адрес: ${it.address}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопки действий
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { onEdit(it.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4A148C))
                ) {
                    Text(text = "Редактировать", color = Color.White)
                }
                Button(
                    onClick = { onDelete(it.id) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Удалить", color = Color.White)
                }
            }
        }
    } ?: run {
        // Если клиента с указанным ID не существует
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Клиент не найден",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.Gray)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClientsScreen() {
    val navController = rememberNavController()
    ClientsScreen(navController = navController)
}
