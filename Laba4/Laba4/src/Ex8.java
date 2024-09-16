import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ex8 {

    static class Products{
        String name;
        Double price;

        Products(String name, Double price){
            this.name=name;
            this.price=price;
        }
        public String getName(){
            return name;
        }
        public Double getPrice(){
            return price;
        }
    }

    public static Optional<String> findHighestPrice(List<Products> products){
        return products.stream()
                .sorted(Comparator.comparingDouble(Products::getPrice).reversed())
                .skip(1)
                .findFirst()
                .map(Products::getName);
    }

    public static void main(String[] args){
        List<Products> products = List.of(
                new Products("Apple", 10.0),
                new Products("Banana", 11.5),
                new Products("Orange", 9.8),
                new Products("Grape", 15.2)
        );
        Optional<String> HighestPrice = findHighestPrice(products);
        System.out.println(HighestPrice);
    }
}
