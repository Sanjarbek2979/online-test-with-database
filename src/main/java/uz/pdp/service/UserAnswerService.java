package uz.pdp.service;

import uz.pdp.model.UserAnswer;
import uz.pdp.response.Response;
import uz.pdp.util.DbConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * @author Sanjarbek Allayev, чт 17:20. 20.01.2022
 */
public class UserAnswerService {
    public static void addUserAnswer(UserAnswer userAnswer) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_user_answers (?,?,?) }");
        callableStatement.setInt(1,userAnswer.getQuestion_id() );
        callableStatement.setInt(2, userAnswer.getUser_id());
        callableStatement.setString(3,userAnswer.getGivenAnswer());
        callableStatement.execute();
        }
}
