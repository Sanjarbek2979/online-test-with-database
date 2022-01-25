package uz.pdp.service;

import uz.pdp.Main;
import uz.pdp.model.Subject;
import uz.pdp.repository.Database;
import uz.pdp.response.Response;
import uz.pdp.util.DbConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Sanjarbek Allayev, чт 17:20. 20.01.2022
 */
public class SubjectService {
    public static void subjectCRUD() throws InterruptedException {

        System.out.println("1. Add subject");
        System.out.println("2. Update subject");
        System.out.println("3. Delete subject");
        System.out.println("4. Show subject list");
        System.out.println("0. Back to menu");
        System.out.print("Select: ");
        int selection = new Scanner(System.in).nextInt();
        switch (selection){
            case 1->{
                System.out.println("Enter subject name: ");
                String subjectName = new Scanner(System.in).nextLine();
                Response response = null;
                try {
                     response = addSubject(subjectName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(response);
                subjectCRUD();
            }
            case 2->{
                int i=1;
                for (Subject subject : Database.subjects) {
                    System.out.println(i+") id->"+subject.getId()+" name->"+subject.getName());
                i++;
                }
                System.out.print("Enter name: ");
                String subjectName= new Scanner(System.in).nextLine();

                System.out.print("Enter new name: ");
                String newName= new Scanner(System.in).nextLine();

                Response response = null;
                try {
                    response = updateSubject(subjectName,newName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(response);
                subjectCRUD();            }
            case 3->{
                int i=1;
                for (Subject subject : Database.subjects) {
                    System.out.println(i+") id->"+subject.getId()+" name->"+subject.getName());
                    i++;
                }
                System.out.print("Enter name of subject: ");
                String deleteSubject= new Scanner(System.in).nextLine();
                Response response = null;
                try {
                    response = deleteSubject(deleteSubject);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(response);
                subjectCRUD();            }
            case 4->{
                try {
                    Database.refreshDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int i=1;
                for (Subject subject : Database.subjects) {
                    System.out.println(i+") "+"id->("+subject.getId()+"), "+"name->("+subject.getName()+")");
                i++;
                }
                System.out.println("Enter 0 for back menu(or enter any keyboard for exit): ");
                int select = new Scanner(System.in).nextInt();
                switch (select){
                    case 0->
                              subjectCRUD();

                       default -> System.out.println("See you soon bro😥😥😥");
                }
            }
            case 0->{

                System.out.println("Wait...");
                Thread.sleep(1820);
                Main.adminMenu();
            }
            default->

                System.out.println("Sorry next time bro");
        }
    }

    private static Response deleteSubject(String deleteSubject) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call delete_subject (?) }");
        callableStatement.setString(1, deleteSubject);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()){
            response.setSuccess(resultSet.getBoolean(1));
            response.setMessage(resultSet.getString(2));
        }
        return response;
    }

    private static Response updateSubject(String subjectName,String newName) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call update_subject (?,?) }");
        callableStatement.setString(1, subjectName);
        callableStatement.setString(2, newName);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()){
            response.setSuccess(resultSet.getBoolean(1));
            response.setMessage(resultSet.getString(2));
        }
        return response;
    }

    private static Response addSubject(String subjectName) throws SQLException {
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call add_subject (?) }");
        callableStatement.setString(1, subjectName);
        ResultSet resultSet = callableStatement.executeQuery();
        while (resultSet.next()){
            response.setSuccess(resultSet.getBoolean(1));
            response.setMessage(resultSet.getString(2));
        }
        return response;
    }

}
