import java.util.ArrayList;

/**
 * Created by samuel_wolff on 3/27/17.
 */
public class Missle extends Sprite {
    Sprite tar;
    public Missle(int x , int y, Sprite target){
        super(x,y,NORTH);
        tar=target;
        this.setSpeed(12);
        this.setPic("Missle.png",EAST);
    }
    public void update(){
        int dir2 = this.getDirection(this.getLoc(),tar.getLoc());
        this.setDir(dir2);
        super.update();
    }
}
