package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sanjarbek Allayev, чт 17:14. 20.01.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VariantAnswer {
    private Integer id;
    private String name;
    private Integer question_id;
    private boolean isCorrect;
}
