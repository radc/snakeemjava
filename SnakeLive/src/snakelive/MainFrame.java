/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakelive;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Ruhan
 */
public class MainFrame extends JFrame {
    //CONSTANTS
     public final static int WINDOW_WIDTH = 1024; 
     public final static int WINDOW_HEIGHT = 768; 
    
          
    //INTERNAL COMPONENTS
     private BorderLayout layout;
     private final PanelTop panelTop;
     private final PanelBottom panelBottom;
     private final PanelGame panelGame;
     
     
    
    public MainFrame(){
        super("Snake Game");
        this.setWindowParameters();   
        this.setLayoutData();
        super.setBackground(Color.BLACK);
        
        this.panelTop = new PanelTop();
        super.add(this.panelTop,BorderLayout.NORTH);
        
        this.panelGame = new PanelGame(this);
        this.panelGame.setVisible(true);
        super.add(this.panelGame,BorderLayout.CENTER);
        
        this.panelBottom = new PanelBottom();
        super.add(this.panelBottom,BorderLayout.SOUTH);
        
    }
    
    private void setWindowParameters(){
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private void setLayoutData(){
        this.layout = new BorderLayout();
        this.setLayout(this.layout);
    }
    
    public void adjustSnakeSizeFrame(int size){
        this.panelBottom.adjustSnakeSize(size);
    }
    
    
}
