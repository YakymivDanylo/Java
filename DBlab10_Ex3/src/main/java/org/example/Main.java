package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Choose what do you want");
        Scanner scanner = new Scanner(System.in);
        HashRecorder recorder = new HashRecorder();
        int choice = 11;
        while (choice != 12) {
            try(BufferedReader reader = new BufferedReader(new FileReader("D:\\java\\DBlab10_Ex3\\src\\main\\resources\\options.txt"))){
                String line;
                while((line = reader.readLine()) != null){
                    System.out.println(line);
                }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
            choice = new Scanner(System.in).nextInt();
            switch(choice){
                case 1:{
                    System.out.println("Enter the ID of Owner:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the address of Owner:");
                    String address = scanner.nextLine();
                    System.out.println("Enter the email of Owner:");
                    String email = scanner.nextLine();
                    System.out.println("Enter the name of Owner:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the phone of Owner:");
                    String phone = scanner.nextLine();
                    recorder.createOwner(id,address,email ,name,phone);
                    break;
                }
                case 2:{
                    System.out.println("Enter the ID of Car:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the ID of Owner:");
                    String id_owner = scanner.nextLine();
                    System.out.println("Enter the brand of Car:");
                    String brand = scanner.nextLine();
                    System.out.println("Enter the color of Car:");
                    String color = scanner.nextLine();
                    System.out.println("Enter the model of Car:");
                    String model = scanner.nextLine();
                    System.out.println("Enter the number of Car:");
                    String number = scanner.nextLine();
                    System.out.println("Enter the year of Car:");
                    String year = scanner.nextLine();
                    recorder.createCar(id,id_owner,brand,color,model,number,year);
                    break;
                }
                case 3:{
                    System.out.println("Enter the ID of Employee:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the name of Employee:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the position of Employee:");
                    String position = scanner.nextLine();
                    recorder.createEmployee(id,name,position);
                    break;
                }
                case 4:{
                    System.out.println("Enter the ID of Fine:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the ID of Violation:");
                    String id_violation = scanner.nextLine();
                    System.out.println("Enter the data of Fine:");
                    String data = scanner.nextLine();
                    System.out.println("Enter the status of Fine:");
                    String status = scanner.nextLine();
                    System.out.println("Enter the sum of Fine:");
                    String sum = scanner.nextLine();
                    recorder.createFine(id,id_violation,data,status,sum);
                    break;
                }
                case 5:{
                    System.out.println("Enter the ID of System:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the ID of Employee:");
                    String id_employee = scanner.nextLine();
                    System.out.println("Enter the ID of Violation:");
                    String id_violation = scanner.nextLine();
                    System.out.println("Enter the position of System:");
                    String position = scanner.nextLine();
                    System.out.println("Enter the type of System:");
                    String type = scanner.nextLine();
                    recorder.createSystem(id,id_employee,id_violation,position,type);
                    break;
                }
                case 6:{
                    System.out.println("Enter the ID of Violation:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the number of Car:");
                    String number = scanner.nextLine();
                    System.out.println("Enter the type of Violation:");
                    String type = scanner.nextLine();
                    System.out.println("Enter the data of Violation:");
                    String data = scanner.nextLine();
                    System.out.println("Enter the place of Violation:");
                    String place = scanner.nextLine();
                    System.out.println("Enter the time of Violation:");
                    String time = scanner.nextLine();
                    recorder.createViolation(id,number,type,data,place,time);
                    break;
                }
                case 7:{
                    System.out.println("Enter the key to read:");
                    String key = scanner.nextLine();
                    System.out.println(recorder.read(key));
                    break;
                }
                case 8:{
                    System.out.println("Enter the ID of Car to update:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the ID of Owner:");
                    String id_owner = scanner.nextLine();
                    System.out.println("Enter the brand of Car:");
                    String brand = scanner.nextLine();
                    System.out.println("Enter the color of Car:");
                    String color = scanner.nextLine();
                    System.out.println("Enter the model of Car:");
                    String model = scanner.nextLine();
                    System.out.println("Enter the number of Car:");
                    String number = scanner.nextLine();
                    System.out.println("Enter the year of Car:");
                    String year = scanner.nextLine();
                    recorder.updateCar(id,id_owner,brand,color,model,number,year);
                    break;
                }
                case 9:{
                    System.out.println("Enter the ID of Employee to update:");
                    String id = scanner.nextLine();
                    System.out.println("Enter the name of Employee:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the position of Employee:");
                    String position = scanner.nextLine();
                    recorder.updateEmployee(id,name,position);
                    break;
                }
                case 10:{
                    System.out.println("Enter the key to delete:");
                    String key = scanner.nextLine();
                    recorder.delete(key);
                    System.out.println("Deleted key: " + key);
                    break;
                }
                case 11:{
                    System.out.println("Exiting...");
                    break;
                }
                default:{
                    System.out.println("Invalid choice. Please try again.");
                }
            }

        }
    }
}