/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakelive;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Altamir
 */
public class PanelTop extends JPanel {
    private final JLabel title;
    private final Font titleFont;
    
    public PanelTop(){        
        this.title = new JLabel("Snake Game by RC");
        this.titleFont = new Font("Agency FB", Font.BOLD, 42);
        this.title.setFont(this.titleFont);       
        this.title.setForeground(new Color(10,150,10));
        this.setBackground(Color.BLACK);
        
        super.setLayout(new FlowLayout());
        super.add(this.title);
    }
    
}
