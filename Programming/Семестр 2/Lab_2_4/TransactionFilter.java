
// Интерфейс для фильтрации транзакций
interface TransactionFilter {
    boolean test(Transaction t);
}