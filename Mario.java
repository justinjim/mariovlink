//Mario.java
//Justin Jim & Alex Lau

//importing the world
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
//class Mario
//this class creates Mario objects which vary depending on the type of mario thats being created.
class Mario{
	//All the basic parameters of a mario such as x,y,angle,speed,movement direction, image, etc.
	protected int x;
	protected int y;
	protected int originalX;
	protected int originalY;
	protected int routespot;
	protected int direction;
	protected int sightRadius;
	protected int imageNum;
	protected int imageDelay;
	protected Color sightColor;
	protected double angle;
	protected double speed;
	protected double boundary;
	protected double boundaryRange;
	protected boolean collide;
	protected boolean attack;
	protected String type;
	protected Image shownImage;
	//ArrayList containing the route of the preset marios
	protected ArrayList<Integer> route = new ArrayList<Integer>();
	//All the images and respective arraylist for each of the 8 directions
	protected ArrayList<Image> marioup = new ArrayList<Image>();
	protected ArrayList<Image> mariodown = new ArrayList<Image>();
	protected ArrayList<Image> marioleft = new ArrayList<Image>();
	protected ArrayList<Image> marioright = new ArrayList<Image>();
	protected ArrayList<Image> mariotopright = new ArrayList<Image>();
	protected ArrayList<Image> mariotopleft = new ArrayList<Image>();
	protected ArrayList<Image> mariobottomright = new ArrayList<Image>();
	protected ArrayList<Image> mariobottomleft = new ArrayList<Image>();
	protected ArrayList<ArrayList<Image>> mariosprites = new ArrayList<ArrayList<Image>>();
	protected Image mario00 =  new ImageIcon("mario00.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario01 =  new ImageIcon("mario01.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario02 =  new ImageIcon("mario02.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario10 =  new ImageIcon("mario10.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario11 =  new ImageIcon("mario11.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario12 =  new ImageIcon("mario12.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario20 =  new ImageIcon("mario20.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario21 =  new ImageIcon("mario21.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario22 =  new ImageIcon("mario22.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario30 =  new ImageIcon("mario30.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario31 =  new ImageIcon("mario31.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario32 =  new ImageIcon("mario32.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario40 =  new ImageIcon("mario41.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario41 =  new ImageIcon("mario42.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario42 =  new ImageIcon("mario43.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario50 =  new ImageIcon("mario50.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario51 =  new ImageIcon("mario51.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario52 =  new ImageIcon("mario52.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario60 =  new ImageIcon("mario60.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario61 =  new ImageIcon("mario61.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario62 =  new ImageIcon("mario62.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario70 =  new ImageIcon("mario70.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario71 =  new ImageIcon("mario71.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	protected Image mario72 =  new ImageIcon("mario72.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
	
	// Mario constructor for marios that are stationary
	public Mario(int x, int y, double angle,double spd,int sight, String type, double boundary
		, double brange, int direction){
		this.x = x;
		this.y = y;
		originalX = x;
		originalY = y;
		this.angle = angle;
		speed = spd;
		sightRadius = sight;
		this.type = type;
		this.boundary = boundary;
		boundaryRange = brange;
		this.direction = direction;
		sightColor = new Color (0,255,0,100);
		addPics();
		imageNum = 0;
		imageDelay = 5;
		attack = false;
	}
	
	// Mario constructor for marios that are preset
	public Mario(int x, int y, double angle,double spd,int sight, String type, ArrayList<Integer> route){
		this.x = x;
		this.y = y;
		this.angle = angle;
		speed = spd;
		sightRadius = sight;
		this.type = type;
		this.route = route;
		routespot = 0;
		sightColor = new Color (0,255,0,100);
		addPics();
		imageNum = 0;
		imageDelay = 5;
		attack = false;

	}
	
	//addPics method
	//adds images to their respective arraylist
	public void addPics(){
		marioup.add(mario00);
		marioup.add(mario01);
		marioup.add(mario02);
		mariodown.add(mario10);
		mariodown.add(mario11);
		mariodown.add(mario12);
		marioright.add(mario20);
		marioright.add(mario21);
		marioright.add(mario22);
		marioleft.add(mario30);
		marioleft.add(mario31);
		marioleft.add(mario32);
		mariotopright.add(mario40);
		mariotopright.add(mario41);
		mariotopright.add(mario42);
		mariotopleft.add(mario50);
		mariotopleft.add(mario51);
		mariotopleft.add(mario52);
		mariobottomright.add(mario60);
		mariobottomright.add(mario61);
		mariobottomright.add(mario62);
		mariobottomleft.add(mario70);
		mariobottomleft.add(mario71);
		mariobottomleft.add(mario72);
		mariosprites.add(marioright);
		mariosprites.add(mariobottomright);
		mariosprites.add(mariodown);
		mariosprites.add(mariobottomleft);
		mariosprites.add(marioleft);
		mariosprites.add(mariotopleft);
		mariosprites.add(marioup);
		mariosprites.add(mariotopright);
	}
	
	//setCollide method
	//sets the collide variable to the one in the parameter. Collide is true when link is in this
	//mario's line of sight
	public void setCollide(boolean touch){
		collide = touch;
	}
	
	//toSpot method
	//changes mario's x, y, and angle to the new position's x,y using angles and their speed
	//new image is show movement is determined from cycling through the arraylist using imageNum
	//changes mario's angle to the new angle taking in consideration going over 0 degrees
	//used mostly for preset marios where their new position is determined through routespot
	public void toSpot(int routeX,int routeY, double routeAngle){
		if (x < routeX -speed || x > routeX + speed){
			x += (int)Math.round((Math.cos(Math.toRadians(routeAngle))*speed));
		}
		if (y < routeY -speed || y > routeY + speed){
			y += (int)Math.round((Math.sin(Math.toRadians(routeAngle))*speed));
		}
		imageDelay -= 1;

		if (imageDelay <= 0){
			imageNum += 1;
			imageDelay = 2;
		}

		if (imageNum > 2){
			imageNum = 0;
		}
		if (routeX -(speed+1) <= x && x <= routeX + (speed+1) && routeY - (speed+1) <= y && y <= routeY + (speed+1)){
			routespot += 2;
		}
		if (routespot+2 > route.size()){
			routespot = 0;
		}
		routeAngle = (routeAngle+360)%360;
		if ((angle+360)%360 < (routeAngle)-(speed+1)){
			if (routeAngle - (angle+360)%360 > (angle+360)%360+360 - routeAngle){
				angle -=1 * speed;
			}
			else {
				angle +=1 * speed;
			}
		}
		else if ((angle+360)%360 > routeAngle+(speed+1)){
			if ((angle+360)%360 - routeAngle > routeAngle+360 - (angle+360)%360){
				angle += 1 * speed;
			}
			else {
				angle -= 1 * speed;
			}
		}
	}
	
	//picRotate method
	//changes the shownImage of mario depending on the angle of him
	public void picRotate(){
		if (angle >= 337.5 || angle <= 22.5){
			shownImage = marioright.get(imageNum);
		}
		else if (angle > 22.5 && angle < 67.5){
			shownImage = mariobottomright.get(imageNum);
		}
		else if (angle >= 67.5 && angle <= 112.5){
			shownImage = mariodown.get(imageNum);
		}
		else if (angle > 112.5 && angle < 157.5){
			shownImage = mariobottomleft.get(imageNum);
		}
		else if (angle >= 157.5 && angle <= 202.5){
			shownImage = marioleft.get(imageNum);
		}
		else if (angle > 202.5 && angle < 247.5){
			shownImage = mariotopleft.get(imageNum);
		}
		else if (angle >= 247.5 && angle <= 292.5){
			shownImage = marioup.get(imageNum);
		}
		else if (angle > 292.5 && angle < 337.5){
			shownImage = mariotopright.get(imageNum);
		}
	}
	
	//move method
	//keeps angle within 0-360 degrees. 
	//calls picRotate()
	//does various movement changes based on mario type if link is not in line of sight
	//stationary marios have their angle changed based on speed and makes sure angle stays within boundary that is set
	//preset marios use toSpot to go towards the positions set within their route pattern
	//if link is in line of sight mario uses toSpot to go towards link and stops if too close where attack is set true leading
	//to a gameover
	public void move(int ninX, int ninY){
		if (angle >= 360){
			angle = angle%360;
		}
		if (angle < 0){
			angle = 360+angle;
		}
		picRotate();
		if (collide){
			sightColor = new Color(255,0,0,100);
			if (Math.sqrt(Math.pow(ninX-x,2)+Math.pow(ninY-y,2)) >= 22){
				toSpot(ninX,ninY,Math.toDegrees(Math.atan2(ninY-y,ninX-x)));
			}
			else{
				attack = true;
				toSpot(x,y,Math.toDegrees(Math.atan2(ninY-y,ninX-x)));
			}

		}
		else {
			sightColor = new Color(0,255,0,100);
			if (type.equals("stationary")){

				if (x < originalX -(speed+2) || x > originalX + (speed+2) || y < originalY -(speed+2) || y > originalY + (speed+2)){
					toSpot(originalX,originalY,Math.toDegrees(Math.atan2(originalY-y,originalX-x)));
				}
				else{
					angle += speed*direction;
					if (boundaryRange != 360){

						if ((angle+360) % 360 <= boundary){
							direction = 1;
						}
						else if ((angle+360) % 360 >=boundary+boundaryRange){
							direction = -1;
						}
					}
				}

			}
			else if (type.equals("preset")){
				int routeX = route.get(routespot);
				int routeY = route.get(routespot+1);
				double routeAngle = Math.toDegrees(Math.atan2(routeY-y,routeX-x));
				toSpot(routeX,routeY,routeAngle);
			}
		}
	}
	//getImage method
	//returns mario image that needs to be displayed
	public Image getImage(){
		return shownImage;
	}


}