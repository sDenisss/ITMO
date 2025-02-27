import java.util.Random;

public class Closing implements Runnable {
    private final Stack<Character> stack;
    private final String name;
    private final char[] closeBrackets = { ')', '}', ']' };
    private final Random random;

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
        char closeBracket = closeBrackets[random.nextInt(0, 3)];
        System.out.println("Created " + closeBracket);
        try {
            char topBracket = stack.top();
            if ((closeBracket == ')' && topBracket == '(') ||
                    (closeBracket == '}' && topBracket == '{') ||
                    (closeBracket == ']' && topBracket == '[')) {
                stack.pop();
                System.out.println(this.name + " popped " + topBracket);
            } else {
                System.out.println(this.name + " found mismatch with " + topBracket);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("----------");
    }
}
