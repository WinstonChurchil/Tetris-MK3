import java.awt.Color;
import javax.swing.JFrame;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


import java.awt.Color;
import javax.swing.JFrame;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class Bot {
    private static Board Tetris;
    private static Robot robot;

    public static void main(String[] args) throws AWTException	{

        
        JFrame frame = new JFrame();

        frame.setTitle("Tetris");
        frame.setSize(600, 800);
        frame.setResizable(true);
        frame.setBackground(Color.white);
        //frame.setLocationRelativeTo(null);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Tetris = new Board(15, 10);
        frame.add(Tetris);

 
        frame.setVisible(true);
 
        Tetris.NewPiece();
         while(!Tetris.GameOver)
        	play();

 


    }
    
    public static void play() throws AWTException	{
    	robot = new Robot();
    	robot.delay(500);
    	//robot.keyPress(KeyEvent.VK_RIGHT);
    	
    	
    	
    	while(!Tetris.GameOver && !Tetris.pause)	{
    		int time = 1;
	    	int[][] Index = Tetris.getRowScore();
	        Tetris.printRowScore(Index);
	    		
	        int iPos = Tetris.getInfo(Index);
	        
	        int Pos = Index[iPos][0];
	        int Rot = Index[iPos][1];
	        int maxScore = Index[iPos][2];
	        
	        System.out.println("Pos : " + Pos + "\nRot: " + Rot + "\nScore " + maxScore );
	        robot.delay(time);
	        if(Rot != 0)	{
	        	int k = 0;
	        	while(k != Rot)	{
	        		robot.delay(time);
	            	robot.keyPress(KeyEvent.VK_DOWN);
	            	System.out.println("Roating...");
	            	k++;
	        	}
	        }
	        
	        if(Pos > Tetris.middle)	{
	        	int k = Pos;
	        	while(k != Tetris.middle)	{
	        		robot.delay(time);
	        		robot.keyPress(KeyEvent.VK_RIGHT);
	        		System.out.println("Going right");
	        		k--;
	        		System.out.println("Pos: " + Pos + "\tk: " + k);
	        	}
	        }
	        else if(Pos < Tetris.middle)	{
	        	int k = Pos;
	        	while(k != Tetris.middle)	{
	        		robot.delay(time);
	        		robot.keyPress(KeyEvent.VK_LEFT);
	        		System.out.println("Going Left");
	        		k++;
	        	}
	        }
	        
	        robot.delay(time);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.delay(500);
		
    	}
    }




    




}