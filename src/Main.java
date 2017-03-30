import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by samuel_wolff on 3/15/17.
 */
public class Main extends JPanel{
    //can be RAMEWIDTH = 1000, FRAMEHEIGHT = 600;
    public static final int FRAMEWIDTH = 1200, FRAMEHEIGHT = 800;
    private Timer timer;
    private String key= " ";
    private String keyType="None";
    private boolean menu=true;
    private int shipSpeed=15;
    private int score=0;
    private int score2=0;
    private Color black = new Color(0,0,0);
    private Color white = new Color(255,255,255);
    private Color red = new Color(255, 30, 26);
    private int z=0;
    private double chance=1;
    private double health=100;
    private int total;
    private ArrayList<Sprite> ships = new ArrayList<Sprite>();
    private ArrayList<Sprite> asteroids = new ArrayList<Sprite>();
    private ArrayList<Chaser> chasers = new ArrayList<Chaser>();
    private ArrayList<Missle> missles = new ArrayList<Missle>();
    private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
    private ArrayList<Ammo> ammunition = new ArrayList<Ammo>();
    private ArrayList<HealthPack> healths = new ArrayList<HealthPack>();
    private ArrayList<Boost> boosts = new ArrayList<Boost>();

    private int bombsleft=3;
    private int count2;
    private double frameCount=0;



//    private boolean boost;

    private int menuLevel=1;
    private int boxLength=200;
    private boolean w,a,s,d,q;

    private Sprite ship = new Starship(500,300,z, false);

    public Main() {

        asteroids = new ArrayList();
        for (int x = 0; x < 640; x+=160) {
            for (int y = 0; y < 960; y+=240) {
                asteroids.add(new Asteroid(x,y));

            }

        }
//        int rand = (int)(Math.random()*3);
//        asteroids.add(new Asteroid(500,50, rand));
//        asteroids.add(new Asteroid(50,500, rand));
//        asteroids.add(new Asteroid(300,300, rand));
       chasers.add(new Chaser(0,0,new Point((int)(Math.random()*1000),(int)(Math.random()*600)),ship));
//        chasers.add(new Chaser(0,0,new Point((int)(Math.random()*1000),(int)(Math.random()*600)),ship));

        timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                score=(int)(frameCount);
                chance=frameCount/500;
                if (!menu) {
                    frameCount++;
                        int rand =(int)(Math.random()*300);
                        if(rand<chance){
                            chasers.add(new Chaser(0,(int)(Math.random()*1000),new Point((int)(Math.random()*1000),(int)(Math.random()*600)),ship));
                        }
                        if (rand==100){
                            ammunition.add(new Ammo((int)(Math.random()*1000),(int)(Math.random()*600)));
                        }
                        if (rand==101){
                            healths.add(new HealthPack((int)(Math.random()*1000),(int)(Math.random()*600)));
                        }
                        if(asteroids.size()<10){
                            if(rand==102){
                                asteroids.add(new Asteroid((int)(Math.random()*1000),(int)(Math.random()*600)));
                            }
                        }
                        //Random int generator

                    menuLevel = 0;
                    if(health<=0){
                        menuLevel=3;
                        menu=true;
                        health=100;
                    }
                    for (int i = 0; i <healths.size() ; i++) {
                        if(healths.get(i).getDead()){
                            healths.remove(i);
                        }
                        else if(healths.get(i).intersects(ship)){
                            if(health<100) {
                                health += 20;
                            }
                            healths.remove(i);
                        }
                    }

                    ship.update();
                    for (Bomb x: bombs){
                        x.update();
                        if(x.getExploded()){
                            for (int i = 0; i < asteroids.size(); i++) {
                                if(asteroids.get(i).intersects(x)){
                                    asteroids.remove(i);
                                }
                            }
                            for (int i = 0; i < chasers.size(); i++) {
                                if(chasers.get(i).intersects(x)){
                                    chasers.remove(i);
                                    score2+=100;
                                }
                            }
                            for (int i = 0; i < missles.size(); i++) {
                                if(missles.get(i).intersects(x)){
                                    missles.remove(i);
                                }
                            }

                        }
                    }
                    for (int i = 0; i < bombs.size(); i++) {
                        if(bombs.get(i).alive()){
                            bombs.remove(i);
                        }

                    }//bombs

                    for (Sprite x: chasers){
                        x.update();
                    }
                    for (Sprite x: healths){
                        x.update();
                    }
                    for (Sprite x: ammunition){
                        x.update();

                    }
                    for (int i = 0; i <ammunition.size() ; i++) {
                        if(ammunition.get(i).getDead()){
                            ammunition.remove(i);
                        }
                        else if(ammunition.get(i).intersects(ship)){
                            bombsleft++;
                            ammunition.remove(i);
                        }
                    }//Ammo

                    for (Sprite x: chasers){
                        if(x.getSpeed()==0){
                            int rand2= (int)(Math.random()*100);
                            if(rand2<1){
                                missles.add(new Missle(x.getLoc().x,x.getLoc().y, ship, x));
                            }
                        }
                    }
                }
                if(w){
                    ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y - shipSpeed));
                    ship.setDir(Sprite.NORTH);
                }
                if(s){
                    ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y + shipSpeed));
                    ship.setDir(Sprite.SOUTH);
                }
                if(a){
                    ship.setLoc(new Point(ship.getLoc().x - shipSpeed, ship.getLoc().y));
                    ship.setDir(Sprite.WEST);
                }
                if(d){
                    ship.setLoc(new Point(ship.getLoc().x + shipSpeed, ship.getLoc().y));
                    ship.setDir(Sprite.EAST);
                }


                //diagonals
                if(w&&d){
                    ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y ));
                    ship.setDir(Sprite.NE);
                }
                if(s&&d){
                    ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y ));
                    ship.setDir(Sprite.SE);
                }
                if(a&&s){
                    ship.setLoc(new Point(ship.getLoc().x , ship.getLoc().y));
                    ship.setDir(Sprite.SW);
                }
                if(w&&a){
                    ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y));
                    ship.setDir(Sprite.NW);
                }


                if(w&&a&&d){
                    ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y));
                    ship.setDir(Sprite.NORTH);
                }

                if(s&&a&&d){
                    ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y));
                    ship.setDir(Sprite.SOUTH);
                }//ship movement


                for (Sprite a: asteroids) {
                    a.update();
                }
                for (Sprite a: missles) {
                    a.update();
                }//movement
