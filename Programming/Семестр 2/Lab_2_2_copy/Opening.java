import java.util.Random;

public class Opening implements Runnable {
    private final Stack<Character> stack;
    private final String name;
    private final char[] opBrackets = { '(', '{', '[' };
    private final Random random;

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
        int numBrackets = random.nextInt(0, 3);

        for (int i = 0; i <= numBrackets; i++) {
            if (!stack.isFull()) {
                int randBracket = (int) random.nextInt(0, 3);
                char bracket = opBrackets[randBracket];
                try {
                    stack.push(bracket);
                    System.out.println(this.name + " pushed " + bracket);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("----------");
        }
    }
}
