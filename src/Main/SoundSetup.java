package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
public class SoundSetup {

    GamePanel gamePanel;
    private final ArrayList<File> soundFile;
    private Clip clip;
    private Clip backgroundClip;

    public SoundSetup(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        this.soundFile = new ArrayList<>();
    }
    public void setSound(){

        String[] soundFilePaths = {
                "C:\\Users\\eOn\\OneDrive\\Desktop\\Mygame\\src\\Resources\\Audio\\Background\\background01.wav",
                "C:\\Users\\eOn\\OneDrive\\Desktop\\Mygame\\src\\Resources\\Audio\\Background\\keyFound.wav",
                "C:\\Users\\eOn\\OneDrive\\Desktop\\Mygame\\src\\Resources\\Audio\\Background\\death.wav",
        };

        try {
            for(String path : soundFilePaths) {
                File file = new File(path);
                soundFile.add(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception message: " + e.getMessage());
        }
    }
    public void startMusic(int index){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile.get(index));

            if(index==0){
                backgroundClip = AudioSystem.getClip();
                backgroundClip.open(audioInputStream);
            }
            else {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if (clip != null) {

            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void loop(int loopCount){

        if(clip != null) {
            clip.loop(loopCount);
        }
        else if(backgroundClip != null){
            backgroundClip.loop(loopCount);
        }
    }
    public void stopMusic(boolean backgroundMusic){

        if(clip != null || backgroundClip !=null) {

            if (backgroundMusic) backgroundClip.stop();

            else if(clip != null){

                clip.stop();
            }
        }
    }
    public void playBackgroundMusic(){

        // the 0 index has the background music
        startMusic(0);
        loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void playSoundEffect(int index){
        // all other sounds which will be temporary sound effects
        stopMusic(false);
        startMusic(index);
    }
}