//                for (Sprite b: missles){
//                    if(b.intersects(ship)){
//                        health= health-10;
//                        missles.remove(b.getID());
//                    }
//                    for (Sprite a: chasers){
//                        if(b.intersects(a)){
//                            missles.remove(b.getID());
//                            chasers.remove(a.getID());
//                        }
//                    }
//                }
                for (int i = 0; i < missles.size(); i++) {
                    if(ship.intersects(missles.get(i))){
                        health= health-10;
                        missles.remove(i);
                    }
                    else {
                        for (int j = 0; j < chasers.size(); j++) {
                            if (missles.get(i).intersects(chasers.get(j))) {
                                if (missles.get(i).getBaby()) {
                                    if (chasers.get(j).equals(missles.get(i).getMom())) {

                                    }
                                }
                                else {
                                    missles.remove(i);
                                    chasers.remove(j);
                                    score2+=200;
                                }

                            }
                        }
                    }
                }
                for (int i = 0; i <asteroids.size() ; i++) {
                    if(asteroids.get(i).intersects(ship)){
                        asteroids.remove(i);
                        health= health - 10;
                    }
                }
                ship.update();
                repaint();
                total=score+score2;
            }
        });

        timer.start();

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent keyEvent) {
                int code = keyEvent.getKeyChar();
                if (!menu) {
                    if (code == 'w') {
                        w=true;
                        ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y - shipSpeed));
                    }
                    if (code == 's') {
                        s=true;
                        ship.setLoc(new Point(ship.getLoc().x, ship.getLoc().y + shipSpeed));
                    }
                    if (code == 'a') {
                        a=true;
                        ship.setLoc(new Point(ship.getLoc().x - shipSpeed, ship.getLoc().y));
                    }
                    if (code == 'd') {
                        d=true;
                        ship.setLoc(new Point(ship.getLoc().x + shipSpeed, ship.getLoc().y));
                    }
                    if(code=='q'){
                        if(bombsleft>0) {
                            q = true;
                            bombs.add(new Bomb(ship.getLoc().x, ship.getLoc().y));
                            bombsleft = bombsleft - 1;
                        }
                    }
                }
            }

            public void keyPressed(KeyEvent keyEvent) {

            }

            public void keyReleased(KeyEvent keyEvent) {
                int code = keyEvent.getKeyChar();
                    if (code == 'w') {
                        w=false;
                    }
                    if (code == 's') {
                        s=false;
                    }
                    if (code == 'a') {
                        a=false;
                    }
                    if (code == 'd') {
                        d=false;
                    }
                    if(code=='q'){
                        q=false;
                    }


            }
        });
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent mouseEvent) {
                //System.out.println("X:"+mouseEvent.getX()+" Y:" +mouseEvent.getY());
                if (menuLevel == 1) {

                    //selection of ships menu
                    if (mouseEvent.getX() > 455 && mouseEvent.getX() < 850 &&
                            mouseEvent.getY() > 195 && mouseEvent.getY() < 260) {
                        menuLevel = 2;
                    }

                    //play button
                    if (mouseEvent.getX() > 455 && mouseEvent.getX() < 580 &&
                            mouseEvent.getY() > 302 && mouseEvent.getY() < 365) {
                        health=100;
                        ship.setLoc(new Point(500,600));
                        menu = false;
                    }
                }


                else if (menuLevel == 2) {

                    //back to main menu button
                    if (mouseEvent.getX() > 55 && mouseEvent.getX() < 170 &&
                            mouseEvent.getY() > 13 && mouseEvent.getY() < 52) {
                        menuLevel = 1;
                    }

                    //play now button
                    if (mouseEvent.getX() > 650 && mouseEvent.getX() < 870 &&
                            mouseEvent.getY() > 12 && mouseEvent.getY() < 52) {
                        menu = false;
                    }

                    //selection of ship 1
                    if (mouseEvent.getX() > 140 && mouseEvent.getX() < 340 &&
                            mouseEvent.getY() > 160 && mouseEvent.getY() < 360) {
                        z = 1;
                        ship = new Starship(500,300,z,false);

                    }

                    //selection of ship 2
                    if (mouseEvent.getX() > 340 && mouseEvent.getX() < 540 &&
                            mouseEvent.getY() > 160 && mouseEvent.getY() < 360) {
                        z = 2;
                        ship = new Starship(500,300,z,false);

                    }

                    //selection of ship 3
                    if (mouseEvent.getX() > 540 && mouseEvent.getX() < 740 &&
                            mouseEvent.getY() > 160 && mouseEvent.getY() < 360) {
                        z = 3;
                        ship = new Starship(500,300,z,false);

                    }

                    //selection of ship 4
                    if (mouseEvent.getX() > 740 && mouseEvent.getX() < 940 &&
                            mouseEvent.getY() > 160 && mouseEvent.getY() < 360) {
                        z = 4;
                        ship = new Starship(500,300,z,false);

                    }

                    //selection of ship 5
                    if (mouseEvent.getX() > 140 && mouseEvent.getX() < 340 &&
                            mouseEvent.getY() > 360 && mouseEvent.getY() < 560) {
                        z = 5;
                        ship = new Starship(500,300,z,false);

                    }

                    //selection of ship 6
                    if (mouseEvent.getX() > 340 && mouseEvent.getX() < 540 &&
                            mouseEvent.getY() > 360 && mouseEvent.getY() < 560) {
                        z = 6;
                        ship = new Starship(500,300,z,false);

                    }

                    //selection of ship 7
                    if (mouseEvent.getX() > 540 && mouseEvent.getX() < 740 &&
                            mouseEvent.getY() > 360 && mouseEvent.getY() < 560) {
                        z = 7;
                        ship = new Starship(500,300,z,false);

                    }

                    //selection of ship 8
                    if (mouseEvent.getX() > 740 && mouseEvent.getX() < 940 &&
                            mouseEvent.getY() > 360 && mouseEvent.getY() < 560) {
                        z = 8;
                        ship = new Starship(500,300,z,false);

                    }



                } else if (menuLevel == 3) {
                    if (mouseEvent.getX() > 455 && mouseEvent.getX() < 850 &&
                            mouseEvent.getY() > 195 && mouseEvent.getY() < 260) {
                        menuLevel = 2;
                    }

                    //play button
                    if (mouseEvent.getX() > 455 && mouseEvent.getX() < 580 &&
                            mouseEvent.getY() > 302 && mouseEvent.getY() < 365) {
                        health=100;
                        ship.setLoc(new Point(500,600));
                        frameCount=0;
                        bombsleft=3;
                        menu = false;
                        bombs.clear();
                        ammunition.clear();
                        chasers.clear();
                        missles.clear();
                        for (int x = 0; x < 640; x+=160) {
                            for (int y = 0; y < 960; y+=240) {
                                asteroids.add(new Asteroid(x,y));
                            }

                        }
                    }


                }


            }

            public void mousePressed(MouseEvent mouseEvent) {

            }

            public void mouseReleased(MouseEvent mouseEvent) {

            }

            public void mouseEntered(MouseEvent mouseEvent) {

            }

            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if(!menu){

            g2.setColor(black);

            //can be g2.fillRect(0,0,1000,600);
            g2.fillRect(0,0,1200,800);
            ship.draw(g2);
            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
            g2.setColor(Color.white);
            g2.drawString("Bombs",100,50);
            g2.drawString(" "+ bombsleft,100,150);
            g2.drawString("Score:"+ total,300,50);
            g2.drawRect(600,20,450,60);
            g2.setColor(red);
            g2.fillRect(600,20,(int)((health)*4.5),60);
            for (Sprite x: chasers){
                x.draw(g2);
            }

            for (Sprite a: asteroids) {
                a.draw(g2);
            }
            for (Sprite a: missles) {
                a.draw(g2);
            }
            for (Sprite x: bombs){
                x.draw(g2);
            }
            for (Sprite x: ammunition){
                x.draw(g2);
            }
            for (Sprite x: healths){
                x.draw(g2);
            }
        }
        if(menu){
            if(menuLevel==1) {
                g2.setColor(black);
                g2.fillRect(0, 0, 1200, 800);
                g2.setColor(Color.white);
                g2.setFont(new Font("Comic Sans MS", Font.BOLD, 68));
                g2.drawString("Dodge", 450, 150);
                g2.drawString("Play", 450, 350);
                g2.drawString("Select Ship", 450, 250);
            }

            //ship selection screen
            if(menuLevel==2){
                g2.setColor(black);
                g2.fillRect(0, 0, 1200, 800);
                g2.setColor(Color.white);
                g2.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
                g2.drawString("Menu",50,50);
                g2.drawString("Play Now",650,50);
                g2.drawString("Selected Ship: "+z,200,50);



                int n = 0;
                for (int r = 1; r < 3; r++) {
                    for (int c = 1; c < 5; c++) {
                        n = n + 1;
                        g2.drawString(""+n,100+c*boxLength,r*205);

                    }
                }

                for (int i = 0; i < 5; i++) {
                    g2.drawLine(140+(i*boxLength),160,140+(i*boxLength),160+boxLength);
                    g2.drawLine(140+(i*boxLength),160+boxLength,140+(i*boxLength),160+(boxLength*2));

                }

                for (int i = 0; i < 4; i++) {
                    g2.drawLine(140,160,140+((i+1)*boxLength),160);
                    g2.drawLine(140,160+boxLength,140+((i+1)*boxLength),160+boxLength);
                    g2.drawLine(140,160+(boxLength*2),140+((i+1)*boxLength),160+(boxLength*2));

                    //adds ships to the menu boxes
                    //need to make it more centered and
                    //we have to implement the clicking that changes it
                    //the ship png is a varible called z
                    ships = new ArrayList();


                    ships.add(new Starship(185+boxLength*i,225,i+1, true));
                    ships.add(new Starship(185+boxLength*i,425,(i+5),true));

                    for (Sprite s: ships) {
                        s.draw(g2);
                    }



                }

            }
            if (menuLevel==3){
                g2.setColor(black);
                g2.fillRect(0, 0, 1200, 800);
                g2.setColor(Color.white);
                g2.setFont(new Font("Comic Sans MS", Font.BOLD, 68));
                g2.drawString("You're Dead", 450, 150);
                g2.drawString("Play", 450, 350);
                g2.drawString("Select Ship", 450, 250);
                g2.drawString("Total Score:"+ total, 450, 450);


            }
        }

    }


    public static void main(String[] args) {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 20 due to title bar.

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();


        window.add(panel);

        window.setVisible(true);
        window.setResizable(false);

    }

}
