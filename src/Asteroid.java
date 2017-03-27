import java.awt.*;

/**
 * Created by zachary_goldstein on 3/24/17.
 */
public class Asteroid extends Sprite {

    private int spin;

    //add int spin
    public Asteroid(int x, int y, int rand){
        super(x,y,NORTH);
        this.spin=spin;

        if (rand ==0){
            this.setPic("A1.png", NORTH);
        }
        if (rand ==1){
            this.setPic("A2.png", NORTH);
        }
        if (rand ==2){
            this.setPic("A3.png", NORTH);
        }

    }

    public void update(){


        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

    }



}

