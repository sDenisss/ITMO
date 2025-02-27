
// Класс Closing реализует поток, который проверяет и удаляет закрывающие скобки из стека
import java.util.Random;

public class Closing implements Runnable {
    private final Stack<Character> stack; // общий стек для удаления элементов
    private final String name; // имя потока для идентификации
    private final char[] closeBrackets = { ')', '}', ']' }; // массив возможных закрывающих скобок
    private final Random random; // генератор случайных чисел для выбора скобок

    // Конструктор инициализирует поток, принимает стек и имя потока
    public Closing(Stack<Character> stack, String name) throws InterruptedException {
        this.stack = stack;
        this.name = name;
        this.random = new Random();
        this.run();
        Thread.sleep(3000);
    }

    @Override
    public void run() {
        System.out.println(this.name + " started.");
        char closeBracket = closeBrackets[random.nextInt(3)]; // случайный выбор закрывающей скобки
        System.out.println("Created " + closeBracket);
        try {
            char topBracket = stack.top(); // получаем верхний элемент из стека
            // Проверяем соответствие открывающей и закрывающей скобки
            if ((closeBracket == ')' && topBracket == '(') ||
                    (closeBracket == '}' && topBracket == '{') ||
                    (closeBracket == ']' && topBracket == '[')) {
                stack.pop(); // удаляем верхний элемент из стека
                System.out.println(this.name + " popped " + topBracket);
            } else {
                System.out.println(this.name + " found mismatch with " + topBracket);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // обрабатываем прерывание потока
        }
        System.out.println("----------");
    }
}
