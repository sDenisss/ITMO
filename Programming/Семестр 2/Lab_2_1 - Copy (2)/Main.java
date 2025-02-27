public class Main {

        public static void main(String[] args) {
                final String str = "Всё имеет тенденцию развиваться от плохого к худшему";
                final String str2 = "очень ";
                ListString listString = new ListString(str);

                System.out.println("Text before: ");
                System.out.println(listString.toString());
                System.out.println(listString.length());

                System.out.println("Substring: ");
                System.out.println(listString.substring(35, 99));

                System.out.println("Inserting: ");
                listString.insert(35, new ListString(str2));
                System.out.println(listString.toString());

                System.out.println("Doubling: ");
                listString.doublingWords();
                System.out.println(listString.toString());
        }
}
