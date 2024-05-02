import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class App extends JFrame implements MouseMotionListener {
    
    private int mouseX, mouseY;
    
    private int screenWidth = 500;
    private int screenHeight = 500;
    
    private int buttonWidth = 120;
    private int buttonHeight = 40;
    
    private JLabel titleLabel;
    private JLabel nameLabel, passLabel;
    private JTextField nameField;
    private JPasswordField passField;
    
    private JButton loginButton;
    private JLabel buttonCollider;
    
    public App() {
        this.setTitle("Kitty Karnival - Login");
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseMotionListener(this);
        this.getContentPane().setBackground(new Color(20, 20, 20));        

        init();
        addComponent();
        
        this.setVisible(true);
        
    }
    
    private void init() {
        titleLabel = new JLabel("Login");
        titleLabel.setBounds(0, 50, screenWidth, 100);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), Font.PLAIN, 24));
        
        nameLabel = new JLabel("Username");
        nameLabel.setBounds(10, 150, 100, 20);
        nameLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.PLAIN, 18));
        
        passLabel = new JLabel("Password");
        passLabel.setBounds(10, nameLabel.getY() + nameLabel.getHeight() + 10, 100, 20);
        passLabel.setFont(new Font(passLabel.getFont().getFontName(), Font.PLAIN, 18));
        
        nameField = new JTextField();
        nameField.setBounds(nameLabel.getX() + nameLabel.getWidth(), nameLabel.getY(), 
                screenWidth - nameLabel.getWidth() - 50, nameLabel.getHeight() + 10);
        passField = new JPasswordField();
        passField.setBounds(passLabel.getX() + passLabel.getWidth(), passLabel.getY(),
                screenWidth - passLabel.getWidth() - 50, passLabel.getHeight() + 10);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(screenWidth / 2 - buttonWidth / 2, 230, buttonWidth, buttonHeight);
        loginButton.setBackground(new Color(30, 30, 30));
        loginButton.setOpaque(true);
        loginButton.setFocusable(false);
        
        buttonCollider = new JLabel();
        buttonCollider.setBounds(loginButton.getX() - 15, loginButton.getY() - 15,
                buttonWidth + 30, buttonHeight + 30);;
    }
    
    private void addComponent() {
        this.add(titleLabel);
        this.add(nameLabel);
        this.add(passLabel);
        
        this.add(nameField);
        this.add(passField);
        
        this.add(loginButton);
        this.add(buttonCollider);
    }
    
    public static void main(String[]args) throws UnsupportedLookAndFeelException{
        UIManager.setLookAndFeel(new FlatMacDarkLaf());
        new App();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY() + 50;  
        
        Point point = new Point(mouseX - 10, mouseY - 85);
        if (!nameField.getText().isEmpty() && !passField.getText().isEmpty()) {
            if (buttonCollider.getBounds().contains(point)) {
                if (point.y < buttonCollider.getY() + buttonCollider.getHeight() / 2) {
                    if (loginButton.getY() + loginButton.getHeight() > screenHeight - loginButton.getHeight()) {
                        loginButton.setLocation(loginButton.getX(), point.y - 50);
                    } else {
                        loginButton.setLocation(loginButton.getX(), point.y + 20);
                    }
                } else {
                    
                    if (loginButton.getY() < passField.getY() + passField.getHeight()) {
                        loginButton.setLocation(loginButton.getX(), point.y + 20);
                    } else {
                        loginButton.setLocation(loginButton.getX(), point.y - 50);
                    }
                }
            }
            buttonCollider.setBounds(loginButton.getX() - 15, loginButton.getY() - 15,
                    buttonWidth + 30, buttonHeight + 30);

        }
    }


}
