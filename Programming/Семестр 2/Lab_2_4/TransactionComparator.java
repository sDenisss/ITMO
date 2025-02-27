// Интерфейс для сравнивания транзакций
public interface TransactionComparator {
    boolean compare(Transaction t1, Transaction t2);
}
