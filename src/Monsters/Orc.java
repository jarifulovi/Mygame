package Monsters;

import Main.GamePanel;
import Character.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Orc extends Characters {

    BufferedImage left_attack, right_attack;

    public Orc(GamePanel gamePanel) {
        super(gamePanel);

        walkState = true;
        attackState = false;
        deadState = false;
        solidArea = new Rectangle(0, 0, gamePanel.TileSize, gamePanel.TileSize);
        collisionFlag = false;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultPosition();
        getPlayerImage();
    }

    public void setDefaultPosition() {
        PositionX = gamePanel.TileSize * 25;
        PositionY = gamePanel.TileSize * 11;
        characterSpeed = 1;
        directionCount = 0;
        directionFrequency = 200;
        spriteFrequency = 20;
    }

    private int npcDirection() {

        if (directionCount < directionFrequency) return 1;
        else if (directionCount < directionFrequency * 2) return 2;
        else if (directionCount < directionFrequency * 3) return 3;
        else return 4;
    }

    public void playerUpdate() {


        if (attackState) {

            spriteCount++;
            if(spriteCount >spriteFrequency) {
                if (spriteNum == 0 || spriteNum == 2) spriteNum = 1;
                else if (spriteNum == 1) spriteNum = 2;
                spriteCount = 0;
            }

            attackState = gamePanel.collisionCheck.checkPlayerCollision(this);
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
        attackState = gamePanel.collisionCheck.checkPlayerCollision(this);

        if (!collisionFlag) {
            PositionX = nextX;
            PositionY = nextY;
        }

        spriteCount++;
        if(spriteCount >spriteFrequency) {
            if (spriteNum == 0 || spriteNum == 2) spriteNum = 1;
            else if (spriteNum == 1) spriteNum = 2;
            spriteCount = 0;
        }

    }


    public void draw(Graphics2D g2d){

        if(walkState) super.draw(g2d);

        else if(attackState){
            interactDirection(gamePanel.player);
            BufferedImage image;

            // this is the default image of the player
            image = defaultImage;

            if(Objects.equals(direction, "up")){

                if(spriteNum==1)
                    image = up1;
                else
                    image = up2;
            }
            else if(Objects.equals(direction, "down")){
                if(spriteNum==1)
                    image = down1;
                else
                    image = down2;
            }
            else if(Objects.equals(direction, "left")){
                if(spriteNum==1)
                    image = left1;
                else
                    image = left_attack;
            }
            else if(Objects.equals(direction, "right")){
                if(spriteNum==1)
                    image = right1;
                else
                    image = right_attack;
            }
            int positionDiffX =  (gamePanel.player.PositionX-gamePanel.player.PlayerPositionX);
            int positionSumX =  (gamePanel.player.PositionX+gamePanel.player.PlayerPositionX);
            int positionDiffY =  (gamePanel.player.PositionY-gamePanel.player.PlayerPositionY);
            int positionSumY = (gamePanel.player.PositionY+gamePanel.player.PlayerPositionY);

            int screenPositionX = PositionX - positionDiffX;
            int screenPositionY = PositionY - positionDiffY;

            if (this.PositionX + gamePanel.TileSize > positionDiffX
                    && this.PositionX - gamePanel.TileSize < positionSumX
                    && this.PositionY + gamePanel.TileSize > positionDiffY
                    && this.PositionY - gamePanel.TileSize < positionSumY)

                g2d.drawImage(image,screenPositionX,screenPositionY,gamePanel.TileSize,gamePanel.TileSize,null);


        }
        else if(deadState){
            // implement dead sprites
        }

    }

    public void getPlayerImage(){
        try {
            defaultImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_default.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_left2.png")));
            left_attack = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_left_attack.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_right2.png")));
            right_attack = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Monsters/Orc/monster_orc_right_attack.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
