import java.util.*;

public class Ex2 {
    public static <T> Set<T> getUniqueElements(List<T> inputList) {
        return new HashSet<>(inputList);
    }

    public static <T> Map<T,Integer> countElementOccurrences(List<T> inputList){
        Map<T,Integer> occurrences = new HashMap<>();
        for(T element : inputList){
            occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);
        }
        return occurrences;
    }

    public static void main(String[] args){
        List<String> names = Arrays.asList("Ivan","Petro","Ivan","Maria","Petro","Ivan");

        System.out.println("Unique names: "+ getUniqueElements(names));

        Map<String,Integer> occurrences = countElementOccurrences(names);
        System.out.println("Element occurrences:");
        for(Map.Entry<String,Integer> entry:occurrences.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
