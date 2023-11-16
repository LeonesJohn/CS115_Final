import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;               //thats alotta imports
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

    public Game() {

        initUI();
    }

    private void initUI() {
        
        setTitle("Deal Or No Deal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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

        JPanel textPanel = new JPanel(new GridLayout(2, 1)); // Use a nested panel for the text labels
        textPanel.setOpaque(false);
        textPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0)); 

        JLabel text1 = new JLabel("Add game stuff idk how deal or no deal works", SwingConstants.CENTER);
        JLabel text2 = new JLabel("Make use of jpanels with gridlayout and buttons n stuff", SwingConstants.CENTER);
        text1.setFont(new Font("Artifakt Element Book", Font.PLAIN, 20));
        text1.setForeground(Color.YELLOW);
        text2.setFont(new Font("Artifakt Element Book", Font.PLAIN, 20));
        text2.setForeground(Color.YELLOW);
        textPanel.add(text1, BorderLayout.CENTER);
        textPanel.add(text2, BorderLayout.CENTER);
        contentPane.add(textPanel, BorderLayout.CENTER);
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