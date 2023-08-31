import java.sql.*;
import java.util.Scanner;

public class ResumeBuilder {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/ResumeBuilder";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            System.out.println("Enter your email: ");
            String email = scanner.nextLine();

            System.out.println("Enter your phone number: ");
            String phone = scanner.nextLine();

            System.out.println("Enter your Skills: ");
            String skills = scanner.nextLine();

            System.out.println("Enter your education: ");
            String education = scanner.nextLine();

            System.out.println("Enter your experience: ");
            String experience = scanner.nextLine();

            String insertQuery = "INSERT INTO Resumes (name, email, phone, skills, education, experience) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, skills);
            preparedStatement.setString(5, education);
            preparedStatement.setString(6, experience);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Resume added successfully!");

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("Generated Resume ID: " + id);
                }
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
