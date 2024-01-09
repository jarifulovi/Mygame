package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Portion_speed extends SuperPortion{

    public Portion_speed(long durationInMillis){
        super(durationInMillis);
        super.name = "portion_speed";

        try {
            String path = "Images/Objects/portion_speed.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
