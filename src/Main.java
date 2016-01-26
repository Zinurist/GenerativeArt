import gui.MainFrame;


public class Main {

    /**
     * Entry point.
     */
    public static void main(String[] args){

        MainFrame.MF = new MainFrame();
        new Thread(MainFrame.MF).start();

    }

}
