import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class Flower extends Canvas {
    public static int [][] stemGrid;
    public static int flowerHeight;
    public static int constantX = 10;
    public static int constantY = 50;
    public static int startFlowerStemX;
    public static int startFlowerStemY;
    public static int [][] branchGrid;
    public static int numberOfStems = 50;
    public static int [] flowerChosen;



    public void paint(Graphics g){
        g.setFont(new Font("default" , Font.BOLD , 50));
        g.drawString("Happy Valentines Day Mommy :)" , 300 , 200);

        //drawing the stem
        g.setColor(Color.decode("#964B00"));

        g.drawLine(stemGrid[0][0] , stemGrid[1][0] ,
                stemGrid[0][1] , stemGrid[1][1]);
        g.drawLine(stemGrid[0][0] + 1 , stemGrid[1][0] ,
                stemGrid[0][1] + 1 , stemGrid[1][1]);
        g.drawLine(stemGrid[0][0] + 2 , stemGrid[1][0] ,
                stemGrid[0][1] + 2 , stemGrid[1][1]);

        int i;

        //drawing the branches
        g.setColor(Color.decode("#90561E"));
        for(i = 0 ; i < branchGrid[0].length ; i++){
            g.drawLine(startFlowerStemX , startFlowerStemY ,
                        branchGrid[0][i],
                        branchGrid[1][i]);
            g.drawLine(startFlowerStemX + 1, startFlowerStemY ,
                    branchGrid[0][i] + 1,
                    branchGrid[1][i]);
            g.drawLine(startFlowerStemX - 1, startFlowerStemY ,
                    branchGrid[0][i] - 1,
                    branchGrid[1][i]);
        }

//        g.drawLine(0,695,1000,695);

        //drawing the pot
        BufferedImage ima = null;
        try {
            ima = ImageIO.read(new File("pot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(ima , stemGrid[0][0] - 100 , stemGrid[1][0] - 150 , 200 , 200 , null);

        //drawing the flowers
        for(i = 0 ; i < flowerChosen.length ; i++){
            int stemX = branchGrid[0][i];
            int stemY = branchGrid[1][i];

            //drawing the branch from stem to flower base
            int flowerBaseY = stemY - 30;
            g.drawLine(stemX , stemY , stemX , flowerBaseY);
            g.drawLine(stemX - 1 , stemY , stemX - 1 , flowerBaseY);

            BufferedImage imag = null;
            try {
                imag = ImageIO.read(new File("vegan.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            g.drawImage(imag , stemX - 28 , stemY - 40, 50 , 50 , null);

            BufferedImage image = null;

            switch(flowerChosen[i]){
                case 1:
                    try {
                        image = ImageIO.read(new File("flower.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(image , stemX - 25 , stemY - 70, 50 , 50 , null);

                    break;

                case 2:
                    try {
                        image = ImageIO.read(new File("black.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(image , stemX - 25 , stemY - 65, 50 , 50 , null);

                    break;

                case 3:
                    try {
                        image = ImageIO.read(new File("hibiscus.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(image , stemX - 25 , stemY - 70, 50 , 50 , null);

                    break;

                case 4:
                    try {
                        image = ImageIO.read(new File("cherry-blossom.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(image , stemX - 25 , stemY - 70, 50 , 50 , null);

                    break;

                case 5:
                    try {
                        image = ImageIO.read(new File("rose.png"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(image , stemX - 25 , stemY - 70, 50 , 50 , null);

                    break;
            }
        }
    }

    public static void main(String args[]) {
        flowerHeight = 175;

        stemGrid = new int [2][2];
        Flower m=new Flower();
        JFrame f=new JFrame();
        int frameHeight = 800;
        int frameWidth = 1400;
        f.setSize(frameWidth,frameHeight);

        startFlowerStemX = constantX + frameWidth/2;
        stemGrid[0][0] = constantX + frameWidth/2;
        stemGrid[0][1] = startFlowerStemX;

        startFlowerStemY = frameHeight - constantY - flowerHeight;
        stemGrid[1][0] = frameHeight - constantY ;
        stemGrid[1][1] = startFlowerStemY;

        branchGrid = new int[2][numberOfStems];
        int maxBranchWidth = numberOfStems < 20 ? numberOfStems * 20 : 300;
        int degree = maxBranchWidth / numberOfStems;
        int branchHeight = 70;
        int c = 0;

        for(int i = 0 ; i < numberOfStems ; i++) {
            //left branches
            if(i < numberOfStems / 2){
                c++;
                branchGrid[0][i] = startFlowerStemX - degree * c;
                branchGrid[1][i] = startFlowerStemY - (int)(Math.random()*100) - branchHeight;
            }
            else if((double)(i) == (double)(numberOfStems/2) && numberOfStems % 2 == 1){
                branchGrid[0][i] = startFlowerStemX;
                branchGrid[1][i] = startFlowerStemY - (int)(Math.random()*70) - branchHeight;
            }
            //right branches
            else{
                branchGrid[0][i] = startFlowerStemX + degree * c;
                branchGrid[1][i] = startFlowerStemY - (int)(Math.random()*100) - branchHeight;
                c--;
            }
        }

        flowerChosen = new int[branchGrid[0].length];

        for(int i = 0 ; i < branchGrid[0].length ; i++){
            flowerChosen[i] = (int)(Math.random() * 5) + 1;
        }

        f.add(m);
        f.setVisible(true);

        System.out.println("SIMULATION COMPLETED");
    }
}