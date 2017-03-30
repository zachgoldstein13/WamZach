/**
 * Created by samuel_wolff on 3/30/17.
 */
public class Ammo extends Sprite {
    private int count;
    private boolean dead;
    public Ammo(int x, int y){
        super(x,y, NORTH);
        this.setPic("Ammo.png",NORTH);
    }
    public void update(){
        count++;
    }
    public boolean getDead(){
        return count>100;
    }

}
