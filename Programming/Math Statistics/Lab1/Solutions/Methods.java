import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Methods {
    static double[] sheduleArrX; // Массив для хранения значений X графика
    static double[] sheduleArrY; // Массив для хранения значений Y графика

    // Метод для нахождения интервального статистического ряда
    public static void findIntervalStatisticalRange(double[] numbers, int n) {
        Arrays.sort(numbers); // Сортируем массив
        // Вычисляем ширину интервала h по формуле Стерджеса
        double h = Math.round((numbers[n - 1] - numbers[0]) / (1 + Math.log(n) / Math.log(2)) * 10) / 10.0;

        double start = numbers[0] - h / 2; // Начальная граница первого интервала
        double finish = start + h; // Конечная граница первого интервала

        List<Double> centers = new ArrayList<>(); // Список центров интервалов
        List<Double> frequencies = new ArrayList<>(); // Список частот

        int num = 0; // Счётчик частоты
        int total = numbers.length; // Общее количество элементов
        int currentIndex = 0; // Индекс текущего элемента массива

        List<Double> sheduleX = new ArrayList<>(); // Список значений X для графика
        List<Double> sheduleY = new ArrayList<>(); // Список значений Y для графика

        // Формируем интервалы
        while (start < numbers[numbers.length - 1]) {
            while (currentIndex < numbers.length && numbers[currentIndex] < finish) {
                num++;
                currentIndex++;
            }

            double center = (start + finish) / 2; // Вычисляем центр интервала
            double frequency = (double) num / total; // Вычисляем относительную частоту

            centers.add(center);
            frequencies.add(frequency);

            sheduleX.add(start); // Границы интервалов
            sheduleX.add(finish);
            sheduleY.add(frequency);

            // Выводим данные в консоль
            System.out.printf("[%.2f, %.2f): частота: %d, частотность: %.2f%n", start, finish, num, frequency);

            start = finish; // Сдвигаем интервал
            finish = start + h;
            num = 0;
        }

        // Заполняем массивы для передачи в класс Histogram
        sheduleArrX = sheduleX.stream().mapToDouble(Double::doubleValue).toArray();
        sheduleArrY = sheduleY.stream().mapToDouble(Double::doubleValue).toArray();

        setHisPolArrX(sheduleArrX);
        setHisPolArrY(sheduleArrY);
    }

    // Метод для расчета статистических характеристик выборки
    public static void statisticalCaracteristics(double[] numbers) {
        System.out.println("Исходные данные:");
        for (double d : numbers) {
            System.out.print(d + " ");
        }

        Arrays.sort(numbers); // Сортируем выборку

        System.out.println("\nВариационный ряд:");
        for (double number : numbers) {
            System.out.print(number + " ");
        }

        // Экстремальные значения и размах выборки
        System.out.println("\nПервая порядковая статистика: " + numbers[0]); // Минимальное значение
        System.out.println("Последняя порядковая статистика: " + numbers[numbers.length - 1]); // Максимальное значение
        double razmah = numbers[numbers.length - 1] - numbers[0]; // Размах выборки
        System.out.println("Размах выборки: " + razmah);

        // Вычисление математического ожидания
        double summ = 0;
        for (double d : numbers) {
            summ += d;
        }
        double mathWait = (1 / (double) numbers.length) * summ;
        System.out.print("Математическое ожидание: ");
        System.out.printf("%.5f", mathWait);

        // Вычисление среднеквадратичного отклонения
        double summ2 = 0;
        for (int i = 0; i < numbers.length; i++) {
            summ2 += Math.pow(numbers[i] - mathWait, 2);
        }
        double standard_deviation = Math.sqrt((1 / (double) numbers.length) * (summ2));
        System.out.print("\nСреднеквадратичное отклонение: ");
        System.out.printf("%.5f", standard_deviation);

        // Исправленное выборочное среднеквадратичное отклонение
        double summ3 = 0;
        for (int i = 0; i < numbers.length; i++) {
            summ3 += Math.pow(numbers[i] - mathWait, 2);
        }
        double fixed_choice_deviation = Math.sqrt((1 / (double) (numbers.length - 1)) * (summ3));
        System.out.print("\nИсправленное выборочное среднеквадратичное отклонение: ");
        System.out.printf("%.5f", fixed_choice_deviation);

        // Вычисление эмпирической функции распределения (ЭФР) это функция, которая
        // отображает вероятность того,
        // что случайная величина примет значение, меньшее или равное какому-либо
        // заданному значению x
        // Инициализация переменных для вычислений ЭФР
        double summ4 = 0; // Переменная для накопления суммы частот
        double roundedNumber = 0; // Переменная для хранения округлённого значения ЭФР
        double[] roundedNumbers = new double[numbers.length]; // Массив для хранения значений ЭФР
        double frequency = 1; // Переменная для частоты текущего элемента (по умолчанию 1)
        int newLenghth = 0; // Переменная для отслеживания длины нового массива ЭФР

        System.out.println("\nЭмпирическая функция распределения (F):");
        // Выводим начальное значение ЭФР для самого первого значения выборки
        System.out.printf("Для x <= %.2f: 0,00%n", numbers[0]);

        // Цикл для вычисления ЭФР по каждому элементу выборки
        for (int i = 1; i < numbers.length;) {
            // Если текущий элемент равен предыдущему, то увеличиваем частоту
            if (numbers[i - 1] == numbers[i]) {
                frequency = 2; // Частота увеличивается на 2 для одинаковых элементов
                summ4 += (frequency / numbers.length); // Добавляем долю частоты к сумме
                // Округляем значение ЭФР до сотых и выводим на экран
                roundedNumber = Math.round(summ4 * 100.0) / 100.0;
                System.out.printf("Для %.2f < x <= %.2f: %.2f%n", numbers[i - 1], numbers[i + 1], roundedNumber);
                newLenghth += 2; // Увеличиваем длину нового массива ЭФР на 2, так как были два одинаковых
                                 // элемента
                i += 2; // Пропускаем следующий элемент, так как он уже учтён
            } else {
                // Если текущий элемент отличается от предыдущего, то частота равна 1
                frequency = 1;
                summ4 += (frequency / numbers.length); // Добавляем частоту текущего элемента к сумме
                // Округляем значение ЭФР до сотых и выводим на экран
                roundedNumber = Math.round(summ4 * 100.0) / 100.0;
                System.out.printf("Для %.2f < x <= %.2f: %.2f%n", numbers[i - 1], numbers[i], roundedNumber);
                newLenghth++; // Увеличиваем длину нового массива ЭФР на 1 для каждого уникального элемента
                i++; // Переходим к следующему элементу
            }
        }
        // Выводим для последнего значения выборки, что оно всегда имеет ЭФР равную 1
        System.out.println("Для " + numbers[numbers.length - 1] + " < x: " + "1,00");

        // Заполнение массива roundedNumbers значениями ЭФР для графика
        roundedNumbers[0] = 0.0; // Начинаем с 0 для самого первого значения ЭФР
        // В цикле заполняем массив значениями ЭФР, увеличивая их на 0.05 для каждого
        // шага
        for (int i = 1; i < roundedNumbers.length; i++) {
            roundedNumbers[i] = roundedNumbers[i - 1] + 0.05; // Увеличиваем ЭФР на 0.05
        }
        // Последнее значение ЭФР всегда равно 1
        roundedNumbers[numbers.length - 1] = 1.0;

        // Инициализируем массивы для значений X и Y для построения графиков
        sheduleArrX = new double[newLenghth]; // Массив для X координат графика (границы интервалов)
        sheduleArrY = new double[newLenghth]; // Массив для Y координат графика (значения ЭФР)

        // Заполняем массивы значениями для построения графика функции распределения
        setSheduleArrX(numbers); // Заполняем массив X значениями интервалов
        setSheduleArrY(roundedNumbers); // Заполняем массив Y значениями ЭФР

    }

    // Геттеры и сеттеры для массивов значений графиков
    public static double[] getSheduleArrX() {
        return sheduleArrX;
    }

    public static double[] getSheduleArrY() {
        return sheduleArrY;
    }

    public static void setHisPolArrX(double[] sheduleArrX) {
        Methods.sheduleArrX = sheduleArrX;
    }

    public static void setHisPolArrY(double[] sheduleArrY) {
        Methods.sheduleArrY = sheduleArrY;
    }

    public static void setSheduleArrX(double[] sheduleArrX) {
        Methods.sheduleArrX = sheduleArrX;
    }

    public static void setSheduleArrY(double[] sheduleArrY) {
        Methods.sheduleArrY = sheduleArrY;
    }
}
