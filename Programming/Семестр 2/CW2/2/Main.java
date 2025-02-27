public class Main {
    private int a, b;

    public Main(int x, int y) {
        a = x;
        b = y;
    }

    public void throwDemo() {
        try {
            throw (a > b) ? new Exception("Искл Exception") : new RuntimeException("Runtime");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception et) {
            System.out.println(et.getMessage());
        }
    }
}
