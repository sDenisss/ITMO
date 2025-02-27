package sdacha;

import java.util.Scanner;
import java.util.Random;

/* 
 * Дан целочисленный двумерный массив размера М×N, заполненный построчно случайными значениями в диапазоне от -99 до 99. 
 * Этот массив вывести на экран. Заменить исходный массив новым, в котором продублирован (вставлен рядом) столбец, 
 * содержащий максимальный элемент исходного массива. Если таких столбцов несколько, то нужно продублировать последний из них. 
 * Получившийся массив вывести на экран. М и И задаются в main().
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для ввода данных с клавиатуры
        System.out.print("M: "); // Приглашение на ввод количества строк массива
        int M = scanner.nextInt(); // Считываем введенное пользователем число строк

        System.out.print("N: "); // Приглашение на ввод количества столбцов массива
        int N = scanner.nextInt(); // Считываем введенное пользователем число столбцов

        int[][] array = firstMas(M, N); // Создаем массив и заполняем его случайными значениями
        printArray(array); // Выводим на экран исходный массив

        int[][] newArray = secondMas(array); // Создаем новый массив с дублированным столбцом
        printArray(newArray); // Выводим на экран новый массив
    }

    /**
     * Метод для генерации двумерного массива со случайными значениями от -99 до 99.
     * 
     * @param row Количество строк в массиве.
     * @param col Количество столбцов в массиве.
     * @return Сгенерированный массив.
     */
    public static int[][] firstMas(int row, int col) {
        int[][] array = new int[row][col]; // Создаем двумерный массив
        Random random = new Random(); // Создаем объект Random для генерации случайных чисел

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                array[i][j] = random.nextInt(-99, 100); // Заполняем массив случайными значениями от -99 до 99
            }
        }
        return array; // Возвращаем сгенерированный массив
    }

    /**
     * Метод для дублирования столбца с максимальным элементом.
     * 
     * @param array Исходный массив.
     * @return Новый массив с дублированным столбцом.
     */
    public static int[][] secondMas(int[][] array) {
        int maxCol = findMaxCol(array); // Находим номер столбца с максимальным элементом
        int row = array.length; // Получаем количество строк исходного массива
        int col = array[0].length; // Получаем количество столбцов исходного массива

        int[][] newArray = new int[row][col + 1]; // Создаем новый массив с дополнительным столбцом

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                newArray[i][j] = array[i][j]; // Копируем элементы из исходного массива в новый массив
            }
            newArray[i][col] = array[i][maxCol]; // Дублируем столбец с максимальным элементом в новом массиве
        }
        return newArray; // Возвращаем новый массив
    }

    /**
     * Метод для поиска столбца с максимальным элементом.
     * 
     * @param array Исходный массив.
     * @return Номер столбца с максимальным элементом.
     */
    public static int findMaxCol(int[][] array) {
        int maxCol = 0; // Переменная для хранения номера столбца с максимальным элементом
        int max = -99; // Переменная для хранения максимального значения

        for (int j = 0; j < array[0].length; j++) {
            int currentMax = array[0][j]; // Инициализируем текущий максимум значением первого элемента столбца
            for (int i = 1; i < array.length; i++) {
                if (array[i][j] > currentMax) {
                    currentMax = array[i][j]; // Обновляем текущий максимум, если найдено большее значение
                }
            }
            if (currentMax >= max) {
                max = currentMax;
                maxCol = j; // Запоминаем номер столбца с максимальным элементом
            }
        }
        return maxCol; // Возвращаем номер столбца с максимальным элементом
    }

    /**
     * Метод для вывода массива на экран.
     * 
     * @param array Массив, который нужно вывести.
     */
    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int num : row) {
                System.out.printf("%4d", num); // Выводим элемент массива с выравниванием по ширине
            }
            System.out.println(); // Переходим на новую строку после вывода всех элементов строки
        }
        System.out.println(); // Печатаем дополнительную пустую строку для улучшения читаемости
    }
}
