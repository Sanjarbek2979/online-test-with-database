package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Sanjarbek Allayev, пн 18:59. 24.01.2022
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CorrectAnswersDto {
    private Integer questions;
    private Integer correctAnswers;
    private Integer incorrectAnswers;
    private Integer overall;
    private Integer yourScore;
    private Integer time;
    private Integer timeLeft;
    private double success;
    private Time date;
}
