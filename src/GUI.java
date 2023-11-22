import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.LogInFormGUI;
import view.RegisterFormGUI;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Aplicație");

        // Inițierea și afișarea formularului de înregistrare
        Stage registerStage = new Stage();
        registerStage.setTitle("Înregistrare"); // Setează titlul pentru formularul de înregistrare
        Scene registerScene = RegisterFormGUI.createRegisterScene(registerStage);
        registerStage.setScene(registerScene);
        registerStage.show();

        // Inițierea și afișarea formularului de autentificare
        Stage loginStage = new Stage();
        loginStage.setTitle("Autentificare"); // Setează titlul pentru formularul de autentificare
        Scene loginScene = LogInFormGUI.createLogInScene(loginStage);
        loginStage.setScene(loginScene);
        loginStage.show();

    }

}
