package uz.pdp.service;

import uz.pdp.enums.UserRole;
import uz.pdp.model.Question;
import uz.pdp.model.Subject;
import uz.pdp.model.User;
import uz.pdp.model.VariantAnswer;
import uz.pdp.repository.Database;
import uz.pdp.repository.QuestionRepository;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sanjarbek Allayev, чт 17:17. 20.01.2022
 */
public class UserService {
    public static UserRole getUserRole(String phone){
UserRole userRole=null;
        for (User user : Database.users) {
            if(user.getPhone().equals(phone)){
                userRole=user.getRole();
            }
        }
     return userRole;
    }
    public static boolean checkUser(String phone){
        for (User user : Database.users) {
            if(user.getPhone().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public static void takeExam() throws SQLException {
        Database.refreshDatabase();
        System.out.println("Please wait a few seconds...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Database is being updated...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("Please enter number of questions(min->4 " +
                "max->20): ");
        int count;
        do {
            count = new Scanner(System.in).nextInt();
        }while (count>20 || count<4);
        System.out.println("---------------------------------------------------------");
        System.out.println("Type of questions: Easy, Medium, Hard");
        System.out.print("Enter type of questions: ");
        String type = new Scanner(System.in).nextLine().toUpperCase();
        System.out.println("---------------------------------------------------------");
        System.out.println("All questions updated");
        System.out.println("For continue enter number (1)");
        System.out.println("For exit enter (0)");
        int selection =new Scanner(System.in).nextInt();
        if (selection==0){
            System.out.println("See you soon bro😋");
        }
        else{
            for (Subject subject : Database.subjects) {
                System.out.println(subject.getId()+") "+subject.getName());
            }
            System.out.print("Please choose the subject id: ");
            int subjectId=new Scanner(System.in).nextInt();

            List<Question> questionUserList= new ArrayList<>();
            questionUserList = QuestionRepository.getListQuestionByType(subjectId , type);

            test(questionUserList,count);


        }

    }

    private static void test(List<Question> questionUserList, int count) {
        List<Integer> listNumbers= new ArrayList<>();
        LocalDateTime now1 = LocalDateTime.now();
        int soni=1;
        while (count!=0){
            int randomNumber = getRandomNumber(1, questionUserList.size());
            listNumbers.add(randomNumber);
            if (!listNumbers.contains(randomNumber)){
            System.out.println(soni+") "+questionUserList.get(randomNumber));
                List<VariantAnswer> allVariants = getAllVariants(questionUserList.get(randomNumber).getId());
                for (VariantAnswer allVariant : allVariants) {
                    System.out.println(allVariant.getName());
                }

                count--;
            }
            else {
                count++;
            }
        }
        LocalDateTime now2 = LocalDateTime.now();
    }

    public static List<VariantAnswer> getAllVariants(Integer id) {
        List<VariantAnswer> variantAnswers = new ArrayList<>();
        for (VariantAnswer variantAnswer : Database.variantAnswers) {
            if (variantAnswer.getQuestion_id() == id){
                variantAnswers.add(variantAnswer);
            }
        }
        return variantAnswers;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void history() {

    }
}
