import java.awt.*;

/**
 * Created by zachary_goldstein on 3/24/17.
 */
public class Asteroid extends Sprite {

    private int spin;

    public Asteroid(int x, int y){
        super(x,y,NORTH);
        this.spin=spin;


        int rand = (int)(Math.random()*3);

        if (rand ==0){
            this.setPic("A1.png", NORTH);
        }
        if (rand ==1){
            this.setPic("A2.png", NORTH);
        }
        if (rand ==2){
            this.setPic("A3.png", NORTH);
        }
        System.out.println(rand);

    }

    public void update(){


    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

    }



}

