package Objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class CartObject extends SuperObject{
    public CartObject(){
        super.name = "cart";
        try{
            String path = "Images/Objects/cart.png";
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
