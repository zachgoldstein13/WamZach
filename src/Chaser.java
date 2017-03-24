import java.awt.*;

/**
 * Created by samuel_wolff on 3/24/17.
 */
public class Chaser extends Sprite {
    private Point rall;
    private boolean there;
    public Chaser(int x, int y, Point rally){
        super(NORTH,x,y);
        this.setPic("Starship.png",NORTH);
        rall= rally;
    }
    public void update(){
        if(there){
            int dir = this.getDirection(this.getLoc(),rall);
            this.setDir(dir);
        }
        if(this.getLoc()==rall){
            there=false;
        }
        if(!there){
            this.setSpeed(0);
        }

        super.update();
    }
}
