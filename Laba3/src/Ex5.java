import java.util.Arrays;
import java.util.List;

public class Ex5 {
    public static class Pair<T,U>{
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }

        public boolean equals(Pair<T,U> other) {
            if(this==other) return true;
            if (other == null) return false;
            return this.first.equals(other.first) && this.second.equals(other.second);
        }
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair1 = new Pair<>(1, "one");
        Pair<Integer, String> pair2 = new Pair<>(2, "two");
        System.out.println("Pair 1: " + pair1);
        System.out.println("Pair 2: " + pair2);
        System.out.println("Is pair 1 equal to pair 2? " + pair1.equals(pair2));

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        Pair<String, List<Integer>> pair3 = new Pair<>("list", list1);
        Pair<String, List<Integer>> pair4 = new Pair<>("list", list2);
        System.out.println("\nPair 3: " + pair3);
        System.out.println("Pair 4: " + pair4);
        System.out.println("Is pair 3 equal to pair 4? " + pair3.equals(pair4));
    }
}