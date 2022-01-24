package uz.pdp.service;

import uz.pdp.dto.CorrectAnswersDto;
import uz.pdp.response.Response;
import uz.pdp.util.DbConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Sanjarbek Allayev, пн 22:55. 24.01.2022
 */
public class CorrectAnswersDtoService {

    public static void addCorrectAnswers(CorrectAnswersDto correctAnswersDto) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_correct_answers_dto (?,?,?,?,?,?,?,?,?) }");
        callableStatement.setInt(1,correctAnswersDto.getQuestions());
        callableStatement.setInt(2,correctAnswersDto.getCorrectAnswers());
        callableStatement.setInt(3,correctAnswersDto.getIncorrectAnswers());
        callableStatement.setInt(4,correctAnswersDto.getOverall());
        callableStatement.setInt(5,correctAnswersDto.getYourScore());
        callableStatement.setInt(6,correctAnswersDto.getTime());
        callableStatement.setInt(7,correctAnswersDto.getTimeLeft());
        callableStatement.setDouble(8,correctAnswersDto.getSuccess());
        callableStatement.setObject(9,correctAnswersDto.getDate());
        callableStatement.execute();
    }
}
