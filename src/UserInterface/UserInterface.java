package UserInterface;

import Main.GamePanel;

import java.awt.*;

public class UserInterface {
    private final GamePanel gamePanel;
    private final PauseGame pauseGame;
    private final DialogScreen dialogScreen;
    private final MenuInterface menuInterface;
    private final PlayerHP playerHP;
    public UserInterface(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        pauseGame = new PauseGame(gamePanel);
        dialogScreen = new DialogScreen(gamePanel);
        menuInterface = new MenuInterface(gamePanel);
        playerHP = new PlayerHP(gamePanel);
    }
    public void draw(Graphics2D g2d){

        if(gamePanel.menuState) menuInterface.draw(g2d);

        else {
            playerHP.draw(g2d);

            if (gamePanel.pauseGame) {
                pauseGame.draw(g2d);
            } else if (gamePanel.dialogState) {
                dialogScreen.draw(g2d);
            }
        }
    }

}
