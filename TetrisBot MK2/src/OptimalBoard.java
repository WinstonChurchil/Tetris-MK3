
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;


public class OptimalBoard extends JComponent {


    private int height;
    private int width;


    private int[][] Board;
    //private boolean[][] SetPieces;


    final int gameWidth =200 ;
    final int gameHeight = 600;
    OptimalPentomino Piece = new OptimalPentomino();
    
    
    private int[][] SetPiecesLog;
    private int keepInMind = 99;


    public OptimalBoard(int newHeight, int newWidth)	{

        height = newHeight;
        width = newWidth;
        Board = new int[height][width];
        //SetPieces = new boolean[height][width];
        
        SetPiecesLog = new int[9][2];
        //12 for number of Pentominos
        //4 for number of possible Rotations
        
        
        


    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame frame = new JFrame();

        frame.setTitle("Tetris");
        frame.setSize(300, 700);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        OptimalBoard Tetris = new OptimalBoard(15, 5);
        frame.add(Tetris);
        Tetris.SetStartPieces();



       frame.setVisible(true);
        

    }
    
    
    
    
    
   
    
    
    public void SetStartPieces()	{
		

		int y = getNextEmpty(1);
		int x = getNextEmpty(2);
		
		/*
		Piece.getNextStartPentomio(0, 2);
		if(tryPieceAt(0,14))	{
			SetPieceAt(0, 14);
			
		}*/
		
		/*
		Piece.getNextStartPentomio(4, 4);
		if(tryPieceAt(0, 14))	{
			SetPieceAt(0, 14);
			
		}*/
		
		
		Piece.getNextStartPentomio(4, 4);
		if(tryPieceAt(0, 14))	{
			SetPieceAt(0, 14);
			
		}
		
		Piece.getNextStartPentomio(0, 2);
		if(tryPieceAt(0, 11))	{
			SetPieceAt(0, 11);
			
		}
		
		
		Piece.getNextStartPentomio(3, 2);
		if(tryPieceAt(4, 6))	{
			SetPieceAt(4, 6);
			
		}
		
		
		trySolving();
		
		
		/*
		Piece.getNextStartPentomio(8, 2);
		if(tryPieceAt(3, 5))	{
			SetPieceAt(3, 5);
			
		}*/
		
		
/*
		Piece.getNextStartPentomio(5, 1);
		if(tryPieceAt(4, 9))	{
			SetPieceAt(4, 9);
			
		}
		*/
		
		repaint();
		
	}
    
    
    
