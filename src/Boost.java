/**
 * Created by samuel_wolff on 3/30/17.
 */
public class Boost extends Sprite {
    private int count;
    public Boost(int x, int y){
        super(NORTH,x, y);
        this.setPic("Boost.png",NORTH);
    }
    public void update(){
        count++;
    }
    public boolean getDead(){
        return count>100;
    }
}
