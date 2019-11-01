package model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Developer {
    private final String name;
    private final BigDecimal salary;
    private int age;
}
