package UserInterface;

import Main.GamePanel;

import java.awt.*;

public class DialogScreen {

    GamePanel gamePanel;
    private final Font font;

    public DialogScreen(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        font = new Font("Arial",Font.PLAIN, 25);
    }
    public void draw(Graphics2D g2d){

        drawDialogBox(g2d);
        drawString(g2d);
    }
    private void drawDialogBox(Graphics2D g2d){

        int x = gamePanel.TileSize;
        int y = gamePanel.TileSize;
        int width = gamePanel.ScreenWidth - 2*gamePanel.TileSize;
        int height = gamePanel.ScreenWidth/4;
        int archWidth = 20;
        int arcHeight = 20;

        Color color = new Color(0,0,0, 200);
        Stroke stroke = new BasicStroke(6f);
        g2d.setStroke(stroke);
        g2d.setColor(color);
        g2d.fillRoundRect(x,y,width,height,archWidth,arcHeight);

    }
    private void drawString(Graphics2D g2d){


        String dialog = gamePanel.npc.get(gamePanel.player.indexNpc).speak();
        int padding = 10;
        int x = gamePanel.TileSize + padding; // Adding some padding
        int y = gamePanel.TileSize + padding; // Adding some padding

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        int lineHeight = fontMetrics.getHeight();
        int currentY = y + lineHeight; // Start slightly down to leave some space

        String[] words = dialog.split("\\s");
        StringBuilder currentLine = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            int lineWidth = fontMetrics.stringWidth(currentLine + " " + word);

            if (lineWidth <= gamePanel.ScreenWidth - 2 * gamePanel.TileSize - (2*padding)) {
                currentLine.append(" ").append(word);
            } else {
                g2d.drawString(currentLine.toString(), x, currentY);
                currentY += lineHeight;
                currentLine = new StringBuilder(word);
            }
        }

        g2d.drawString(currentLine.toString(), x, currentY);
    }
}
