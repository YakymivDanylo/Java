import java.util.List;

public class ArrayList {
    public static void main(String[] args) {
        List<Integer> numbers = new java.util.ArrayList<>();
         numbers.add(1);
         numbers.add(2);
         numbers.add(3);
         numbers.add(2);
         numbers.add(4);

         int numberToRemove = 0;
         removeElements(numbers,numberToRemove);
         System.out.println("List after deleting "+numbers);
    }
    public static void removeElements(List<Integer> list, int number) {
        try{
            list.removeIf( n -> n == number);
        }
        catch(Exception e){
            System.out.println("There was a problem deleting "+e.getMessage());
        }
    }
}
