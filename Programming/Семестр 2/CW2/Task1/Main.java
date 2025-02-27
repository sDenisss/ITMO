public class Main {

    private int a, b;

    Main(int x, int y) {
        a = x;
        b = y;
    }

    public void ThrowDemo() throws Exception {
        try {
            throw a > b ? new Exception("Exc") : new RuntimeException("RExc");
        } catch (Exception e) {
            System.out.println("Rep Exc");
            throw e;
        } catch (RuntimeException e) { // Добавлен блок catch для RuntimeException
            System.out.println("Rep RExc");
            throw e;
        }
    }

    public static void main(String[] args) {
        Main m = new Main(11, 10);
        m.ThrowDemo();
    }
}
