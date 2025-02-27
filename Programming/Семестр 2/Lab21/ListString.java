public class ListString {

    private static class StringItem {
        private final static int SIZE = 16; // Максимальный размер блока
        private char[] symbols;
        private StringItem next;
        private byte size;

        // Конструктор по умолчанию
        public StringItem() {
            this.symbols = new char[SIZE];
            this.next = null;
            this.size = 0;
        }

        // Добавить символ в блок
        public boolean addSymbol(char ch) {
            if (size < SIZE) {
                symbols[size++] = ch;
                return true;
            }
            return false;
        }

        // Получить символ по индексу
        public char getSymbol(int index) {
            if (index >= 0 && index < size) {
                return symbols[index];
            }
            throw new IndexOutOfBoundsException("Invalid index in StringItem");
        }

        // Заменить символ по индексу
        public void setSymbol(int index, char ch) {
            if (index >= 0 && index < size) {
                symbols[index] = ch;
            } else {
                throw new IndexOutOfBoundsException("Invalid index in StringItem");
            }
        }
    }

    private StringItem head;

    // Конструктор по умолчанию
    public ListString() {
        this.head = null;
    }

    // Получение длины строки
    public int length() {
        int length = 0;
        StringItem current = head;
        while (current != null) {
            length += current.size;
            current = current.next;
        }
        return length;
    }

    // Вернуть символ по индексу
    public char charAt(int index) {
        StringItem current = head;
        while (current != null) {
            if (index < current.size) {
                return current.getSymbol(index);
            } else {
                index -= current.size;
                current = current.next;
            }
        }
        throw new IndexOutOfBoundsException("Invalid index in ListString");
    }

    // Заменить символ по индексу
    public void setCharAt(int index, char ch) {
        StringItem current = head;
        while (current != null) {
            if (index < current.size) {
                current.setSymbol(index, ch);
                return;
            } else {
                index -= current.size;
                current = current.next;
            }
        }
        throw new IndexOutOfBoundsException("Invalid index in ListString");
    }

    // Взять подстроку от start до end
    public ListString substring(int start, int end) {
        if (start < 0 || end < start) {
            throw new IllegalArgumentException("Invalid start or end positions");
        }

        ListString result = new ListString();
        StringItem current = head;
        int currentIndex = 0;

        while (current != null && start < end) {
            for (int i = 0; i < current.size; i++) {
                if (currentIndex >= start && currentIndex < end) {
                    result.append(current.getSymbol(i));
                }
                currentIndex++;
            }
            current = current.next;
        }

        return result;
    }

    // Добавить символ в конец строки
    public void append(char ch) {
        if (head == null) {
            head = new StringItem();
            head.addSymbol(ch);
        } else {
            StringItem current = head;
            while (current.next != null) {
                current = current.next;
            }
            if (!current.addSymbol(ch)) {
                StringItem newItem = new StringItem();
                newItem.addSymbol(ch);
                current.next = newItem;
            }
        }
    }

    // Добавить строку ListString в конец
    public void append(ListString string) {
        if (string == null || string.head == null) {
            return;
        }

        StringItem current = head;
        if (current == null) {
            head = new StringItem();
            current = head;
        }

        while (current.next != null) {
            current = current.next;
        }

        StringItem appendCurrent = string.head;
        while (appendCurrent != null) {
            for (int i = 0; i < appendCurrent.size; i++) {
                if (!current.addSymbol(appendCurrent.getSymbol(i))) {
                    current.next = new StringItem();
                    current = current.next;
                    current.addSymbol(appendCurrent.getSymbol(i));
                }
            }
            appendCurrent = appendCurrent.next;
        }
    }

    // Добавить строку String в конец
    public void append(String string) {
        if (string == null) {
            return;
        }

        for (char ch : string.toCharArray()) {
            append(ch);
        }
    }

    // Вставить строку ListString в позицию index
    public void insert(int index, ListString string) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Invalid index for insertion");
        }

        ListString tempString = substring(0, index);
        tempString.append(string);
        tempString.append(substring(index, length()));
        this.head = tempString.head;
    }

    // Вставить строку String в позицию index
    public void insert(int index, String string) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException("Invalid index for insertion");
        }

        ListString tempString = substring(0, index);
        tempString.append(string);
        tempString.append(substring(index, length()));
        this.head = tempString.head;
    }

    // Преобразование ListString в строку
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringItem current = head;
        while (current != null) {
            for (int i = 0; i < current.size; i++) {
                sb.append(current.getSymbol(i));
            }
            current = current.next;
        }
        return sb.toString();
    }

    // Метод для удвоения каждого слова в строке
    public void doublingWords() {
        ListString result = new ListString();
        boolean insideWord = false;
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < length(); i++) {
            char ch = charAt(i);

            if (Character.isLetter(ch)) {
                insideWord = true;
                currentWord.append(ch);
            } else {
                if (insideWord) {
                    // Добавляем слово дважды
                    result.append(currentWord.toString());
                    result.append(currentWord.toString());
                    currentWord.setLength(0); // Очищаем текущее слово
                    insideWord = false;
                }
                result.append(ch); // Добавляем разделитель (пробел или пунктуацию)
            }

            System.out.print(ch);
        }

        // Если строка заканчивается словом
        if (insideWord) {
            result.append(currentWord.toString());
            result.append(currentWord.toString());
        }

        this.head = result.head; // Обновляем текущую строку результатом
    }
}
