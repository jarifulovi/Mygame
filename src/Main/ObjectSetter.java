package Main;

import Monsters.Green_slime;
import Monsters.Orc;
import Objects.*;
import Character.*;
import java.awt.*;

public class ObjectSetter {
    GamePanel gamePanel;
    ObjectSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){


        gamePanel.obj.add(new TreasureObject());
        gamePanel.obj.get(0).positionX = 23 * gamePanel.TileSize;
        gamePanel.obj.get(0).positionY = 8 * gamePanel.TileSize;
        gamePanel.obj.get(0).solidArea = new Rectangle(0,0,gamePanel.TileSize,gamePanel.TileSize);


        gamePanel.obj.add(new CartObject());
        gamePanel.obj.get(1).positionX = 7 * gamePanel.TileSize;
        gamePanel.obj.get(1).positionY = 6 * gamePanel.TileSize;
        gamePanel.obj.get(1).solidArea = new Rectangle(0,0,gamePanel.TileSize,gamePanel.TileSize);


        gamePanel.obj.add(new Portion_health(5000));
        gamePanel.obj.get(2).positionX = 7 * gamePanel.TileSize;
        gamePanel.obj.get(2).positionY = 8 * gamePanel.TileSize;
        gamePanel.obj.get(2).solidArea = new Rectangle(0,0,gamePanel.TileSize,gamePanel.TileSize);


    }
    public void setNpc(){

        Characters orangeBoy = new OrangeBoy(gamePanel);
        Characters oldMan = new OldMan(gamePanel);

        gamePanel.npc.add(orangeBoy);
        //gamePanel.npc.add(oldMan);
    }
    public void setMonsters(){

        Characters orc = new Orc(gamePanel);
        Characters green_slime1 = new Green_slime(gamePanel);
        Characters green_slime2 = new Green_slime(gamePanel);
        green_slime2.PositionX = gamePanel.TileSize*10;
        green_slime2.PositionY = gamePanel.TileSize*45;
        //gamePanel.monsters.add(orc);
        gamePanel.monsters.add(green_slime1);
        gamePanel.monsters.add(green_slime2);

    }
    public void drawNpc(Graphics2D g2d){

        for(Characters c : gamePanel.npc) c.draw(g2d);
    }
    public void drawMonsters(Graphics2D g2d){

        for(Characters m : gamePanel.monsters) m.draw(g2d);

    }
    public void drawObject(Graphics2D g2d){

        for(SuperObject sp : gamePanel.obj){
            sp.draw(g2d,gamePanel);
        }
    }

}
