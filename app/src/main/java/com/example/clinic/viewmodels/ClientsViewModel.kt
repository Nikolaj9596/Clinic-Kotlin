import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.clinic.models.Client
import com.example.clinic.utils.MockDataGenerator

class ClientsViewModel : ViewModel() {
    private val _clients = mutableStateOf(MockDataGenerator.getClients())
    val clients: State<List<Client>> = _clients

    fun addClient(client: Client) {
        MockDataGenerator.addClient(client)
        _clients.value = MockDataGenerator.getClients()
    }

    fun updateClient(client: Client) {
        MockDataGenerator.updateClient(client)
        _clients.value = MockDataGenerator.getClients()
    }

    fun deleteClient(clientId: Int) {
        MockDataGenerator.deleteClient(clientId)
        _clients.value = MockDataGenerator.getClients()
    }
}
