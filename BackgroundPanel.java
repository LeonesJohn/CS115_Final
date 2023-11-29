import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
  private Image backgroundImage;

  public BackgroundPanel(String path) {
    backgroundImage = new ImageIcon(path).getImage();
    this.setLayout(new BorderLayout());
  }

  // Override the paintComponent method
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Draw the image, scaled to fit the entire panel
    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
  }
}