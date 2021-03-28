/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakelive;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Altamir
 */
public final class PanelGame extends JPanel implements ActionListener, KeyListener {
    //CONSTANTS
    public final static int NUMBER_SPOTS_X = 45; 
    public final static int NUMBER_SPOTS_Y = 25; 
    private final static int TIMER_DELAY = 100;
    
    //ATTR    
    private final MainFrame root;
    private int headI;
    private int headJ;       
    private Dir currDir;
    private Dir oldDir;
    private final Spot[][] spots;    
    private Timer timer;    
    private int snakeSize;
    
    
    //Constructor
    public PanelGame(MainFrame root){  
        this.root = root;
        super.setBackground(Color.BLACK);     
        super.setLayout(null);
        
        this.spots = new Spot[NUMBER_SPOTS_Y][NUMBER_SPOTS_X];
        for (int i = 0; i < NUMBER_SPOTS_Y; i++) {
            for (int j = 0; j < NUMBER_SPOTS_X; j++) {
                this.spots[i][j] = new Spot(this);
                this.spots[i][j].setLocation(i, j);   
                this.spots[i][j].addKeyListener(this);
                super.add(this.spots[i][j]);
            }
        }
        setNeighborsOnSpots();
        setInitialHeadPosition();  
        setInitiaFoodPosition();
        setTimerAndListeners();
        //super.requestFocus();
        
    }
    
    private void setNeighborsOnSpots(){
        for (int i = 0; i < NUMBER_SPOTS_Y; i++) {
            for (int j = 0; j < NUMBER_SPOTS_X; j++) {
                if (i > 0)                  this.spots[i][j].setUpNeighbor   (spots[i-1][j]);
                if (i < NUMBER_SPOTS_Y-1)   this.spots[i][j].setDownNeighbor (spots[i+1][j]);                
                if(j > 0)                   this.spots[i][j].setLeftNeighbor (spots[i][j-1]);
                if(j < NUMBER_SPOTS_X-1)    this.spots[i][j].setRightNeighbor(spots[i][j+1]);
                
            }
        }
    }
    
    private void setTimerAndListeners(){
        System.out.println("Defining Keyboard Listener");
        this.addKeyListener(this);
        this.root.addKeyListener(this);
        
        System.out.println("Starting Timer!");
        this.timer = new Timer (TIMER_DELAY,this);
        this.timer.start();
    }
    
    
    private void setInitialHeadPosition(){
        this.headI = 10;        
        this.headJ = 10;
        this.currDir = Dir.RIGHT;
        this.snakeSize = 1;
        this.spots[headI][headJ].setHead();
    }
    
    
    public void setInitiaFoodPosition(){
        boolean notOk = true;
        int i,j;
        Random r = new Random();
        while(notOk){
            i = r.nextInt(NUMBER_SPOTS_Y);
            j = r.nextInt(NUMBER_SPOTS_X);
            notOk = !spots[i][j].setFood();
        }
    }
    
    private void advance(){
        int oldI = headI;
        int oldJ = headJ;
        
        if (currDir == Dir.UP) this.headI--;            
        if (currDir == Dir.DOWN) this.headI++;
        if (currDir == Dir.RIGHT) this.headJ++;
        if (currDir == Dir.LEFT) this.headJ--;
        
        if(this.checkCollision()){
            this.timer.stop();
            JOptionPane.showMessageDialog(this,"Você perdeu");
            return;
        }
        
        this.spots[oldI][oldJ].advance();
        this.oldDir = currDir;
    }
    
    private boolean checkCollision(){
        if (this.headI < 0 || this.headI >= NUMBER_SPOTS_Y ) return true;
        if (this.headJ < 0 || this.headJ >= NUMBER_SPOTS_X ) return true;        
        if(this.spots[headI][headJ].isBody()) return true;        
        return false;
    }
    
    public void setHeadBySpot(Spot sp){
        this.headI = sp.getI();
        this.headJ = sp.getJ();
    }
    
    public void incrementSnakeSize(){
        this.snakeSize++;
        root.adjustSnakeSizeFrame(this.snakeSize);
    }
    
    /*
    private void testColors(){
        this.spots[3][3].setFood();
        this.spots[5][3].setBody();
        this.spots[5][4].setBody();
        this.spots[5][5].setBody();
        this.spots[5][6].setHead();
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Test");
        this.advance();
    }

    public Dir getDir() {
        return this.currDir;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed!");
        
        if (e.getKeyCode() == KeyEvent.VK_UP && oldDir != Dir.DOWN) this.currDir = Dir.UP;         
        if (e.getKeyCode() == KeyEvent.VK_RIGHT  && oldDir != Dir.LEFT) this.currDir = Dir.RIGHT;
        if (e.getKeyCode() == KeyEvent.VK_DOWN  && oldDir != Dir.UP) this.currDir = Dir.DOWN;
        if (e.getKeyCode() == KeyEvent.VK_LEFT  && oldDir != Dir.RIGHT) this.currDir = Dir.LEFT;
        
         if (e.getKeyCode() == KeyEvent.VK_SPACE) this.actionPerformed(null);
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    
}
