package Main;
import Character.*;
import Objects.*;

import java.util.ArrayList;
import java.util.Objects;


public class CollisionCheck {
    GamePanel gamePanel;
    public CollisionCheck(GamePanel gp){
        this.gamePanel = gp;
    }
    public void checkTilesCollision(Characters character){
        // character has a solidArea variable of Rectangle
        int characterLeftPositionX = character.PositionX + character.solidArea.x;
        int characterRightPositionX = character.PositionX + character.solidArea.x + character.solidArea.width;
        int characterTopPositionY = character.PositionY + character.solidArea.y;
        int characterBottomPositionY = character.PositionY + character.solidArea.y + character.solidArea.height;

        int characterLeftCol = characterLeftPositionX / gamePanel.TileSize;
        int characterRightCol = characterRightPositionX / gamePanel.TileSize;
        int characterTopRow = characterTopPositionY / gamePanel.TileSize;
        int characterBottomRow = characterBottomPositionY / gamePanel.TileSize;

        int tileNum1, tileNum2;

        if(Objects.equals(character.direction, "up")){
            characterTopRow = (characterTopPositionY - character.characterSpeed) / gamePanel.TileSize;
            tileNum1 = gamePanel.tileSetup.map[characterTopRow][characterLeftCol];
            tileNum2 = gamePanel.tileSetup.map[characterTopRow][characterRightCol];

            if(gamePanel.tileSetup.tiles.get(tileNum1).collision || gamePanel.tileSetup.tiles.get(tileNum2).collision) {
                character.collisionFlag = true;
            }
        }
        else if(Objects.equals(character.direction, "down")){
            characterBottomRow = (characterBottomPositionY + character.characterSpeed) / gamePanel.TileSize;
            tileNum1 = gamePanel.tileSetup.map[characterBottomRow][characterLeftCol];
            tileNum2 = gamePanel.tileSetup.map[characterBottomRow][characterRightCol];

            if(gamePanel.tileSetup.tiles.get(tileNum1).collision || gamePanel.tileSetup.tiles.get(tileNum2).collision) {
                character.collisionFlag = true;
            }
        }
        else if(Objects.equals(character.direction, "left")){
            characterLeftCol = (characterLeftPositionX - character.characterSpeed) / gamePanel.TileSize;
            tileNum1 = gamePanel.tileSetup.map[characterTopRow][characterLeftCol];
            tileNum2 = gamePanel.tileSetup.map[characterBottomRow][characterLeftCol];

            if(gamePanel.tileSetup.tiles.get(tileNum1).collision || gamePanel.tileSetup.tiles.get(tileNum2).collision) {
                character.collisionFlag = true;
            }
        }
        else if(Objects.equals(character.direction, "right")){
            characterRightCol = (characterRightPositionX + character.characterSpeed) / gamePanel.TileSize;
            tileNum1 = gamePanel.tileSetup.map[characterTopRow][characterRightCol];
            tileNum2 = gamePanel.tileSetup.map[characterBottomRow][characterRightCol];

            if(gamePanel.tileSetup.tiles.get(tileNum1).collision || gamePanel.tileSetup.tiles.get(tileNum2).collision) {
                character.collisionFlag = true;
            }
        }
    }

