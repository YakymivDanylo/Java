//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void task1(String[] args) {
        int asciiCode = 38;
        char character = (char) asciiCode;
        System.out.println("Символ з ASCII кодом " + asciiCode + ": " + character);
    }

    public static void task2(String[] args) {
        double number = 5;

        if (number % 2 == 0) {
            System.out.println("Your number is even");
        } else {
            System.out.println("Your number is odd");
        }
    }

    public static void task3(String[] args) {
        int value = 0;
        switch (value) {
            case 1:
                System.out.println("It`s winter");
                break;
            case 2:
                System.out.println("It`s spring");
                break;
            case 3:
                System.out.println("It`s summer");
                break;
            case 4:
                System.out.println("It`s autumn");
                break;
            default:
                System.out.println("Sorry, i can`t understand you");

        }
    }

    public static void task4(String[] args) {
        int n = 2;
        while (n < 10){
            System.out.println(n);
            n = n + (n-1);

        }

    }

    public static void task5(String[] args) {
        int [][] array = {{1,2,3},{1,2,3},{1,2,3}};
        int sum =0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];
            }
        }
            System.out.println("Сума усіх елементів масиву: " + sum);
    }


    public static void main(String[] args) {
//            task1(args);
//            task2(args);
//            task3(args);
//            task4(args);
//        task5(args);
    }
}
