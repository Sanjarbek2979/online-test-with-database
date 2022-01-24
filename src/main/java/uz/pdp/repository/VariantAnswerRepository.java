package uz.pdp.repository;
import uz.pdp.model.Question;
import uz.pdp.model.VariantAnswer;
import uz.pdp.util.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sanjarbek Allayev, чт 20:22. 20.01.2022
 */
public class VariantAnswerRepository {
    public static void refreshVariantAnswers() throws SQLException {
        Database.variantAnswers.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from variant_answers");
        while (resultSet.next()) {
            VariantAnswer variantAnswer = new VariantAnswer();
            variantAnswer.setId(resultSet.getInt(1));
            variantAnswer.setName(resultSet.getString(2));
            variantAnswer.setQuestion_id(resultSet.getInt(3));
            variantAnswer.setCorrect(resultSet.getBoolean(4));
            Database.variantAnswers.add(variantAnswer);
        }
    }
}
