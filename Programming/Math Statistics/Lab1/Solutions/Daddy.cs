namespace Lab1.Solutions
{
    public class Daddy
    {
         public static void MaxMinAndDifference(int[] numbers, int n) {
            int min = numbers[0];
            int max = numbers[n-1];
            int differenceMaxMin = max - min;

            System.Console.WriteLine($"Минимальное значение: {min}");
            System.Console.WriteLine($"Максимальное значение: {max}");
            System.Console.WriteLine($"Размах: {differenceMaxMin}");
        }

        public static void SortAndInput(int[] numbers, int n) {
            Console.WriteLine("Исходные данные: ");
            for (int i = 0; i < n; i++)
            {
                Console.Write(numbers[i] + " ");
            }

            Array.Sort(numbers); // Сортируем массив

            Console.WriteLine("\nВариационный ряд:");
            for (int i = 0; i < n; i++)
            {
                Console.Write(numbers[i] + " ");
            }
        }
    }
}