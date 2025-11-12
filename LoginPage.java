package app;
import javax.swing.*;

public class LoginPage extends JFrame {
    JTextField userField;
    JPasswordField passField;
    UserDAO dao = new UserDAO();

    public LoginPage() {
        setTitle("Login");
        setSize(300,160);
        setLayout(new java.awt.GridLayout(3,2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new JLabel("Username:"));
        userField = new JTextField(); add(userField);

        add(new JLabel("Password:"));
        passField = new JPasswordField(); add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> login()); add(loginBtn);

        JButton regBtn = new JButton("Register");
        regBtn.addActionListener(e -> new RegisterPage()); add(regBtn);

        setVisible(true);
    }

    void login(){
        Integer uid = dao.login(userField.getText(), new String(passField.getPassword()));
        if(uid!=null){ JOptionPane.showMessageDialog(this,"Login Success"); new ForumPage(uid); dispose();}
        else JOptionPane.showMessageDialog(this,"Invalid Login");
    }

    public static void main(String[] args){ new LoginPage(); }
}
