package uz.pdp.repository;

import uz.pdp.model.Subject;
import uz.pdp.util.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sanjarbek Allayev, чт 20:22. 20.01.2022
 */
public class SubjectRepository {
    public static void refreshSubjects() throws SQLException {
        Database.subjects.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from subject");
        while (resultSet.next()) {
            Subject subject = new Subject();
            subject.setId(resultSet.getInt(1));
            subject.setName(resultSet.getString(2));
            subject.setActive(resultSet.getBoolean(3));
            Database.subjects.add(subject);
        }
        System.out.println("Refresh subjects");
    }
}
