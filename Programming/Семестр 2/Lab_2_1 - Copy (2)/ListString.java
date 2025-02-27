public class ListString {

    // Внутренний класс StringItem
    private class StringItem {
        private final static int SIZE = 16; // размер символьного массива в блоке
        private char[] symbols; // массив символов
        private StringItem next; // ссылка на следующий блок
        private byte size; // количество символов в блоке

        // Конструктор по умолчанию
        private StringItem() {
            symbols = new char[SIZE];
            next = null;
            size = 0;
        }

        // Конструктор с параметром String (часть строки)
        private StringItem(String str, int start, int length) {
            symbols = new char[SIZE];
            size = 0;
            for (int i = 0; i < length && i < SIZE; i++) {
                symbols[i] = str.charAt(start + i);
                size++;
            }
            next = null;
        }
    }

    private StringItem head; // ссылка на первый блок

    // Конструктор по умолчанию
    public ListString() {
        head = null; // пустая строка
    }

    // Конструктор с параметром String
    public ListString(String str) {
        if (str == null || str.isEmpty()) {
            head = null;
        } else {
            head = new StringItem();
            StringItem current = head;
            int index = 0;
            while (index < str.length()) {
                current.size = 0;
                while (current.size < StringItem.SIZE && index < str.length()) {
                    current.symbols[current.size++] = str.charAt(index++);
                }
                if (index < str.length()) {
                    current.next = new StringItem();
                    current = current.next;
                }
            }
        }
    }

    // Вернуть реальную длину строки
    public int length() {
        int length = 0;
        StringItem current = head;
        while (current != null) {
            length += current.size;
            current = current.next;
        }
        return length;
    }

    // Вернуть символ в строке в позиции index
    public char charAt(int index) {
        StringItem current = head;
        while (current != null && index >= current.size) {
            index -= current.size;
            current = current.next;
        }
        if (current == null) {
            throw new InvalidPositionException("Index out of bounds");
        }
        return current.symbols[index];
    }

    // Заменить в строке символ в позиции index на символ ch
    public void setCharAt(int index, char ch) {
        StringItem current = head;
        while (current != null && index >= current.size) {
            index -= current.size;
            current = current.next;
        }
        if (current == null) {
            throw new InvalidPositionException("Index out of bounds");
        }
        current.symbols[index] = ch;
    }

    // Взятие подстроки от start до end, не включая end
    public ListString substring(int start, int end) {
        if (start < 0 || end < start) {
            throw new InvalidPositionException("Invalid substring range");
        }

        ListString ListString = new ListString();
        StringItem current = head;
        int currentIndex = 0;
        StringItem resultCurrent = null;

        while (current != null && currentIndex < end) {
            int blockStart = Math.max(0, start - currentIndex);
            int blockEnd = Math.min(current.size, end - currentIndex);

            if (blockStart < blockEnd) {
                StringItem newItem = new StringItem();
                for (int i = blockStart; i < blockEnd; i++) {
                    newItem.symbols[newItem.size++] = current.symbols[i];
                }

                if (resultCurrent == null) {
                    ListString.head = newItem;
                    resultCurrent = ListString.head;
                } else {
                    resultCurrent.next = newItem;
                    resultCurrent = resultCurrent.next;
                }
            }

            currentIndex += current.size;
            current = current.next;
        }

        return ListString;
    }

    // Добавить в конец строки символ
    public void append(char ch) {
        if (head == null) {
            head = new StringItem();
            head.symbols[0] = ch;
            head.size = 1;
        } else {
            StringItem current = head;
            while (current.next != null) {
                current = current.next;
            }
            if (current.size < StringItem.SIZE) {
                current.symbols[current.size++] = ch;
            } else {
                current.next = new StringItem();
                current.next.symbols[0] = ch;
                current.next.size = 1;
            }
        }
    }

    // Добавить в конец строки объект ListString
    public void append(ListString string) {
        if (string == null || string.head == null) {
            return;
        }

        StringItem stringCopyHead = copyItems(string.head);
        if (head == null) {
            head = stringCopyHead;
        } else {
            StringItem current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = stringCopyHead;
        }
    }

    // Добавить в конец строки объект String
    public void append(String string) {
        if (string == null || string.isEmpty()) {
            return;
        }
        append(new ListString(string));
    }

    // Вставить в строку в позицию index объект ListString
    public void insert(int index, ListString string) {
        if (index < 0 || string == null || string.head == null) {
            throw new InvalidPositionException("Invalid index or null string");
        }

        // Шаг 1: Найти позицию для вставки
        StringItem current = head;
        // StringItem prev = null;
        int curIndex = 0;

        while (current != null && index >= curIndex + current.size) {
            curIndex += current.size;
            // prev = current;
            current = current.next;
        }

        // Шаг 2: Создать копию вставляемой строки
        StringItem stringCopyHead = copyItems(string.head);

        // Шаг 3: Разбить текущий блок на два, если необходимо
        if (current != null && index > curIndex) {
            StringItem newBlock = new StringItem();
            int copyIndex = index - curIndex;

            // Перенос оставшихся символов текущего блока в новый блок
            for (int i = copyIndex; i < current.size; i++) {
                newBlock.symbols[newBlock.size++] = current.symbols[i];
            }

            // Обновление текущего блока
            current.size = (byte) copyIndex;
            newBlock.next = current.next;
            current.next = newBlock;
            // current = newBlock;
        }

        // Шаг 4: Присоединить вставляемую строку
        // if (prev != null) {
        // prev.next = stringCopyHead;
        // } else {
        // head = stringCopyHead;
        // }

        StringItem newBlock = current.next;
        current.next = stringCopyHead;

        StringItem tail = getTail(stringCopyHead);
        tail.next = newBlock;
    }

    // Вспомогательный метод для копирования элементов StringItem
    private StringItem copyItems(StringItem startItem) {
        if (startItem == null) {
            return null;
        }

        StringItem copyHead = new StringItem();
        StringItem currentCopy = copyHead;
        StringItem currentOriginal = startItem;

        while (currentOriginal != null) {
            currentCopy.size = currentOriginal.size;
            for (int i = 0; i < currentOriginal.size; i++) {
                currentCopy.symbols[i] = currentOriginal.symbols[i];
            }
            if (currentOriginal.next != null) {
                currentCopy.next = new StringItem();
                currentCopy = currentCopy.next;
            }
            currentOriginal = currentOriginal.next;
        }

        return copyHead;
    }

    // Вставить в строку в позицию index объект String
    public void insert(int index, String string) {
        insert(index, new ListString(string));
    }

    // Дублирование всех слов в строке
    // Дублирование всех слов в строке
    public void doublingWords() {
        if (head == null) {
            return;
        }

        ListString doubledList = new ListString(); // Новый объект для дублированных слов
        StringItem current = head;

        while (current != null) {
            int start = 0;

            while (start < current.size) {
                int end = start;

                // Найти конец текущего слова
                while (end < current.size && current.symbols[end] != ' ') {
                    end++;
                }

                // Добавить слово и его дубликат в новый ListString
                if (start < end) {
                    StringBuilder word = new StringBuilder();
                    for (int i = start; i < end; i++) {
                        word.append(current.symbols[i]);
                    }
                    String wordStr = word.toString();
                    doubledList.append(wordStr); // Добавляем слово
                    doubledList.append(wordStr); // Добавляем дубликат слова
                }

                // Добавить пробел, если он есть
                if (end < current.size && current.symbols[end] == ' ') {
                    doubledList.append(' ');
                }

                // Перейти к следующему слову
                start = end + 1;
            }
            current = current.next;
        }
        // Перезаписываем текущий объект на новый, содержащий дублированные слова
        this.head = doubledList.head;
    }

    // Строковое представление объекта ListString
    @Override
    public String toString() {
        if (head == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        StringItem current = head;
        while (current != null) {
            for (int i = 0; i < current.size; i++) {
                sb.append(current.symbols[i]);
            }
            // sb.append("|");
            current = current.next;
        }
        return sb.toString();
    }

    // Вспомогательный метод для получения последнего элемента в цепочке StringItem
    private StringItem getTail(StringItem item) {
        while (item.next != null) {
            item = item.next;
        }
        return item;
    }
}
