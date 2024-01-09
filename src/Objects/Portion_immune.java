package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Portion_immune extends SuperPortion{
    Portion_immune(long durationInMillis){
        super(durationInMillis);
        super.name = "portion_immune";
        try {
            String path = "Images/Objects/portion_immune.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
