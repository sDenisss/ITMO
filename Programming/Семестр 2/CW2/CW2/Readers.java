import java.util.Random;

public class Readers extends Thread {
    private final Library library;
    private final int readerId;
    private final Random random = new Random();
    private Integer currentBook = null; // Текущая книга на руках

    public Readers(Library library, int readerId) {
        this.library = library;
        this.readerId = readerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (currentBook != null) {
                    library.returnBook(currentBook, readerId);
                    currentBook = null;
                }
                int bookToBorrow = random.nextInt(10);
                library.borrowBook(bookToBorrow, readerId);
                currentBook = bookToBorrow;

                // Читатель "читает" книгу какое-то время
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
