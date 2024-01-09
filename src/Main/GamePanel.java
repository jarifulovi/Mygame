package Main;

import Character.*;
import Objects.SuperObject;
import Tiles.TileSetup;
import UserInterface.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    // Screen settings
    final int OriginalTileSize = 16;   // pixel size of a single tile
    final int Scale = 3;   // for adjusting modern screen
    public final int  TileSize = OriginalTileSize * Scale;
    public final int ScreenColumn = 16;
    public final int ScreenRow = 12;
    public final int ScreenWidth = TileSize * ScreenColumn;
    public final int ScreenHeight = TileSize * ScreenRow;
    final int TargetFPS = 100;
    public final int MapRow = 50;
    public final int MapCol = 50;

    Thread gameThread;
    KeyBoardInput keyBoardInput;
    public Player player;
    TileSetup tileSetup;
    public CollisionCheck collisionCheck;
    public ArrayList<SuperObject> obj;
    public ObjectSetter objectSetter;
    public SoundSetup soundSetup;
    public UserInterface userInterface;

    public ArrayList<Characters> npc;
    public ArrayList<Characters> monsters;

    // game states used in userInterface class
    public boolean pauseGame;
    public boolean dialogState;
    public boolean menuState;
    public int menuStateIndex;

    public GamePanel(){

        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        keyBoardInput = new KeyBoardInput(this);
        this.addKeyListener(keyBoardInput);

        // character part
        player = new Player(keyBoardInput,this);
        npc = new ArrayList<>();
        monsters = new ArrayList<>();

        tileSetup = new TileSetup(this);
        obj = new ArrayList<>();
        objectSetter = new ObjectSetter(this);
        collisionCheck = new CollisionCheck(this);
        soundSetup = new SoundSetup(this);
        userInterface = new UserInterface(this);

        this.setFocusable(true);
        this.SetupGame();
        this.StartGame();
    }

    public void SetupGame(){
        menuState = true;
        menuStateIndex = 0;
        objectSetter.setObject();
        objectSetter.setNpc();
        objectSetter.setMonsters();
        soundSetup.setSound();
        soundSetup.playBackgroundMusic();
    }
    public void StartGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {


        double drawInterval = (float)1000000000/ TargetFPS;
        double nextDrawInterval = System.nanoTime();

        while(gameThread != null){

            update();

            repaint();


            try {
                double remainingTime = nextDrawInterval - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0) remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawInterval += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void update(){

        if(!pauseGame&&!menuState) {
            player.playerUpdate();
            for(Characters c : npc){
               c.playerUpdate();
            }
            for(Characters m : monsters){
                m.playerUpdate();
            }
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        if(!menuState) {
            tileSetup.draw(g2d);
            objectSetter.drawObject(g2d);
            objectSetter.drawNpc(g2d);
            objectSetter.drawMonsters(g2d);
            player.draw(g2d);
            userInterface.draw(g2d);
        }
        else{
            userInterface.draw(g2d);
        }
        g2d.dispose();
    }
}
