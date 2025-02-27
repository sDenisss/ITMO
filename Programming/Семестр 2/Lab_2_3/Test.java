
public class Test {
    // метод в который будет передаваться лямбда-выражение
    public static String stringOp(StringFunc st, String s) {
        return st.func(s);
    }

    public static void main(String[] args) {
        String inStr = "Лямбда-выражения в Java";
        String outStr;

        outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println("Строка прописными буквами: " + outStr);

        outStr = stringOp((str) -> {
            String result = "";
            for (int i = 0; i < str.length(); i++)
                if (str.charAt(i) != ' ')
                    result += str.charAt(i);
            return result;
        }, inStr);
        System.out.println("Строка без пробелов: " + outStr);
    }
}
