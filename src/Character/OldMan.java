package Character;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class OldMan extends Characters{

    int directionFrequency;
    int spriteFrequency;
    ArrayList<String> dialog1;
    public OldMan(GamePanel gamePanel){
        super(gamePanel);

        dialog1 = new ArrayList<>();
        solidArea = new Rectangle(0,0,gamePanel.TileSize,gamePanel.TileSize);
        collisionFlag = false;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultPosition();
        setDialogs();
        getPlayerImage();
    }
    public void setDefaultPosition(){
        PositionX = gamePanel.TileSize *25;
        PositionY = gamePanel.TileSize *11;
        characterSpeed = 1;
        directionCount = 0;
        directionFrequency = 200;
        spriteFrequency = 30;
    }

    public void setDialogs(){

        currentDialog = -1;
        currentDialogIndex = 0;

        dialog1.add("Hello old man.");
        dialog1.add("What is it boy ?");
        dialog1.add("I am an advanturer and i am willing to find the dungeon treasure");
        dialog1.add("Ohh i see.");

        dialogs.add(dialog1);
        dialogs.add(dialog1);
    }

    private int npcDirection(){

        if(directionCount < directionFrequency) return 1;
        else if(directionCount < directionFrequency*2) return 2;
        else if(directionCount < directionFrequency*3) return 3;
        else  return 4;
    }

    public void playerUpdate() {

        if(isDialog){
            // this is when the npc is in conversation with the player

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
            defaultImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_default.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Oldman/npc_oldman_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
