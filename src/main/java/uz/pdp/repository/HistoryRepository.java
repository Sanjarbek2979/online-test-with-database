package uz.pdp.repository;

import uz.pdp.model.History;
import uz.pdp.model.Subject;
import uz.pdp.model.UserAnswer;
import uz.pdp.util.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Sanjarbek Allayev, чт 20:22. 20.01.2022
 */
public class HistoryRepository {
    public static void refreshHistory() throws SQLException {
        Database.histories.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from history");
        while (resultSet.next()) {
            History history = new History();
            history.setId(resultSet.getInt(1));
            history.setDate(resultSet.getString(2));
            history.setPoint(resultSet.getDouble(3));
            history.setUserAnswerId(resultSet.getInt(4));
            Database.histories.add(history);
        }
    }
}
