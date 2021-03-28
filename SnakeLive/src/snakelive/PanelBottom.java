/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakelive;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JLabel;

/**
 *
 * @author Altamir
 */
public final class PanelBottom extends Panel {
    
    private final JLabel title;
    private final Font titleFont;
    private int snakeSize;
    
    public PanelBottom(){        
        this.resetSnakeSize();
        this.title = new JLabel("Snake Size: " + Integer.toString(this.snakeSize));
        this.titleFont = new Font("Agency FB", Font.BOLD, 28);
        this.title.setFont(this.titleFont);       
        this.title.setForeground(new Color(10,100,10));
        super.setBackground(Color.BLACK);
        
        super.setLayout(new FlowLayout());
        super.add(this.title);
    }
    
    public void resetSnakeSize(){
       this.snakeSize = 1;
    }
    
    public void adjustSnakeSize(int size){
        this.snakeSize = size;
        this.title.setText("Snake Size: " + Integer.toString(this.snakeSize));
    }
    
}
