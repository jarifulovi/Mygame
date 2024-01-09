package UserInterface;

import Main.GamePanel;

import java.awt.*;

public class MenuInterface {
    GamePanel gamePanel;
    private final int screenHeight;
    private final int screenWidth;
    private final Font font;
    private final Color color;

    public MenuInterface(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        this.font = new Font("Arial",Font.BOLD,30);
        this.color = new Color(125,132,231);
        screenHeight = gamePanel.ScreenHeight;
        screenWidth = gamePanel.ScreenWidth;
    }
    public void draw(Graphics2D g2d){
        drawBackground(g2d);
        drawString(g2d);
        drawMenuStrings(g2d);
    }
    private void drawBackground(Graphics2D g2d){

        g2d.setColor(color);
        g2d.fillRect(0,0,screenWidth,screenHeight);
    }
    private void drawString(Graphics g2d){

        String text = "MY GAME";
        Font gamefont = new Font("Arial",Font.BOLD,80);
        g2d.setFont(gamefont);
        g2d.setColor(Color.LIGHT_GRAY);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        // Calculate the position to center the text
        int textWidth = fontMetrics.stringWidth(text);
        int x = (screenWidth - textWidth) / 2;
        int y = screenHeight / 4;

        g2d.drawString(text,x+3,y+3);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text,x,y);

    }
    private void drawMenuStrings(Graphics2D g2d){

        drawMenuString(g2d,"Play",0);
        drawMenuString(g2d,"Load game",1);
        drawMenuString(g2d,"Quit",2);

    }
    private void drawMenuString(Graphics2D g2d,String text,int index){
        int textWidth,x,y;

        g2d.setFont(font);
        g2d.setColor(Color.WHITE);

        textWidth = g2d.getFontMetrics().stringWidth(text);
        x = (screenWidth - textWidth) / 2;
        y = (screenHeight / 2) + (index * 2 * font.getSize());

        g2d.drawString(text, x, y);

        if (gamePanel.menuStateIndex == index) {
            g2d.setColor(new Color(50, 50, 50));
            g2d.drawString(text, x, y);
        }

    }

}
