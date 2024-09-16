import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ex6 {
    static class Person{
        String name;
        List<Person> friends;

        Person(String name, List<Person> friends){
            this.name = name;
            this.friends = friends;
        }

        public List<Person> getFriends() {
            return friends;
        }
        public String getName() {
            return name;
        }

    }

    public static Set<String> getUniqueFriendsNames(List<Person> people){
        return people.stream()
                .flatMap(person -> person.getFriends().stream())
                        .map(Person::getName)
                        .map(String::toUpperCase)
                        .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        Person ivan = new Person("Ivan",List.of());
        Person petro = new Person("Petro",List.of(ivan));
        Person anna = new Person("Anna",List.of(ivan,petro));
        Person maria = new Person("Maria",List.of(ivan,petro,anna));

        List<Person> people = List.of(ivan,petro,anna,maria);

        Set<String> uniqueFriendsName = getUniqueFriendsNames(people);
        System.out.println(uniqueFriendsName);
    }
}
