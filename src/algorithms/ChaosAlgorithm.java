package algorithms;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ChaosAlgorithm extends Algorithm {


    public int XZ;
    public int YZ;
    private static Random R=new Random();

    private World w;

    @Override
    public String toString() {
        return "Chaos World";
    }

    @Override
    public void step(Graphics g) {
        w.step();

        g.setColor(Color.WHITE);
        g.fillRect(0,0,IMG.getWidth(), IMG.getHeight());
        for(Actor a:w.actors){
            switch(a.type){
                case 0:
                    g.setColor(new Color(255,0,0,a.ttl));
                    break;
                case 1:
                    g.setColor(new Color(0,255,0,a.ttl));
                    break;
                case 2:
                    g.setColor(new Color(0,0,255,a.ttl));
                    break;
                case 3:
                    g.setColor(new Color(0,0,0,a.ttl));
                    break;
            }
            g.fillRect(((int)a.x&0xFF)*XZ, ((int)a.y&0xFF)*YZ, XZ,YZ);
        }
    }

    @Override
    public void reset() {
        w=new World();
        XZ=IMG.getWidth()/256;
        YZ=IMG.getHeight()/256;
    }

    private class World {


        public List<Actor> actors;
        public HashMap<Integer,Actor> hashActors;

        public World(int num){
            actors=new LinkedList<Actor>();
            for(int i=0; i<num; i++){
                actors.add(new Actor());
            }
            hashActors=new HashMap<Integer,Actor>();
        }

        public World(){
            this(R.nextInt(2000)+1000);
        }

        public void step(){
            Actor b;
            for(Actor a:actors){
                a.step();
                b=hashActors.get(a.hashCode());
                if(b!=null && b!=a){
                    a.add(b);
                    //b.add(a);
                }
                hashActors.put(a.hashCode(), a);
            }
        }
    }

    private class Actor {

        public byte x, y, vx, vy,type;
        public int ttl;

        public Actor(byte x, byte y, byte vx, byte vy, byte type) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.type = type;
            ttl=20;
        }

        public Actor(byte x, byte y, byte vx, byte vy) {
            this(x, y, vx, vy, (byte) (R.nextInt(4)));
        }

        public Actor(byte x, byte y) {
            this(x, y, (byte) (R.nextInt(7) - 3),(byte) (R.nextInt(7) - 3));
        }

        public Actor() {
            this((byte) (R.nextInt(256) - 128), (byte) (R.nextInt(256) - 128));
        }

        public void step() {
            if(ttl==0){
                ttl=R.nextInt(30)+100;
                switch(type){
                    case 0:
                        x=63;
                        y=63;
                        vx=(byte) (R.nextInt(7) - 3);
                        vy=(byte) (R.nextInt(7) - 3);
                        break;
                    case 1:
                        x=-64;
                        y=63;
                        vx=(byte) (R.nextInt(7) - 3);
                        vy=(byte) (R.nextInt(7) - 3);
                        break;
                    case 2:
                        x=63;
                        y=-64;
                        vx=(byte) (R.nextInt(7) - 3);
                        vy=(byte) (R.nextInt(7) - 3);
                        break;
                    case 3:
                        x=-64;
                        y=-64;
                        vx=(byte) (R.nextInt(7) - 3);
                        vy=(byte) (R.nextInt(7) - 3);
                        break;
                }
            }else{
                ttl--;
                x += vx;
                y += vy;
            }
        }

        public void add(Actor a) {
            int t = type * 4 + a.type;
            switch (t) {
                case 0 * 4 + 0:
                    vx++;
                    vy++;
                    a.vx++;
                    a.vy++;
                    break;
                case 0 * 4 + 1:
                    vx--;
                    vy++;
                    a.vx++;
                    a.vy++;
                    break;
                case 0 * 4 + 2:
                    vx++;
                    vy--;
                    a.vx++;
                    a.vy++;
                    break;
                case 0 * 4 + 3:
                    vx--;
                    vy--;
                    a.vx++;
                    a.vy++;
                    break;
                case 1 * 4 + 0:
                    vx++;
                    vy++;
                    a.vx--;
                    a.vy++;
                    break;
                case 1 * 4 + 1:
                    vx--;
                    vy++;
                    a.vx--;
                    a.vy++;
                    break;
                case 1 * 4 + 2:
                    vx++;
                    vy--;
                    a.vx--;
                    a.vy++;
                    break;
                case 1 * 4 + 3:
                    vx--;
                    vy--;
                    a.vx--;
                    a.vy++;
                    break;
                case 2 * 4 + 0:
                    vx++;
                    vy++;
                    a.vx++;
                    a.vy--;
                    break;
                case 2 * 4 + 1:
                    vx--;
                    vy++;
                    a.vx++;
                    a.vy--;
                    break;
                case 2 * 4 + 2:
                    vx++;
                    vy--;
                    a.vx++;
                    a.vy--;
                    break;
                case 2 * 4 + 3:
                    vx--;
                    vy--;
                    a.vx++;
                    a.vy--;
                    break;
                case 3 * 4 + 0:
                    vx++;
                    vy++;
                    a.vx--;
                    a.vy--;
                    break;
                case 3 * 4 + 1:
                    vx--;
                    vy++;
                    a.vx--;
                    a.vy--;
                    break;
                case 3 * 4 + 2:
                    vx++;
                    vy--;
                    a.vx--;
                    a.vy--;
                    break;
                case 3 * 4 + 3:
                    vx--;
                    vy--;
                    a.vx--;
                    a.vy--;
                    break;
            }
        }

        public int hashCode() {
            return (x * 256) + (y);
        }
    }
}
