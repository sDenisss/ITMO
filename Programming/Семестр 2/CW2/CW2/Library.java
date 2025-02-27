import java.util.concurrent.locks.*;

public class Library {
    private final boolean[] books = new boolean[10]; // Наличие книг (true - доступна, false - занята)
    private final Lock lock = new ReentrantLock();
    private final Condition[] bookAvailableConditions = new Condition[10];

    public Library() {
        for (int i = 0; i < books.length; i++) {
            books[i] = true; // Все книги доступны изначально
            bookAvailableConditions[i] = lock.newCondition();
        }
    }

    // Получить книгу
    public void borrowBook(int bookId, int readerId) throws InterruptedException {
        lock.lock();
        try {
            // Если книга занята, ждем пока она освободится
            while (!books[bookId]) {
                System.out.println("Reader " + readerId + " wait book " + bookId);
                bookAvailableConditions[bookId].await();
            }
            books[bookId] = false; // Книга занята
            System.out.println("Reader " + readerId + " take book " + bookId);
            printBooksAvailability();
        } finally {
            lock.unlock();
        }
    }

    // Вернуть книгу
    public void returnBook(int bookId, int readerId) {
        lock.lock();
        try {
            books[bookId] = true; // Книга возвращена
            System.out.println("Reader " + readerId + " return book " + bookId);
            printBooksAvailability();
            bookAvailableConditions[bookId].signal(); // Уведомляем ожидающих
        } finally {
            lock.unlock();
        }
    }

    // Печать доступности книг
    private void printBooksAvailability() {
        StringBuilder sb = new StringBuilder("Library: \n");
        for (int i = 0; i < books.length; i++) {
            sb.append("Book ").append(i).append(": ").append(books[i] ? "true" : "false").append("; ");
        }
        System.out.println(sb.toString());
    }
}