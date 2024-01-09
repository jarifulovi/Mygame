package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class HorseObject extends SuperObject {

    public HorseObject(){
        super.name = "horse";
        try{
            String path = "Images/Objects/character_horse.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
