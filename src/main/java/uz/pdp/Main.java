package uz.pdp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import uz.pdp.dto.CorrectAnswersDto;
import uz.pdp.enums.UserRole;
import uz.pdp.model.Subject;
import uz.pdp.model.User;
import uz.pdp.model.UserAnswer;
import uz.pdp.repository.*;
import uz.pdp.response.Response;
import uz.pdp.service.SubjectService;
import uz.pdp.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Sanjarbek Allayev, чт 17:13. 20.01.2022
 */
public class Main {
    public static String currentUserPhone=null;
    public static final Scanner SCANNER_NUM= new Scanner(System.in);
    public static final Scanner SCANNER_STR= new Scanner(System.in);


    public static void main(String[] args) throws SQLException, InterruptedException {

        runn();
    }

    public static void runn() throws InterruptedException {
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
        currentUserPhone=phoneNumber;
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


    public static void userMenu() throws InterruptedException {
        try {
            Database.refreshDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public static void superAdminMenu() {
        try {
            Database.refreshDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void register(String phoneNumber) throws InterruptedException {
        String name;
        System.out.print("Enter your name: ");
        name =SCANNER_STR.nextLine();
        Response response = null;
        try {
            Thread.sleep(1000);
             response = UserRepository.callFunctionRegister(name, phoneNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            userMenu();
        }
        else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Something went wrong please try again");
            try {
                Thread.sleep(1920);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.runn();
        }
    }

    public static void adminMenu() throws InterruptedException {
        try {
            Database.refreshDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("+----------------------------------------+");
        System.out.println("|           Welcome ADMIN 😎😎😎          |");
        System.out.println("+----------------------------------------+");
        System.out.println();
        System.out.println("1.SUBJECT CRUD");
        System.out.println("2.QUESTION CRUD");
        System.out.println("3.USER ANSWERS HISTORY");
        System.out.println("0.Exit");
        System.out.print("Choose: ");
        int selection= new Scanner(System.in).nextInt();
        switch (selection){
            case 1->{
                SubjectService.subjectCRUD();
            }
            case 2->{


            }
            case 3->{

            }
            case 4->{

            }
            case 0->{
                System.out.println("See you soon 😥😥😥");
            }
            default -> System.out.println("Next time bro😎😎😎");
        }


    }


}
