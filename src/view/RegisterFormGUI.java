package view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class RegisterFormGUI {
    private static TextField firstnameField;
    private static TextField lastnameField;
    private static TextField usernameField;
    private static DatePicker birthdayPicker;
    private static TextField emailField;
    private static PasswordField passwordField;
    private static PasswordField confirmPasswordField;

    public static Scene createRegisterScene(Stage primaryStage) {
        primaryStage.setTitle("Înregistrare");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);

        Label firstnameLabel = new Label("Nume");
        Label lastnameLabel = new Label("Prenume");
        Label usernameLabel = new Label("Nume de utilizator");
        Label emailLabel = new Label("E-mail");
        Label birthdayLabel = new Label("Data nașterii");
        Label passwordLabel = new Label("Parolă");
        Label confirmPasswordLabel = new Label("Confirmare parolă");

        firstnameField = new TextField();
        lastnameField = new TextField();
        usernameField = new TextField();
        passwordField = new PasswordField();
        birthdayPicker = new DatePicker();
        emailField = new TextField();
        confirmPasswordField = new PasswordField();
        firstnameField.getStyleClass().add("text-field");
        lastnameField.getStyleClass().add("text-field");
        usernameField.getStyleClass().add("text-field");
        passwordField.getStyleClass().add("text-field");
        emailField.getStyleClass().add("text-field");
        birthdayPicker.getStyleClass().add("birthday-picker");
        confirmPasswordField.getStyleClass().add("text-field");
        Button registerButton = new Button("Înregistrează-te");

        registerButton.getStyleClass().add("buttonRegister");

        grid.add(firstnameLabel, 0, 0);
        grid.add(firstnameField, 1, 0);
        grid.add(lastnameLabel, 0, 1);
        grid.add(lastnameField, 1, 1);
        grid.add(usernameLabel, 0, 2);
        grid.add(usernameField, 1, 2);
        grid.add(birthdayLabel, 0, 3);
        grid.add(birthdayPicker, 1, 3);
        grid.add(emailLabel, 0,4);
        grid.add(emailField, 1, 4);
        grid.add(passwordLabel, 0, 5);
        grid.add(passwordField, 1, 5);
        grid.add(confirmPasswordLabel, 0, 6);
        grid.add(confirmPasswordField, 1, 6);
        grid.add(registerButton, 1, 7);
        root.getChildren().add(grid);

        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String firstName = firstnameField.getText();
            String lastName = lastnameField.getText();
            String email = emailField.getText();
            String birthday = birthdayPicker.getValue().toString();

            JSONObject userObject = new JSONObject();
            try {
                userObject.put("username", username);
                userObject.put("password", password);
                userObject.put("firstName", firstName);
                userObject.put("lastName", lastName);
                userObject.put("email", email);
                userObject.put("birthday", birthday);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            // Citirea fișierului JSON existent, dacă există
            JSONArray usersArray;
            try {
                usersArray = new JSONArray(FileUtils.readFileToString(new File("users.json"), "UTF-8"));
            } catch (IOException | JSONException e) {
                usersArray = new JSONArray();
            }

            // Adăugarea noului utilizator în array
            usersArray.put(userObject);

            // Scrierea array-ului actualizat înapoi în fișierul JSON
            try (FileWriter file = new FileWriter("users.json")) {
                file.write(usersArray.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Restul codului pentru autentificare și afișarea alertelor
        });

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("styles.css");
        return scene;
    }

    private static void handle(ActionEvent event) throws JSONException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstnameField.getText();
        String lastName = lastnameField.getText();
        String email = emailField.getText();
        String birthday = birthdayPicker.getValue().toString();

        JSONObject userObject = new JSONObject();
        userObject.put("username", username);
        userObject.put("password", password);
        userObject.put("firstName", firstName);
        userObject.put("lastName", lastName);
        userObject.put("email", email);
        userObject.put("birthday", birthday);

        // Citirea fișierului JSON existent, dacă există
        JSONArray usersArray;
        try {
            usersArray = new JSONArray(FileUtils.readFileToString(new File("users.json"), "UTF-8"));
        } catch (IOException e) {
            usersArray = new JSONArray();
        }

        // Adăugarea noului utilizator în array
        usersArray.put(userObject);

        // Scrierea array-ului actualizat înapoi în fișierul JSON
        try (FileWriter file = new FileWriter("users.json")) {
            file.write(usersArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Restul codului pentru autentificare și afișarea alertelor
    }
}