    public int checkObjectCollision(Characters character,boolean player){
        int index = -1;


        for(SuperObject sp : gamePanel.obj) {

            // position of the player
            character.solidArea.x = character.PositionX + character.solidArea.x;
            character.solidArea.y = character.PositionY + character.solidArea.y;
            // position of the object
            sp.solidArea.x += sp.positionX;
            sp.solidArea.y += sp.positionY;

            if(Objects.equals(gamePanel.player.direction, "up")){
                character.solidArea.y -= character.characterSpeed;
                if(sp.solidArea.intersects(character.solidArea)) {

                    if(sp.collision)
                        character.collisionFlag = true;
                    if (player) {
                        index = gamePanel.obj.indexOf(sp);
                    }
                }
            }
            else if(Objects.equals(gamePanel.player.direction, "down")){
                character.solidArea.y += character.characterSpeed;
                if(sp.solidArea.intersects(character.solidArea)) {
                    if(sp.collision)
                        character.collisionFlag = true;
                    if (player) {
                        index = gamePanel.obj.indexOf(sp);
                    }
                }
            }
            else if(Objects.equals(gamePanel.player.direction, "left")){
                character.solidArea.x -= character.characterSpeed;
                if(sp.solidArea.intersects(character.solidArea)) {
                    if(sp.collision)
                        character.collisionFlag = true;
                    if (player) {
                        index = gamePanel.obj.indexOf(sp);
                    }
                }
            }
            else if(Objects.equals(gamePanel.player.direction, "right")){
                character.solidArea.x += character.characterSpeed;
                if(sp.solidArea.intersects(character.solidArea)) {
                    if(sp.collision)
                        character.collisionFlag = true;
                    if (player) {
                        index = gamePanel.obj.indexOf(sp);
                    }
                }
            }
            // initializing the default value
            character.solidArea.x = character.solidAreaDefaultX;
            character.solidArea.y = character.solidAreaDefaultY;
            sp.solidArea.x = sp.solidAreaDefaultX;
            sp.solidArea.y = sp.solidAreaDefaultY;
        }

        return index;
    }
    public int checkCharacterCollision(Characters character,ArrayList<Characters> others){

        int indexCharacter = -1;

        for(Characters ch : others) {

            // position of the player
            character.solidArea.x = character.PositionX + character.solidArea.x;
            character.solidArea.y = character.PositionY + character.solidArea.y;
            // position of the other characters
            ch.solidArea.x += ch.PositionX;
            ch.solidArea.y += ch.PositionY;

            if (Objects.equals(character.direction, "up")) {
                character.solidArea.y -= character.characterSpeed;
                if (ch.solidArea.intersects(character.solidArea)) {

                    character.collisionFlag = true;

                    if (character instanceof Player) {  // don't need if the method only works in the player class
                        indexCharacter = others.indexOf(ch);
                    }
                }
            } else if (Objects.equals(character.direction, "down")) {
                character.solidArea.y += character.characterSpeed;
                if (ch.solidArea.intersects(character.solidArea)) {

                    character.collisionFlag = true;

                    if (character instanceof Player) {
                        indexCharacter = others.indexOf(ch);
                    }
                }
            } else if (Objects.equals(character.direction, "left")) {
                character.solidArea.x -= character.characterSpeed;
                if (ch.solidArea.intersects(character.solidArea)) {

                    character.collisionFlag = true;

                    if (character instanceof Player) {
                        indexCharacter = others.indexOf(ch);
                    }
                }
            } else if (Objects.equals(character.direction, "right")) {
                character.solidArea.x += character.characterSpeed;
                if (ch.solidArea.intersects(character.solidArea)) {

                    character.collisionFlag = true;

                    if (character instanceof Player) {
                        indexCharacter = others.indexOf(ch);
                    }
                }
            }
            // initializing the default value
            character.solidArea.x = character.solidAreaDefaultX;
            character.solidArea.y = character.solidAreaDefaultY;
            ch.solidArea.x = ch.solidAreaDefaultX;
            ch.solidArea.y = ch.solidAreaDefaultY;
        }
        return indexCharacter;
    }
    // checks collision with player for the other characters
    public boolean checkPlayerCollision(Characters character){


        // position of the character
        character.solidArea.x = character.PositionX + character.solidArea.x;
        character.solidArea.y = character.PositionY + character.solidArea.y;
        // position of the player
        gamePanel.player.solidArea.x += gamePanel.player.PositionX;
        gamePanel.player.solidArea.y += gamePanel.player.PositionY;

        if (Objects.equals(character.direction, "up")) {
            character.solidArea.y -= character.characterSpeed;
            if (gamePanel.player.solidArea.intersects(character.solidArea)) {

                character.collisionFlag = true;
                return true;
            }
        } else if (Objects.equals(character.direction, "down")) {
            character.solidArea.y += character.characterSpeed;
            if (gamePanel.player.solidArea.intersects(character.solidArea)) {

                character.collisionFlag = true;
                return true;
            }
        } else if (Objects.equals(character.direction, "left")) {
            character.solidArea.x -= character.characterSpeed;
            if (gamePanel.player.solidArea.intersects(character.solidArea)) {

                character.collisionFlag = true;
                return true;
            }
        } else if (Objects.equals(character.direction, "right")) {
            character.solidArea.x += character.characterSpeed;
            if (gamePanel.player.solidArea.intersects(character.solidArea)) {

                character.collisionFlag = true;
                return true;
            }
        }
        // initializing the default value
        character.solidArea.x = character.solidAreaDefaultX;
        character.solidArea.y = character.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        return false;
    }
}
