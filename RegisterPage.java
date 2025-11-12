package app;
import javax.swing.*;

public class RegisterPage extends JFrame {
    JTextField u, n;
    JPasswordField p;
    UserDAO dao = new UserDAO();

    public RegisterPage(){
        setTitle("Register");
        setSize(300,200);
        setLayout(new java.awt.GridLayout(4,2));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new JLabel("Username:")); u = new JTextField(); add(u);
        add(new JLabel("Password:")); p = new JPasswordField(); add(p);
        add(new JLabel("Display Name:")); n = new JTextField(); add(n);

        JButton b = new JButton("Register");
        b.addActionListener(e -> {
            if(dao.register(u.getText(),new String(p.getPassword()),n.getText()))
                { JOptionPane.showMessageDialog(this,"Done"); dispose();}
            else JOptionPane.showMessageDialog(this,"Failed");
        });
        add(b);

        setVisible(true);
    }
}
