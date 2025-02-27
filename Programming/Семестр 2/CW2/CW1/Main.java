public class Main {

    private int a, b;

    public Main(int x, int y) {
        a = x;
        b = y;
    }

    public void ThrowDemo() throws Exception {
        try {
            throw (a > b ? new Exception("Исключение Exception") : new RuntimeException("Исключение RuntimeException"));
        } catch (Exception e) {
            System.out.println("Повторно выброшено исключение: " + e.getMessage());
            throw e; // повторно выбрасываем исключение
        }
    }

    public static void main(String[] args) {
        try {
            Main t = new Main(11, 12);
            t.ThrowDemo();
        } catch (Exception e) {
            System.out.println("Исключение поймано в методе main: " + e.getMessage());
        }
    }
}
