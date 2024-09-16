import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ex4 {
    public static class Employee{
        String name;
        double salary;

        public Employee(String name, double salary){
            this.name=name;
            this.salary=salary;
        }
        public String getName(){
            return name;
        }
        public double getSalary(){
            return salary;
        }
        @Override
        public String toString(){
            return "{name="+name+",salary="+salary+"}";
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("Ivan",2500),
                new Employee("Petro",4000),
                new Employee("Anna",3200),
                new Employee("Maria",7000)
        );

        Map<String, Optional<Employee>> groupedEmployees = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> {
                            if (employee.salary < 3000) {
                                return "< 3000";
                            } else if (employee.salary >= 3000 && employee.salary <= 5000) {
                                return "3000-5000";
                            } else {
                                return "> 5000";
                            }
                        },
                        Collectors.maxBy((e1, e2) -> Double.compare(e1.salary, e2.salary))
                ));
        System.out.println(groupedEmployees);
    }

}
