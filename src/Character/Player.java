package Character;

import Main.GamePanel;
import Main.KeyBoardInput;
import Monsters.Green_slime;
import Objects.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Characters {

    KeyBoardInput keyBoardInput;
    /* Player position at the screen */
    public final int PlayerPositionX;
    public final int PlayerPositionY;

    public int currentSpeed;
    public int indexNpc;
    public int indexMonster;
    public ArrayList<SuperPortion> portions = new ArrayList<>(); // modify in a class called PlayerPortion

    // player states

    public Player(KeyBoardInput keyBoardInput,GamePanel gamePanel){

        super(gamePanel);
        this.keyBoardInput = keyBoardInput;
        PlayerPositionX = gamePanel.ScreenWidth/2 - gamePanel.TileSize/2;
        PlayerPositionY = gamePanel.ScreenHeight/2 - gamePanel.TileSize/2;

        solidArea = new Rectangle(8,gamePanel.TileSize/2,gamePanel.TileSize-16,gamePanel.TileSize/2);
        collisionFlag = false;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        this.setDefaultPosition();
        this.getPlayerImage();
    }
    public void setDefaultPosition(){
        PositionX = gamePanel.TileSize *25;
        PositionY = gamePanel.TileSize *9;
        characterSpeed = 2;
        currentSpeed = characterSpeed;  // used for portion speed 2x
    }
    public void playerUpdate() {

        if(isDialog){

            return;
        }


        indexNpc = -1;
        indexMonster = -1;
        collisionFlag = false;
        // interact with npc
        indexNpc = gamePanel.collisionCheck.checkCharacterCollision(this,gamePanel.npc);
        indexMonster = gamePanel.collisionCheck.checkCharacterCollision(this,gamePanel.monsters);


        if (keyBoardInput.upPressed || keyBoardInput.downPressed || keyBoardInput.leftPressed || keyBoardInput.rightPressed) {
            int nextX = PositionX;
            int nextY = PositionY;

            if (keyBoardInput.upPressed) {
                nextY -= currentSpeed;
                direction = "up";
            }
            else if (keyBoardInput.downPressed) {
                nextY += currentSpeed;
                direction = "down";
            }
            else if (keyBoardInput.leftPressed) {
                nextX -= currentSpeed;
                direction = "left";
            }
            else {
                nextX += currentSpeed;
                direction = "right";
            }

            // Collision check when player moving
            gamePanel.collisionCheck.checkTilesCollision(this);
            int index = gamePanel.collisionCheck.checkObjectCollision(this,true);
            interactObject(index);
            indexNpc = gamePanel.collisionCheck.checkCharacterCollision(this,gamePanel.npc);
            indexMonster = gamePanel.collisionCheck.checkCharacterCollision(this,gamePanel.monsters);

            if (!collisionFlag) {
                PositionX = nextX;
                PositionY = nextY;
            }


            spriteCount++;
            if (spriteCount > 20) {
                if (spriteNum == 0 || spriteNum == 2) spriteNum = 1;
                else if (spriteNum == 1) spriteNum = 2;
                spriteCount = 0;
            }
        }
        interactMonsters(indexMonster);
        System.out.println(playerHP);
    }
    public void interactObject(int index){
        if(index == -1){

            return;
        }
        if(Objects.equals(gamePanel.obj.get(index).name, "treasure")) {

           // the treasure is the final goal
        }
        else if(Objects.equals(gamePanel.obj.get(index).name, "key")){


        }
        else if(Objects.equals(gamePanel.obj.get(index).name, "portion_health")){

            portions.add((SuperPortion) gamePanel.obj.get(index));
            portions.get(0).startWorking();
            gamePanel.obj.remove(index);
        }
    }
    public void interactMonsters(int index){

        if(index == -1) return;


        if(gamePanel.monsters.get(index) instanceof Green_slime){
            System.out.println("green slime hit");
        }

        gamePanel.monsters.get(index).attack(this);

    }

    public void getPlayerImage(){
        try {
            defaultImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/default1.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Images/Characters/Player/right2.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
