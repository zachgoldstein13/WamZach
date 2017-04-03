import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by samuel_wolff on 3/16/17.
 */
public class Starship extends Sprite {
    private int zs;
    private boolean fo;

    public Starship(int x, int y, int z, boolean f){
        super(x,y,NORTH);

        zs= z;

        fo=f;




        //takes in user imputed ship and sets it to the correct one

        //sets ship one as default if not picked otherwise






    }
    public void update(){
        if(zs==0||zs==1){
            if (fo){
                this.setPic("SF1.png", NORTH);
            }else {
                this.setPic("Spaceship1.png", NORTH);
            }
        }
        if(zs==2){
            if (fo){
                this.setPic("SF2.png", NORTH);
            }else {
                this.setPic("Spaceship2.png", NORTH);
            }
        }
        if(zs==3){
            if (fo){
                this.setPic("SF3.png", NORTH);
            }else {
                this.setPic("Spaceship3.png", NORTH);
            }
        }
        if(zs==4){
            if (fo){
                this.setPic("SF4.png", NORTH);
            } else {
                this.setPic("Spaceship4.png", NORTH);
            }
        }
        if(zs==5){
            if (fo){
                this.setPic("SF5.png", NORTH);
            } else {
                this.setPic("Spaceship5.png", NORTH);
            }
        }
        if(zs==6){
            if (fo){
                this.setPic("SF6.png", NORTH);
            }else{
                this.setPic("Spaceship6.png", NORTH);
            }
        }
        if(zs==7){
            if (fo){
                this.setPic("SF7.png", NORTH);
            }else {
                this.setPic("Spaceship7.png", NORTH);
            }
        }
        if(zs==8){
            if (fo){
                this.setPic("SF8.png", NORTH);
            }else {
                this.setPic("Spaceship8.png", NORTH);
            }
        }


    }

    public int getZs() {
        return zs;
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

    }

    @Override
    public void setDir(int newDir) {
        super.setDir(newDir);

    }
    public void setF(boolean newF){
        fo=newF;

    }
}

