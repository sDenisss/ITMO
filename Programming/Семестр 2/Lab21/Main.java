public class Main {
    public static void main(String[] args) {
        ListString listString = new ListString();
        listString.append('H');
        listString.append('e');
        listString.append('l');
        listString.append('l');
        listString.append('o');
        System.out.println("String: " + listString.toString());
        System.out.println("Length: " + listString.length());

        listString.setCharAt(1, 'a');
        System.out.println("Modified String: " + listString.toString());

        ListString subStr = listString.substring(1, 4);
        System.out.println("Substring: " + subStr.toString());

        listString.append(" World");
        System.out.println("After append: " + listString.toString());

        ListString insertString = new ListString();
        insertString.append('X');
        insertString.append('Y');
        listString.insert(2, insertString);
        System.out.println("After insert: " + listString.toString());

        listString.doublingWords();
    }
}
