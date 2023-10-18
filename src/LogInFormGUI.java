import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInFormGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogInFormGUI(JFrame receivedFrame) {
        setTitle("Autentificare");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Utilizator:");
        JLabel passwordLabel = new JLabel("Parolă:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Log in");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                //date hardcodate momentan
                if (username.equals("utilizator") && password.equals("parola")) {

                    //JOptionPane.showMessageDialog(LoginForm.this, "Autentificare reușită!");
                    DashboardGUI Obj = new DashboardGUI();
                    receivedFrame.setVisible(false);
                    Obj.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LogInFormGUI.this, "Autentificare eșuată. Verificați utilizatorul și parola.");
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Spațiu gol
        panel.add(loginButton);

        add(panel);

        setVisible(true);
    }

}
