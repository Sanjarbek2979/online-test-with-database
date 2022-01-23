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
public class Question {
    private Integer id;
    private String text;
    private Integer subjectId;
    private String type;
    private boolean active;
    private String correctAnswer;
}
