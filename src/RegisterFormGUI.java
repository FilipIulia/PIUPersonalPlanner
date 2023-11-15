import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterFormGUI {
    private static TextField firstnameField;
    private static TextField lastnameField;
    private static TextField usernameField;
    private static DatePicker birthdayPicker;
    private static TextField email;
    private static PasswordField passwordField;


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
        Label usernameLabel = new Label("Utilizator:");
        Label birthdayLabel = new Label("Data nașterii:");
        Label passwordLabel = new Label("Parolă");

        firstnameField = new TextField();
        lastnameField = new TextField();
        usernameField = new TextField();
        passwordField = new PasswordField();
        birthdayPicker = new DatePicker();
        firstnameField.getStyleClass().add("text-field");
        lastnameField.getStyleClass().add("text-field");
        usernameField.getStyleClass().add("text-field");
        passwordField.getStyleClass().add("text-field");

        Button registerButton = new Button("Înregistrează-te");
        registerButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Check user data here

            if (username.equals("utilizator") && password.equals("parola")) {
                // Successful authentication
                DashboardGUI dashboard = new DashboardGUI();
                dashboard.start(primaryStage);
            } else {
                // Show an error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare");
                alert.setHeaderText("Autentificare eșuată");
                alert.setContentText("Verificați utilizatorul și parola.");
                alert.showAndWait();
            }
        });
        registerButton.getStyleClass().add("buttonRegister");

        grid.add(firstnameLabel, 0, 0);
        grid.add(firstnameField, 1, 0);
        grid.add(lastnameLabel, 0, 1);
        grid.add(lastnameField, 1, 1);
        grid.add(usernameLabel, 0, 2);
        grid.add(usernameField, 1, 2);
        grid.add(birthdayLabel, 0, 3);
        grid.add(birthdayPicker, 1, 3);
        grid.add(passwordLabel, 0, 4);
        grid.add(passwordField, 1, 4);
        grid.add(registerButton, 1, 5);
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("styles.css");
        return scene;
    }
}
