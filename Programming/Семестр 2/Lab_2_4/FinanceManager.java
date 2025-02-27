public class FinanceManager {
    protected Transaction[] transactions; // массив транзакций
    protected int size; // текущий размер массива (количество транзакций)

    // Конструктор: создает менеджер финансов с заданной вместимостью
    public FinanceManager(int capacity) {
        transactions = new Transaction[capacity]; // инициализация массива транзакций
        size = 0; // начальное количество транзакций равно 0
    }

    // Метод для добавления транзакции в конец массива
    public void addTransaction(Transaction transaction) {
        if (size < transactions.length) {
            transactions[size++] = transaction;
        } else {
            System.out.println("Не удается добавить транзакцию: массив заполнен.");
        }
    }

    // Метод для суммирования всех доходов
    public double sumIncome() {
        return sumTransactions(t -> "доход".equals(t.getType()));
    }

    // Метод для суммирования всех расходов
    public double sumExpense() {
        return sumTransactions(t -> "расход".equals(t.getType()));
    }

    // Вспомогательный метод для суммирования транзакций с фильтром
    private double sumTransactions(TransactionFilter filter) {
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            Transaction t = transactions[i];
            if (t != null && filter.test(t)) {
                sum += t.getAmount();
            }
        }
        return sum;
    }

    // Метод для сортировки транзакций с использованием ссылки на метод
    public void sortTransactions(String field, boolean ascending) {
        TransactionComparator comparator; // Используем TransactionFilter как основу для ссылки на метод

        if ("amount".equals(field)) {
            comparator = ascending ? this::compareByAmountAsc : this::compareByAmountDesc;
        } else {
            comparator = ascending ? this::compareByDateAsc : this::compareByDateDesc;
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(transactions[j], transactions[j + 1])) {
                    Transaction temp = transactions[j];
                    transactions[j] = transactions[j + 1];
                    transactions[j + 1] = temp;
                }
            }
        }
    }

    // Метод для фильтрации транзакций по критерию с использованием лямбда-выражений
    public FinanceManager filter(TransactionFilter filter) {
        FinanceManager filteredManager = new FinanceManager(size);
        for (int i = 0; i < size; i++) {
            Transaction t = transactions[i];
            if (t != null && filter.test(t)) {
                filteredManager.addTransaction(t);
            }
        }
        return filteredManager;
    }

    // Метод для сравнения по сумме по возрастанию
    private boolean compareByAmountAsc(Transaction t1, Transaction t2) {
        return t1.getAmount() > t2.getAmount();
    }

    // Метод для сравнения по сумме по убыванию
    private boolean compareByAmountDesc(Transaction t1, Transaction t2) {
        return t1.getAmount() < t2.getAmount();
    }

    // Метод для сравнения по дате по возрастанию
    private boolean compareByDateAsc(Transaction t1, Transaction t2) {
        return t1.getDate().isAfter(t2.getDate());
    }

    // Метод для сравнения по дате по убыванию
    private boolean compareByDateDesc(Transaction t1, Transaction t2) {
        return t1.getDate().isBefore(t2.getDate());
    }

    // Метод для вывода всех транзакций на экран
    public void printTransactions() {
        for (int i = 0; i < size; i++) {
            System.out.println(transactions[i]);
        }
    }
}
