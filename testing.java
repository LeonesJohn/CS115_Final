import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class testing extends JFrame {

    public testing() {
        initUI();
    }

    private void initUI() {
        setTitle("Simple example");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a JLabel to hold the image
        JLabel label = new JLabel();

        // Load an image
        ImageIcon icon = new ImageIcon("fish.jpeg"); // Replace with the actual path to your image
        label.setIcon(icon);

        // Create a Box with BoxLayout for both vertical and horizontal centering
        Box box = Box.createVerticalBox();
        box.setAlignmentX(400);
        box.setAlignmentY(300);

        // Add an invisible component for vertical spacing
        box.add(Box.createHorizontalGlue());

        // Add the JLabel to the Box
        box.add(label);

        // Add an invisible component for vertical spacing
       // box.add(Box.createVerticalGlue());

        // Add the Box to the JFrame
        add(box);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new testing();
            ex.setVisible(true);
        });
    }
}