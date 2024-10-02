package org.example;
import lombok.Getter;

@Getter
public class Person {
    int id;
    String name;
    String surname;
    String last_name;
    String birth_date;

    public Person(int id) {
        this.id = id;
    }
    public Person(int id, String name, String surname, String last_name, String birth_date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.last_name = last_name;
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", last_name=" + last_name + ", birth_date=" + birth_date + "]";
    }
}
