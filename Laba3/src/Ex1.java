import java.util.HashMap;
import java.util.Map;


public class Ex1 {
    public static class Student {
            String name;
            int studentId;
            String group;

            public Student(String name, int studentId, String group) {
                this.name = name;
                this.studentId = studentId;
                this.group = group;
        }

        public String toString(){
                return "Student: " + name + ", ID: " + studentId + ", Group: " + group;
        }
    }

    public static void main(String[] args){
        Map<Integer, Student> studentsRegistry = new HashMap<>();
        studentsRegistry.put(1, new Student("Danylo Yakymiv", 1,"Group A"));
        studentsRegistry.put(2, new Student("Ivanna Bychkova", 2,"Group B"));
        studentsRegistry.put(3, new Student("Rostyslav Rodguck", 3,"Group A"));

        System.out.println("List of students:");
        for (Student student : studentsRegistry.values()) {
            System.out.println(student);
        }

            int searchId=2;
            if(studentsRegistry.containsKey(searchId)){
                System.out.println("\nStudent found:");
                System.out.println(studentsRegistry.get(searchId));
            }
            else {
                System.out.println("\nStudent with ID: " + searchId + " not found");
            }

            studentsRegistry.remove(1);

            System.out.println("\nList of students after deletion:");
            for (Student student : studentsRegistry.values()) {
                System.out.println(student);
            }
        }
    }
