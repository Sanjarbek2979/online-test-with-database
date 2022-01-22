package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Sanjarbek Allayev, чт 17:13. 20.01.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class History {
    private Integer id;
    private Timestamp date;
    private Double point;
    private UserAnswer userAnswer;
}
