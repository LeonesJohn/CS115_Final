import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class testing extends JFrame {

    public testing() {
        initUI();
    }

    private void initUI() {
        setTitle("Simple example");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();                           //WE USING JPANELS NOT THIS IN DA GAME FYI
        layeredPane.setPreferredSize(new Dimension(800, 600));

        ImageIcon icon1 = new ImageIcon("lebronjames.png");  //ICON FILE

        JButton button = new JButton("Press to die"); //BUTTON
        button.setIcon(icon1);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false); //
        button.setHorizontalTextPosition(JButton.CENTER); // these 3 needed to overlay text over image
        button.setVerticalTextPosition(JButton.CENTER); //
        button.setPreferredSize(new Dimension(800, 600)); //button press dimensions
        button.setBounds(0, 0, 800, 600); 
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);
        button.addActionListener(e -> {
            System.out.println("kablam");
        });

        add(layeredPane);
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Freddy_faz.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();

        EventQueue.invokeLater(() -> {
            var ex = new testing();
            ex.setVisible(true);
        });
    }
}