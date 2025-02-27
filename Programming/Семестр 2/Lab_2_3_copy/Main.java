public class Main {
    public static void main(String[] args) {
        List<ListElement> list1 = new List<>(5, ListElement.class);
        List<ListElement> list2 = new List<>(5, ListElement.class);
        List<ListElement> mergedList = new List<>(10, ListElement.class);

        System.out.println("--List 1--");
        list1.insert(new ListElement("apple", 1.0, 2.0), list1.end(list1), list1);
        list1.insert(new ListElement("banana", 3.0, 4.0), list1.first(list1), list1);
        list1.insert(new ListElement("cherry", 5.0, 6.0), list1.end(list1), list1);
        list1.insert(new ListElement("d", 5.0, 6.0), list1.end(list1), list1);
        list1.insert(new ListElement("e", 5.0, 9.0), list1.end(list1), list1);
        list1.delete(1, list1);

        System.out.println("--List 2--");
        System.out.println("---");
        list2.insert(new ListElement("apricot", 0.5, 1.5), list2.first(list2), list2);
        list2.insert(new ListElement("blueberry", 2.5, 3.5), list2.first(list2), list2);
        list2.insert(new ListElement("coconut", 4.5, 5.5), list2.first(list2), list2);

        System.out.println("--Merged List--");
        System.out.println("---");
        joinLists(list1, list2, mergedList);

        System.out.println("List 1:");
        list1.printlist(list1);

        System.out.println("List 2:");
        list2.printlist(list2);

        System.out.println("Merged List:");
        mergedList.printlist(mergedList);
    }

    public static <T extends Comparable<T>> void joinLists(List<T> list1, List<T> list2, List<T> mergedList) {
        int i = 0, j = 0;
        while (i < list1.size && j < list2.size) {
            if (list1.elements[i].compareTo(list2.elements[j]) <= 0 || list1.elements[i] == null) {
                mergedList.insert(list1.elements[i], mergedList.end(mergedList), mergedList);
                i++;
            } else {
                mergedList.insert(list2.elements[j], mergedList.end(mergedList), mergedList);
                j++;
            }
        }
        while (i < list1.size) {
            mergedList.insert(list1.elements[i], mergedList.end(mergedList), mergedList);
            i++;
        }
        while (j < list2.size) {
            mergedList.insert(list2.elements[j], mergedList.end(mergedList), mergedList);
            j++;
        }
    }
}