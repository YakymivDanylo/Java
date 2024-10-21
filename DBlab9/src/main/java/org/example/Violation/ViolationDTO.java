package org.example.Violation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Car.CarDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ViolationDTO {
    private String id;
    private CarDTO car;
    private String type;
    private String data;
    private String place;
    private String time;

    @Override
    public String toString() {
        return id+" "+car+" "+type+" "+data+" "+place+" "+time;
    }
}
