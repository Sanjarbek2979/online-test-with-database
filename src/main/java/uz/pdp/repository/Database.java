package uz.pdp.repository;

import uz.pdp.dto.CorrectAnswersDto;
import uz.pdp.model.*;
import uz.pdp.util.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanjarbek Allayev, чт 17:23. 20.01.2022
 */
public class Database {
    public static List<History> histories = new ArrayList<>();
    public static List<Question> questions = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>();
    public static List<UserAnswer> userAnswers = new ArrayList<>();
    public static List<VariantAnswer> variantAnswers = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<CorrectAnswersDto> userHistory =new ArrayList<>();

    public static void refreshDatabase() throws SQLException {
        HistoryRepository.refreshHistory();
        QuestionRepository.refreshQuestions();
        SubjectRepository.refreshSubjects();
        UserRepository.refreshUsers();
        VariantAnswerRepository.refreshVariantAnswers();
        CorrectAnswersDtoRepository.refreshCorrectAnswers();
    }

}
