package Tiles;

import Main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileSetup {
     GamePanel gamePanel;
     public ArrayList<Tile> tiles;
     public int[][] map;

     public TileSetup(GamePanel gamePanel){
         this.gamePanel = gamePanel;
         this.tiles = new ArrayList<>();
         this.loadMap("Tiles/map.txt");
         this.getTileImage();
     }
    private void loadMap(String path) {
        map = new int[gamePanel.MapRow][gamePanel.MapCol];
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))) {
            for (int row = 0; row < gamePanel.MapRow; row++) {
                String[] line = reader.readLine().split(" ");
                for (int col = 0; col < gamePanel.MapCol; col++) {
                    map[row][col] = Integer.parseInt(line[col]);
                }
            }
        } catch (IOException | NullPointerException | NumberFormatException e) {
            throw new RuntimeException("Error loading map from file", e);
        }
    }

    // retrieve images for each tiles
     public void getTileImage() {
         String[] tilePaths = {
                 "Images/Tiles/PNG/Default (64px)/grass_upside.png",
                 "Images/Tiles/PNG/Default (64px)/grass_edge.png",
                 "Images/Tiles/PNG/Default (64px)/stone_upside.png",
                 "Images/Tiles/PNG/Default (64px)/dirt_upside.png",  // 3
                 "Images/Tiles/PNG/Default (64px)/sand_upside.png",
                 "Images/Tiles/PNG/Default (64px)/ice_upside.png",
                 "Images/Tiles/PNG/Default (64px)/water1.png",     // 6
                 "Images/Tiles/PNG/Default (64px)/trees_1.png",
                 "Images/Tiles/PNG/Default (64px)/stone_wall.png", // 8
                 "Images/Tiles/PNG/Default (64px)/lava1.png",      // 9
         };
        boolean[] collision = {false,false,false,false,false,false,true,true,true,true};
         int index = 0;
         for (String path : tilePaths) {
             Tile tile = new Tile();
             try {
                 tile.image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
                 tile.collision = collision[index++];

                 // these are for improving rendering performance
                 BufferedImage scaledImage = new BufferedImage(gamePanel.TileSize,gamePanel.TileSize,tile.image.getType());
                 Graphics2D g2d = scaledImage.createGraphics();
                 g2d.drawImage(tile.image,0,0,gamePanel.TileSize,gamePanel.TileSize,null);
                 tile.image = scaledImage;

             } catch (IOException e) {
                 throw new RuntimeException("Error loading tile image: " + path, e);
             }
             tiles.add(tile);
         }
     }


    // this method fully set up the whole screen tiles
     public void draw(Graphics2D g2d) {
         int positionDiffX =  (gamePanel.player.PositionX-gamePanel.player.PlayerPositionX);
         int positionSumX =  (gamePanel.player.PositionX+gamePanel.player.PlayerPositionX);
         int positionDiffY =  (gamePanel.player.PositionY-gamePanel.player.PlayerPositionY);
         int positionSumY = (gamePanel.player.PositionY+gamePanel.player.PlayerPositionY);
         for (int row = 0; row < gamePanel.MapRow; row++) {
             for (int col = 0; col < gamePanel.MapCol; col++) {
                 int positionX = (col * gamePanel.TileSize);
                 int positionY = (row * gamePanel.TileSize);
                 int screenPositionX = positionX - positionDiffX;
                 int screenPositionY = positionY - positionDiffY;
                 int tileIndex = map[row][col];

                 if(tileIndex > tiles.size()) try {
                     throw new IndexOutOfBoundsException();
                 } catch (Exception e) {
                     throw new RuntimeException(e);
                 }

                 if(positionX + gamePanel.TileSize > positionDiffX
                         && positionX - gamePanel.TileSize < positionSumX
                    && positionY +gamePanel.TileSize > positionDiffY
                         && positionY - gamePanel.TileSize < positionSumY)

                 g2d.drawImage(tiles.get(0).image, screenPositionX, screenPositionY,null);
                 g2d.drawImage(tiles.get(tileIndex).image, screenPositionX, screenPositionY, null);
             }
         }

     }
}
