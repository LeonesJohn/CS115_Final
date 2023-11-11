import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class SimpleEx extends JFrame {

    public SimpleEx() {

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
        ImageIcon icon = new ImageIcon("lebronjames.png"); // Replace with the actual path to your image
        Image image = icon.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT); // Adjust the width and height as needed
        icon = new ImageIcon(image);
        label.setIcon(icon);


        //this doesnt work bruh

        // Create a Box to hold the label in the center
        // Create a Box with BoxLayout for both vertical and horizontal centering
        Box box = Box.createVerticalBox();
        box.setAlignmentX(Box.CENTER_ALIGNMENT);
        box.setAlignmentY(Box.CENTER_ALIGNMENT);
        box.add(label);
        // Add the box to the JFrame using BorderLayout.CENTER
        add(box);



        /* 
        
        label.setBounds(400, 300, 200, 150);

         // Set layout manager to null for absolute positioning
        setLayout(null); 

        // Set layout manager to center the component
        setLayout(new FlowLayout(FlowLayout.CENTER)); */

        // Add the JLabel to the JFrame
        //add(label);
    }

    public static void main(String[] args) {
        

        EventQueue.invokeLater(() -> {

            var ex = new SimpleEx();
            

            ex.setVisible(true);
        });
    }
}