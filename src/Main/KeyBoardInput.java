package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Character.*;

public class KeyBoardInput implements KeyListener {
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    public GamePanel gamePanel;
    private Characters currentCharacter;

    public KeyBoardInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gamePanel.menuState){
            // index of menuStates
            // 0 -> play
            // 1 -> load game
            // 2 -> quit

            if (code == KeyEvent.VK_E || code == KeyEvent.VK_UP) {
                gamePanel.menuStateIndex--;
            } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_DOWN) {
                gamePanel.menuStateIndex++;
            } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_LEFT) {
                gamePanel.menuStateIndex--;
            } else if (code == KeyEvent.VK_F || code == KeyEvent.VK_RIGHT) {
                gamePanel.menuStateIndex++;
            }

            // Ensure the menuStateIndex stays within the valid range [0, 2]
            if (gamePanel.menuStateIndex > 2) {
                gamePanel.menuStateIndex = 0;
            } else if (gamePanel.menuStateIndex < 0) {
                gamePanel.menuStateIndex = 2;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gamePanel.menuStateIndex==0) {
                    gamePanel.menuState = false;
                }
                else if(gamePanel.menuStateIndex==1) {
                    // load game
                }
                else {
                    System.exit(0);
                }
            }

            return;
        }


        if(code == KeyEvent.VK_P){
            gamePanel.pauseGame = !gamePanel.pauseGame;
        }
        // dialog mode when pressed enter
        else if(code == KeyEvent.VK_ENTER && gamePanel.player.indexNpc>-1){


            currentCharacter = gamePanel.npc.get(gamePanel.player.indexNpc);
            currentCharacter.interactDirection(gamePanel.player);

            // dialog process
            if(currentCharacter.currentDialog<currentCharacter.dialogs.get(currentCharacter.currentDialogIndex).size()-1){
                currentCharacter.currentDialog++;
                gamePanel.dialogState = true;
                gamePanel.player.isDialog = true;
                currentCharacter.isDialog = true;

            }
            else{
                gamePanel.dialogState = !gamePanel.dialogState;
                gamePanel.player.isDialog = !gamePanel.player.isDialog;
                currentCharacter.isDialog = !currentCharacter.isDialog;

            }


        }
        // normal game playing mode
        else if(!gamePanel.pauseGame && !gamePanel.dialogState){
            if (code == KeyEvent.VK_E || code == KeyEvent.VK_UP) {
                upPressed = true;
            } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            } else if (code == KeyEvent.VK_F || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_E  || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        else if(code == KeyEvent.VK_D  || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        else if(code == KeyEvent.VK_S  || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_F  || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
}
