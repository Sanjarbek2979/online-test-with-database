package uz.pdp.repository;

import uz.pdp.dto.CorrectAnswersDto;
import uz.pdp.model.History;
import uz.pdp.util.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

/**
 * @author Sanjarbek Allayev, пн 22:56. 24.01.2022
 */
public class CorrectAnswersDtoRepository {
    public static void refreshCorrectAnswers() throws SQLException {
        Database.userHistory.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from correct_answers_dto");
        while (resultSet.next()) {
            CorrectAnswersDto correctAnswersDto = new CorrectAnswersDto();
            correctAnswersDto.setQuestions(resultSet.getInt(1));
            correctAnswersDto.setCorrectAnswers(resultSet.getInt(2));
            correctAnswersDto.setIncorrectAnswers(resultSet.getInt(3));
            correctAnswersDto.setOverall(resultSet.getInt(4));
            correctAnswersDto.setYourScore(resultSet.getInt(5));
            correctAnswersDto.setTime(resultSet.getInt(6));
            correctAnswersDto.setTimeLeft(resultSet.getInt(7));
            correctAnswersDto.setSuccess(resultSet.getDouble(8));
            correctAnswersDto.setDate((LocalTime) resultSet.getObject(9));
            Database.userHistory.add(correctAnswersDto);
        }
    }
}