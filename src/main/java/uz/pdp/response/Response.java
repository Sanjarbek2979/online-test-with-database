package uz.pdp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sanjarbek Allayev, чт 20:04. 20.01.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private boolean success;
    private String message;
}
