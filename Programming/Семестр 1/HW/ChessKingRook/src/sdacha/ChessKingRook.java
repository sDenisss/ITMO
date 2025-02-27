package sdacha;
import java.util.Random;
import java.util.Scanner;

/*
 * Дан целочисленный двумерный массив размера M×N, заполненный построчно случайными значениями в диапазоне от -99 до 99. 
 * Этот массив вывести на экран. Заменить исходный массив новым, в котором продублирован (вставлен рядом) столбец, 
 * содержащий максимальный элемент исходного массива. Если таких столбцов несколько, то нужно продублировать последний из них. 
 * Получившийся массив вывести на экран. М и И задаются в main().
 * */

public class ChessKingRook {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in); // Создание объекта Scanner для ввода данных с клавиатуры
        System.out.print("M: "); // Вывод приглашения на ввод и считывание значения M (количество строк)
        int M = scanner.nextInt(); // Считывание значения M
        System.out.print("N: "); // Вывод приглашения на ввод и считывание значения N (количество столбцов)
        int N = scanner.nextInt(); // Считывание значения N

        int[][] array = firstMas(M, N); // Генерация и вывод первого массива
        printArray("First massive:", array);
        int[][] newArray = secondMas(array); // Генерация и вывод второго массива с дублированным столбцом
        printArray("Second Massive:", newArray);
    }

    /**
     * Метод для генерации двумерного массива со случайными значениями от -99 до 99.
     * @param row Количество строк в массиве.
     * @param col Количество столбцов в массиве.
     * @return Сгенерированный массив.
     */
    public static int[][] firstMas(int row, int col) {
        int[][] array = new int[row][col]; // Создание двумерного массива
        Random random = new Random(); // Создание объекта Random для генерации случайных чисел

        for (int i = 0; i < row; i++) { // Заполнение массива случайными значениями
            for (int j = 0; j < col; j++) {
                array[i][j] = random.nextInt(-99, 99); // Генерация случайного числа от -99 до 99
            }
        }
        return array; // Возврат сгенерированного массива
    }

    /**
     * Метод для дублирования столбца с максимальным элементом.
     * @param array Исходный массив.
     * @return Новый массив с дублированным столбцом.
     */
    public static int[][] secondMas(int[][] array) {
        int maxCol = findMaxCol(array); // Находим номер столбца с максимальным элементом
        int row = array.length; // Получаем количество строк в массиве
        int col = array[0].length; // Получаем количество столбцов в массиве

        int[][] newArray = new int[row][col + 1]; // Создаем новый массив с дополнительным столбцом

        for (int i = 0; i < row; i++) { // Копируем значения из исходного массива в новый и добавляем дублированный столбец
            for (int j = 0; j < col; j++) {
                newArray[i][j] = array[i][j];
            }
            newArray[i][col] = array[i][maxCol];
        }
        return newArray; // Возврат нового массива
    }

    /**
     * Метод для поиска столбца с максимальным элементом.
     * @param array Исходный массив.
     * @return Номер столбца с максимальным элементом.
     */
    public static int findMaxCol(int[][] array) {
        int maxCol = 0; // Инициализация переменной для хранения номера столбца с максимальным элементом
        int max = Integer.MIN_VALUE; // Инициализация переменной для хранения максимального значения

        // Поиск столбца с максимальным элементом
        for (int j = 0; j < array[0].length; j++) {
            int currentMax = array[0][j]; // Инициализация текущего максимума значением первого элемента столбца
            for (int i = 1; i < array.length; i++) {
                if (array[i][j] > currentMax) {
                    currentMax = array[i][j]; // Обновление текущего максимума, если найдено большее значение
                }
            }
            if (currentMax >= max) {
                max = currentMax; // Обновление максимального значения
                maxCol = j; // Обновление номера столбца с максимальным элементом
            }
        }
        return maxCol; // Возврат номера столбца с максимальным элементом
    }

    /**
     * Метод для вывода массива на экран.
     * @param message Сообщение, предшествующее выводу массива.
     * @param array Массив, который нужно вывести.
     */
    public static void printArray(String message, int[][] array) {
        System.out.println(message); // Вывод сообщения перед массивом
        for (int[] row : array) {
            for (int num : row) {
                System.out.printf("%4d", num); // Вывод элементов массива с выравниванием по ширине
            }
            System.out.println(); // Переход на новую строку после каждой строки массива
        }
        System.out.println(); // Пустая строка для отделения вывода первого и второго массивов
    }
}
