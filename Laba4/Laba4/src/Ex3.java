import java.util.List;
import java.util.Optional;

public class Ex3 {

    public static Optional<String> findLongestName(List<String> names){
        return names.stream()
                .reduce((name1,name2)->name1.length()>name2.length()?name1:name2);
    }

    public static void main(String[] args) {
     List<String> names1 = List.of("Ivan","Petro","Andriy","Oleksandr");
     List<String> names2 = List.of();

     System.out.println("Stream 1"+findLongestName(names1));
     System.out.println("Stream 2"+findLongestName(names2));
    }
}