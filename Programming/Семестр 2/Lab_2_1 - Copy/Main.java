/*
 * doublingWords() - дублирование всех слов в объекте. Слово--последовательность символов между пробелами. 
 * Дубль слова помещается непомредственно за словом. Пробелы не дублируютсяя
 */

public class Main {

        public static void main(String[] args) {
                // Инициализация строки str и создание объекта ListString
                final String str = "Всё имеет тенденцию развиваться от плохого к худшему";
                // Инициализация второй строки для вставки
                final String str2 = "очень ";

                // Создание объекта ListString из строки str
                ListString listString = new ListString(str);

                // Вывод текста до изменений
                System.out.println("Text before: ");
                System.out.println(listString.toString());
                System.out.println(listString.length()); // Вывод длины строки

                // Взятие подстроки и вывод результата
                System.out.println("Substring: ");
                System.out.println(listString.substring(35, 99)); // Подстрока от 35 до 99 символа

                // Вставка строки str2 и вывод результата
                System.out.println("Inserting: ");
                listString.insert(35, new ListString(str2)); // Вставка строки str2 на позицию 35
                System.out.println(listString.toString());

                // Дублирование слов и вывод результата
                System.out.println("Doubling: ");
                listString.doublingWords(); // Дублирование всех слов в строке
                System.out.println(listString.toString());
        }
}
