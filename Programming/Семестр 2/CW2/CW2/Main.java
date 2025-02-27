public class Main {

    public static void main(String[] args) throws InterruptedException {
        Library library = new Library();
        Thread[] readers = new Thread[10];

        for (int i = 0; i < 10; i++) {
            readers[i] = new Thread(new Readers(library, i));
            readers[i].start();
        }

        // Потоки будут работать бесконечно
        for (Thread reader : readers) {
            try {
                reader.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
