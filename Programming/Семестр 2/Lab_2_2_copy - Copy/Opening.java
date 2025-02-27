
// Класс Opening реализует поток, который добавляет открывающие скобки в стек
import java.util.Random;

public class Opening implements Runnable {
    private final Stack<Character> stack; // общий стек для добавления элементов
    private final String name; // имя потока для идентификации
    private final char[] opBrackets = { '(', '{', '[' }; // массив возможных открывающих скобок
    private final Random random; // генератор случайных чисел для выбора скобок

    // Конструктор инициализирует поток, принимает стек и имя потока
    public Opening(Stack<Character> stack, String name) throws InterruptedException {
        this.stack = stack;
        this.name = name;
        this.random = new Random();
        this.run();
        Thread.sleep(3000);
    }

    @Override
    public void run() {
        System.out.println(this.name + " started.");
        int numBrackets = random.nextInt(3); // количество скобок для добавления

        // Добавляем случайные скобки в стек
        for (int i = 0; i <= numBrackets; i++) {
            if (!stack.isFull()) {
                int randBracket = random.nextInt(3); // случайный выбор скобки
                char bracket = opBrackets[randBracket]; // выбираем скобку из массива
                try {
                    stack.push(bracket); // добавляем скобку в стек
                    System.out.println(this.name + " pushed " + bracket);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // обрабатываем прерывание потока
                }
            }
            System.out.println("----------");
        }
    }
}
