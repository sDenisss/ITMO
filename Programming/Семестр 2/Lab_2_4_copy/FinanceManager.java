import java.util.Arrays;
import java.util.function.Predicate;

public class FinanceManager {
    private Transaction[] transactions;
    private int size;

    // Конструктор: инициализирует менеджер финансов с заданным размером массива
    // транзакций
    public FinanceManager(int capacity) {
        transactions = new Transaction[capacity];
        size = 0;
    }

    // Метод для добавления транзакции в конец массива
    public void addTransaction(Transaction transaction) {
        if (size < transactions.length) {
            transactions[size++] = transaction;
        } else {
            System.out.println("Не удается добавить транзакцию: массив заполнен.");
        }
    }

    // Метод для суммирования доходов
    public double sumIncome() {
        return Arrays.stream(transactions)
                .filter(t -> t != null && "доход".equals(t.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Метод для сортировки по сумме транзакций
    public void sortByAmount(boolean ascending) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (ascending ? transactions[j].getAmount() > transactions[j + 1].getAmount()
                        : transactions[j].getAmount() < transactions[j + 1].getAmount()) {
                    Transaction temp = transactions[j];
                    transactions[j] = transactions[j + 1];
                    transactions[j + 1] = temp;
                }
            }
        }
    }

    // Метод для сортировки по дате транзакций
    public void sortByDate(boolean ascending) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (ascending ? transactions[j].getDate().isAfter(transactions[j + 1].getDate())
                        : transactions[j].getDate().isBefore(transactions[j + 1].getDate())) {
                    Transaction temp = transactions[j];
                    transactions[j] = transactions[j + 1];
                    transactions[j + 1] = temp;
                }
            }
        }
    }

    // Метод для фильтрации транзакций по критерию
    public FinanceManager filter(Predicate<Transaction> predicate) {
        FinanceManager filteredManager = new FinanceManager(size);
        for (int i = 0; i < size; i++) {
            if (predicate.test(transactions[i])) {
                filteredManager.addTransaction(transactions[i]);
            }
        }
        return filteredManager;
    }

    // Метод для вывода всех транзакций на экран
    public void printTransactions() {
        for (int i = 0; i < size; i++) {
            System.out.println(transactions[i]);
        }
    }
}
