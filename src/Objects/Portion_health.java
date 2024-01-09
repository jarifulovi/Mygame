package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Portion_health extends SuperPortion {
    public Portion_health(long durationInMillis){
        super(durationInMillis);
        super.name = "portion_health";
        try {
            String path = "Images/Objects/portion_health.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