    private boolean sameAsLastSet()	{
    	
    	for(int i = SetPiecesLog.length - 1; i >= 0; i--)	{
    		//printLog();
    		//System.out.println(Piece.position);
    		if(SetPiecesLog[i][0] != 0 && SetPiecesLog[i][0] == Piece.position)	{
    			//printLog();
    			return true;
    		}
    		
    	}
    	return false;
    	
    }
    
    
    
    
    
    
    private void trySolving()	{
    	boolean debug = true;
    	
    	int i = 0;
    	int rot = 1;
    	int x,y,val;
    	int prevI = 99;
    	int prevRot = 99;
    	
    	int keepInMind = 99;
    	int keepRot = 99;
    	
    	double iterations = 0;
    	y = 15;
    	x = 0;
    	
    	boolean stop = false;
    	
    	while(!solved() && !stop && iterations < 30000){
    		if(i >= 9)	{
    			i = 0;
    		}
    		
    		
    		
    		y = getNextEmpty(1);
    		x = getNextEmpty(2);
    		
    		if(y <= 3)
    			stop = true;
    		
    		Piece.getNextPentomio(i, rot);
    		
    		//System.out.print("Iteration: " + iterations + "\nPentomino: " + Piece.getName() + "\tat Position: " + i + "\nRotation: " + rot + "\nAt: X: " + x + "\t" + "Y: " + y + "\nCurrently Placed: " + checkIfAlreadyPlaced() + "\nFits: " + tryPieceAt(x,y));
    		
    		//The Piece I am currently trying to place, has already been placed
    		if(checkIfAlreadyPlaced())	{
    			//System.out.print("\nSame as last one I tried?\t" + cILP(i));
    			//The Piece I am trying to place is the same as the one I tried last time
    			if(cILP(i) == true)	{
    				//I am loading the previous rotation of this Piece, which lead to an undesired Situation
    				int previousRotation = rROPS(i);
    				
    				removePiece(i);
    				printBoard();
    				remFromList(i);
    				//printLog();
    				if(previousRotation == 4)	{
    					i++;
    					rot = 1;
    				}
    				else if(previousRotation < 4)	{
    					rot = previousRotation + 1;
    				}
    			}
    			else if(cILP(i) != true)	{
    				//The Piece I am currently trying to place, is not the same as the one I tried last time
    				i++;
    				rot = 1;
    			}
    			
    		}
    		else if(!checkIfAlreadyPlaced())	{
    			//The Piece I am trying to place has not been set yet
    			if(i == 4)	{
    				System.out.println("\nCan be placed: " + tryPieceAt(x,y));
    			}
    			if(tryPieceAt(x,y))	{
    				
    				//Piece can be set in this rotation and will be set here
    				SetPieceAt(x,y);
    				printBoard();
    				if(i == 4)	{
	    				System.out.println("\nPiece placed");
	    			}
    				if(pruneSpace())	{
    					//Setting the Piece here creates a pruneSpace
    					
    					
    					
    					removePiece(i);
    					//Removing Piece
    					if(rot == 4)	{
    						//the rotation of the Piece I am currently trying to place is the maximum rotation
    						i++;
    						rot = 1;
    					}
    					else if(rot < 4)	{
    						//the rotation of the Piece I am currently trying to place is below 4 and will be iterated
    						rot++;
    					}
    				}
    				else if(!pruneSpace())	{
    					//Setting the Piece does not create a PruneSpace
    					
    					//Adding the Piece set to the List of Set Pentominos
    					addToList(i, rot);
    					//printLog();
    					i++;
    					rot = 1;
    					
    				}
    			}
    			else if(!tryPieceAt(x,y))	{
    				//The Piece can not be placed like this in this location
    				if(rot == 4)	{
    					i++;
    					rot = 1;
    				}
    				else if(rot < 4)
    					rot++;
    			}
    		
    			
    		}
    		repaint();
    		System.out.print("\n\n");
    		iterations++;
    	}
    	System.out.print("Number of Iterations: " + iterations + "\n");
    	printLog();
    	

    }

    
  //Check if Last Placed
	private boolean cILP(int Pentomino)	{
		for(int i = SetPiecesLog.length - 1; i >= 0; i--)	{
			if(SetPiecesLog[i][1] != 0 && SetPiecesLog[i][0] == Pentomino)	{
				return true;
			}
			else if(SetPiecesLog[i][1] != 0 && SetPiecesLog[i][0] != Pentomino){
				return false;
			}
		}
		return false;
	}
	
	//return Rotation of prev Set
	private int rROPS(int Pentomino)	{
		for(int i = 0; i < SetPiecesLog.length; i++)	{
			if(SetPiecesLog[i][0] == Pentomino && SetPiecesLog[i][1] != 0)	{
				return SetPiecesLog[i][1];
			}
		}
		return 0;
	}
	
	private void addToList(int Position, int rotation)	{
		for(int i = 0; i < SetPiecesLog.length; i++)	{
			if(SetPiecesLog[i][1] == 0)	{
				//Adding the informations to the log
				SetPiecesLog[i][0] = Position;
				SetPiecesLog[i][1] = rotation;
				
				//to end for-loop
				i = SetPiecesLog.length;
			}
		}
	}
    
	private void remFromList(int Position)	{
		for(int i = 0; i < SetPiecesLog.length; i++)	{
			if(SetPiecesLog[i][0] == Position)	{

				//removing the informations to the log
				SetPiecesLog[i][0] = 0;
				SetPiecesLog[i][1] = 0;
				
				//to end for-loop
				i = SetPiecesLog.length;
			}
		}
	}
    
    private boolean solved()	{
    	int i,j;
    	int count = 0;
    	for(i = 0; i < Board.length; i++)	{
    		for(j = 0; j < Board[0].length; j++)	{
        		if(Board[i][j] != 0)	{
        			count++;
        		}
        	}
    	}
    	if(count == height * width)	
    		return true;
    	else
    		return false;
    }
    
    private void removePiece(int Position)	{
    	int val = Piece.getValue2(Position);
    	int i,j;
    	for(i = 0; i < Board.length; i++)	{
    		for(j = 0; j < Board[0].length; j++)	{
        		if(Board[i][j] == val)
        			Board[i][j] = 0;
        	}
    	}
    }
    
    
   
    
    
