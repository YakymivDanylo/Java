import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main {
    public static int convertStringToInteger(String input) {
        try{
            return Integer.parseInt(input);
        }
        catch(NumberFormatException e){
            System.out.println("Input is not a number");
            Scanner scanner = new Scanner(System.in);
            return convertStringToInteger(scanner.nextLine());
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int result = convertStringToInteger(input);
        System.out.println("Converted number: "+result);
    }
}



