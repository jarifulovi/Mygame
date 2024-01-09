package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class KeyObject extends SuperObject {

    public KeyObject() {
        super.name = "key";
        try {
            String path = "Images/Objects/key.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
