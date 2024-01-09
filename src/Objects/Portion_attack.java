package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Portion_attack extends SuperPortion{
    public Portion_attack(long durationInMillis){

        super(durationInMillis);
        super.name = "portion_attack";


        try {
            String path = "Images/Objects/portion_attack2x.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }

}
