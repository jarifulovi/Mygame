package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class DoorObject extends SuperObject{

    public DoorObject(){
        super.name = "door";
        try {
            String path = "Images/Objects/door.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
