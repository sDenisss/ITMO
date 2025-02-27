import java.time.LocalDate;

public class Transaction {
    private String type; // Тип транзакции: "доход" или "расход"
    private double amount; // Сумма транзакции
    private LocalDate date; // Дата транзакции

    // Конструктор: инициализирует транзакцию с заданным типом, суммой и датой
    public Transaction(String type, double amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Методы-геттеры
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction [type=" + type + ", amount=" + amount + ", date=" + date + "]";
    }

}
