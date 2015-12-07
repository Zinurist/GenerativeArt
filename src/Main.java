import gui.MainFrame;


public class Main {

    /**
     * Entry point.
     */
    public static void main(String[] args){

        MainFrame mf = new MainFrame();
        new Thread(mf).start();

    }

}
