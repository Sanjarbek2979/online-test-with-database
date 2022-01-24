package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sanjarbek Allayev, чт 17:15. 20.01.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAnswer {
    private Integer id;
    private Integer question_id;
    private Integer user_id;
    private String givenAnswer;

    public UserAnswer(Integer question_id, Integer user_id, String givenAnswer) {
        this.question_id = question_id;
        this.user_id = user_id;
        this.givenAnswer = givenAnswer;
    }
}
