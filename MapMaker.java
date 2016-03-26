//MapMaker.java
//Justin Jim & Alex Lau

//importing the world
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

//MapMaker class
//MapMaker class creates data files of maps that are read by the MapViewer class
public class MapMaker{
	public static void delay (long len)
    {
		try{
			Thread.sleep (len);
		}
		catch (InterruptedException ex){
			System.out.println(ex);
		}
    }
	public static void main(String[]args){
		MapMakerFrame frame = new MapMakerFrame();
		while (true){
			frame.update();
		}

	}
}
//MapMakerFrame that gets the holy grail of awesomeness from Mr.Mackenzie's BaseFrame
class MapMakerFrame extends BaseFrame{
	Rectangle [][] grid;
	int [][] drawchecker;
	Color [][] colours;
	Color [] colour;
	int currentselection;
	String mapname;
	PrintWriter outfile;
	String filter;
	//MapMakerFrame Contructor
	//sets sizes to 2-D arrays, sets each spot in colour array to a specific colour, opens a text file saved as
	//the inputted title, and creates the base 40*30 grid for the map
	public MapMakerFrame(){
		super();
		mapname = JOptionPane.showInputDialog("Map name:");
		grid = new Rectangle[40][30];
		drawchecker = new int[40][30];
		colours = new Color[40][30];
		colour = new Color[9];
		colour[0] = Color.blue;
		colour[1] = Color.red;
		colour[2] = Color.orange;
		colour[3] = Color.pink;
		colour[4] = Color.yellow;
		colour[5] = Color.green;
		colour[6] = Color.magenta;
		colour[7] = Color.black;
		colour[8] = Color.lightGray;
		currentselection = 1;
		outfile = null;
		try{
			outfile = new PrintWriter(new BufferedWriter(
				new FileWriter(mapname+".txt")));
			}

		catch(IOException ex){
			System.out.println("Can't open file. Write protected?");
		}
		for (int x = 0; x <800; x += 20){
			for (int y = 0; y < 600; y +=20){
				grid[x/20][y/20] = new Rectangle(x,y,20,20);
				drawchecker[x/20][y/20] = 1;
				colours[x/20][y/20] = Color.red;
			}
		}
	}

	//paint method
	//changes the colour of the next tile to be drawn according to the number inputted
	//if esc is pressed the tiles drawn are printed on the text file according to mapchecker
	//mapchecker is the translation of colours into numbers to be put into the text file and is read later by mapViewer
	public void paint(Graphics g){
    	g.setColor(Color.blue);
    	g.fillRect(0,0,getWidth(), getHeight());
    	if (keys['1']){
    		currentselection = 1;
    	}
    	else if (keys['2']){
    		currentselection = 2;
    	}
    	else if (keys['3']){
    		currentselection = 3;
    	}
    	else if (keys['4']){
    		currentselection = 4;
    	}
    	else if (keys['5']){
    		currentselection = 5;
    	}
    	else if (keys['6']){
    		currentselection = 6;
    	}
    	else if (keys['7']){
    		currentselection = 7;
    	}
    	else if (keys['8']){
    		currentselection = 8;
    	}

    	else if (keys['0']){
    		currentselection = 0;
    	}
		else if (keys[ESC]){
			for (int i = 0; i < 30; i ++){
				for (int k = 0; k < 40;k++){
					if (k != 39){

						outfile.printf(drawchecker[k][i]+" ");
					}
					else{
						outfile.println(drawchecker[k][i]);
					}
				}
			}
			outfile.close();
			System.exit(0);
		}
		for (int i = 0; i < drawchecker.length; i ++){
			for (int k = 0; k< drawchecker[0].length; k++){
				if (drawchecker[i][k]!=0){
					g.setColor(colours[i][k]);
					g.fillRect(grid[i][k].x,grid[i][k].y,20,20);
				}
				if (mx > grid[i][k].x && mx < grid[i][k].x+20 && my > grid[i][k].y && my < grid[i][k].y+20 && mb == 1){
					drawchecker[i][k] = currentselection;
					colours[i][k] = colour[currentselection];
				}
				else if (mx > grid[i][k].x && mx < grid[i][k].x+20 && my > grid[i][k].y && my < grid[i][k].y+20 && mb == 3){
					drawchecker[i][k] = 0;
				}

			}
		}
	}
}
