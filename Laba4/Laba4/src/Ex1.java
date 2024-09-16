import java.util.List;
import java.util.Optional;

import static javax.management.Query.or;

public class Ex1 {
    public static Optional<String> findString(List<String> strings){
        return strings.stream()
                .filter(s->s.startsWith("X") && s.length() > 5)
                .findFirst()
        .or(()->Optional.of("Default"));
    }

    public static void main(String[] args) {
        List<String> stringsList1 = List.of("Apple","Xylophone","Orange","X123456");
        List<String> stringList2 = List.of("Apple","Orange","Banana");

        System.out.println(findString(stringsList1));
        System.out.println(findString(stringList2));
    }
}