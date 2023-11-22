package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogInFormGUI {
    private static TextField usernameField;
    private static PasswordField passwordField;

    private static Circle photoCircle;

    public static Scene createLogInScene(Stage primaryStage) {
        primaryStage.setTitle("Logare");

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        photoCircle = new Circle(120);
        photoCircle.setFill(javafx.scene.paint.Color.LIGHTGRAY);
        Button addPhotoButton = new Button("Adaugă o fotografie");
        VBox photoBox = new VBox();
        photoBox.getChildren().addAll(photoCircle, addPhotoButton);
        photoBox.setAlignment(Pos.CENTER);
        addPhotoButton.getStyleClass().add("addPhotoButton");
        Label usernameLabel = new Label("Utilizator");
        Label passwordLabel = new Label("Parolă");

        passwordField = new PasswordField();
        usernameField = new TextField();
        usernameField.getStyleClass().add("text-field");
        passwordField.getStyleClass().add("text-field");

        Button registerButton = new Button("Înregistrează-te");
        registerButton.getStyleClass().add("buttonRegister");
        Button loginButton = new Button("Autentifică-te");
        loginButton.getStyleClass().add("buttonLogin");
        Hyperlink forgotPasswordLink = new Hyperlink("Ai uitat parola?");
        Separator separator = new Separator();
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.setAlignment(Pos.CENTER);
        grid.add(photoBox, 0, 1);
        grid.add(usernameField,0,2);
        usernameField.setPromptText("Nume utilizator");
        passwordField.setPromptText("Parolă");
        grid.add(passwordField, 0, 4);
        grid.add(loginButton,0, 6 );

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Verificare datele din fișierul JSON
            JSONArray usersArray;
            try {
                String content = new String(Files.readAllBytes(Paths.get("users.json")));
                usersArray = new JSONArray(content);
                boolean authenticated = false;
                for (int i = 0; i < usersArray.length(); i++) {
                    Object userObject = usersArray.get(i);

                    if (userObject instanceof JSONObject) {
                        JSONObject user = (JSONObject) userObject;

                        String storedPassword = null;
                        try {
                            storedPassword = user.getString("password");
                            String storedUsername = user.getString("username");
                            System.out.println(storedUsername);
                            System.out.println(storedPassword);
                            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                                authenticated = true;
                                break;
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                if (authenticated) {
                    // Autentificare reușită
                    DashboardGUI dashboard = new DashboardGUI();
                    dashboard.start(primaryStage);
                } else {
                    // Afișare mesaj de eroare
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setHeaderText("Autentificare eșuată");
                    alert.setContentText("Verificați utilizatorul și parola.");
                    alert.showAndWait();
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return;
            }


        });

        grid.add(forgotPasswordLink, 0, 7);
        grid.add(separator, 0, 8);
        grid.add(registerButton, 0, 9);
        root.getChildren().addAll(photoBox,grid);
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("styles.css");
        return scene;
    }
}
