import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Ex10 {
    private static double calculateAverage(List<Integer> temperatures){
        return temperatures.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);
    }


    public static Optional<String> findCity(Map<String, List<Integer>> temperatures){
        return temperatures.entrySet().stream()
                .max(Map.Entry.comparingByValue((temps1, temps2) -> Double.compare(calculateAverage(temps1), calculateAverage(temps2))))
                .map(Map.Entry::getKey); }

    public static void main(String[] args) {
        Map<String, List<Integer>> temperatures = Map.of(
                "Kyiv", List.of(20, 22, 25, 23, 21),
                "Lviv", List.of(18, 19, 21, 20, 19),
                "Odesa", List.of(23, 25, 26, 24, 22)
        );

        Optional<String> cityWithHighestAverageTemperature = findCity(temperatures);
        System.out.println(cityWithHighestAverageTemperature);
    }

}
