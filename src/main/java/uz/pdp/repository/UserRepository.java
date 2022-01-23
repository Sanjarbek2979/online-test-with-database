package uz.pdp.repository;

import com.google.gson.JsonElement;
import uz.pdp.enums.UserRole;
import uz.pdp.model.User;
import uz.pdp.response.Response;
import uz.pdp.util.DbConfig;

import java.sql.*;

/**
 * @author Sanjarbek Allayev, чт 20:22. 20.01.2022
 */
public class UserRepository {
    public static void refreshUsers() throws SQLException {
        Database.users.clear();
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setPhone(resultSet.getString(3));
            user.setRole(UserRole.valueOf(resultSet.getString(4)));
            user.setActive(resultSet.getBoolean(5));
            user.setDeleted(resultSet.getBoolean(6));
            Database.users.add(user);
        }
        System.out.println("Refresh Users");
    }

    public static Response callFunctionRegister(String name,String phone) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call register_user (?,?,?,?) }");
        callableStatement.setString(1, name);
        callableStatement.setString(2, phone);
        callableStatement.registerOutParameter(3,Types.BOOLEAN);
        callableStatement.registerOutParameter(4, Types.VARCHAR);
        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(3));
        response.setMessage(callableStatement.getString(4));
        System.out.println(response);
        return response;
    }

//    public static Response callFunctionUpdate(String name, String newName,Double size,String contentType) throws SQLException {
//        Response response = new Response();
//        Connection connection = DbConfig.ulanish();
//        CallableStatement callableStatement = connection.prepareCall("{call update_attachment (?,?,?,?,?,?)}");
//        callableStatement.setString(1, name);
//        callableStatement.setString(2, newName);
//        callableStatement.setDouble(3, size);
//        callableStatement.setString(4, contentType);
//        callableStatement.registerOutParameter(5, Types.BOOLEAN);
//        callableStatement.registerOutParameter(6, Types.VARCHAR);
//
//        callableStatement.execute();
//        response.setSuccess(callableStatement.getBoolean(5));
//        response.setMessage(callableStatement.getString(6));
//
//        System.out.println(response);
//        return response;
//
//    }
//
//    public static Response callFunctionDelete(String name) throws SQLException {
//        Response response = new Response();
//        Connection connection = DbConfig.ulanish();
//        CallableStatement callableStatement = connection.prepareCall("{call delete_attachment (?,?,?)}");
//        callableStatement.setString(1, name);
//        callableStatement.registerOutParameter(2, Types.BOOLEAN);
//        callableStatement.registerOutParameter(3, Types.VARCHAR);
//
//        callableStatement.execute();
//        response.setSuccess(callableStatement.getBoolean(2));
//        response.setMessage(callableStatement.getString(3));
//
//        System.out.println(response);
//        return response;
//    }
//
//    public static void callFunctionSelect() {
//        int i=1;
//        for (Attachment attachment : Database.attachments) {
//            System.out.println(i+") "+attachment);
//            i++;
//        }
//    }
}
