import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ex9 {
    public static List<String> getNamesProducts(Map<Integer, Optional<String>> products){
        return products.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Map<Integer,Optional<String>> products = Map.of(
                1, Optional.of("Apple"),
                2, Optional.empty(),
                3, Optional.of("Banana"),
                4, Optional.of("Orange")
        );

        List<String> productNames = getNamesProducts(products);
        System.out.println(productNames);
    }
}
