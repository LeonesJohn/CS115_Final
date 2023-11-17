import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;               //thats alotta imports
import java.text.NumberFormat;
import java.util.Currency;

import javax.naming.ldap.StartTlsRequest;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class Game extends JFrame {

    private double money;
    private static final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    private static final double[] moneyChoices = {0.01,1,5,10,25,50,75,100,200,300,400,500,750,1000,5000,10000,25000,50000,75000,100000,200000,300000,400000,500000,750000,1000000};

    public double getMoney(){ return money; }
    public void setMoney(double m){
        if (m >= 0){
            money = m;
        }
    }
    public void addMoney(double m){
        money += m;
    }


    public Game() {
        startScreen();
    }

    private void startScreen() {
        
        setTitle("Deal Or No Deal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Create a JPanel to hold components
        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel textPanel = new JPanel(new GridLayout(2, 1)); // Use a nested panel for the text labels

        contentPane.setBackground(Color.BLACK); // Set the background color of the panel

        /* // Create a JLabel to hold the image
        JLabel label = new JLabel();

        // Load an image
        ImageIcon icon = new ImageIcon("lebronjames.png"); // Replace with the actual path to your image
        Image image = icon.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT);
        icon = new ImageIcon(image);
        label.setIcon(icon); */

        //welcome text
        JLabel welcomeText = new JLabel("BIENVENIDOS");
        welcomeText.setFont(new Font("Artifakt Element Book", Font.BOLD, 50));
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setForeground(Color.YELLOW);

        JLabel additionalText = new JLabel("a deal or no deal");
        additionalText.setFont(new Font("Artifakt Element Book", Font.PLAIN, 20));
        additionalText.setHorizontalAlignment(SwingConstants.CENTER);
        additionalText.setForeground(Color.YELLOW);


        // Add an empty border to create space at the top
        textPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0)); 

        // Add the label and welcomeText to the contentPane
        /* contentPane.add(label, BorderLayout.CENTER); */

        textPanel.setOpaque(false); // Make the panel transparent
        textPanel.add(welcomeText); //adding jlabels
        textPanel.add(additionalText);
        contentPane.add(textPanel, BorderLayout.NORTH);


        JButton button = new JButton("empezar"); // Create a button
        button.setFont(new Font("Artifakt Element Book", Font.BOLD, 18)); 
        button.setPreferredSize(new Dimension(200, 50)); // Set button size
        button.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add some space

         // Add ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameScene(contentPane);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to hold the button
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(button); // Add button to the panel

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(contentPane); // Set the content pane to the customized JPanel
    }

    private void gameScene(JPanel contentPane){
        contentPane.removeAll();
        contentPane.repaint();

        ImageIcon icon = new ImageIcon("nodealordeal.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);


        JPanel cases = new JPanel(new  GridLayout(7, 4)); //26 cases at the start of the game

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); 

        JPanel possibleChoices = new JPanel(new GridLayout(13, 2));
        possibleChoices.setOpaque(false);
        possibleChoices.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));

        JLabel[] possibleChoice = new JLabel[26];
        for (int i = 0; i < 26; i++){
            possibleChoice[i] = (new JLabel(moneyFormat.format(moneyChoices[i]))
            {{
                setFont(new Font("Artifakt Element Book", Font.PLAIN, 12));
                setPreferredSize(new Dimension(100,50)); 
                setBackground(Color.YELLOW); 
                setOpaque(true);
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }});

            possibleChoices.add(possibleChoice[i]);
        }
        

        JLabel text1 = new JLabel("im thinking the panel on the left is show the possible money avaliable", SwingConstants.CENTER);
        JLabel text2 = new JLabel("and on the right is where we click the cases, and down here is dialogue", SwingConstants.CENTER);
        text1.setFont(new Font("Artifakt Element Book", Font.PLAIN, 20));
        text1.setForeground(Color.YELLOW);
        text2.setFont(new Font("Artifakt Element Book", Font.PLAIN, 20));
        text2.setForeground(Color.YELLOW);
        textPanel.add(text1, BorderLayout.CENTER);
        textPanel.add(text2, BorderLayout.CENTER);

        contentPane.add(possibleChoices, BorderLayout.WEST);
        contentPane.add(textPanel, BorderLayout.SOUTH);
        contentPane.add(logo, BorderLayout.NORTH);
        setContentPane(contentPane);
        
    }

    public static void main(String[] args) {
        

        EventQueue.invokeLater(() -> {
            System.out.println("Hello");
            var ex = new Game();

            

            ex.setVisible(true);
        });
    }
}