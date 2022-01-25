package uz.pdp.service;

import com.google.gson.Gson;
import uz.pdp.Main;
import uz.pdp.dto.CorrectAnswersDto;
import uz.pdp.enums.UserRole;
import uz.pdp.model.*;
import uz.pdp.repository.Database;
import uz.pdp.repository.QuestionRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sanjarbek Allayev, чт 17:17. 20.01.2022
 */
public class UserService {
    public static UserRole getUserRole(String phone) {
        UserRole userRole = null;
        for (User user : Database.users) {
            if (user.getPhone().equals(phone)) {
                userRole = user.getRole();
            }
        }
        return userRole;
    }

    public static User getUser(String phone) {
        User currentUser = null;
        for (User user1 : Database.users) {
            if (user1.getPhone().equals(phone)) {
                currentUser = user1;
            }
        }
        return currentUser;
    }

    public static boolean checkUser(String phone) {
        for (User user : Database.users) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public static void takeExam() throws SQLException {
        Database.refreshDatabase();
        System.out.println("Please wait a few seconds...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Database is being updated...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("Please enter number of questions(min->3 " +
                "max->20): ");
        int count;

        do {
            count = new Scanner(System.in).nextInt();
        } while (count > 20 || count < 3);

        System.out.println("---------------------------------------------------------");
        System.out.println("Type of questions: Easy, Medium, Hard");
        System.out.print("Enter type of questions: ");
        String type = new Scanner(System.in).nextLine().toUpperCase();
        System.out.println("---------------------------------------------------------");
        System.out.println("All questions updated");
        System.out.println("For continue enter number (1)");
        System.out.println("For exit enter (0)");
        int selection = new Scanner(System.in).nextInt();
        if (selection == 0) {
            System.out.println("See you soon bro😋");
        } else {
            for (Subject subject : Database.subjects) {
                System.out.println(subject.getId() + ") " + subject.getName());
            }
            System.out.print("Please choose the subject id: ");
            int subjectId = new Scanner(System.in).nextInt();

            List<Question> questionUserList;
            questionUserList = QuestionRepository.getListQuestionByType(subjectId, type);
            test(questionUserList, count,type);


        }

    }

    private static void test(List<Question> questionUserList, int count,String type) {
        int countOfQuestions = count;
        int ball = 0;
       int balcha=getBalcha(type);
        UserAnswer userAnswer = null;
        List<Integer> listNumbers = new ArrayList<>();
        LocalTime now1 = LocalTime.now();
        int soni = 1;
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        int time = 0;

        int timeForTesting = 0;
        int timeTest = countOfQuestions * 60;
        System.out.println();
        System.out.println("########################################");
        System.out.println("Time for testing: " + timeTest / 60 + " minutes");
        System.out.println("########################################");
        System.out.println();
        System.out.println("Starting...");
        try {
            Thread.sleep(1820);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (count > 0 && timeForTesting <= (now1.getSecond() + timeTest)) {
            timeForTesting = LocalDateTime.now().getSecond();
            int randomNumber = getRandomNumber(1, questionUserList.size());
            Question currentQuestion = questionUserList.get(randomNumber);
            if (!listNumbers.contains(randomNumber)) {
                System.out.println(soni + ") " + currentQuestion.getText());
                List<VariantAnswer> allVariants = getAllVariants(currentQuestion.getId());
                for (VariantAnswer allVariant : allVariants) {
                    System.out.println(allVariant.getName());
                }
                System.out.print("Your answer: ");
                String answer = new Scanner(System.in).next();
                String givenAnswer = null;
                for (VariantAnswer allVariant : allVariants) {
                    if (allVariant.getName().startsWith(answer.toUpperCase())) {
                        givenAnswer = allVariant.getName();
                    }
                }
                Integer userId = getUser(Main.currentUserPhone).getId();
                userAnswer = new UserAnswer(currentQuestion.getId(), userId, givenAnswer);
                try {
                    UserAnswerService.addUserAnswer(userAnswer);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Integer userAnswerId = userAnswer.getId();
                String date = String.valueOf(now1);
                Double point = (double) (correctAnswers * 5);

                History history = new History(date,point,userAnswerId);
//                try {
//                    HistoryService.addHistory(history);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
                for (VariantAnswer allVariant : allVariants) {
                    if (allVariant.getName().startsWith(answer.toUpperCase())) {
                        if (allVariant.isCorrect()) {
                            ball = ball + balcha;
                            correctAnswers++;
                        } else {
                            incorrectAnswers++;
                        }
                    }
                }
                count--;
                listNumbers.add(randomNumber);
                soni++;
            }
        }
        LocalTime now2 = LocalTime.now();
        System.out.println("Finished😀😀😀");
        System.out.println("Please wait,now your answers are being checked.");
        try {
            Thread.sleep(1820);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int timeLeft = timeTest - (now2.getSecond() - now1.getSecond());




        double successRate = Double.valueOf((double) (correctAnswers * 100 / countOfQuestions));
        CorrectAnswersDto history = new CorrectAnswersDto(countOfQuestions, correctAnswers, incorrectAnswers, (countOfQuestions * 5), ball, now2.getSecond() - now1.getSecond(), timeLeft, successRate, now2);
        System.out.println();
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("|                   YOUR RESULT ");
        System.out.println("| Count of all questions : " + history.getQuestions());
        System.out.println("| Correct answers : " + history.getCorrectAnswers());
        System.out.println("| Incorrect answers : " + history.getIncorrectAnswers());
        System.out.println("| Not answered : " + (countOfQuestions-(correctAnswers+incorrectAnswers)));
        System.out.println("| Overall : " + (countOfQuestions * 5) + " points");
        System.out.println("| Your score : " + history.getYourScore() + " points");
        System.out.println("| Time(seconds) : " + (history.getTime() / 60) + " minutes " + (history.getTime() % 60) + " seconds");
        System.out.println("| Time left (seconds) : " + (history.getTimeLeft() / 60) + " minutes " + (history.getTimeLeft() % 60) + " seconds");
        System.out.println("| Success rate : " + history.getSuccess() + " %");
        System.out.println("| Date : " + history.getDate());
        System.out.println("+-------------------------------------------------------------+");
        Database.userHistory.add(history);
        try {
            CorrectAnswersDtoService.addCorrectAnswers(history);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1820);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1. Back to menu");
        System.out.println("0. Exit");
        System.out.println("Select ");
        int select=new Scanner(System.in).nextInt();
        switch (select){
            case 1->{

        Main.userMenu();
            }
            case 0 ->{
                System.out.println("See you soon 😪😪😪");
            }
            default -> System.out.println("Bilib turib ko`zga cho`p tiqish yaxshimas bro🤨🤨🤨");
        }
    }

    private static int getBalcha(String type) {
        int balcha = 0;
        if(type.toUpperCase().equals("EASY")){
            balcha=5;
        }
        else if(type.toUpperCase().equals("MEDIUM")){
            balcha=10;
        }
        else if(type.toUpperCase().equals("HARD")){
            balcha=20;
        }
        return balcha;
    }

    public static List<VariantAnswer> getAllVariants(Integer id) {
        List<VariantAnswer> variantAnswers = new ArrayList<>();
        for (VariantAnswer variantAnswer : Database.variantAnswers) {
            if (variantAnswer.getQuestion_id() == id) {
                variantAnswers.add(variantAnswer);
            }
        }
        return variantAnswers;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void history() {

        List<CorrectAnswersDto> histories = Database.userHistory;
    if (histories.isEmpty()){
        System.out.println("History is empty!!!");
        Main.userMenu();
    }
    else {

        int i=1;
        for (CorrectAnswersDto history : histories) {
            System.out.println(i+" - attempt");

            System.out.println("+-------------------------------------------------------------+");
            System.out.println("| Count of all questions : " + history.getQuestions());
            System.out.println("| Correct answers : " + history.getCorrectAnswers());
            System.out.println("| Incorrect answers : " + history.getIncorrectAnswers());
            System.out.println("| Your score : " + history.getYourScore() + " points");
            System.out.println("| Time(seconds) : " + (history.getTime() / 60) + " minutes " + (history.getTime() % 60) + " seconds");
            System.out.println("| Time left (seconds) : " + (history.getTimeLeft() / 60) + " minutes " + (history.getTimeLeft() % 60) + " seconds");
            System.out.println("| Success rate : " + history.getSuccess() + " %");
            System.out.println("| Date : " + history.getDate());
            System.out.println("+-------------------------------------------------------------+");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            i++;
        }

        Main.userMenu();
    }
    }
}
