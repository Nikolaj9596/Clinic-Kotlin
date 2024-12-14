object MockDataGenerator {
    fun getClients(): List<Client> {
        return listOf(
            Client(1, "Иван", "Иванов", "Иванович", "1990-01-01", "ул. Ленина, 1", Date()),
            Client(2, "Мария", "Петрова", "Сергеевна", "1985-05-10", "ул. Пушкина, 10", Date())
        )
    }

    fun getDoctors(): List<Doctor> {
        return listOf(
            Doctor(1, "Петр", "Смирнов", "Викторович", Profession(1, "Терапевт"), Date(), Date()),
            Doctor(2, "Анна", "Кузнецова", "Александровна", Profession(2, "Хирург"), Date(), Date())
        )
    }
}
