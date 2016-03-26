//MainGame.java
//Justin Jim & Alex Lau

//In this game you play as Link and hide in trees while trying to kill Marios. 

//Importing the world. 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
//class MainGame
//Where everything comes together in one main. oh dang. 
public class MainGame{
	public static void main(String[]args) throws IOException{
		MainGameFrame frame = new MainGameFrame();
		while(true){
			frame.move();
			frame.attack();
			frame.update();
			frame.collide();
			frame.killMario();
		}
	}
}
//class MainGameFrame
//This class extends off MapViewerFrame which extends off of BaseFrame. Since this project was done in parts and was done by different people
//this was the easiest way to combind everything into one class. MainGameFrame class paints everything 
class MainGameFrame extends MapViewerFrame {
	private int linkX, linkY;
	private int level = 1;
	private double linkAngle = 0;
	private	double slashAngle = 0;
	private	double startSlashAngle = 0;
	private	int slashCoolDown = 0;
	private	boolean slash = false;
	private	int imageNum = 0;
	private	int imageDelay = 4;
	private	Image shownImage;
	String mode = "menu"; 
	//Array of Mario objects that are on the level currently. 
	private ArrayList <Mario> marios = new ArrayList <Mario>();
	//All of the images and their respective arraylists
	private	ArrayList <Image> zeldadown = new ArrayList<Image>();
	private	ArrayList <Image> zeldaright = new ArrayList<Image>();
	private	ArrayList <Image> zeldaleft = new ArrayList<Image>();
	private	ArrayList <Image> zeldaup = new ArrayList<Image>();
	private	ArrayList <Image> zeldadownright = new ArrayList<Image>();
	private	ArrayList <Image> zeldadownleft = new ArrayList<Image>();
	private	ArrayList <Image> zeldaupright = new ArrayList<Image>();
	private	ArrayList <Image> zeldaupleft = new ArrayList<Image>();
	private ArrayList <Tile> temporaryholder = new ArrayList<Tile>(); 
	private	Image zeldadown1 =  new ImageIcon("xander_link_sheet016001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadown2 =  new ImageIcon("xander_link_sheet018001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadown3 =  new ImageIcon("xander_link_sheet022001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaup1 =  new ImageIcon("xander_link_sheet037001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaup2 =  new ImageIcon("xander_link_sheet035002.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaup3 =  new ImageIcon("xander_link_sheet031002.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaleft1 =  new ImageIcon("xander_link_sheet026001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaleft2 =  new ImageIcon("xander_link_sheet028001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaleft3 =  new ImageIcon("xander_link_sheet024001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaright1 =  new ImageIcon("xander_link_sheet041002.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaright2 =  new ImageIcon("xander_link_sheet043002.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaright3 =  new ImageIcon("xander_link_sheet039002.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadownright1 =  new ImageIcon("xander_link_sheet045010.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadownright2 =  new ImageIcon("xander_link_sheet045009.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadownright3 =  new ImageIcon("xander_link_sheet045011.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaupleft1 =  new ImageIcon("xander_link_sheet048008.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaupleft2 =  new ImageIcon("xander_link_sheet046005.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaupleft3 =  new ImageIcon("xander_link_sheet047005.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaupright1 =  new ImageIcon("xander_link_sheet045004.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaupright2 =  new ImageIcon("xander_link_sheet044004.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldaupright3 =  new ImageIcon("xander_link_sheet046004.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadownleft1 =  new ImageIcon("xander_link_sheet051001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadownleft2 =  new ImageIcon("xander_link_sheet050001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image zeldadownleft3 =  new ImageIcon("xander_link_sheet052001.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private	Image daggerpic =  new ImageIcon("dagger.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	private Image menu = new ImageIcon("Menu.png").getImage().getScaledInstance(800,600,Image.SCALE_SMOOTH);
	private Image zeldaLOSER = new ImageIcon("xander_link_sheet052005.png").getImage().getScaledInstance(800,600,Image.SCALE_SMOOTH);
	private	Image zeldaTRIFORCE =  new ImageIcon("xander_link_sheet052006.png").getImage().getScaledInstance(800,600,Image.SCALE_SMOOTH);
	
	//MainGameFrame constructor that creates all the Marios and adds the sprite pictures to their respective ArrayLists. 
	public MainGameFrame()throws IOException{
		super();
		makemarios();
		addPics();
	}
	//delay method
	//Mr.Mac made this.
	public static void delay (long len)
    {
		try{
			Thread.sleep (len);
		}
		catch (InterruptedException ex){
			System.out.println(ex);
		}
    }
	//makemario method
	//This method takes in a data file (different data files per level) and with the information in the data file it creates Mario objects.
	//Example text from data file;
	/*	50,50
		150,150,0,3,80,stationary,0,360,-1
		300,300,90,2,250,stationary,0,360,1
		400,500,180,4,300,preset
		500,500,400,500*/
	// The first two numbers are the starting x,y positions of Link
	//The second row is the first Mario following this format (startx,starty, startangle, speed, line of sight radius, type of motion, 
	//start boundary, boundary range, direction)
	//If the type is preset (it moves), the numbers below it are its pathway. 
    public void makemarios()throws IOException{
    	Scanner inFile = new Scanner(new BufferedReader(new FileReader("pirateinfo"+level+".txt")));
    	String [] linkjainfo = inFile.nextLine().split(",");
    	linkX = Integer.parseInt(linkjainfo[0]);
    	linkY = Integer.parseInt(linkjainfo[1]);
    	while (inFile.hasNextLine()){
    		String [] line = inFile.nextLine().split(",");
    		if (line[5].equals("preset")){
    			String [] movelist = inFile.nextLine().split(",");
    			ArrayList<Integer> route = new ArrayList<Integer>();
    			for (String spot:movelist){
    				route.add(Integer.parseInt(spot));
    			}
    			marios.add(new Mario(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Double.parseDouble(line[2]),
    			Double.parseDouble(line[3]),Integer.parseInt(line[4]),line[5],route));
    		}
    		else{
    			marios.add(new Mario(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Double.parseDouble(line[2]),
    			Double.parseDouble(line[3]),Integer.parseInt(line[4]),line[5],Double.parseDouble(line[6]),Double.parseDouble(line[7]),
    			Integer.parseInt(line[8])));
    		}

    	}
    }
	//addPics method
	//This method takes all the images we made and adds them to their respective ArrayLists, which lets us able to cycle through the 
	//sprites to make it look like the pictures are moving and stuff. 
    public void addPics(){
    	zeldaup.add(zeldaup1);
    	zeldaup.add(zeldaup2);
    	zeldaup.add(zeldaup3);
    	zeldaupright.add(zeldaupright1);
    	zeldaupright.add(zeldaupright2);
    	zeldaupright.add(zeldaupright3);
    	zeldaupleft.add(zeldaupleft1);
    	zeldaupleft.add(zeldaupleft2);
    	zeldaupleft.add(zeldaupleft3);
    	zeldadown.add(zeldadown1);
    	zeldadown.add(zeldadown2);
    	zeldadown.add(zeldadown3);
    	zeldadownright.add(zeldadownright1);
    	zeldadownright.add(zeldadownright2);
    	zeldadownright.add(zeldadownright3);
    	zeldadownleft.add(zeldadownleft1);
    	zeldadownleft.add(zeldadownleft2);
    	zeldadownleft.add(zeldadownleft3);
    	zeldaright.add(zeldaright1);
    	zeldaright.add(zeldaright2);
    	zeldaright.add(zeldaright3);
    	zeldaleft.add(zeldaleft1);
    	zeldaleft.add(zeldaleft2);
    	zeldaleft.add(zeldaleft3);

    }

	//delayImage method
	//delayImage method delays the time between the transitions of the sprites, which makes zelda look normal and not on steroids. 
    public void delayImage(){
    	imageDelay -= 1;
    	if (imageDelay <= 0){
    		imageNum += 1;
    		imageDelay = 2;
    	}

    }
	//move method
	//move method gets input from the user (W,A,S,D) and moves Link(the player) accordingly. There are also collisions in this method to 
	//make sure that Link doesnt walk on water like he's in an old chinese movie, even though that'd be pretty awesome. 
	//move also calls picRotate() which is explained below
	public void move(){
		delay (15);
		if(keys['W'] && linkY-40 >= 11 ){
			delayImage();
			if (tiles.get((int)(Math.round(linkY/20-1)*40+Math.round(linkX/20))).getType()!= 0){
				linkY -= 2;
			}
		}
		if(keys['S'] && linkY+10 <= getHeight()-11){
			delayImage();
			if (tiles.get((int)(Math.round(linkY/20+1)*40+Math.round(linkX/20))).getType()!= 0){
				linkY += 2;
			}
		}
		if(keys['A'] && linkX-20 >= 11 ){
			delayImage();
			if (tiles.get((int)(Math.round(linkY/20)*40+Math.round(linkX/20-1))).getType()!= 0){
				linkX -= 2;
			}
		}
		if(keys['D'] && linkX+10 <= getWidth()- 11 ){
			delayImage();
			if (tiles.get((int)(Math.round(linkY/20)*40+Math.round(linkX/20+1))).getType()!= 0){
				linkX += 2;
			}
		}
		if (imageNum > 2){
			imageNum = 0;
		}
		linkAngle = Math.toDegrees(Math.atan2((my-linkY),(mx-linkX)));
		picRotate();
	}
    //picRotate method
    //this method rotates the Link sprites according to where the mouse is. The face of the sprite will always try to face the mouse
    // which gives it a more realistic feel
    public void picRotate(){
    	if (linkAngle >= 360){
    		linkAngle = linkAngle%360;
    	}
    	if (linkAngle < 0){
    		linkAngle = linkAngle + 360;
    	}
		if (linkAngle >= 337.5 || linkAngle <= 22.5){
			shownImage = zeldaright.get(imageNum);
		}
		else if (linkAngle > 22.5 && linkAngle < 67.5){
			shownImage = zeldadownright.get(imageNum);
		}
		else if (linkAngle >= 67.5 && linkAngle <= 112.5){
			shownImage = zeldadown.get(imageNum);
		}
		else if (linkAngle > 112.5 && linkAngle < 157.5){
			shownImage = zeldadownleft.get(imageNum);
		}
		else if (linkAngle >= 157.5 && linkAngle <= 202.5){
			shownImage = zeldaleft.get(imageNum);
		}
		else if (linkAngle > 202.5 && linkAngle < 247.5){
			shownImage = zeldaupleft.get(imageNum);
		}
		else if (linkAngle >= 247.5 && linkAngle <= 292.5){
			shownImage = zeldaup.get(imageNum);
		}
		else if (linkAngle > 292.5 && linkAngle < 337.5){
			shownImage = zeldaupright.get(imageNum);
		}
    }
    //attack method
    //attack method gets input from the user (mouse clicks) and determines whether an attack is made or not.
    //if an attack is made then it determines how much the sword rotates from the initial angle. 
	public void attack(){
		if (mb==1 && slash==false && slashCoolDown <= 0){
			slash = true;
			slashCoolDown = 25;
			startSlashAngle = linkAngle;
		}
		slashCoolDown -= 1;
		mb = 0;
		if (slash){
			slashAngle += 10;
			if (slashAngle > startSlashAngle + 45){
				slash = false;
			}
		}
		else{
			slashAngle = linkAngle;
		}
	}
	//collide method
	//collide method determines whether or not Link is close enough to be seen by any of the Marios (checks the radius around Marios)
	// and if he is close enough, it checks the arc (line of sight) to determine whether or not the Marios chase Link or not. 
	public void collide(){
		for (Mario p:marios){
			if (Math.sqrt(Math.pow(linkX-p.x,2)+ Math.pow(linkY-p.y,2)) <= 10+p.sightRadius/2){
				double pirateTolinkja = 180-Math.toDegrees(Math.atan2(p.y-linkY,p.x-linkX));
				if ((pirateTolinkja > 360-(p.angle%360)-50 || (pirateTolinkja < 360-(p.angle%360)-50 && pirateTolinkja +360 - (360-(p.angle%360)-50) < 100 && pirateTolinkja +360 - (360-(p.angle%360)-50) > 0))
					 && (pirateTolinkja < (360-(p.angle%360)+50)%360 || (pirateTolinkja > (360-(p.angle%360)+50)%360 && (360-(p.angle%360)+50)%360 + 360 - pirateTolinkja < 100 && (360-(p.angle%360)+50)%360 + 360 - pirateTolinkja > 0))
					 	&& tiles.get((int)(Math.round(linkY/20)*40+Math.round(linkX/20))).getType()!= 8){
					p.setCollide(true);
				}
				else {
					p.setCollide(false);
				}
			}
			else{
				p.setCollide(false);
			}
			dielinkdie(p);
		}
	}
	//drawmarios method
	//this method draws all of the marios in the marios arraylist onto the screen. 
	public void drawmarios(Graphics g){
		for (int i = 0; i < marios.size(); i++){
			marios.get(i).move(linkX,linkY);
			int pX = marios.get(i).x;
			int pY = marios.get(i).y;
			double pAngle = marios.get(i).angle;
			int pSight = marios.get(i).sightRadius;
			g.setColor(Color.blue);
			g.drawImage(marios.get(i).getImage(),pX-10,pY-10,null);
			g.setColor(marios.get(i).sightColor);
			g.fillArc(pX-pSight/2,pY-pSight/2,pSight,pSight,(int)(360-pAngle-50),100);
		}
	}
	//killMario method
	//killMario method determines if there is any marios within killing range of Link once Link swings his sword and removes 
	//marios from the arraylist if true. If the arraylist of marios is empty, that means the level is over and the user moves on
	//to the next level. BAAAAAAAAAAAAAAAYUM.  
	public void killMario()throws IOException{
		ArrayList <Mario> deadmarios = new ArrayList<Mario>();
		for (Mario p:marios){
			if (Math.sqrt(Math.pow(p.x - linkX,2) + Math.pow(p.y - linkY,2)) <= 35*Math.sqrt(2) && p.collide == false){
				double pirateTolinkja = (180+Math.toDegrees(Math.atan2(p.y-linkY,p.x-linkX)))%360;
				if ((pirateTolinkja+180)%360 <= slashAngle+20 && (pirateTolinkja+180)%360 >= startSlashAngle-30 && slash){
					deadmarios.add(p);
				}
			}
		}

		for (Mario deadp:deadmarios){
			if (marios.contains(deadp)){
				marios.remove(deadp);
			}
		}
		if(marios.size()==0){
			level += 1;
			if (winlinkwin()){
			
				makemarios();
				mapchanger("map"+level);
			}
			else{
				mode = "win";
			}
		}

	}
	
	public void dielinkdie(Mario m){
		if (m.attack){
			mode = "death";
		}
	}
	
	public boolean winlinkwin(){
		if (level >= 9){
			return false; 
		}
		else{
			return true; 
		}
	}
	
	//mapchanger method
	//mapchanger method changes the map when all of the marios are dead. This is done by creating a new ArrayList of Tiles (new maps stored 
	//on data files). this changes the map because the map is drawn by going through each Tile object in the ArrayList of tiles and drawing each
	//20by20 tile one at a time. If you change the ArrayList, you change the map. 
	public void mapchanger(String newmap){
		try{
			infile = new Scanner(new BufferedReader(new FileReader(newmap+".txt")));
			}
		catch(IOException ex){
			System.out.println("bad");
		}

		tileX = 0;
		tileY = 0;
		while (infile.hasNext()){
			temporaryholder.add(new Tile(infile.nextInt(),tileX,tileY));
			tileX += 20;
			if (tileX == 800){
				tileX = 0;
				tileY += 20;
			}
		}
		tiles = new ArrayList<Tile>(temporaryholder); 
		temporaryholder.clear();
	}
	//paint method. 
	//paint method was extended from BaseFrame which was made by Mr.Mac and contains a bunch of dark magic and stuff. 
	//it paints stuff. 
	public void paint(Graphics g){
		if (mode.equals("menu")){
			g.drawImage(menu,0,0,null);
			if (keys[SPACE]){
				mode = "game"; 
			}
		}
		else if (mode.equals("game")){
			//drawing the map
	    	for (Tile singletile:tiles){
	    		if (singletile.getType() == 8){
	    			g.drawImage(singletile.getPictures().get(0) ,singletile.getX(),singletile.getY(),null);
	    			g.drawImage(singletile.getPictures().get(1),singletile.getX(),singletile.getY(),null);
	    		}
	    		else{
					g.drawImage(singletile.getPictures().get(singletile.getSpriteposition()),singletile.getX(),singletile.getY(),null);
					singletile.spriteMove();
	    		}
	    	}
			g.setColor(Color.red);
			Graphics2D g2D = (Graphics2D)g;
			AffineTransform saveXform = g2D.getTransform();
			AffineTransform at = new AffineTransform();
			g.drawImage(shownImage,(int)linkX-10,(int)linkY-10,null);
			at.rotate(Math.toRadians(slashAngle-42),linkX,linkY);
			g2D.transform(at);
			g2D.drawImage(daggerpic,linkX,linkY,null);
			g2D.setTransform(saveXform);
			drawmarios(g);
			//draws the trees 
			for (Tile singletile:tiles){
				if (singletile.getType()==8){
	    			g.drawImage(singletile.getPictures().get(1),singletile.getX(),singletile.getY(),null);
				}
	    	}
		}
		else if(mode.equals("win")){
			g.setColor(Color.black);
			g.fillRect(0,0,getWidth(),getHeight());
			g.drawImage(zeldaTRIFORCE,0,0,null);
			if (keys[SPACE]){
				mode = "menu";
				marios.clear();
				tiles.clear();
				level = 0; 
				
				System.out.println("fag");
			}
		}
		else if(mode.equals("death")){
			g.setColor(Color.black);
			g.fillRect(0,0,getWidth(),getHeight());
			g.drawImage(zeldaLOSER,0,0,null);
			if (keys[SPACE]){
				mode = "menu";
				marios.clear();
				tiles.clear();
				level = 0; 
				
				System.out.println("fag");
			}
		}
				
	}
}


