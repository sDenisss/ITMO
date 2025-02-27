import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {

        List<String> listOne = new ArrayList<>();
        listOne.add("a");
        listOne.add("b");
        listOne.add("c");
        List<String> listTwo = new ArrayList<>();
        listTwo.add("e");

        List<String> newList = Stream.concat(listOne.stream(), listTwo.stream()).toList();

        for (String string : newList) {
            System.out.println(string);
        }
    }
}