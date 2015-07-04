import processing.core.*;

public class BasicSimulation extends PApplet {

	Car mycar;
	DrawNetwork dn;
	PGraphics pg;
	int matched = 1;
	int fr_rate=60;
	PGraphics moving;
	

	public void setup() {
		size(900,650);
		stroke(0);
		moving = createGraphics(width, height);
		pg = createGraphics(width, height);
		mycar = new Car(this,0);		
		dn = new DrawNetwork(this);
		image(pg, 0, 0);
	}


	public void draw() {
		frameRate(fr_rate);
		if(matched==1)
			move();
		moving.beginDraw();
		moving.background(165,100);
		display();
		moving.endDraw();
		image(moving, 0, 0);
		image(pg, 0, 0);
	}

	void move() {
		mycar.x = mycar.x + mycar.speed;
		if (mycar.x > width)
			mycar.x = 0;
	}

	void display() {
		moving.fill(mycar.c);
		if(mycar.x < 170)
			moving.rect(mycar.x,mycar.y,15,10);
		else if(mycar.x > 215)
			moving.rect(mycar.x-45,mycar.y,15,10);
		else
		{
			moving.fill(255,0,0);
			moving.rect(170,mycar.y,15,10);
			moving.fill(mycar.c);
		}
		
		// Overtaking effected car   
		moving.fill(255,255,0);
		if((900-mycar.x) > 800)
			moving.rect(900-mycar.x,mycar.y+15,15,10);
		else if(740 <= (900-mycar.x) &&  (900-mycar.x) <= 800)
		{
			moving.fill(255,0,0);
			moving.rect((float) (900-0.95*mycar.x),mycar.y+15,15,10);
			moving.fill(70);
		}
		else if(680 <= (900-mycar.x) &&  (900-mycar.x) <= 740)
		{
			moving.fill(255,0,0);
			int temp_y = (int)((740-(900-mycar.x))/4); 
			moving.rect((float) (900-0.95*mycar.x),mycar.y+15+temp_y,15,10);
			moving.fill(0,0,255,125);
		}
		else
			moving.rect(900-mycar.x,mycar.y+15+15,15,10);


		// Overtaking Car which is doing it
		moving.fill(0,0,255,125);
		if(900-mycar.x>800)
			moving.rect((float) (900-1.25*mycar.x),mycar.y-15+45,15,10);
		else if(740 <= (900-mycar.x) &&  (900-mycar.x) <= 800)
		{
			moving.fill(255,0,0);
			int temp_y = (int)((800-(900-mycar.x))/4); 
			moving.rect((float) (900-1.25*mycar.x),mycar.y-15-temp_y+45,15,10);
			moving.fill(0,0,255,125);
		}
		else if(900-mycar.x<740)
			moving.rect((float) (900-1.25*mycar.x),mycar.y-15+15+15,15,10);


		moving.fill(180);
		moving.rect(mycar.y+85,mycar.x-85,10,15);
		
		moving.fill(220);
		if(650-mycar.x>155)
			moving.rect(mycar.y+70,650-mycar.x,10,15);
//		else if(110 <= (650-mycar.x) &&  (650-mycar.x) <= 155)
//		{
//			moving.fill(255,0,0);
//			moving.rect(mycar.y+70,155,10,15);
//			moving.fill(220);
//		}
		else if(650-mycar.x<155)
			moving.rect(mycar.y+70,650-(mycar.x),10,15);

		// Green Car
		moving.fill(0,255,0);
		if(900-mycar.x-80>210)
			moving.rect(900-mycar.x-80,mycar.y+430,15,10);
		else if((900-mycar.x-80) >= 160 &&  (900-mycar.x-80) <= 210)
		{
			moving.fill(255,0,0);
			moving.rect(210,mycar.y+430,15,10);
			moving.fill(mycar.c);
		}
		else if(900-mycar.x-80<160)
			moving.rect(mycar.y+85,mycar.x-115,10,15);

	}

	public void keyPressed()
	{
		if (key == 's')
		{
			matched = 1 - matched;
		}
		else if (key == 'a')
		{
			fr_rate = fr_rate + 15;
		}
		else if (key == 'd')
		{
			fr_rate = fr_rate - 15;
		}
		else if (key == 'q')
		{
			fr_rate = 60;
		}
		else if (key == 'r')
		{
			mycar.x = 0;
		}
	}

	//	public static void main(String[] args) {
		//		// TODO Auto-generated method stub
	//		PApplet.main(new String[] { "--present", "Traffic_car" });
	//
	//	}	

}
