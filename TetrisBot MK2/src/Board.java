
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Board extends JComponent implements Serializable {
    private Score newScore = new Score(997, "Wouter");
    private ArrayList<Score> myScores = newScore.getScores();

    private JFrame gameOverFrame;
    private JTextField nameGetter;
    private int Score;
    private int LinesToBeCleared;

    private int height;
    private int width;
    public int middle;


    public boolean GameOver;
    public boolean pause;

    public static int count = 0;

    private int[][] Board;
    private boolean[][] SetPieces;


    private int PosX = 0;
    private int PosY = 0;

    Pentomino Piece = new Pentomino();

    TimeClass tc = new TimeClass();
    Timer timer = new Timer(1000, tc);

    public Board(int newHeight, int newWidth) {
        newScore.read();

        //middle = width / 2;
        setFocusable(true);
        height = newHeight;
        width = newWidth;
        middle = newWidth / 2;
        Board = new int[height][width];
        SetPieces = new boolean[height][width];
        GameOver = false;
        pause = false;

        //LinesToBeCleared = 0;

        Score = 0;

        timer.start();

        addKeyListener(new InputReceiver2());

        System.out.println(Score);


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame frame = new JFrame();

        frame.setTitle("Tetris");
        frame.setSize(500, 800);
        frame.setResizable(true);
        frame.setBackground(Color.white);
        //frame.setLocationRelativeTo(null);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Board Tetris = new Board(15, 5);
        frame.add(Tetris);


        frame.setVisible(true);

        Tetris.NewPiece();


    }


    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        /**
         * Change fontSize, and font itself
         */
        int fontSize = 20;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g2.setFont(f);
        g2.setColor(Color.orange);

        /**
         * Convert the Score to integer
         * draw the string at position x,y
         */
/*
        String score = Integer.toString(Score);
        g2.drawString("Score: " + score, 10, 400);
        String highScore = Integer.toString(myScores.get(0).getScore());
        g2.drawString("Highscore: " + highScore, 10, 450);
*/
        /**
         * Check if GameOver is true, if it is true, draw the string at position x,y
         */





            /**
             * Create the colors
             */

            Color c1 = new Color(255, 51, 51);
            Color c2 = new Color(255, 153, 51);
            Color c3 = new Color(255, 255, 51);
            Color c4 = new Color(153, 255, 51);
            Color c5 = new Color(0, 204, 0);
            Color c6 = new Color(102, 255, 178);
            Color c7 = new Color(51, 255, 255);
            Color c8 = new Color(0, 140, 204);
            Color c9 = new Color(134, 189, 255);
            Color c10 = new Color(153, 51, 255);
            Color c11 = new Color(255, 51, 255);
            Color c12 = new Color(255, 51, 153);

            /**
             * set the squarelength (Width and height are the same, because it a square)
             * set the position of the frame of the game (x,y)
             */

            int sl = 50;
            int x = 10;
            int y = 0;

            /**
             * create a black background
             * create a big white background behind the gameframe because otherwise you have grey spare spaces between the blocks
             * set color to white
             * fill rectangle at x position, y position, rect width, rect height
             */

            g.setColor(Color.black);
            g.fillRect(x - 2, y - 2, width * sl + 4, height * sl + 4);
            g.setColor(Color.white);
            g.fillRect(x, y, width * sl, height * sl);


            /**
             * draw the rectangles
             * first set the color
             * then fill rectangle, x position, y position, squarelength, squarelength
             * sl-1 is the draw a space between the blocks
             */

            for (int i = 0; i < Board.length; i++) {
                for (int j = 0; j < Board[0].length; j++) {
                    if (Board[i][j] == 0) {
                        g.setColor(Color.white);
                        g.fillRect(x + (j * sl), y + (i * sl), sl, sl);
                    } else if (Board[i][j] == 1) {
                        g.setColor(c1);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 2) {
                        g.setColor(c2);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 3) {
                        g.setColor(c3);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 4) {
                        g.setColor(c4);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 5) {
                        g.setColor(c5);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 6) {
                        g.setColor(c6);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 7) {
                        g.setColor(c7);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 8) {
                        g.setColor(c8);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 9) {
                        g.setColor(c9);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 10) {
                        g.setColor(c10);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 11) {
                        g.setColor(c11);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    } else if (Board[i][j] == 12) {
                        g.setColor(c12);
                        g.fillRect(x + (j * sl), y + (i * sl), sl - 1, sl - 1);
                    }

                }

            }
        }


    public void clearUnsetPieces() {


        int i, j;
        for (i = 0; i < Board.length; i++) {
            for (j = 0; j < Board[0].length; j++) {

                if (Board[i][j] != 0 && SetPieces[i][j] != true) {
                    Board[i][j] = 0;
                }
            }
        }
    }

    private void MoveDown() {

        clearUnsetPieces();
        MovePiece(PosX, PosY + 1);
    }

    public void MovePiece(int x, int y) {


        int coords[][] = Piece.getCoords();

        //int Position = 0; //Position in coordinates Matrix of Pentomnino
        //int count = 0;
        int i, j;
        int Ymin = Piece.getYMin();
        int Ymax = Piece.getYMax();

        int Xmin = Piece.getXMin();
        int Xmax = Piece.getXMax();

        int val = Piece.getValue();

        for (i = y + Ymin; i >= 0 && i < Board.length && i <= y + Ymax; i++) {
            for (j = x + Xmin; j >= 0 && j < Board[0].length && j <= x + Xmax; j++) {
                for (int k = 0; k < coords.length; k++) {
                    if (i - y == coords[k][0] && j - x == coords[k][1] && SetPieces[i][j] == false) {
                        Board[i][j] = val;
                        //SetPieces[i][j] = true;
                    }
                }
            }
        }
        PosX = x;
        PosY = y;


    }


    /*
     * @tryPiece
     * Checks whether a Piececan fit a certain Position
     * returns true if yes
     * returns false if yes
     */

    private boolean tryPiece(int x, int y) {
        int coords[][] = Piece.getCoords();

        int i, j;
        int count = 0;
        int Ymin = Piece.getYMin();
        int Ymax = Piece.getYMax();

        int Xmin = Piece.getXMin();
        int Xmax = Piece.getXMax();

        int val = Piece.getValue();
		/*
		System.out.println("y-Start: " + (PosY + Ymin));
		System.out.println("x-Start: " + (PosX + Xmin));

		System.out.println("y-End: " + (PosY + Ymax));
		System.out.println("x-End: " + (PosX + Xmax));
		*/
        for (i = y + Ymin; i >= 0 && i < Board.length && i <= y + Ymax; i++) {

            for (j = x + Xmin; j >= 0 && j < Board[0].length && j <= x + Xmax; j++) {
                for (int k = 0; k < coords.length; k++) {
                    if (i - y == coords[k][0] && j - x == coords[k][1] && SetPieces[i][j] == false) {
                        count++;
                    }
                }
            }
        }

        /*
         * if all five spaces that Pentomino requires to be placed are empty then this method returns true
         */
        if (count == 5) {
            return true;
        }

        return false;


    }

    /*
     * @SetPiece
     * This Method is used to place Pentoino at POston PosX, PosY
     * After setting the Piece , the Board is redrawn using the repaint() method
     *
     */

    public void SetPiece() {


        int coords[][] = Piece.getCoords();

        //int Position = 0; //Position in coordinates Matrix of Pentomnino
        //int count = 0;
        int i, j;
        int Ymin = Piece.getYMin();
        int Ymax = Piece.getYMax();

        int Xmin = Piece.getXMin();
        int Xmax = Piece.getXMax();

        int val = Piece.getValue();

        for (i = PosY + Ymin; i >= 0 && i < Board.length && i <= PosY + Ymax; i++) {
            for (j = PosX + Xmin; j >= 0 && j < Board[0].length && j <= PosX + Xmax; j++) {
                for (int k = 0; k < coords.length; k++) {
                    if (i - PosY == coords[k][0] && j - PosX == coords[k][1] && SetPieces[i][j] == false) {
                        Board[i][j] = val;
                        SetPieces[i][j] = true;
                    }
                }
            }
        }
        /* BOT */
        getFullRows();

        if (checkForFullRows()) {
            searchAndDestroy();
        }
        /*BOT*/
        getHeightGame();
        //System.out.println("height " + getHeightGame());
        /*BOT*/
        getHoles();
        //System.out.println("holes " + getHoles());
        /*BOT*/
        getScoreBot();



       // System.out.println("Score bot " + getScoreBot());
        NewPiece();


    }



   


    public void SetPieceTemp(int x, int y) {


        int coords[][] = Piece.getCoords();

        //int Position = 0; //Position in coordinates Matrix of Pentomnino
        //int count = 0;
        int i, j;
        int Ymin = Piece.getYMin();
        int Ymax = Piece.getYMax();

        int Xmin = Piece.getXMin();
        int Xmax = Piece.getXMax();

        int val = Piece.getValue();



        for (i = y + Ymin; i >= 0 && i < Board.length && i <= y + Ymax; i++) {
            for (j = x + Xmin; j >= 0 && j < Board[0].length && j <= x + Xmax; j++) {
                for (int k = 0; k < coords.length; k++) {
                    if (i - y == coords[k][0] && j - x == coords[k][1] && Board[i][j] == 0) {
                        Board[i][j] = val;
                    }
                }
            }
        }




    }




    public void RemovePiece(int x, int y) {


        int coords[][] = Piece.getCoords();

        //int Position = 0; //Position in coordinates Matrix of Pentomnino
        //int count = 0;
        int i, j;
        int Ymin = Piece.getYMin();
        int Ymax = Piece.getYMax();

        int Xmin = Piece.getXMin();
        int Xmax = Piece.getXMax();

        int val = Piece.getValue();

        for (i = y + Ymin; i >= 0 && i < Board.length && i <= y + Ymax; i++) {
            for (j = x + Xmin; j >= 0 && j < Board[0].length && j <= x + Xmax; j++) {
                for (int k = 0; k < coords.length; k++) {
                    if (i - y == coords[k][0] && j - x == coords[k][1] && SetPieces[i][j] == false && Board[i][j] == val) {
                        Board[i][j] = 0;
                    }
                }
            }
        }

    }






    private boolean checkForFullRows() {
        int i, j;
        for (i = 0; i < Board.length; i++) {
            if (SetPieces[i][0] == true) {
                count = 1;
                for (j = 1; j < Board[0].length; j++) {
                    if (SetPieces[i][j] == true)
                        count++;
                }
                if (count == Board[0].length) {
                    //System.out.println("Found full Row!");
                    return true;

                }
            }
        }
        return false;
    }

    //Method is executed when entering a filled row
    public void searchAndDestroy() {


        int number = 0;
        double lines = 0.0;
        int i, j;
        int multi = 1;
        LinesToBeCleared = 0;
        for (i = 0; i < SetPieces.length; i++) {
            if (SetPieces[i][0] == true && checkIfRowFull(i)) {
                //Score++;
                number++;
                lines += 1.0;
                LinesToBeCleared++;
                for (j = 0; j < Board[0].length; j++) {
                    Board[i][j] = 0;
                    SetPieces[i][j] = false;
                }

                fallDown(i);

                if (LinesToBeCleared == 2) {
                    multi = (int)lines + 1;
                }
                else if (LinesToBeCleared != 2) {

                    int fact = 1;
                    int number2 = (int) lines;
                    while (number2 > 0){
                        fact = (int) number2 * fact;
                        number2--;
                    }
                    multi = fact;

                }


            }
        }
        //System.out.println("Number: " + number + " | Lines: " + lines + " | Multiplyer: " + multi + " Score: " + Score);

        Score += multi;
        //System.out.println("New Score: " + Score);
    }


    private int[][] sADTemp(int[][] Array) {


        int number = 0;
        double lines = 0.0;
        int i, j;
        int multi = 1;
        LinesToBeCleared = 0;
        for (i = 0; i < Array.length; i++) {
            if (checkTempFull(Array, i)) {
                //Score++;
                number++;
                lines += 1.0;
                LinesToBeCleared++;
                for (j = 0; j < Array[0].length; j++) {
                    Array[i][j] = 0;
                }

                Array = fallTempDown(Array, i);

            }
        }
        return Array;
    }


    private boolean checkTempFull(int[][] Array, int row)	{
        int count = 0;
        for(int j = 0; j < Array[0].length; j++)	{
            if(Array[row][j] != 0)
                count++;
        }

        if(count == Array[0].length)
            return true;
        else
            return false;
    }

    private void fallDown(int y) {
        int temp;
        boolean setTemp;
        for (int i = y - 1; i >= 0; i--) {
            for (int j = 0; j < Board[0].length; j++) {

                temp = Board[i + 1][j];
                Board[i + 1][j] = Board[i][j];
                Board[i][j] = temp;

                setTemp = SetPieces[i + 1][j];
                SetPieces[i + 1][j] = SetPieces[i][j];
                SetPieces[i][j] = setTemp;
            }
            //printBoard();
            //printBoolean();
        }
    }

    private int[][] fallTempDown(int[][] Array,int y) {
        int temp;
        boolean setTemp;
        for (int i = y - 1; i >= 0; i--) {
            for (int j = 0; j < Array[0].length; j++) {

                temp = Array[i + 1][j];
                Array[i + 1][j] = Array[i][j];
                Array[i][j] = temp;
            }
            //printBoard();
            //printBoolean();
        }
        return Array;
    }

    public int getHeightGame() {

        int height = 0;
        for (int j = 0; j < Board[0].length; j++)	{
        	for(int i = 0; i < Board.length; i++)	{
        		if(Board[i][j] != 0)	{
        			height += (Board.length - i);
        		}
        	}
        }
        return height;
    }

    public int getHoles() {
    	
    	int holes = 0;
    	for(int i = Board.length - 1; i > 0 ; i--)	{
    		for (int j = 0; j < Board[0].length; j++)	{
    			if(Board[i][j] == 0 && Board[i-1][j] != 0)	{
    				System.out.println("Hole at:\tX = " + j + "\tY = " + i);
    				System.out.println("[" + j + "/" + i +"] = \n\n");
    				holes++;
    			}
    		}
    	}
    
    	
        return holes;
    }

    public int getFullRows(){
        int a;
        int bRows = 0;
        for(int i = 0;i<Board.length;i++){
            a=0;
            for(int j = 0;j<Board[0].length;j++){
                if(Board[i][j] != 0){
                    a++;
                }
            }
            if(a==Board[0].length){
                bRows++;
            }
        }
        return bRows;
    }
    
    private int getBlockades()	{
    	
        int block = 0;
        for(int i = Board.length - 1; i > 0 ; i--)	{
        	for (int j = 0; j < Board[0].length; j++)	{
        		if(Board[i][j] == 0 && Board[i-1][j] != 0)	{
        			for(int k = i-1; k > 0; k--)	{
        				if(Board[k][j] != 0)	{
        					block++;
        				}
        			}
        		}
        	}
        }
        return block;
    }
    
    private int getAdjaEdges()	{
    	int edges = 0;
    	
    	for(int i = Board.length - 1; i > 0; i--)	{
    		for(int j = 0; j < Board[0].length; j++)	{
    			if(Board[i][j] != 0)	{
    				if(i - 1 >= 0 && Board[i-1][j] != 0)
    					edges++;
    				if(i + 1 < Board.length && Board[i+1][j] != 0)
    					edges++;
    				if(j + 1 < Board[0].length && Board[i][j+1] != 0)
    					edges++;
    				if(j - 1 >= 0 && Board[i][j-1] != 0)
    					edges++;
    			}
        	}
    	}
    	
    	return edges;
    }
    
    
    private int getWallPieces()	{
    	int wallPieces = 0;
    	int j = 0;
    	for(int i = 0; i < Board.length; i++)	{
    		if(Board[i][j] != 0)
    			wallPieces++;
    	}
    	
    	j = Board[0].length - 1;
    	for(int i = 0; i < Board.length; i++)	{
    		if(Board[i][j] != 0)
    			wallPieces++;
    	}
    	
    	return wallPieces;
    }
    
    private int getFloorPieces()	{
    	int floorPieces = 0;
    	int i = Board.length - 1;
    	
    	for(int j = 0; j < Board[0].length; j++)	{
    		if(Board[i][j] != 0)
    			floorPieces++;
    	}
    	
    	return floorPieces;
    }
    


    public int getMonotone() {
        int a=0;
        int sum=0;
        int counter = 0;
        int[]array = new int[Board[0].length+1];
        for (int i = 0; i < Board[0].length; i++) {
            for (int j = 0; j < Board.length; j++) {
                if (Board[j][i] != 0) {
                    counter=Board.length-j;
                    a++;
                    array[a]=counter;
                    counter=0;
                    break;

                }

            }
        }
    
    
	    for(int k=0; k<array.length-1;k++) {
	        sum += Math.abs(array[k] - array[k + 1]);
	    }
	
	    return sum;
    }
    
    

    public int getScoreBot(){ 
    	printBoard();
    	int height= getHeightGame(); 
    	int holes = getHoles(); 
    	int fullrows = getFullRows(); 
    	
    	int blocks = getBlockades();
    	int adjEdges = getAdjaEdges();
    	int wallPieces = getWallPieces();
    	int floorPieces = getFloorPieces();
    	
    	int w1 = -30; 	//height 
    	int w2 = -750;	//holes 
    	int w3 = -350; 	//Blockades
    	int w4 = 800;	//full rows 
    	int w5 = 300;	//Adjacent Blocks
    	int w6 = 250;	//Wall Blocks
    	int w7 = 500;	//Floor Blocks
    	
    	
    	height = height * w1;
    	holes = holes * w2;
    	blocks = blocks * w3;
    	fullrows = fullrows * w4;
    	adjEdges = adjEdges * w5;
    	wallPieces = wallPieces * w6;
    	floorPieces = floorPieces * w7;
    	
    	int sum = height + holes + blocks + fullrows + adjEdges + wallPieces + floorPieces;
    			
    	return sum; 
    	//int d = -150; 
    	
    	
    	//int monotone = getMonotone(); 
    	
    }


    public double getScore()	{

        int i = PosY;
        while (tryPiece(PosX, i)) {
            i++;
        }
        clearUnsetPieces();
        //System.out.println("tempY " + (i-1));
        SetPieceTemp(PosX, i-1);
        double scoreBot = getScoreBot();
        //printBoard();
        /*
        System.out.println("Score bot " + scoreBot);
        System.out.println("Height" + getHeightGame());
        System.out.println("Holes" + getHoles());
        System.out.println("fullrows" + getFullRows());
        System.out.println("\n\n");
         */
        RemovePiece(PosX, i-1);

        MovePiece(PosX, PosY);


        return scoreBot;
    }

    public int[][] getRowScore()	{
        int[][] Index = new int[width*4][3];
        int i = 0;
        int rot = 0;
        int Pos = -middle;
        int indexPos = 0;
        int move = 0;
        
        int max = (Integer.MIN_VALUE);
        int maxPosX = 0;
        int maxRot = 0;


        while(indexPos < Index.length)	{
            int kRot = 0;

            //We start in the middle
            //First we rotate it into a certain Rotation
            if(rot != 0)	{
                while(kRot != rot)	{
                    Piece.RotateRight();
                    kRot++;
                }
            }
            clearUnsetPieces();
            MovePiece(PosX,PosY);

            //Here to the left
            move = 0;
            if(Pos < 0)	{
                while(Pos != move)	{
                    if(tryPiece(PosX-1, PosY))
                        PosX--;
                    move--;
                }
            }
            else if(Pos > 0)	{
                while(Pos != move)	{
                    if(tryPiece(PosX+1, PosY))
                        PosX++;
                    move++;
                }
            }

            clearUnsetPieces();
            MovePiece(PosX, PosY);

            //System.out.println("Score: " + getScore());
            int score = (int)getScore();


            //Fist Entry in the ScoreIndex is the move variable to See where o move the Piece
            Index[indexPos][0] = PosX;

            //Second Entry in the ScoreIndex is the Rotation variable to how to orient the Piece
            Index[indexPos][1] = kRot;

            //Third Entry in the ScoreIndex is the Score at that Position
            Index[indexPos][2] = score;
            if(score > max)	{
            	max = score;
            	maxPosX = Index[indexPos][0];
            	maxRot = kRot;
            }
            
            //Get Piece back into previous Position
            PosX = middle;
            clearUnsetPieces();
            if(tryPiece(PosX, PosY))
                MovePiece(PosX, PosY);


            //Get Piece back into previous Orientation
            while(kRot != 0)	{
                Piece.RotateLeft();
                kRot--;
            }
            clearUnsetPieces();

            MovePiece(middle, PosY);

            if(rot == 3)	{
                Pos++;
                rot = 0;
            }
            else
                rot++;


            indexPos++;
            //printBoard();
        }

        System.out.println("\nBest Position: " + maxPosX + "\nBest Rotation: " + maxRot + "\nBest Score: " + max + "\n\n");
        

        return Index;
    }


    public void printBoard()	{
        System.out.print("\n\n");
        for(int i = 0; i < Board.length; i++)	{
            for(int j = 0; j < Board[0].length; j++)	{
                System.out.print(Board[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    public void printArray(int[][] Array)	{
        System.out.print("\n\n");
        for(int i = 0; i < Array.length; i++)	{
            for(int j = 0; j < Array[0].length; j++)	{
                System.out.print(Array[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

    public void printRowScore(int[][] Array)	{
        System.out.print("\n\nPosition-X: \t" );
        for(int i = 0; i < Array.length; i++)	{
            System.out.print(Array[i][0] + "\t");
        }
        System.out.print("\nRotation: \t" );
        for(int i = 0; i < Array.length; i++)	{
            System.out.print(Array[i][1] + "\t");
        }
        System.out.print("\nScore: \t\t" );
        for(int i = 0; i < Array.length; i++)	{
            System.out.print(Array[i][2] + "\t");
        }
        System.out.print("\n");
    }

    private boolean checkIfRowFull(int y) {
        //System.out.println("Row: " + y + " is full");

        int count = 1;
        for (int j = 1; j < SetPieces[0].length; j++) {
            if (SetPieces[y][j] == true)
                count++;
        }
        if (count == SetPieces[0].length) {
            return true;
        }
        return false;
    }

    public void NewPiece() {
        aggregateHeightCalculator();
        Piece.getNewPentomino();
        PosX = middle;
        int yMin = Piece.getYMin();
        PosY = -yMin;


        if (!tryPiece(PosX, PosY)) {
            GameOver = true;
            //printBoard();
            System.out.println("Score: "  + Score + " GAME OVER");
            gameOver();


        }
        else {
        	int time = 500;
            MovePiece(PosX, PosY);
            /*
            try {
                Thread.sleep(time);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            if(!pause){
	            //For Bot Start HERE!!!!!!
	
	            //Create an Index of best Position with best Rotation
	            int[][] Index = getRowScore();
	
	            printRowScore(Index);
	            //Find Position and Rotation with best Score
	
	            int Pos = getInfo(Index,1);
	            int Rot = getInfo(Index,2);
	            int maxScore = getInfo(Index,3);
	
	            System.out.println("Pos : " + Pos + "\nRot: " + Rot + "\nScore " + maxScore );
	
	            
	            //Move Piece to Position
	            clearUnsetPieces();
	            if(Rot == 1)	{
	                Piece.RotateRight();
	            }
	            else if(Rot == 2)	{
	                Piece.RotateRight();
	                Piece.RotateRight();
	            }
	            else if(Rot == 3)	{
	                Piece.RotateRight();
	                Piece.RotateRight();
	                Piece.RotateRight();
	            }
	
	            MovePiece(Pos, PosY);
	
	
	            //DropPiece
	            int i = PosY;
	            while (tryPiece(PosX, i)) {
	                i++;
	            }
	            PosY = i - 1;
	            clearUnsetPieces();
	            
	            
	            SetPiece();
            }
            
            repaint();
            */
			
        }
    }

    public int getInfo(int[][] array){

        int index =0;
        int largestnumber=array[0][2];

        for (int i = 1; i < array.length; i++) {
            if (array[i][2] >= largestnumber) {
                largestnumber = array[i][2];
                index = i;
            }
        }

        //If val is 1 PosX is returned
        return index;

    }

    class InputReceiver2 extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keyValue = e.getKeyCode();

            boolean test = true;
            if (keyValue == KeyEvent.VK_RIGHT) {
                if (!pause || test) {
                    //System.out.println("Right");
                    //System.out.println("Move to right " + tryPiece(PosX+1, PosY));
                    if (tryPiece(PosX + 1, PosY)) {
                        clearUnsetPieces();
                        MovePiece(PosX + 1, PosY);
                    }
                }

            } else if (keyValue == KeyEvent.VK_LEFT) {
                if (!pause || test) {
                    //System.out.println("Left");
                    //System.out.println("Move to left " + tryPiece(PosX-1, PosY));
                    if (tryPiece(PosX - 1, PosY)) {
                        clearUnsetPieces();
                        MovePiece(PosX - 1, PosY);
                    }
                }
            } else if (keyValue == KeyEvent.VK_UP) {
                if (!pause || test) {
                    Piece.RotateLeft();
                    if (tryPiece(PosX, PosY)) {
                        clearUnsetPieces();
                        MovePiece(PosX, PosY);
                    } else
                        Piece.RotateRight();

                    //System.out.println("Up");
                }
            } else if (keyValue == 'd' || keyValue == 'D') {
                if (!pause) {
                    //System.out.println("Down");
                    if (!tryPiece(PosX, PosY + 1)) {
                        SetPiece();
                        //printBoard();

                    } else
                        MoveDown();
                }
            } else if (keyValue == KeyEvent.VK_DOWN) {
                if (!pause || test) {
                    Piece.RotateRight();
                    if (tryPiece(PosX, PosY)) {
                        clearUnsetPieces();
                        MovePiece(PosX, PosY);
                    } else
                        Piece.RotateLeft();
                }
            } else if (keyValue == KeyEvent.VK_SPACE) {
                if (!pause) {
                    //System.out.println("Space");
                    int i = PosY;
                    while (tryPiece(PosX, i)) {
                        i++;
                    }
                    PosY = i - 1;
                    clearUnsetPieces();

                    SetPiece();

                }
            } else if (keyValue == 'p' || keyValue == 'P') {
                if (!pause) {
                    pause = true;
                    timer.stop();
                } else {
                    pause = false;
                    timer.start();
                }
            }
            else if(keyValue == 'b' || keyValue == 'B')	{
                int[][] Index = getRowScore();
                printRowScore(Index);
            }
            else if(keyValue == 'n' || keyValue == 'N')	{
            	//clearUnsetPieces();
            	printBoard();
                //System.out.println("Current Score: " + getScore());
                //System.out.println("Number of Holes: " + getHoles());
                //MovePiece(PosX, PosY);
            }


            repaint();


        }
    }

    class TimeClass implements ActionListener {
        public TimeClass() {

        }

        public void actionPerformed(ActionEvent tc) {
            if (!tryPiece(PosX, PosY + 1)) {
                SetPiece();
                //printBoard();

                //NewPiece();
            } else
                MoveDown();
            repaint();
        }
    }

    public void gameOver() {
        timer.stop();
        GameOver = true;
        gameOverFrame = new JFrame("Game Over!");
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setResizable(false);
        JPanel gameOverPanel = new JPanel(new FlowLayout());
        nameGetter = new JTextField(12);
        JButton gameOverButton = new JButton("Submit");
        GameOverListener listener = new GameOverListener();
        gameOverButton.addActionListener(listener);
        gameOverPanel.add(nameGetter);
        gameOverPanel.add(gameOverButton);
        gameOverFrame.add(gameOverPanel);
        gameOverFrame.pack();
        gameOverFrame.setVisible(true);
        repaint();
    }
    class GameOverListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String name = nameGetter.getText();
            Score newScore = new Score(Score, name);
            newScore.read();
            newScore.add(newScore);
            newScore.sortList();
            newScore.write();
            myScores = newScore.getScores();
            for (int i = 0; i < myScores.size(); i++) {
                System.out.println(myScores.get(i).toString());
            }
            gameOverFrame.dispose();
        }
    }

    /**
     * BOT
     *
     * @return
     */

    public void aggregateHeightCalculator() {
        int heightSum = 0;
        boolean heightChecked;
        for (int i = 0; i < 5; i++){
            heightChecked = false;
            for ( int j = 0; j < 15; j++) {
                if (Board[j][i] != 0 && !heightChecked){
                    heightSum += j;
                    heightChecked = true;
                    //System.out.println("Height row " + (i + " is " + (15 - j));
                }
            }
        }
        int averageHeight = 15 - (heightSum / 5);
        //System.out.println(averageHeight);
    }

}
