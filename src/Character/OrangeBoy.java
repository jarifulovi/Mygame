package Character;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class OrangeBoy extends Characters{

    public ArrayList<String> dialog1;
    public ArrayList<String> dialog2;


    public OrangeBoy(GamePanel gamePanel){
        super(gamePanel);

        dialog1 = new ArrayList<>();
        dialog2 = new ArrayList<>();
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
        spriteFrequency = 20;
    }
    public void setDialogs(){

        currentDialog = -1;
        currentDialogIndex = 0;

        dialog1.add("Hello. Are you searching something?");
        dialog1.add("Yes. I am actually finding the dungeon of deep.Do you know something about it");
        dialog1.add("Ohh my! I heard that the dungeon is very dangerous.You should not go there...");
        dialog1.add("No i will be fine.I am actually prepare for this.");
        dialog1.add("I also heard that there is a treasure hidden there.I wish i could find but i am too afraid to go there");
        dialog1.add("Do you know something about the treasure or it's exact location.");
        dialog1.add("Not much i thing there is a old man in the riverside who could tell you about that.");
        dialog1.add("Thanks,i will talk to that person.");
        dialog1.add("Be safe...");

        dialogs.add(dialog1);

        dialog2.add("You have got the portion.I see...");
        dialog2.add("Do you know what it is used for?");
        dialog2.add("Hmm... I thing it can be valuable in the dungeon.");
        dialog2.add("ohh then i should keep it safely.");
        dialog2.add("Yes and again be safe...");

        dialogs.add(dialog2);
    }
    public void determineDialogIndex(){

        if(gamePanel.player.portions.isEmpty()) currentDialogIndex = 0;
        else currentDialogIndex = 1;
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
            defaultImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_up1.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/OrangeBoy/npc_right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
