package uz.pdp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.pdp.model.Question;
import uz.pdp.model.Subject;
import uz.pdp.util.DbConfig;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sanjarbek Allayev, чт 20:22. 20.01.2022
 */
public class QuestionRepository {
    public static void refreshQuestions() throws SQLException {
        Database.questions.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from question");
        while (resultSet.next()) {
            Question question = new Question();
            question.setId(resultSet.getInt(1));
            question.setText(resultSet.getString(2));
            question.setSubjectId( resultSet.getInt(3));
            question.setType(resultSet.getString(4));
            question.setActive(resultSet.getBoolean(5));
            question.setCorrectAnswer(resultSet.getString(6));
            Database.questions.add(question);
        }
    }
    public static void callFunctionSelect() {
        int i=1;
        for (Question question : Database.questions) {
            System.out.println(i+") "+question);
            i++;
        }
    }

    public static List<Question> getListQuestionByType(int subjectId, String type) {
        List<Question> questions = new ArrayList<>();

        for (Question question : Database.questions) {
            if (question.getType().equals(type.toUpperCase()) && question.getId()==subjectId){
                questions.add(question);
            }
        }
        return questions;
    }
}
