package algorithms;

import algorithms.randomizer.Randomizer;

import java.util.Random;
import image.Color;
import option.OptionList;

public class Shadow extends Algorithm {

    private int divider, shadow, algo;
    private int yOffset, xOffset;
    private Algorithm[] algs;
    private Randomizer[] rands;

    public Shadow(){
        this(0,0,0);
    }

    public Shadow(int div, int sh, int alg){
        super();
        this.divider = div;
        this.shadow = sh;
        this.algo = alg;

        algs = Algorithm.getAlgorithmsList();
        rands = Algorithm.getRandomizersList();

        xOffset = -100;
        yOffset = -10;
    }

    @Override
    public String toString() {
        return "Shadow";
    }

    @Override
    public void step() {
        init();

        IMG.setColor(Color.BLACK);
        IMG.translate(xOffset, yOffset);

        Randomizer.color = true;

        long seed = System.currentTimeMillis();
        Randomizer.r = new Random(seed);
        rands[shadow].step(IMG.getWidth(),IMG.getHeight());

        algs[divider].step();

        IMG.untranslate();

        Randomizer.r = new Random(seed);
        rands[algo].step(IMG.getWidth(), IMG.getHeight());
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = new OptionList();

        String[] salgs = new String[algs.length];
        String[] srands = new String[rands.length];
        for(int i=0; i<algs.length; i++)
            salgs[i] = algs[i].toString();
        for(int i=0; i<rands.length; i++)
            srands[i] = rands[i].toString();

        list.addOption("Divider", salgs, divider, val -> divider = val);
        list.addOption("Shadow", srands, shadow, val -> shadow = val);
        list.addOption("Algorithm", srands, algo, val -> algo = val);

        list.addOption("x offset", xOffset, -1000, 1000, val -> xOffset = val);
        list.addOption("y offset", yOffset, -1000, 1000, val -> yOffset = val);
        list.addList(algs[divider].getOptionList());
        list.addList(rands[shadow].getOptionList());
        list.addList(rands[algo].getOptionList());
        return list;
    }

    @Override
    public void reset() {

    }
}
