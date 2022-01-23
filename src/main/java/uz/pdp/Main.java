package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import uz.pdp.enums.UserRole;
import uz.pdp.model.Subject;
import uz.pdp.model.User;
import uz.pdp.model.UserAnswer;
import uz.pdp.repository.*;
import uz.pdp.response.Response;
import uz.pdp.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Sanjarbek Allayev, чт 17:13. 20.01.2022
 */
public class Main {
    public static final Scanner SCANNER_NUM= new Scanner(System.in);
    public static final Scanner SCANNER_STR= new Scanner(System.in);


    public static void main(String[] args) throws SQLException {

        UserRepository.refreshUsers();
        SubjectRepository.refreshSubjects();
        HistoryRepository.refreshHistory();
        QuestionRepository.refreshQuestions();
        VariantAnswerRepository.refreshVariantAnswers();

        runn();
    }

    private static void runn() {
        for (Subject subject : Database.subjects) {
            System.out.println(subject);
        }
        try {
            Database.refreshDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("+------------------------------------+");
        System.out.println("| WELCOME TO ONLINE TESTING BRO😎😎😎 |");
        System.out.println("+------------------------------------+");
        System.out.println("   Please enter your phone for continue (if you wanna exit please enter 0) 👇👇👇");
        System.out.print("For example(+998xxxxxxxxx): ");
        String phoneNumber=SCANNER_STR.nextLine();
        if(phoneNumber.equals("0")){

            System.out.println("See you soon😪😪😪");
            return;

        }else {
            if (UserService.checkUser(phoneNumber) && UserService.getUserRole(phoneNumber).equals(UserRole.ADMIN)) {
                adminMenu();
            } else if (UserService.checkUser(phoneNumber) && UserService.getUserRole(phoneNumber).equals(UserRole.USER)) {
                userMenu();
            } else if (UserService.checkUser(phoneNumber) && UserService.getUserRole(phoneNumber).equals(UserRole.SUPERADMIN)) {
                superAdminMenu();
            } else
                register(phoneNumber);
        }
    }


    private static void userMenu() {
        System.out.println("+----------------------------------------+");
        System.out.println("|          Welcome to USER menu          |");
        System.out.println("+----------------------------------------+");
        System.out.println();
        System.out.println("1.Take the exam");
        System.out.println("2.History");
        System.out.println("0.Exit");
        System.out.println("Choose: ");
        int n = SCANNER_NUM.nextInt();
        switch (n){
            case 1->{
                try {
                    UserService.takeExam();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case 2->{
                UserService.history();
            }
            case 0->{
                System.out.println("See you soon bro☺");
                return;
            }
            default -> {
                System.out.println("Sorry next time bro😐😐😐");
                runn();
            }
        }
    }

    private static void superAdminMenu() {

    }

    private static void register(String phoneNumber) {
        String name;
        System.out.print("Enter your name: ");
        name =SCANNER_STR.nextLine();
        try {
           UserRepository.callFunctionRegister(name, phoneNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userMenu();
    }

    private static void adminMenu() {
        System.out.println("+----------------------------------------+");
        System.out.println("|           Welcome ADMIN 😎😎😎          |");
        System.out.println("+----------------------------------------+");
        System.out.println();
        System.out.println("1.USER CRUD");
        System.out.println("2.SUBJECT CRUD");
        System.out.println("3.QUESTION CRUD");
        System.out.println("4.VARIANT ANSWERS CRUD");
        System.out.println("0.Exit");
    }


}
