/**
 * Created by samuel_wolff on 3/30/17.
 */
public class HealthPack extends Sprite {
    private int count;
    public HealthPack(int x, int y){
        super(x, y ,NORTH);
        this.setPic("Health.png",NORTH);
    }
    public void update(){
        count++;
    }
    public boolean getDead(){
        return count>100;
    }
}
