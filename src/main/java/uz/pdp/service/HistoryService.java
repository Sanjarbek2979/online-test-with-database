package uz.pdp.service;

import uz.pdp.model.History;
import uz.pdp.response.Response;
import uz.pdp.util.DbConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Sanjarbek Allayev, чт 17:18. 20.01.2022
 */
public class HistoryService {
    public static void addHistory(History history) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_history (?,?,?) }");
        callableStatement.setString(1, history.getDate());
        callableStatement.setDouble(2,history.getPoint() );
        callableStatement.setInt(3, history.getUserAnswerId());
        callableStatement.execute();
    }
}