    private int allEmptyCells()	{
    	int count = 0;
    	for(int i = 0; i < Board.length; i++)	{
    		for(int j = 0; j < Board[0].length; j++)	{
    			if(Board[i][j] == 0)
    				count++;
        	}
    	}
    	return count;
    }
    
    private int allflodded()	{
    	int count = 0;
    	int i,j;
    	for(i = 0; i < Board.length; i++)	{
    		for(j = 0; j < Board[0].length; j++)	{
    			if(Board[i][j] == 99)
    				count++;
        	}
    	}
    	return count;
    }
    
    public boolean checkIfAlreadyPlaced()	{
    	//System.out.println("Checking...");
    	int val = Piece.getValue();
    	int i,j;
    	for(i = Board.length-1; i >= 0; i--)	{
    		for(j = 0; j < Board[0].length; j++)	{
    			if(Board[i][j] == val)
    				return true; 
        	}
    	}
    	return false; 
    	
    }
    
    
    
    public int getNextEmpty(int xy)	{
    	for(int i = Board.length - 1; i >= 0; i--)	{
    		for(int j = 0; j < Board[0].length; j++)	{
        		if(Board[i][j] == 0)	{
        			if(xy == 1)
        				return i;
        			else if(xy == 2)
        				return j;
        		}
        	}
    	}
    	return 1;
    }
    
