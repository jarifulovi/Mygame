package UserInterface;
import Character.*;
import Main.GamePanel;

import java.awt.*;

public class PlayerHP {

    GamePanel gamePanel;
    int totalSegment = 5;
    public PlayerHP(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2d){

        int x = gamePanel.ScreenHeight/10;
        int y = gamePanel.ScreenHeight/10;
        int width = x/2;
        int height = y/4;
        int currentSegment = calculateHPSegment(gamePanel.player.playerHP);

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(6F));

        for(int i=0;i<totalSegment;i++) {

            if(i < currentSegment) {
                g2d.setColor(Color.red);
                g2d.fillRect(x + (width * i), y, width, height);
            }
            // Draw the outline (edge) of the rectangle
            g2d.setColor(Color.BLACK); // Change color for the outline
            g2d.drawRect(x + (width * i), y, width, height);

        }
    }
    private int calculateHPSegment(int hp){

        if(hp<1) return 0;

        return ((hp-1) / (100/totalSegment)) + 1;
    }
}
