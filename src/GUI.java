import javax.swing.*;

public class GUI {
    final private JFrame frame;
    public GUI(){
       frame = new JFrame();
        new LogInFormGUI(frame);
    }
    public static void main(String[] args){
       new GUI();
    }
}
