public class Main {
    private int a, b;

    public Main(int x, int y) {
        a = x;
        b = y;
    }

    public void ThrowDemo() throws Exception {
        if (a > b)
            throw new Exception("Exception");
        else
            throw new RuntimeException("Runtime");
    }

    public static void main(String[] args) {
        Main m = new Main(15, 12);
        try {
            m.ThrowDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
