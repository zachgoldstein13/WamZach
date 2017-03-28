import java.awt.*;

/**
 * Created by zachary_goldstein on 3/24/17.
 */
public class Asteroid extends Sprite {

    private int spin,rand;

    //add int spin
    public Asteroid(int x, int y){
        super(x,y,NORTH);

        spin= (int)(Math.random()*360);
        rand=(int)(Math.random()*3);


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

        setDir(spin);

        if (this.getLoc().y<=0){
            this.setLoc(new Point(this.getLoc().x,800));
        }

        if (this.getLoc().y>800){
            this.setLoc(new Point(this.getLoc().x,0));
        }

        if (this.getLoc().x<=0){
            this.setLoc(new Point(1200,this.getLoc().y));
        }
        if (this.getLoc().x>1200){
            this.setLoc(new Point(0,this.getLoc().y));
        }


        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

    }



}

