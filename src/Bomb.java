import javax.swing.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * Created by samuel_wolff on 3/29/17.
 */
public class Bomb extends Sprite {
    private int count;
    private boolean exploded;
    public Bomb(int x, int y){
        super(x,y, NORTH);
        this.setPic("Bomb.png", NORTH);
        this.setSpeed(0);
    }
    public void update(){
        count++;

        if(count>20){
            exploded = true;
            this.setPic("Explosion.png",NORTH);
        }
    }
    public boolean getExploded(){
        return exploded;
    }
    public boolean alive(){
        return count>50;

    }
}
