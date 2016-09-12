import gui.MainFrame;


public class Main {

    /**
     * Entry point of the program. Creates the main frame and starts the animation loop.
     */
    public static void main(String[] args){

        MainFrame.MF = new MainFrame();
        new Thread(MainFrame.MF).start();

    }

}
