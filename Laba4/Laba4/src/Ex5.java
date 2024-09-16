import java.util.List;
import java.util.Optional;

public class Ex5 {
    public static Optional<Integer> findOddumber(List<Integer> numbers){
        return numbers.stream()
                .filter(n->n%2!=0)
                .reduce((n1,n2)->n1*n2);
    }

    public static void main(String[] args) {
        List<Integer> numbers1 = List.of(1,2,3,4,5);

        System.out.println(findOddumber(numbers1));
    }

}
