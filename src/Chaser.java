import java.awt.*;

/**
 * Created by samuel_wolff on 3/24/17.
 */
public class Chaser extends Sprite {
    private Point rall;
    private boolean there=true;
    Sprite targ;
    public Chaser(int x, int y, Point rally,Sprite target){
        super(NORTH,x,y);
        this.setSpeed(15);
        targ=target;
        this.setPic("Starship.png",NORTH);
        rall= rally;
    }
    public void update(){
        if(there){
            int dir = this.getDirection(this.getLoc(),rall);
            this.setDir(dir);
        }
        if(this.getLoc().getX()>rall.getX()-50&&this.getLoc().getX()<rall.getX()+50&&this.getLoc().getY()>rall.getY()-50&&
                this.getLoc().getY()<rall.getY()+50){
            there=false;
        }
        if(!there){
            int dir2 = this.getDirection(this.getLoc(),targ.getLoc());
            this.setDir(dir2);
            this.setSpeed(0);
        }
        super.update();


    }
}
