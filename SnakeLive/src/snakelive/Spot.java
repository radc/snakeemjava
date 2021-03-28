/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakelive;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author Altamir
 */
public class Spot extends JLabel{
    //CONSTANTS
    private final static int SPOT_SIZE = 20;
    private final static int SPOT_MARGIN = 2;
    private final static int X_OFFSET = 10;
    private final static int Y_OFFSET = 10;    
    //COLOR CONSTANTS
    private final static Color FREE_SPOT_COLOR = new Color(20,20,20);
    private final static Color BODY_SPOT_COLOR = new Color(127,10,10);
    private final static Color FOOD_SPOT_COLOR = new Color(10,150,10);
    private final static Color HEAD_SPOT_COLOR = new Color(10,10,190);
    
    //ATTR
    private final PanelGame root;
    private boolean isFood, isBody, isHead;
    private Spot back;
    private final Spot[] neighbors;
    private int i,j;
    
    //STATIC ATTR
    private static boolean foodTaken = false;
    
    public Spot(PanelGame root){        
        //super("x");
        //Layout Settings
        super.setLayout(null);
        super.setSize(SPOT_SIZE,SPOT_SIZE);
        super.setBackground(FREE_SPOT_COLOR);
        super.setOpaque(true);
        
        //Initializing Attr
        this.root = root;
        this.isFood = false;        
        this.isBody = false;    
        this.isHead = false;        
        this.back = null;        
        this.neighbors = new Spot[4];
    }
    /*
    public void advance(){
        Spot nextHead = this.neighbors[root.getDir().ordinal()];
        Spot auxBack = this.back;
        foodTaken = nextHead.getIsFood(); //verifica se pegou uma comida
        nextHead.setHead();
        
        while(true){
            
        }
        
    }*/  
    
    public void advance(){
        if (this.isHead){
            this.printBody();
            
            System.out.println("Im a head!");
            Spot nextHead = this.neighbors[root.getDir().ordinal()];            
            foodTaken = nextHead.getIsFood(); //verifica se pegou uma comida
            nextHead.setHead();
            
            if (foodTaken){
                root.setInitiaFoodPosition();
                root.incrementSnakeSize();
            }
            
            if(foodTaken || this.back != null){    
                nextHead.back = this;                
                System.out.println("Food Taken!");                                               
            }
        }
        
        if (this.back == null){
            if (!foodTaken){               
                System.out.println("?");
                this.setFree();
            }else{
                this.setBody();
                System.out.println("Peguei comida e cheguei aqui");
            }
        }            
        else {           
            System.out.println("Inducao Recursiva");
            this.setBody();
            this.back.advance();
            if(this.back.isFree()) this.back = null;
        }
    }

    
   /*
    public void advance() {
        if (this.isHead){
            Spot nextHead = this.neighbors[root.getDir().ordinal()];
            foodTaken = nextHead.isFood; //verifica se pegou uma comida
            nextHead.setHead();            
            
            if (foodTaken){ //É CABECA, VAI PEGAR COMIDA
                nextHead.back = this; //ok                
                this.setBody(nextHead); // ok     
                if (this.back == null){ //É CABECA, VAI PEGAR COMIDA, TAMANHO 1                    
                    System.out.println(">>> É CABECA, VAI PEGAR COMIDA, TAMANHO 1");
                    //DO NOTHING
                }
                else { //É CABECA, VAI PEGAR COMIDA, TAMANHO MAIOR QUE 1
                    this.back.advance();
                }
            }else{ //É CABECA | NÃO VAI PEGAR COMIDA
                if (this.back == null){ //É CABECA, NÃO VAI PEGAR COMIDA E TAMANHO 1
                    this.setFree();                                        
                }
                else { //É CABECA, NÃO VAI PEGAR COMIDA E TAMANHO MAIOR QUE 1
                    this.back.advance();
                }
            }
            
            
        } else { //NÃO É CABECA
            if (this.back != null){ //NÃO É CABECA E NAO É A OUTRA EXTREMIDADE
                this.back.advance();
            }else { //NÃO É CABECA MAS É A OUTRA EXTREMIDADE
                if (foodTaken){
                    this.setBody(this.forw);
                }else{
                    this.forw.back = null;
                    this.setFree();
                }
                
                
            }
        }
        
    }
    */
    @Override
    public void setLocation(int i, int j){
        this.i = i;
        this.j = j;
        super.setLocation(j*(SPOT_SIZE+SPOT_MARGIN) + X_OFFSET, i*(SPOT_SIZE+SPOT_MARGIN) + Y_OFFSET);
    }
    
    public void setUpNeighbor(Spot sp){
        this.neighbors[Dir.UP.ordinal()] = sp;
    }
    public void setRightNeighbor(Spot sp){
        this.neighbors[Dir.RIGHT.ordinal()] = sp;
    }
    public void setDownNeighbor(Spot sp){
        this.neighbors[Dir.DOWN.ordinal()] = sp;
    }
    public void setLeftNeighbor(Spot sp){
        this.neighbors[Dir.LEFT.ordinal()] = sp;
    }
    
    public boolean setFood(){
        if (this.isFood){
            return false;
        }            
        this.isFood = true;
        this.isBody = false;
        this.isHead = false;
        this.setBackground(FOOD_SPOT_COLOR);        
        return true;
    }
    
    public boolean getIsFood(){
        return this.isFood;
    }
    
    public boolean setBody(){
        if (this.isBody){
            System.out.println("BODY ERROR");
            return false;
        }  
        
        System.out.print("BODY==>(");
        System.out.print(getI());
        System.out.print(":");
        System.out.print(getJ());
        System.out.print(")");        
        
        this.isBody = true;        
        this.isFood = false;
        this.isHead = false;
        this.setBackground(BODY_SPOT_COLOR);        
        return true;
    }
    
    public boolean setHead(){
        if (this.isHead){
            System.out.println("HEAD ERROR");
            return false;
        }
        System.out.print("HEAD==>(");
        System.out.print(getI());
        System.out.print(":");
        System.out.print(getJ());
        System.out.print(")");        
        this.isHead = true;   
        this.isBody = false;
        this.isFood = false;
        this.setBackground(HEAD_SPOT_COLOR);        
        return true;
    }
    
    public void setFree(){
        this.setBackground(FREE_SPOT_COLOR);
        this.back = null;        
        this.isFood = false;
        this.isHead = false;
        this.isBody = false;
    }
    
    public boolean isFree(){
        return !isFood && !isBody && !isHead;
    }
    
    public boolean isBody(){
        return this.isBody;
    }
    
    public int getI(){
        return this.i;
    }
    
    public int getJ(){
        return this.j;
    }
    
    private void printBody(){
        System.out.print(">>>(");
        System.out.print(i);
        System.out.print(",");
        System.out.print(j);
        System.out.println(")");
        
        if (this.back!=null) this.back.printBody();
    }
    
    
}

