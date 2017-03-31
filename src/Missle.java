import java.util.ArrayList;

/**
 * Created by samuel_wolff on 3/27/17.
 */
public class Missle extends Sprite {
    private Sprite tar;
    private Sprite mom;
    private int count=0;
    private boolean baby=true;
    public Missle(int x , int y, Sprite target,Sprite mother){
        super(x,y,NORTH);
        tar=target;
        mom= mother;
        this.setSpeed(10);
        this.setPic("Missle.png",EAST);
    }
    public void update(){
        int dir2 = this.getDirection(this.getLoc(),tar.getLoc());
        this.setDir(dir2);
        super.update();
        count++;
        if (count>20){
            baby=false;
        }
    }
    public Sprite getMom(){
        return mom;
    }
    public boolean getBaby(){
        return baby;
    }
}
