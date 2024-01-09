package UserInterface;


import Main.GamePanel;

import java.awt.*;

public class PauseGame {
    GamePanel gamePanel;
    private  final Font font;
    private final int screenWidth;
    private final int screenHeight;

    public PauseGame(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        this.font = new Font("Arial",Font.BOLD,60);
        screenWidth = gamePanel.ScreenWidth;
        screenHeight = gamePanel.ScreenHeight;
    }
    public void draw(Graphics2D g2d){

        shadowScreen(g2d);
        drawString(g2d);
    }
    private void drawString(Graphics g2d){

        String text = "Paused";

        // Set font and color for the pause screen
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        // Calculate the position to center the text
        int textWidth = fontMetrics.stringWidth(text);
        int x = (screenWidth - textWidth) / 2;
        int y = screenHeight / 2;

        // Draw the pause screen text
        g2d.drawString(text, x, y);
    }
    private void shadowScreen(Graphics2D g2d){

        g2d.setColor(new Color(0,0,0,200));
        g2d.fillRect(0,0,screenWidth,screenHeight);

    }
}
