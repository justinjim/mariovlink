//MapViewer.java
//Justin Jim & Alex Lau

//importing the world
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
//MapViewer class views the maps created by the MapMaker class by creating an ArrayList of Tiles that corresponds
//to the data file made by the MapMaker class.
public class MapViewer{
	public static void delay (long len)
    {
		try{
			Thread.sleep (len);
		}
		catch (InterruptedException ex){
			System.out.println(ex);
		}
    }
	public static void main(String[]args) throws IOException{
		MapViewerFrame frame = new MapViewerFrame();
		while (true){
			frame.update();
		}

	}
}
//MapViewerFrame that gets a bunch of methods and black magic from Mr.Mackenzie's BaseFrame
class MapViewerFrame extends BaseFrame{
	private String mapname;
	private Scanner infile;
	private ArrayList<Tile> tiles;
	private int type;
	private int tileX;
	private int tileY;
	//MapViewerFrame constructor that takes in the data file MapMaker makes, creates Tile objects from that data file,
	//and then adds them to an ArrayList of tile objects.
	public MapViewerFrame() throws IOException{
		super();
		tiles = new ArrayList<Tile>();
		tileX = 0;
		tileY = 0;
		mapname = "map1";
		try{
			infile = new Scanner(new BufferedReader(new FileReader(mapname+".txt")));
			}
		catch(IOException ex){
			System.out.println("bad");
		}
		while (infile.hasNext()){
			tiles.add(new Tile(infile.nextInt(),tileX,tileY));
			tileX += 20;
			if (tileX == 800){
				tileX = 0;
				tileY += 20;
			}
		}
	}
	//paint method
	//paint method goes through each Tile object in the ArrayList of tile objects and draws them one by one
	public void paint(Graphics g){
    	for (Tile singletile:tiles){
    		//trees
    		if (singletile.getType() == 8){
    			g.drawImage(singletile.getPictures().get(0) ,singletile.getX(),singletile.getY(),null);
    			g.drawImage(singletile.getPictures().get(1),singletile.getX(),singletile.getY(),null);
    		}
    		//everything else
    		else{
				g.drawImage(singletile.getPictures().get(singletile.getSpriteposition()),singletile.getX(),singletile.getY(),null);
				singletile.spriteMove();
    		}
    	}

	}

}
//Tile class
//Tile class creates Tile objects that correspond to the data file MapMaker creates
class Tile{
	private ArrayList<Image>pictures;
	private Image water1 =  new ImageIcon("water1.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image water2 = new ImageIcon("water2.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image floor = new ImageIcon("floor.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image tree = new ImageIcon("tree.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image wallup = new ImageIcon("wallup.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image walldown = new ImageIcon("walldown.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image wallleft = new ImageIcon("wallleft.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image wallright = new ImageIcon("wallright.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image walltopleft = new ImageIcon("walltopleft.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image walltopright = new ImageIcon("walltopright.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image wallbottomleft = new ImageIcon("wallbottomleft.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image wallbottomright = new ImageIcon("wallbottomright.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private int spritecount = 0;
	private int x;
	private int y;
	private int spriteposition;
	private int thetype;
	//Tile constructor that takes in the type (ex 1 = ground), x position, and y position.
	public Tile(int type, int xposition, int yposition){
		thetype = type;
		pictures = new ArrayList<Image>();
		if (type == 0){
			pictures.add(water1);
			pictures.add(water2);
		}
		else if (type == 1){
			pictures.add(floor);
		}
		else if (type == 2){
			pictures.add(wallleft);
		}
		else if (type == 3){
			pictures.add(walltopleft);
		}
		else if (type == 4){
			pictures.add(wallup);
		}
		else if (type == 5){
			pictures.add(wallright);
		}
		else if (type == 6){
			pictures.add(walldown);
		}
		else if (type == 7){
			pictures.add(walltopright);
		}
		else if (type == 8){
			pictures.add(floor);
			pictures.add(tree);
		}
		x = xposition;
		y = yposition;
		spriteposition = 0;
	}
	//getPictures method
	//returns the ArrayList of Image that each Tile object has
	public ArrayList<Image> getPictures(){
		return pictures;
	}
	//getX method
	//returns the x position of the tile
	public int getX(){
		return x;
	}
	//getY method
	//returns the y position of the tile
	public int getY(){
		return y;
	}
	//getSpriteposition method
	//returns the current frame position the sprite is at (for water)
	public int getSpriteposition(){
		return spriteposition;
	}
	//spriteMove method
	//cycles through the ArrayList of images to make the tiles animated
	public void spriteMove(){
		spritecount += 1;
		if (spritecount == 20){
			spritecount = 0;
			if (spriteposition < pictures.size()-1){
				spriteposition += 1;
			}
			else{
				spriteposition = 0;
			}
		}
	}
	//getType method
	//returns what kind of Tile it is
	public int getType(){
		return thetype;
	}

}