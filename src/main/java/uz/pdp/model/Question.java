package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.QuestionType;

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
    private QuestionType type;
    private boolean active;
    private String correctAnswer;
}
