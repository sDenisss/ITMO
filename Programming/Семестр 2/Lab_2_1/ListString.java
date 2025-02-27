public class ListString {

    // Внутренний класс StringItem
    private class StringItem {
        private final static int SIZE = 16; // размер символьного массива в блоке
        private char[] symbols; // массив символов
        private StringItem next; // ссылка на следующий блок
        private byte size; // количество символов в блоке

        // Конструктор по умолчанию инициализирует массив symbols и устанавливает next в
        // null, размер символов в блоке — 0.
        private StringItem() {
            symbols = new char[SIZE];
            next = null;
            size = 0;
        }

        // Конструктор с параметром позволяет заполнить блок символами из строки,
        // начиная с позиции start и копируя до length символов (или пока блок не
        // заполнится до SIZE).
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

    private StringItem head; // ссылка на первый блок строки. Этот элемент будет указывать на первый блок
                             // (или часть строки).

    // Конструктор по умолчанию: создает пустую строку, где head будет указывать на
    // null.
    public ListString() {
        head = null; // пустая строка
    }

    // Конструктор с параметром `String`: создаёт блоки для каждого куска строки.
    public ListString(String str) {
        // Если строка пуста или null, head будет null.
        if (str == null || str.isEmpty()) {
            head = null;
            /*
             * Если строка не пуста, создаются блоки StringItem и заполняются символами
             * строки.
             * Каждый блок может хранить до 16 символов, а если строка длиннее,
             * то создается следующий блок, который ссылается на предыдущий через поле next.
             */
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

    // Возвращает длину строки, суммируя размеры всех блоков StringItem.
    public int length() {
        int length = 0;
        StringItem current = head;
        while (current != null) {
            length += current.size;
            current = current.next;
        }
        return length;
    }

    // Возвращает символ по указанному индексу. Метод проходит по блокам до тех пор,
    // пока не найдет блок, в котором находится символ с нужным индексом.
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

    // Заменяет символ в позиции index на символ ch. Работает аналогично методу
    // charAt(), только изменяет символ, а не возвращает его.
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

    /*
     * Возвращает подстроку от позиции start до end (не включая end).
     * Метод создает новый объект ListString, копируя символы из исходной строки в
     * новый объект,
     * начиная с start и до end. В результате получается новая строка,
     * представленная блоками StringItem.
     */
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

    // Добавляет символ в конец строки.
    public void append(char ch) {
        // Если последний блок не заполнен (его размер меньше 16), символ добавляется в
        // этот блок.
        if (head == null) {
            head = new StringItem();
            head.symbols[0] = ch;
            head.size = 1;
            // Если блок заполнен, создается новый блок, и символ добавляется в него.
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

    // Добавляет к текущей строке другую строку (ListString).
    // Копирует блоки из переданной строки и присоединяет их к последнему блоку
    // текущей строки.
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

    // Добавляет к текущей строке строку типа String, преобразуя её в объект
    // ListString.
    public void append(String string) {
        if (string == null || string.isEmpty()) {
            return;
        }
        append(new ListString(string));
    }

    // Вставляет строку ListString в позицию index в текущей строке. Если строка не
    // пустая и индекс правильный:
    public void insert(int index, ListString string) {
        // Проверка корректности индекса и наличия вставляемой строки
        if (index < 0 || string == null || string.head == null) {
            throw new InvalidPositionException("Invalid index or null string");
        }

        // Шаг 1: Найти позицию для вставки
        StringItem current = head; // Начинаем с первого блока текущей строки
        // StringItem prev = null; // Закомментировано: переменная для хранения
        // предыдущего блока не используется
        int curIndex = 0; // Хранит текущий индекс относительно всех блоков

        // Поиск нужного блока, пока не найдём блок, где будет вставка или не дойдём до
        // конца
        while (current != null && index >= curIndex + current.size) {
            curIndex += current.size; // Обновляем текущий индекс
            // prev = current; // Закомментировано: запоминание предыдущего блока
            current = current.next; // Переход к следующему блоку
        }

        // Шаг 2: Создать копию вставляемой строки
        StringItem stringCopyHead = copyItems(string.head); // Создаем копию всех блоков вставляемой строки

        // Шаг 3: Разбить текущий блок на два, если необходимо
        if (current != null && index > curIndex) { // Если текущий блок найден и индекс внутри его
            StringItem newBlock = new StringItem(); // Создаем новый блок для хранения оставшихся символов текущего
                                                    // блока
            int copyIndex = index - curIndex; // Находим позицию, где нужно разбить блок

            // Переносим символы из текущего блока в новый, начиная с позиции index
            for (int i = copyIndex; i < current.size; i++) {
                newBlock.symbols[newBlock.size++] = current.symbols[i]; // Копируем символы в новый блок
            }

            // Обновляем текущий блок, уменьшая его размер до позиции вставки
            current.size = (byte) copyIndex;
            newBlock.next = current.next; // Присоединяем новый блок к остальной цепочке
            current.next = newBlock; // Связываем текущий блок с новым
            // current = newBlock; // Закомментировано: переменная `current` больше не
            // используется для перехода к новому блоку
        }

        StringItem newBlock = current.next; // Запоминаем, что было после текущего блока
        current.next = stringCopyHead; // Присоединяем копию вставляемой строки к текущему блоку

        // Найти хвост вставленной строки и присоединить к нему оставшуюся часть
        // исходной строки
        StringItem tail = getTail(stringCopyHead); // Находим последний блок вставленной строки
        tail.next = newBlock; // Присоединяем остаток исходной строки к хвосту вставленной строки
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

    /*
     * Дублирует все слова в строке. Сначала определяется каждое слово (подстрока,
     * разделенная пробелами),
     * затем это слово дублируется. Пробелы остаются между словами.
     */
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
