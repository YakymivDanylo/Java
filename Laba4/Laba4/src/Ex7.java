import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ex7 {
    static class Transaction {
        double amount;
        String category;

        Transaction(double amount,String category){
            this.amount=amount;
            this.category=category;
        }
        public double getAmount(){
            return amount;
        }
        public String getCategory(){
            return category;
        }
    }

    public static void main(String[] args){
        List<Transaction> transactions = List.of(
                new Transaction(100, "Food"),
                new Transaction(50, "Transport"),
                new Transaction(200, "Food"),
                new Transaction(150, "Clothes"),
                new Transaction(75, "Transport")
        );
        Map<String, Double> categorySums = transactions.stream()
                .collect(Collectors.groupingBy
                        (Transaction::getCategory,
                                Collectors.summingDouble(Transaction::getAmount)));
        System.out.println(categorySums);

    }
}
