
public class OptimalPentomino {

	private static String[] Pentominos = {"IShape","PShape","FShape","LShape","NShape","TShape","UShape","VShape","WShape","XShape","YShape","ZShape"};
	private static String[] Pentominos2 = {"FShape","PShape","TShape","UShape","VShape","WShape","XShape","YShape","ZShape"};

	private int [][] coords;
	private int [][][] coordsTable;
	private int Val;
	private String PentominoName;
	private int Ran = 0;
	
	
	
	public int position;
	public int rotation;
	
	public OptimalPentomino()	{
		
		coords = new int[5][2];
		SetShape(0);
		
	}
	
	public void SetShape(int randomNumber)	{
		
		//Coordinates need to be changed
		coordsTable = new int[][][] {
        	{ { 0,-2 }, { 0, -1 }, { 0, 0 }, { 0, 1 },{0,2} /* I */ },
        	{ { 0,0 }, {0, - 1}, { 0,1 }, { -1, -1 }, { -1, 0} /* P */},
        	{ { 0,0 }, { 0, -1}, { 1, 0 }, { 0, 1},{-1,1} /* F */},
        	{ { 0,0 }, { 0, 1 }, { 0, 2 }, { 0, -1},{1,-1} /* L */},
        	{ { 0,0 }, { -1, 0 }, { -1, -1 }, { -1,-2},{0,1} /* N */},
        	{ { 0,0 }, { 0, -1 }, { 0, 1}, { -1,1},{1,1} /* T */},
        	{ { 0,0 }, { -1, 0 }, { -1, 1 }, { 1, 0},{1,1} /* U */},
        	{ { 0,0 }, { 0, -1 }, { 0, -2 }, { -1, 0},{-2,0} /* V */},
        	{ { 0,0 }, { 0,-1 }, { -1, 1 }, { -1, 0},{1,-1} /* W */},
        	{ { 0,0 }, { 0, 1 }, { 0, -1 }, { -1, 0},{1,0} /* X */},
        	{ { 0,0 }, { 0, 1 }, { 0, -1 }, { 0, -2},{1,0} /* Y */},
        	{ { 0,0 }, { 0, -1 }, { 0,1 }, {-1, -1},{1,1} /* Z */},


        };
        
        for (int i = 0; i < 5 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[randomNumber][i][j];
            }
        }
        
        
		
	}
	
	
