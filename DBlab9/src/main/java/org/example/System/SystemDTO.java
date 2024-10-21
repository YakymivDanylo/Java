package org.example.System;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Employee.EmployeeDTO;
import org.example.Violation.ViolationDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemDTO {
    private String id;
    private EmployeeDTO employee;
    private ViolationDTO violation;
    private String place;
    private String type;

}
