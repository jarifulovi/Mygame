package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision;
    public Tile(){
        this.image = null;
        collision = false;
    }
}
