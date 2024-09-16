import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ex2 {
    public static List<Integer> getIntegers(List<Optional<Integer>> optionals){
        return optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Optional<Integer>> optionals = List.of(Optional.of(1),Optional.empty(),Optional.of(2),Optional.of(3),Optional.empty());
        List<Integer> integers = getIntegers(optionals);
        System.out.println(integers);

    }
}