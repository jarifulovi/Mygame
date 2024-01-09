package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class TreasureObject extends SuperObject{
    public TreasureObject(){
        super.name = "treasure";
        try{
            String path = "Images/Objects/box_treasure.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));

        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