    //if prunespace = true 
    // then there is a pruneSpace and the last Piece has to be removed
    // if it return false, then everything is in order
    private boolean pruneSpace()	{
    	int y = getNextEmpty(1);
		int x = getNextEmpty(2);
		
		int all = allEmptyCells();
		if(!floodfill(x, y))	{

			if(all != allflodded() && allflodded() % 5 != 0)	{
				//System.out.println("All Empty Cells: " + allEmptyCells());
				//System.out.println("All Flooded Cells: " + all);
				removeAllFlodded();
				return true;
			}
			removeAllFlodded();
		}
		return false;	
    }
    
    
    private void removeAllFlodded()	{
    	int i,j;
    	
    	
    	for(i = Board.length - 1; i >= 0; i--)	{
    		for(j = 0; j < Board[0].length; j++)	{
        		if(Board[i][j] == 99)	{
        			Board[i][j] = 0;
        		}
        	}
    	}
    }
    
    
    private boolean floodfill(int x, int y)	{
    	
    	int i,j;
    	
    	for(i = y - 1; i < Board.length && i < y + 2; i++)	{
    		
    		for(j = x - 1; j < Board[0].length && j < x + 2; j++)	{
        		
    			if(i >= 0 && j >= 0 && Board[i][j] == 0)	{
    				if(j == x || i == y)	{
	    				Board[i][j] = 99;
	    				floodfill(j, i);
    				}
    			}
    			
        	}
    		
    	}
    	return false;
    	
    }
    
    
    public void printLog()	{
    	System.out.print("Piece: ");
    	for(int i = 0; i < SetPiecesLog.length; i++)	{
    		System.out.print("\t" + SetPiecesLog[i][0]);
    	}
    	System.out.print("\nRotat: ");
    	for(int i = 0; i < SetPiecesLog.length; i++)	{
    		System.out.print("\t" + SetPiecesLog[i][1]);
    	}
    	System.out.print("\n\n");
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
	
	
	public void SetPieceAt(int x, int y)	{
    	
    	int coords[][] = Piece.getCoords();
        int i, j;
        int Ymin = Piece.getYMin();
        int Ymax = Piece.getYMax();

        int Xmin = Piece.getXMin();
        int Xmax = Piece.getXMax();

        int val = Piece.getValue();

        
        
        int yStart = 0;
		int xStart = 0;
		
		for(i = 0; i < coords.length; i++)	{
			if(coords[i][0] > yStart)	{
				yStart = coords[i][0];
				xStart = coords[i][1];
			}
			else if(coords[i][0] == yStart && coords[i][1] <= xStart) 	{
				xStart = coords[i][1];
			}
		}
        
		//printCoords();
		//System.out.println("Y-Start: " + yStart + "\t" + " X-Start: " + xStart);
		
		
		x = x - xStart;
        y = y - yStart;
        
        //System.out.println("Y-Start: " + y + "\t" + " X-Start: " + x);
        
        for(i = y + Ymin; i >= 0 && i < Board.length && i <= y + Ymax; i++)	{
            for(j = x + Xmin; j >= 0 && j < Board[0].length && j <= x + Xmax; j++)	{
                for(int k = 0; k < coords.length; k++)	{
                    if(i - y == coords[k][0] && j - x == coords[k][1])	{
                        Board[i][j] = val;
                        //SetPieces[i][j] = true;
                        
                    }
                }
            }
        }
        
        //printBoard();
        repaint();
    	
    }
	
	
	
	private boolean tryPieceAt(int x, int y)	{
		int coords[][] = Piece.getCoords();
		//int Position = 0; //Position in coordinates Matrix of Pentomnino
		//int count = 0;
		int i, j;
		int count = 0;
		int Ymin = Piece.getYMin();
		int Ymax = Piece.getYMax();
		
		int Xmin = Piece.getXMin();
		int Xmax = Piece.getXMax();
		
		int val = Piece.getValue();
		
		boolean print = false;
		if(x == 0 && y == 6)
			print = false;
		
		int yStart = 0;
		int xStart = 0;
		
		
		for(i = 0; i < coords.length; i++)	{
			if(coords[i][0] > yStart)	{
				yStart = coords[i][0];
				xStart = coords[i][1];
			}
			else if(coords[i][0] == yStart && coords[i][1] <= xStart) 	{
				xStart = coords[i][1];
			}
		}
		
		
		if(x == 3 && y == 14)	{
			printCoords();
			System.out.println("Y-Start: " + yStart + "\t" + " X-Start: " + xStart);
			System.out.println("Y: " + (y - yStart) + "\t" + " X: " + (x - xStart));
		}
		
		x = x - xStart;
        y = y - yStart;
		
        
        
      //System.out.println("Y-Start: " + yStart + "\t" + " X-Start: " + xStart);
      //System.out.println("Y: " + y + "\t" + " X: " + x);
        
		
		for(i = y + Ymin; i >= 0 && i < Board.length && i <= y + Ymax; i++)	{
            for(j = x + Xmin; j >= 0 && j < Board[0].length && j <= x + Xmax; j++)	{
                for(int k = 0; k < coords.length; k++)	{
                    if(i - y == coords[k][0] && j - x == coords[k][1] && Board[i][j] == 0)	{
                        count++;
                    }
                }
            }
        }
		
		if(print)	{
			System.out.println("\n");
			printCoords();
			System.out.println("\nCOUNT: " + count);
			System.out.println("Y: " + y + "\t" + " X: " + x);
		}
		if(count == 5)
			return true;
		else 	{
			return false;
		}
		
	}
	
	private void printCoords()	{
		int coords[][] = Piece.getCoords();
		for(int i = 0; i < coords.length; i++)	{
			System.out.println("Y: " + coords[i][0] + "\t" + "X: " + coords[i][1]);
		}
		System.out.println("\n");
	}
    
	
	public void paint(Graphics g) {
        int sl = gameWidth / width; // sl=squarelength, because we need squares it doesn't matter if we use width our height
        int x = 0;
        int y = 0;
        

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if (Board[i][j] == 0) {
                    g.setColor(Color.white);
                    g.fillRect((j * sl),  (i * sl), sl, sl);
                } else if (Board[i][j] == 1) {
                    g.setColor(Color.black);
                    g.fillRect( (j * sl), (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 2) {
                    g.setColor(Color.green);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 3) {
                    g.setColor(Color.blue);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 4) {
                    g.setColor(Color.gray);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 5) {
                    g.setColor(Color.yellow);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 6) {
                    g.setColor(Color.orange);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 7) {
                    g.setColor(Color.pink);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 8) {
                    g.setColor(Color.lightGray);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 9) {
                    g.setColor(Color.darkGray);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 10) {
                    g.setColor(Color.red);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 11) {
                    g.setColor(Color.cyan);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                } else if (Board[i][j] == 12) {
                    g.setColor(Color.magenta);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                }
                else if (Board[i][j] == 99)	{
                    g.setColor(Color.PINK);
                    g.fillRect(x + (j * sl), y + (i * sl), sl-1, sl-1);
                }

            }

        }
        
    }
    
}

