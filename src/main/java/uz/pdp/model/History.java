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
    private String date;
    private Double point;
    private Integer userAnswerId;

    public History(String date, Double point, Integer userAnswerId) {
        this.date = date;
        this.point = point;
        this.userAnswerId = userAnswerId;
    }
}
