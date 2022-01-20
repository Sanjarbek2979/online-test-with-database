package uz.pdp.repository;

import uz.pdp.model.*;

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

}
