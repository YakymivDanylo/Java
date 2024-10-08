package org.example.admin;

import lombok.Getter;
import org.example.Person;

@Getter
public class AdminDTO extends Person {
    int password;

    public AdminDTO(int id, String name, String surname, String last_name, String birth_date, int password){
        super(id,name,surname,last_name,birth_date);
        this.password = password;
    }
    @Override
    public String toString() {
        return super.toString() + " " + password;
    }

}