public void SetShape2(int randomNumber)	{

	//Coordinates need to be changed
	coordsTable = new int[][][] {

		{ { 0,0 }, { 0, -1}, { 1, 0 }, { 0, 1},{-1,1} /* F */},
		{ { 0,0 }, {0, - 1}, { 0,1 }, { -1, -1 }, { -1, 0} /* P */},
		{ { 0,0 }, { 0, -1 }, { 0, 1}, { -1,1},{1,1} /* T */},
		{ { 0,0 }, { -1, 0 }, { -1, 1 }, { 1, 0},{1,1} /* U */},
		{ { 0,0 }, { 0, -1 }, { 0, -2 }, { -1, 0},{-2,0} /* V */},
		{ { 0,0 }, { 0,-1 }, { -1, 1 }, { -1, 0},{1,-1} /* W */},
		{ { 0,0 }, { 0, 1 }, { 0, -1 }, { -1, 0},{1,0} /* X */},
		{ { 0,0 }, { 0, 1 }, { 0, -1 }, { 0, -2},{1,0} /* Y */},
    	{ { 0,0 }, { 0, -1 }, { 0,1 }, {1, -1},{-1,1} /* Z */},
    	


    };
    
    for (int i = 0; i < 5 ; i++) {
        for (int j = 0; j < 2; ++j) {
            coords[i][j] = coordsTable[randomNumber][i][j];
        }
    }
    
    
	}
	
	
	private void setX(int index, int x) { coords[index][0] = x; }
    private void setY(int index, int y) { coords[index][1] = y; }
    public int ReturnX(int index) { return coords[index][0]; }
    public int ReturnY(int index) { return coords[index][1]; }
	
	
	public void getNewPentomino()	{
		//System.out.println("Trying to get new Pentomino");
		Ran = (int) (Math.random() * Pentominos.length);
		PentominoName = Pentominos[Ran];
		SetShape(Ran);
		
	}
	
	public String getName()	{
		return PentominoName;
	}
	
	public void getNextStartPentomio(int Position, int rot)	{
		PentominoName = Pentominos[Position];
		rotation = rot;
		position = Position;
		
		SetShape(Position);
		//+90°
        if(rot == 2)	{
        	RotateRight();
        }
        //+180°
        else if(rot == 3)	{
        	RotateRight();
        	RotateRight();
        }
        else if(rot == 4)	{
        	RotateRight();
        	RotateRight();
        	RotateRight();
        }
		
	}
	
	public void getNextPentomio(int Position, int rot)	{
		PentominoName = Pentominos2[Position];
		rotation = rot;
		position = Position;
		
		SetShape2(Position);
		//+90°
		if(rot == 2)	{
        	RotateRight();
        }
        //+180°
        else if(rot == 3)	{
        	RotateRight();
        	RotateRight();
        }
        else if(rot == 4)	{
        	RotateRight();
        	RotateRight();
        	RotateRight();
        }
		
	}

	
	public void printCoords()	{
		//System.out.println(PentominoName);
		for(int i = 0; i < 5; i++)	{
			for(int j = 0; j < 2; j++)	{
				System.out.print(coords[i][j] + "\t");
			}
			System.out.print("\n");
		}
		System.out.print("\n\n");
	}
	
	
	public int getValueOfPosition(int Pos)	{
		if(Pos == 0)	{
			return 1;
		}
		else if(Pos == 1)	{
			return 8;
		}
		else if(Pos == 2)	{
			return 4;
		}
		else if(Pos == 3)	{
			return 9;
		}
		else if(Pos == 4)	{
			return 5;
		}
		else if(Pos == 5)	{
			return 7;
		}
		else if(Pos == 6)	{
			return 12;
		}
		else if(Pos == 7)	{
			return 6;
		}
		else if(Pos == 8)	{
			return 10;
		}
		else if(Pos == 9)	{
			return 3;
		}
		else if(Pos == 10)	{
			return 11;
		}
		else if(Pos == 11)	{
			return 2;
		}
		else
			return 99;
	}
	
	public int getValue2(int Pos)	{
		String name = Pentominos2[Pos];
		//System.out.println(PentominoName);
		if(name.equals("IShape"))	{
			return 1;
		}
		else if(name.equals("PShape"))	{
			return 8;
		}
		else if(name.equals("FShape"))	{
			return 4;
		}
		else if(name.equals("LShape"))	{
			return 9;
		}
		else if(name.equals("NShape"))	{
			return 5;
		}
		else if(name.equals("TShape"))	{
			return 7;
		}
		else if(name.equals("UShape"))	{
			return 12;
		}
		else if(name.equals("VShape"))	{
			return 6;
		}
		else if(name.equals("WShape"))	{
			return 10;
		}
		else if(name.equals("XShape"))	{
			return 3;
		}
		else if(name.equals("YShape"))	{
			return 11;
		}
		else if(name.equals("ZShape"))	{
			return 2;
		}
		else 
			return 99;
		
	}
	
	
	public int getValue()	{
		//System.out.println(PentominoName);
		if(PentominoName.equals("IShape"))	{
			return 1;
		}
		else if(PentominoName.equals("PShape"))	{
			return 8;
		}
		else if(PentominoName.equals("FShape"))	{
			return 4;
		}
		else if(PentominoName.equals("LShape"))	{
			return 9;
		}
		else if(PentominoName.equals("NShape"))	{
			return 5;
		}
		else if(PentominoName.equals("TShape"))	{
			return 7;
		}
		else if(PentominoName.equals("UShape"))	{
			return 12;
		}
		else if(PentominoName.equals("VShape"))	{
			return 6;
		}
		else if(PentominoName.equals("WShape"))	{
			return 10;
		}
		else if(PentominoName.equals("XShape"))	{
			return 3;
		}
		else if(PentominoName.equals("YShape"))	{
			return 11;
		}
		else if(PentominoName.equals("ZShape"))	{
			return 2;
		}
		else 
			return 99;
		
	}
	
	public int[][] getCoords()	{
		return coords;
	}
	
	
	
    

	
	
	public void RotateRight()	{
		
		OptimalPentomino NewPiece = new OptimalPentomino();
		NewPiece.getNewPentomino();
		
		
		for(int i = 0; i < 5; i++)	{
			NewPiece.setX(i, ReturnY(i));
			NewPiece.setY(i, -ReturnX(i));
		}
		coords = NewPiece.getCoords();
	}
	
	
	public void RotateLeft()	{
		
		OptimalPentomino NewPiece = new OptimalPentomino();
		NewPiece.getNewPentomino();
		
		
		for(int i = 0; i < 5; i++)	{
			NewPiece.setX(i, -ReturnY(i));
			NewPiece.setY(i, ReturnX(i));
		}
		coords = NewPiece.getCoords();
	}
	
	
	
	
	public void rotate(int rotation)	{

		
		int[][] rotMatrix1 = { {0, -1}, {1, 0} };
		int[][] rotMatrix2 = { {0, 1}, {-1, 0} };
		
		int[][] RotationMatrix = rotMatrix1;
		
		 int[][] temp = coords;
		

		if(rotation == -1)	{
			RotationMatrix = rotMatrix2;
		}
		
		for (int i = 0; i < 2; i++)	{
			for (int j = 0; j < 2; j++)	{
				System.out.print(RotationMatrix[i][j] + "\t");
			}
			System.out.print("\n");
		}
		System.out.print("\n\n");
			

			
		for (int i = 0; i < 5; i++)	{
			for (int j = 0; j < 2; j++)	{
				for (int k = 0; k < 2; k++)	{
					coords[i][j] += temp[i][k] * RotationMatrix[j][k];
				}
			}
		}

	}
	
	
	
	
	
	
	public static void Print(int[][] array)	{
	for(int i = 0; i < array.length; i++)	{
		for(int j = 0; j < array[0].length; j++)	{
			System.out.print(array[i][j] + " ");
		}
		System.out.print("\n");
	}
	System.out.print("\n\n");
	}
	
	public int getXMin()	{
		int i;
		int m = 0;
		for(i = 0; i < coords.length; i++)	{
			if(Math.min(m, coords[i][1]) < m)	{
				m = Math.min(m, coords[i][1]);
			}
		}
		return m;
	}
	
	public int getXMax()	{
		int i;
		int m = 0;
		for(i = 0; i < coords.length; i++)	{
			if(Math.max(m, coords[i][1]) > m)	{
				m = Math.max(m, coords[i][1]);
			}
		}
		return m;
	}

	
	public int getYMin()	{
		int i;
		int m = 0;
		for(i = 0; i < coords.length; i++)	{
			if(Math.min(m, coords[i][0]) < m)	{
				m = Math.min(m, coords[i][0]);
			}
		}
		return m;
	}
	
	public int getYMax()	{
		int i;
		int m = 0;
		for(i = 0; i < coords.length; i++)	{
			if(Math.max(m, coords[i][0]) > m)	{
				m = Math.max(m, coords[i][0]);
			}
		}
		return m;
	}
	
	
	

}



