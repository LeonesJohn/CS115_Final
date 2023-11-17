import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;               //thats alotta imports
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

public class Game extends JFrame {


    private int currentCase;
    private boolean start = true;
    private JLabel leftText;

    Random randy = new Random();

    private static final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    private static double[] moneyChoices = {0.01,1,5,10,25,50,75,100,200,300,400,500,750,1000,5000,10000,25000,50000,75000,100000,200000,300000,400000,500000,750000,1000000};

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

    private int caseCount = 25; //not counting the first chosen
    private void gameScene(JPanel contentPane){
        
        contentPane.removeAll();
        contentPane.repaint();

        ImageIcon icon = new ImageIcon("nodealordeal.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(486, 64, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);



        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0)); 

        JPanel possibleChoices = new JPanel(new GridLayout(13, 2));
        possibleChoices.setOpaque(false);
        possibleChoices.setBorder(BorderFactory.createEmptyBorder(10, 25, 0, 5));

        JLabel[] possibleChoice = new JLabel[26];
        for (int i = 0; i < 26; i++){
            possibleChoice[i] = (new JLabel(moneyFormat.format(moneyChoices[i]))
            {{
                setFont(new Font("Artifakt Element Book", Font.PLAIN, 12));
                setPreferredSize(new Dimension(100,50)); 
                setBackground(Color.YELLOW); 
                setForeground(Color.BLACK);
                setOpaque(true);
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }});

            possibleChoices.add(possibleChoice[i]);
        }

        JPanel cases = new JPanel(new  GridLayout(7, 4)); //26 cases at the start of the game
        cases.setOpaque(false);
        cases.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 30));
        JButton[] caseButtons = new JButton[26];

        for(int i = 1; i <= 26; i++){
            final int caseNum = i;

            caseButtons[i-1] = (new JButton(Integer.toString(caseNum))
            {{
                setPreferredSize(new Dimension(121,50));
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.BLACK); 
                setFont(new Font("Artifakt Element Book", Font.BOLD, 30));


                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setBackground(Color.BLACK); 
                        setForeground(Color.BLACK); 
                        setBorder(null);
                        setText("");
                        setEnabled(false);

                        if(start == true){
                            currentCase = caseNum;
                        }
                        else{
                          int num = 0;
                          do {
                            num = randy.nextInt(26); // Generate a number between 0 and 25 inclusive
                        } while (moneyChoices[num] == 0);
                          
                          
                          possibleChoice[num].setBackground(new Color(139, 128, 0)); // RGB values for Dark Khaki
                          possibleChoice[num].setForeground(Color.DARK_GRAY);

                          moneyChoices[num] = 0;

                          caseCount -=1 ;
                          System.out.println("Current case: " + currentCase);
                        }
                        updateGame(contentPane, bottomPanel);
                    }
                });

            }});

            cases.add(caseButtons[i-1]);
        }
        

        leftText = new JLabel("Choose a case", SwingConstants.LEFT);

       // JLabel text2 = new JLabel("hot sweaty men", SwingConstants.CENTER);
        leftText.setFont(new Font("Artifakt Element Book", Font.PLAIN, 50));
        leftText.setForeground(Color.YELLOW);
       // text2.setFont(new Font("Artifakt Element Book", Font.PLAIN, 20));
       // text2.setForeground(Color.YELLOW);
        bottomPanel.add(leftText, BorderLayout.CENTER);
       // bottomPanel.add(text2, BorderLayout.CENTER);

        contentPane.add(cases, BorderLayout.EAST);
        contentPane.add(possibleChoices, BorderLayout.WEST);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        contentPane.add(logo, BorderLayout.NORTH);
        setContentPane(contentPane);
    }

    public void updateGame(JPanel contentPane, JPanel bottomPanel){
        
        if(start == true){
            JLabel curCase = new JLabel(Integer.toString(currentCase));
            curCase.setOpaque(true);
            curCase.setPreferredSize(new Dimension(121, 50));
            curCase.setBackground(Color.LIGHT_GRAY);
            curCase.setForeground(Color.BLACK); 
            curCase.setFont(new Font("Artifakt Element Book", Font.BOLD, 20));
            curCase.setHorizontalAlignment(SwingConstants.CENTER); //text alignment
            curCase.setVerticalAlignment(SwingConstants.CENTER);

            bottomPanel.removeAll(); 

            bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // new layout
            
            

            leftText.setText("Current case: ");
            leftText.setFont(new Font("Artifakt Element Book", Font.PLAIN, 30));
            start = false;
            
            bottomPanel.add(leftText); 
            bottomPanel.add(curCase);
            
            
        }

    
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