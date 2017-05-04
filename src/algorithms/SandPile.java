package algorithms;

import image.Color;
import option.OptionList;

public class SandPile extends Algorithm {

    private int numGrains;
    private boolean drawBorder;
    private int cellWidthOpt, cellHeightOpt;

    private int[][] pile;
    private int xBorderHigh, yBorderHigh, xBorderLow, yBorderLow;
    private int cellWidth, cellHeight;

    public SandPile() {
        numGrains = (int)Math.pow(2,30);
        drawBorder = false;
        cellWidthOpt = 10;
        cellHeightOpt = 10;
        reset();
    }

    @Override
    public String toString() {
        return "Sand pile";
    }

    @Override
    public void step() {
        int startX = Math.max(0, xBorderLow);
        int startY = Math.max(0, yBorderLow);
        int endX = Math.min(pile.length-1, xBorderHigh);
        int endY = Math.min(pile[0].length-1, yBorderHigh);

        for(int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                int r = Math.min(pile[x][y],10)*255/10;
                IMG.setColor(new Color(r,0,0));
                int tx = IMG.getWidth()/2;
                tx += (x-pile.length/2)*cellWidth;
                int ty = IMG.getHeight()/2;
                ty += (y-pile[x].length/2)*cellHeight;
                IMG.fillRect(tx, ty, cellWidth, cellHeight);
            }
        }

        if(drawBorder){
            IMG.setColor(new Color(0,255,0));
            IMG.drawRect((startX - pile.length/2)*cellWidth + IMG.getWidth()/2-1, (startY-pile[0].length/2)*cellHeight + IMG.getHeight()/2-1, (endX-startX+1)*cellWidth+1, (endY-startY+1)*cellHeight+1);
            IMG.setColor(new Color(0,0,255));
            IMG.drawRect(IMG.getWidth()/2 - pile.length/2*cellWidth - 1, IMG.getHeight()/2 - pile[0].length/2*cellHeight - 1, pile.length*cellWidth+1, pile[0].length*cellHeight+1);
        }

        int[][] newPile = new int[pile.length][pile[0].length];
        for(int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                newPile[x][y] = pile[x][y];
            }
        }

        for(int x = startX; x <= endX; x++){
            for(int y = startY; y <= endY; y++){
                if(pile[x][y] >= 4){
                    int count = 0;
                    if(x+1 < pile.length)   { newPile[x+1][y]++; count++; if(x == endX) xBorderHigh = endX+1; }
                    if(x-1 >= 0)            { newPile[x-1][y]++; count++; if(x == startX) xBorderLow = startX-1;}
                    if(y+1 < pile[x].length){ newPile[x][y+1]++; count++; if(y == endY) yBorderHigh = endY+1; }
                    if(y-1 >= 0)            { newPile[x][y-1]++; count++; if(y == startY) yBorderLow = startY-1;}

                    newPile[x][y] -= count;
                }
            }
        }
        pile = newPile;
    }

    @Override
    public void reset() {
        cellWidth = cellWidthOpt;
        cellHeight = cellHeightOpt;
        emptyIMG(new Color(0,0,0));
        int w = IMG.getWidth()*3/4/cellWidth;
        int h = IMG.getHeight()*3/4/cellHeight;
        pile = new int[w][h];
        pile[w/2][h/2] = numGrains;
        xBorderHigh = xBorderLow = w/2;
        yBorderHigh = yBorderLow = h/2;
    }

    @Override
    public OptionList getOptionList(){
        OptionList list = super.getOptionList();
        list.addOption("num grains", numGrains, 100, Integer.MAX_VALUE, val -> numGrains = val);
        list.addOption("cell width", cellWidthOpt, 1, 100, val -> cellWidthOpt = val);
        list.addOption("cell height", cellHeightOpt, 1, 100, val -> cellHeightOpt = val);
        list.addOption("draw border", drawBorder, val -> drawBorder = val);
        return list;
    }
}
