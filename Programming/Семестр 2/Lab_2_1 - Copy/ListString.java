public class ListString {

    // Внутренний класс StringItem для хранения блоков символов
    private class StringItem {
        private final static int SIZE = 16; // размер символьного массива в блоке
        private char[] symbols; // массив символов
        private StringItem next; // ссылка на следующий блок
        private byte size; // количество символов в блоке

        // Конструктор по умолчанию: инициализирует пустой блок символов
        private StringItem() {
            symbols = new char[SIZE]; // выделение памяти для символов
            next = null; // ссылка на следующий блок отсутствует
            size = 0; // размер блока 0, так как он пустой
        }

        // Конструктор с параметром String: копирует подстроку в блок
        private StringItem(String str, int start, int length) {
            symbols = new char[SIZE]; // выделение памяти для символов
            size = 0; // начальный размер 0
            // Копирование символов строки в блок
            for (int i = 0; i < length && i < SIZE; i++) {
                symbols[i] = str.charAt(start + i);
                size++; // увеличение размера блока
            }
            next = null; // следующий блок отсутствует
        }
    }

    private StringItem head; // ссылка на первый блок

    // Конструктор по умолчанию: создает пустую строку
    public ListString() {
        head = null; // указатель на первый блок равен null
    }

    // Конструктор с параметром String: инициализирует строку, разбивая её на блоки
    public ListString(String str) {
        if (str == null || str.isEmpty()) {
            head = null; // если строка пустая, создаем пустую структуру
        } else {
            head = new StringItem(); // создаем первый блок
            StringItem current = head; // текущий блок
            int index = 0; // текущий индекс символа
            while (index < str.length()) {
                current.size = 0; // обнуляем размер блока
                // Заполняем текущий блок символами
                while (current.size < StringItem.SIZE && index < str.length()) {
                    current.symbols[current.size++] = str.charAt(index++);
                }
                // Создаем новый блок, если в текущем больше нет места
                if (index < str.length()) {
                    current.next = new StringItem();
                    current = current.next;
                }
            }
        }
    }

    // Метод возвращает длину строки, складывая размеры всех блоков
    public int length() {
        int length = 0; // текущая длина строки
        StringItem current = head; // текущий блок
        while (current != null) {
            length += current.size; // добавляем размер текущего блока
            current = current.next; // переходим к следующему блоку
        }
        return length; // возвращаем общую длину строки
    }

    // Метод возвращает символ по индексу в строке
    public char charAt(int index) {
        StringItem current = head; // текущий блок
        // Поиск нужного блока, который содержит символ с данным индексом
        while (current != null && index >= current.size) {
            index -= current.size;
            current = current.next;
        }
        if (current == null) {
            throw new InvalidPositionException("Index out of bounds"); // если индекс вне диапазона
        }
        return current.symbols[index]; // возвращаем символ
    }

    // Метод заменяет символ по индексу на новый
    public void setCharAt(int index, char ch) {
        StringItem current = head; // текущий блок
        // Поиск блока с нужным индексом
        while (current != null && index >= current.size) {
            index -= current.size;
            current = current.next;
        }
        if (current == null) {
            throw new InvalidPositionException("Index out of bounds"); // если индекс вне диапазона
        }
        current.symbols[index] = ch; // замена символа
    }

    // Метод возвращает подстроку от start до end
    public ListString substring(int start, int end) {
        if (start < 0 || end < start) {
            throw new InvalidPositionException("Invalid substring range"); // если диапазон некорректен
        }

        ListString result = new ListString(); // результат - новая строка
        StringItem current = head; // текущий блок
        int currentIndex = 0; // текущий индекс символов в общей строке
        StringItem resultCurrent = null; // указатель на блок результата

        // Поиск и копирование символов в диапазоне
        while (current != null && currentIndex < end) {
            int blockStart = Math.max(0, start - currentIndex); // начало блока для копирования
            int blockEnd = Math.min(current.size, end - currentIndex); // конец блока для копирования

            if (blockStart < blockEnd) {
                StringItem newItem = new StringItem(); // новый блок для результата
                for (int i = blockStart; i < blockEnd; i++) {
                    newItem.symbols[newItem.size++] = current.symbols[i];
                }

                if (resultCurrent == null) {
                    result.head = newItem; // если это первый блок, делаем его головным
                    resultCurrent = result.head;
                } else {
                    resultCurrent.next = newItem; // присоединяем блок к результату
                    resultCurrent = resultCurrent.next;
                }
            }

            currentIndex += current.size; // переходим к следующему блоку
            current = current.next;
        }

        return result; // возвращаем подстроку
    }

    // Метод добавляет символ в конец строки
    public void append(char ch) {
        if (head == null) {
            head = new StringItem(); // создаем первый блок, если строка пустая
            head.symbols[0] = ch; // записываем символ
            head.size = 1;
        } else {
            StringItem current = head;
            while (current.next != null) {
                current = current.next; // поиск последнего блока
            }
            if (current.size < StringItem.SIZE) {
                current.symbols[current.size++] = ch; // добавляем символ в блок
            } else {
                current.next = new StringItem(); // создаем новый блок, если в текущем нет места
                current.next.symbols[0] = ch;
                current.next.size = 1;
            }
        }
    }

    // Метод добавляет строку (объект ListString) в конец
    public void append(ListString string) {
        if (string == null || string.head == null) {
            return; // ничего не делаем, если строка пустая
        }

        StringItem stringCopyHead = copyItems(string.head); // копируем строки
        if (head == null) {
            head = stringCopyHead; // если исходная строка пустая, присваиваем копию
        } else {
            StringItem current = head;
            while (current.next != null) {
                current = current.next; // поиск последнего блока
            }
            current.next = stringCopyHead; // добавляем копию к концу
        }
    }

    // Метод вставляет строку (объект ListString) в указанную позицию
    public void insert(int index, ListString string) {
        if (index < 0 || string == null || string.head == null) {
            throw new InvalidPositionException("Invalid index or null string"); // если индекс некорректен или строка
                                                                                // пуста
        }

        // Поиск нужного блока для вставки
        StringItem current = head;
        int curIndex = 0;

        while (current != null && index >= curIndex + current.size) {
            curIndex += current.size;
            current = current.next;
        }

        // Создаем копию вставляемой строки
        StringItem stringCopyHead = copyItems(string.head);

        if (current == null) {
            append(string); // если дошли до конца, добавляем строку в конец
        } else {
            // Вставляем строку в середину
            int localIndex = index - curIndex;
            StringItem nextItems = new StringItem();
            for (int i = localIndex; i < current.size; i++) {
                nextItems.symbols[nextItems.size++] = current.symbols[i]; // копируем оставшиеся символы в новый блок
            }
            nextItems.next = current.next;
            current.size = (byte) localIndex; // обновляем размер текущего блока
            current.next = stringCopyHead;

            // Поиск последнего блока вставленной строки
            StringItem temp = stringCopyHead;
            while (temp.next != null) {
                temp = temp.next;
            }

            // Присоединение оставшихся блоков
            temp.next = nextItems;
        }
    }

    // Метод дублирует все слова в строке
    public void doublingWords() {
        ListString newString = new ListString(); // новая строка
        StringBuilder word = new StringBuilder(); // текущий обрабатываемый символ

        StringItem current = head;
        while (current != null) {
            for (int i = 0; i < current.size; i++) {
                char ch = current.symbols[i];
                if (ch == ' ') { // если конец слова
                    ListString listWord = new ListString(word.toString());
                    newString.append(listWord); // добавляем слово
                    newString.append(listWord); // дублируем слово
                    newString.append(' '); // добавляем пробел
                    word.setLength(0); // сбрасываем текущую строку
                } else {
                    word.append(ch); // добавляем символ к слову
                }
            }
            current = current.next; // переходим к следующему блоку
        }

        // Добавляем последнее слово, если оно не пустое
        if (word.length() > 0) {
            ListString listWord = new ListString(word.toString());
            newString.append(listWord);
            newString.append(listWord);
        }

        this.head = newString.head; // заменяем текущую строку на новую
    }

    // Метод создает копию цепочки блоков
    private StringItem copyItems(StringItem head) {
        if (head == null) {
            return null; // если цепочка пуста, возвращаем null
        }

        StringItem newHead = new StringItem(); // создаем первый блок копии
        StringItem current = newHead; // текущий блок копии
        StringItem original = head; // текущий блок оригинала

        // Копируем символы и ссылки на следующий блок
        while (original != null) {
            current.size = original.size;
            for (int i = 0; i < original.size; i++) {
                current.symbols[i] = original.symbols[i];
            }
            if (original.next != null) {
                current.next = new StringItem(); // создаем новый блок в копии
                current = current.next; // переходим к следующему блоку
            }
            original = original.next; // переходим к следующему блоку оригинала
        }

        return newHead; // возвращаем голову копии
    }

    // Метод возвращает строковое представление всей строки
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(); // строковый буфер для вывода
        StringItem current = head; // текущий блок
        while (current != null) {
            for (int i = 0; i < current.size; i++) {
                builder.append(current.symbols[i]); // добавляем символ в буфер
            }
            current = current.next; // переходим к следующему блоку
        }
        return builder.toString(); // возвращаем строку
    }
}