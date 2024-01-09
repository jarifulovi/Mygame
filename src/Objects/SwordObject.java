package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SwordObject extends SuperObject{
    public SwordObject(){
        super.name = "sword1";
        try {
            String path = "Images/Objects/sword_normal.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
