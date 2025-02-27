public class LambdaClass {
    public static void main(String[] args) {

        LamInterface countSimple = (a, b) -> {
            int ans = 0;
            double toSq;
            if (a % 2 == 0) {
                a++;
                if (a == 2) {
                    ans++;
                }
            }

            int j;
            for (int i = a; i <= b; i += 2) {

                toSq = Math.sqrt(i);
                for (j = 3; j <= toSq; j += 2) {
                    if (i % j == 0) {
                        break;
                    }

                }
                if (j > toSq)
                    ans++;
            }

            return ans;
        };
        int n = 1;
        int n1 = 20;
        System.out.println(countSimple.func(n, n1));
    }
}