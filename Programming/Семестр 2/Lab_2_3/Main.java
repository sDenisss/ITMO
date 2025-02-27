public class Main {
    public static void main(String[] args) {
        // Создаём массивы для элементов списков и объединённого списка
        ListElement[] l1 = new ListElement[5];
        ListElement[] l2 = new ListElement[5];
        ListElement[] mergedElem = new ListElement[10];

        // Создаём два списка и один объединённый список
        List<ListElement> list1 = new List<>(5, l1);
        List<ListElement> list2 = new List<>(5, l2);
        List<ListElement> mergedList = new List<>(10, mergedElem);

        System.out.println("--List 1--");
        // Вставляем элементы в первый список
        list1.insert(new ListElement("apple", 2.0, 2.0), list1.end(list1), list1);
        list1.insert(new ListElement("banana", 4.0, 4.0), list1.end(list1), list1);
        list1.insert(new ListElement("cherry", 6.0, 6.0), list1.end(list1), list1);
        list1.insert(new ListElement("melon", 7.0, 7.0), list1.end(list1), list1);

        // Вызываем элемент
        // list1.retrieve(3, list1);

        // Удаляем элемент из первого списка
        // list1.delete(1, list1);

        System.out.println("--List 2--");
        System.out.println("---");
        // Вставляем элементы во второй список
        list2.insert(new ListElement("apricot", 1.0, 1.0), list2.first(list2), list2);
        list2.insert(new ListElement("blueberry", 3.0, 3.0), list2.first(list2), list2);
        list2.insert(new ListElement("coconut", 5.0, 5.0), list2.first(list2), list2);

        // list2.makenull(list2);

        System.out.println("--Merged List--");
        System.out.println("---");
        // Объединяем два списка в один
        joinLists(list1, list2, mergedList);

        // Выводим содержимое всех списков
        System.out.println("List 1:");
        list1.printlist(list1);

        System.out.println("List 2:");
        list2.printlist(list2);

        System.out.println("Merged List:");
        mergedList.printlist(mergedList);
    }

    // Метод для объединения двух списков
    public static <T extends Comparable<T>> void joinLists(List<T> list1, List<T> list2, List<T> mergedList) {
        int index1 = list1.start; // Начало списка list1
        int index2 = list2.start; // Начало списка list2
        int mergedIndex = 0; // Индекс для объединённого списка

        // Пока оба списка имеют элементы
        while (index1 != -1 && index2 != -1) {
            // Сравниваем элементы по res и вставляем в объединённый список
            if (list1.retrieve(index1, list1).compareTo(list2.retrieve(index2, list2)) <= 0) {
                mergedList.insert(list1.retrieve(index1, list1), mergedIndex, mergedList); // Вставляем элемент из list1
                index1 = list1.pos[index1].getP(); // Переходим к следующему элементу в list1
            } else {
                mergedList.insert(list2.retrieve(index2, list2), mergedIndex, mergedList); // Вставляем элемент из list2
                index2 = list2.pos[index2].getP(); // Переходим к следующему элементу в list2
            }
            mergedIndex++;
        }

        // Если остались элементы в list1, добавляем их в объединённый список
        while (index1 != -1) {
            mergedList.insert(list1.retrieve(index1, list1), mergedIndex, mergedList);
            index1 = list1.pos[index1].getP();
            mergedIndex++;
        }

        // Если остались элементы в list2, добавляем их в объединённый список
        while (index2 != -1) {
            mergedList.insert(list2.retrieve(index2, list2), mergedIndex, mergedList);
            index2 = list2.pos[index2].getP();
            mergedIndex++;
        }

        // Выводим результат объединения
        mergedList.printlist(mergedList);
    }

}
