import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //thats alotta imports
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
import java.util.Scanner;
import java.util.concurrent.Flow;

public class Game extends JFrame {

    private int currentCase;
    private boolean start = true;
    private JLabel leftText;
    private JLabel rightText;
    private JLabel rightTextNum;

    Random randy = new Random();

    private static final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    private static double[] moneyChoices = { 0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000,
            10000, 25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000 };

    public Game() {
        startScreen();
    }

    private void startScreen() {

        setTitle("No Deal Or Deal");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Create a JPanel to hold components
        BackgroundPanel contentPane = new BackgroundPanel("bg.jpeg"); // Replace with your image path
        // JPanel contentPane = new JPanel(new BorderLayout());
        JPanel textPanel = new JPanel(new GridLayout(2, 1)); // Use a nested panel for the text labels
        contentPane.setBackground(Color.BLACK); // Set the background color of the panel

        ImageIcon icon = new ImageIcon("nodealordeal.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(486, 64, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);
        contentPane.add(logo, BorderLayout.NORTH);

        // Add an empty border to create space at the top
        textPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0));

        // Add action buttons

        Dimension buttonSize = new Dimension(100, 100);
        ImageIcon buttonIcon = new ImageIcon("case.png");
        // Resize the image to fit the button
        Image image = buttonIcon.getImage();
        Image resizedImage = image.getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_DEFAULT);
        buttonIcon = new ImageIcon(resizedImage);
        Font buttonFont = new Font("Artifakt Element Book", Font.BOLD, 18);

        JButton startButton = new JButton();
        startButton.setFont(buttonFont);
        startButton.setText("START");
        startButton.setPreferredSize(buttonSize); // Set button size
        startButton.setIcon(buttonIcon);
        startButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some space
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startButton.setVerticalTextPosition(SwingConstants.CENTER);
        startButton.setForeground(Color.BLACK);

        JButton helpButton = new JButton();
        helpButton.setFont(buttonFont);
        helpButton.setText("HELP");
        helpButton.setPreferredSize(buttonSize); // Set button size
        helpButton.setIcon(buttonIcon);
        helpButton.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some space
        helpButton.setHorizontalTextPosition(SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(SwingConstants.CENTER);
        helpButton.setForeground(Color.BLACK);

        // Add ActionListener to the button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameScene(contentPane);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Panel to hold the button
        buttonPanel.setOpaque(false);
        buttonPanel.add(startButton); // Add button to the panel
        buttonPanel.add(helpButton);

        contentPane.add(buttonPanel, BorderLayout.CENTER);

        setContentPane(contentPane); // Set the content pane to the customized JPanel
    } // end of start screen

    private int caseCount = 25; // not counting the first chosen
    private int round = 1; // for case removal numbers

    private void gameScene(JPanel contentPane) {

        contentPane.removeAll();
        contentPane.repaint();

        ImageIcon icon = new ImageIcon("nodealordeal.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(486, 64, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));

        JPanel possibleChoices = new JPanel(new GridLayout(13, 2));
        possibleChoices.setOpaque(false);
        possibleChoices.setBorder(BorderFactory.createEmptyBorder(10, 25, 0, 5));

        JLabel[] possibleChoice = new JLabel[26];
        for (int i = 0; i < 26; i++) {
            possibleChoice[i] = (new JLabel(moneyFormat.format(moneyChoices[i])) {
                {
                    setFont(new Font("Artifakt Element Book", Font.PLAIN, 12));
                    setPreferredSize(new Dimension(100, 50));
                    setBackground(Color.YELLOW);
                    setForeground(Color.BLACK);
                    setOpaque(true);
                    setBorder(BorderFactory.createLineBorder(Color.GRAY));
                }
            });

            possibleChoices.add(possibleChoice[i]);
        }

        JPanel cases = new JPanel(new GridLayout(7, 4)); // 26 cases at the start of the game
        cases.setOpaque(false);
        cases.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 30));
        JButton[] caseButtons = new JButton[26];

        for (int i = 1; i <= 26; i++) { // create case buttons
            final int caseNum = i;

            caseButtons[i - 1] = (new JButton(Integer.toString(caseNum)) {
                {
                    setPreferredSize(new Dimension(121, 50));
                    setBackground(Color.LIGHT_GRAY);
                    setForeground(Color.BLACK);
                    setFont(new Font("Artifakt Element Book", Font.BOLD, 30));

                    addActionListener(new ActionListener() { // button action methods
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            setBackground(Color.BLACK);
                            setForeground(Color.BLACK);
                            setBorder(null);
                            setText("");
                            setEnabled(false);

                            if (start == true) {
                                currentCase = caseNum;
                            } else {
                                int num = 0;
                                do {
                                    num = randy.nextInt(26); // Generate a number between 0 and 25 inclusive
                                } while (moneyChoices[num] == 0);

                                possibleChoice[num].setBackground(new Color(139, 128, 0)); // RGB values for Dark Khaki
                                possibleChoice[num].setForeground(Color.DARK_GRAY);

                                openCase(moneyChoices[num], caseNum);
                                moneyChoices[num] = 0;

                                caseCount -= 1;
                                System.out.println("Current case: " + currentCase);

                            }

                            // update text
                            if (start == true) {
                                JLabel curCase = new JLabel(Integer.toString(currentCase));
                                curCase.setOpaque(true);
                                curCase.setPreferredSize(new Dimension(121, 50));
                                curCase.setBackground(Color.LIGHT_GRAY);
                                curCase.setForeground(Color.BLACK);
                                curCase.setFont(new Font("Artifakt Element Book", Font.BOLD, 20));
                                curCase.setHorizontalAlignment(SwingConstants.CENTER); // text alignment
                                curCase.setVerticalAlignment(SwingConstants.CENTER);

                                leftText.setText("Current case: ");
                                leftText.setFont(new Font("Artifakt Element Book", Font.PLAIN, 30));
                                start = false;

                                bottomPanel.add(curCase);
                                bottomPanel.add(new JLabel() {
                                    {
                                        setPreferredSize(new Dimension(20, 20));
                                    }
                                });
                                bottomPanel.add(rightText);
                                bottomPanel.add(rightTextNum);
                                rightText.setText("Cases left to open: ");
                                rightTextNum.setText("6");
                            } else {
                                rightTextNum.setText(Integer.toString(openCases()));
                            }
                        } // end of button action method
                    });

                }
            });

            cases.add(caseButtons[i - 1]);
        } // end of adding buttons method

        leftText = new JLabel("Choose a case", SwingConstants.LEFT);
        leftText.setFont(new Font("Artifakt Element Book", Font.PLAIN, 50));
        leftText.setForeground(Color.YELLOW);

        rightText = new JLabel();
        rightTextNum = new JLabel();
        rightText.setFont(new Font("Artifakt Element Book", Font.PLAIN, 30));
        rightText.setForeground(Color.YELLOW);

        rightTextNum.setFont(new Font("Artifakt Element Book", Font.PLAIN, 30));
        rightTextNum.setForeground(Color.YELLOW);

        bottomPanel.add(leftText);

        contentPane.add(cases, BorderLayout.EAST);
        contentPane.add(possibleChoices, BorderLayout.WEST);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
        contentPane.add(logo, BorderLayout.NORTH);
        setContentPane(contentPane);
    }

    private int casesToOpen = 6; // 6,5,4,3,2,1,1,1

    private int openCases() {
        if (casesToOpen == 1) {
            round++;
            switch (round) {
                case 2:
                    casesToOpen = 5;
                    break;
                case 3:
                    casesToOpen = 4;
                    break;
                case 4:
                    casesToOpen = 3;
                    break;
                case 5:
                    casesToOpen = 2;
                    break;
                case 6:
                    casesToOpen = 1;
                    break;
                case 7:
                    casesToOpen = 1;
                    break;
                case 8:
                    casesToOpen = 1;
                    break; // 2 cases left
            }

            bankerDeal();
        } else {
            casesToOpen--;
        }
        return casesToOpen;
    }

    private void openCase(double dinero, int casenumber) { // open case method
        ImageIcon icon = new ImageIcon("nodealordeal.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(486, 64, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);

        Container curContentPane = getContentPane();

        JPanel caseopen = new JPanel(new GridLayout(5, 1));
        caseopen.setPreferredSize(new Dimension(800, 600));
        caseopen.setBackground(Color.BLACK);
        JPanel crase = new JPanel() {
            {
                setOpaque(false);
            }
        };
        JLabel showcase = new JLabel(Integer.toString(casenumber)) {
            {
                setPreferredSize(new Dimension(200, 100));
                setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally
                setVerticalAlignment(SwingConstants.CENTER); // Center the text vertically
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.BLACK);
                setFont(new Font("Artifakt Element Book", Font.BOLD, 30));
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
                setOpaque(true);
            }
        };

        JLabel text1 = new JLabel("This case had: ");
        JLabel text2 = new JLabel(moneyFormat.format(dinero));
        text1.setFont(new Font("Artifakt Element Book", Font.BOLD, 50));
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setForeground(Color.YELLOW);
        text2.setFont(new Font("Artifakt Element Book", Font.BOLD, 50));
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setForeground(Color.YELLOW);

        JButton continueButton = new JButton("Continue") {
            {
                setPreferredSize(new Dimension(121, 50));
                setBackground(Color.LIGHT_GRAY);
                setForeground(Color.BLACK);
                setFont(new Font("Artifakt Element Book", Font.BOLD, 30));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setContentPane(curContentPane);
                    }
                });
            }
        };

        crase.add(showcase);
        caseopen.add(logo);
        caseopen.add(crase);
        caseopen.add(text1);
        caseopen.add(text2);
        caseopen.add(continueButton);

        setContentPane(caseopen);
    } // end of open case method

    private void bankerDeal() {
        ImageIcon icon = new ImageIcon("nodealordeal.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(486, 64, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(icon);

        Container curContentPane = getContentPane();

        JPanel caseopen = new JPanel(new FlowLayout(FlowLayout.CENTER));
        caseopen.setPreferredSize(new Dimension(800, 600));
        caseopen.setBackground(Color.BLACK);

        JLabel text1 = new JLabel("Banker offer: ");
        JLabel text2 = new JLabel(moneyFormat.format(3000000));
        text1.setFont(new Font("Artifakt Element Book", Font.BOLD, 50));
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setForeground(Color.YELLOW);
        text2.setFont(new Font("Artifakt Element Book", Font.BOLD, 50));
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setForeground(Color.YELLOW);

        caseopen.add(logo);
        caseopen.add(text1);
        caseopen.add(text2);
        // caseopen.add(continueButton);

        setContentPane(caseopen);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
          Scanner scanner = new Scanner(System.in);

        File file = new File("Wistle.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();

        
      
        EventQueue.invokeLater(() -> {
            System.out.println("Hello");
            var ex = new Game();

            

            ex.setVisible(true);
        });
    }
}
