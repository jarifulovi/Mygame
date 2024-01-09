package Character;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Characters {

    public GamePanel gamePanel;
    public int PositionX, PositionY;
    public int characterSpeed;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,defaultImage;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionFlag;
    public String direction;
    public int spriteNum;
    public int spriteCount;
    public int directionCount;

    public int directionFrequency;
    public int spriteFrequency;

    // These are player and npc specific attributes
    public boolean isDialog;
    public ArrayList<ArrayList<String>> dialogs;
    public int currentDialog;
    public int currentDialogIndex;
    public int playerHP = 100;

    // These are monster specific attributes

    public boolean walkState;
    public boolean attackState;
    public boolean deadState;

    public int attackCount;
    public int damage;
    public int attackSpeed;

    public Characters(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        dialogs = new ArrayList<>();
    }

    public void setDialogs(){

    }
    public void playerUpdate(){


    }
    public void attack(Characters character){
        // this method for character attack both for player and monsters
        // this parameter is for opposite character which get hit
        // playerHP of this character will decrease
        attackCount++;
        if(attackCount==attackSpeed) {
            character.playerHP -= damage;
            attackCount = 0;
        }
    }
    public String speak(){

        int previousIndex = currentDialogIndex;

        determineDialogIndex();

        if(previousIndex!=currentDialogIndex) currentDialog = 0;

        return dialogs.get(currentDialogIndex).get(currentDialog);
    }
    // needs to override to determine the dialog index in the collection of dialogs
    public void determineDialogIndex(){

    }
    public void draw(Graphics2D g2d){

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
                image = left2;
        }
        else if(Objects.equals(direction, "right")){
            if(spriteNum==1)
                image = right1;
            else
                image = right2;
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
    public void interactDirection(Characters player){
        // dialog image for interact character
        // also attack image for interact monsters
        if(Objects.equals(player.direction, "up")){
            direction = "down";
        }
        else if(Objects.equals(player.direction, "down")){
            direction = "up";
        }
        else if(Objects.equals(player.direction, "left")){
            direction = "right";
        }
        else if(Objects.equals(player.direction, "right")){
            direction = "left";
        }
    }
}
