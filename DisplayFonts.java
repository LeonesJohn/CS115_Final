import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.*;


/* FONTS TESTING */

public class DisplayFonts {
    public static void main(String[] args) {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        String text = "bienvenidos";
        for (String fontName : fontNames) {
            Font font = new Font(fontName, Font.PLAIN, 20);
            JLabel label = new JLabel(text);
            label.setFont(font);
            JOptionPane.showMessageDialog(null, label, fontName, JOptionPane.PLAIN_MESSAGE);
        }
    }
}