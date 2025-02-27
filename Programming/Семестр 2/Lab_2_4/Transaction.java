import java.time.LocalDate;

public class Transaction {
    private String type; // Тип транзакции: "доход" или "расход"
    private double amount; // Сумма транзакции
    private LocalDate date; // Дата транзакции

    // Конструктор: создает объект транзакции с заданным типом, суммой и датой
    public Transaction(String type, double amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Геттер для получения типа транзакции
    public String getType() {
        return type;
    }

    // Геттер для получения суммы транзакции
    public double getAmount() {
        return amount;
    }

    // Геттер для получения даты транзакции
    public LocalDate getDate() {
        return date;
    }

    @Override
    // Метод для преобразования транзакции в строку
    public String toString() {
        return String.format("Transaction{type='%s', amount=%.2f, date=%s}", type, amount, date);
    }
}
