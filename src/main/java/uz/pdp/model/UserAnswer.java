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

}
