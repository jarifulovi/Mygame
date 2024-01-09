package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import Main.GamePanel;
public class SuperObject {


    public BufferedImage image;

    public String name;

    public boolean collision;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public int positionX,positionY;


    public void draw(Graphics2D g2d,GamePanel gamePanel){


        int positionDiffX =  (gamePanel.player.PositionX-gamePanel.player.PlayerPositionX);
        int positionSumX =  (gamePanel.player.PositionX+gamePanel.player.PlayerPositionX);
        int positionDiffY =  (gamePanel.player.PositionY-gamePanel.player.PlayerPositionY);
        int positionSumY = (gamePanel.player.PositionY+gamePanel.player.PlayerPositionY);

        int screenPositionX = positionX - positionDiffX;
        int screenPositionY = positionY - positionDiffY;
        if (this.positionX + gamePanel.TileSize > positionDiffX
                && this.positionX - gamePanel.TileSize < positionSumX
                && this.positionY + gamePanel.TileSize > positionDiffY
                && this.positionY - gamePanel.TileSize < positionSumY)

            g2d.drawImage(this.image, screenPositionX, screenPositionY, gamePanel.TileSize, gamePanel.TileSize, null);

    }
}
