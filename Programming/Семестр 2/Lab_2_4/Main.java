
/*Создайте класс Transaction для представления финансовой транзакции с переменными (расход\доход), суммой, датой.
Напишите класс FinanceManager который будет содержать помимо массива транзакций 
методы для добавления транзакций в конец массива, нахождения сумм доходов 
с использованием ссылок на методы, упорядочивания транзакций по различным переменным с  
использованием ссылок на методы, а также фильтрации по различным критериям с использованием лямбда-выражений.
В методе main() добавьте несколько транзакций, примените упорядочивание, 
суммирование и фильтрацию и выведите результат на экран. 
Упорядочивание должно выполняться без создания объекта FinanceManager методом 
простого обмена по неубыванию и по невозрастанию по суммам 
и по датам сначала доходы потом расходы. При фильтрации создаются новые объекты FinanceManager, 
критерии отбора: доход или расход, расход и доход вместе позже заданной даты.
 */
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FinanceManager manager = new FinanceManager(10);

        // Добавление транзакций
        manager.addTransaction(new Transaction("доход", 500.0, LocalDate.of(2024, 7, 1)));
        manager.addTransaction(new Transaction("расход", 200.0, LocalDate.of(2024, 7, 2)));
        manager.addTransaction(new Transaction("доход", 150.0, LocalDate.of(2024, 7, 3)));
        manager.addTransaction(new Transaction("расход", 50.0, LocalDate.of(2024, 7, 4)));
        manager.addTransaction(new Transaction("доход", 300.0, LocalDate.of(2024, 7, 5)));

        // Сортировка по сумме по неубыванию
        System.out.println("Сортировка по сумме по неубыванию:");
        manager.sortTransactions("amount", true);
        manager.printTransactions();

        // Сортировка по дате по невозрастанию
        System.out.println("\nСортировка по дате по невозрастанию:");
        manager.sortTransactions("date", false);
        manager.printTransactions();

        // Суммирование доходов
        double totalIncome = manager.sumIncome();
        System.out.println("\nСумма всех доходов: " + totalIncome);

        // Суммирование расходов
        double totalExpenses = manager.sumExpense();
        System.out.println("\nСумма всех расходов: " + totalExpenses);

        // Фильтрация доходов после определенной даты
        System.out.println("\nФильтрация доходов после 2024-07-02:");
        FinanceManager filteredManager = manager
                .filter(t -> "доход".equals(t.getType()) && t.getDate().isAfter(LocalDate.of(2024, 7, 2)));
        filteredManager.printTransactions();
    }
}
