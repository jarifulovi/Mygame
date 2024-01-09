package Monsters;
import Character.*;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Green_slime extends Characters {

    public int damage = 1;
    public int attackSpeed = 10;

    public Green_slime(GamePanel gamePanel){
        super(gamePanel);

        solidArea = new Rectangle(0,gamePanel.TileSize/4,gamePanel.TileSize,gamePanel.TileSize);
        collisionFlag = false;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultPosition();
        getPlayerImage();
    }
    public void setDefaultPosition(){
        PositionX = gamePanel.TileSize *10;
        PositionY = gamePanel.TileSize *42;
        characterSpeed = 1;
        directionCount = 0;
        directionFrequency = 100;
        spriteFrequency = 20;
    }
    private int npcDirection(){

        if(directionCount < directionFrequency) return 1;
        else if(directionCount < directionFrequency*2) return 2;
        else if(directionCount < directionFrequency*3) return 3;
        else  return 4;
    }
    public void playerUpdate() {

        if(attackState){
            // this is when the monster is in attack mode with the player
            return;
        }

        directionCount++;
        if(directionCount==directionFrequency*4) directionCount = 0;

        int nextX = PositionX;
        int nextY = PositionY;

        if (npcDirection()==1) {
            nextY -= characterSpeed;
            direction = "up";
        }
        else if (npcDirection()==3) {
            nextY += characterSpeed;
            direction = "down";
        }
        else if (npcDirection()==2) {
            nextX -= characterSpeed;
            direction = "left";
        }
        else {
            nextX += characterSpeed;
            direction = "right";
        }

        collisionFlag = false;

        // object collision check
        gamePanel.collisionCheck.checkTilesCollision(this);
        gamePanel.collisionCheck.checkObjectCollision(this,false);
        gamePanel.collisionCheck.checkPlayerCollision(this);

        if (!collisionFlag) {
            PositionX = nextX;
            PositionY = nextY;
        }


        spriteCount++;
        if (spriteCount > spriteFrequency) {
            if (spriteNum == 0 || spriteNum == 2) spriteNum = 1;
            else if (spriteNum == 1) spriteNum = 2;
            spriteCount = 0;
        }
    }

    public void getPlayerImage(){
        try {
            defaultImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_1.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Green_slime/greenslime_down_2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
