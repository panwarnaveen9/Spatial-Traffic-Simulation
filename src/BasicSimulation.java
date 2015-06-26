
import processing.core.*;

public class BasicSimulation extends PApplet {
	
	Car mycar;
	int matched = 1;
	int fr_rate=60;

	public void setup() {
	  mycar = new Car(this,0);
	  size(400,400);
	}


	public void draw() {
	  background(245,240,240);
	  frameRate(fr_rate);
	  if(matched==1)
	  {
	    move();
	  }
	  display();
	}

	void move() {
	  mycar.x = mycar.x + mycar.speed;
	  if (mycar.x > width) {
	    mycar.x = 0;
	  }
	}

	void display() {
	  
	  line(0,85,400,85);
	  draw_divider(0,(float)97.5,400,(float)97.5);
	  line(0,110,400,110);

	  line(85,0,85,400);
	  draw_divider((float)97.5,400,(float)97.5,0); 
	  line(110,0,110,400);
	  
	  fill(mycar.c);
	  if(mycar.x < 70)
	    rect(mycar.x,mycar.y-15,15,10);
	  else if(mycar.x > 95)
	    rect(mycar.x-25,mycar.y-15,15,10);
	  else
	  {
	    fill(255,0,0);
	    rect(70,mycar.y-15,15,10);
	    fill(mycar.c);
	  }
	    
	  // Overtaking effected car   
	  fill(70);  
	  if(240 <= (400-mycar.x) &&  (400-mycar.x) <= 300)
	  {
	    fill(255,0,0);
	    rect((float) (400-0.95*mycar.x),mycar.y,15,10);
	    fill(70);
	  }
	  else
	    rect(400-mycar.x,mycar.y,15,10);
	  
	  
	  // Overtaking Car which is doing it
	  fill(0,0,255,125);
	  if(400-mycar.x>300)
	    rect((float) (400-1.25*mycar.x),mycar.y-15,15,10);
	  else if(240 <= (400-mycar.x) &&  (400-mycar.x) <= 300)
	  {
	    fill(255,0,0);
	    int temp_y = (int)((300-(400-mycar.x))/4); 
	    rect((float) (400-1.25*mycar.x),mycar.y-15+temp_y,15,10);
	    fill(0,0,255,125);
	  }
	  else if(400-mycar.x<240)
	    rect((float) (400-1.25*mycar.x),mycar.y-15+15,15,10);
  
	  
	  fill(180);
	  rect(mycar.y,mycar.x,10,15);
	  fill(220);
	  if(400-mycar.x>110)
	    rect(mycar.y-15,400-mycar.x,10,15);
	  else if(65 <= (400-mycar.x) &&  (400-mycar.x) <= 110)
	  {
	    fill(255,0,0);
	    rect(100-15,110,10,15);
	    fill(220);
	  }
	  else if(400-mycar.x<65)
	    rect(mycar.y-15,400-(mycar.x-45),10,15);
	  
	  
	  line(285,0,285,85);
	  draw_divider((float)297.5,0,(float)297.5,85); 
	  line(310,0,310,85);
	  
	  line(110,285,400,285);
	  draw_divider(110,(float)297.5,400,(float)297.5); 
	  line(110,310,400,310);
	  
	    
	  fill(0,255,0);
	  if(400-mycar.x>110)
	    rect(400-mycar.x,mycar.y+200,15,10);
	  else if((400-mycar.x) >= 75 &&  (400-mycar.x) <= 110)
	  {
	    fill(255,0,0);
	    rect(110,400-100,15,10);
	    fill(mycar.c);
	  }
//	  else if(400-mycar.x > 70 && 400-mycar.x < 95)
//	    rect(400-mycar.x+15,mycar.y+200,15,10);
	  else if(400-mycar.x<75)
	    rect(mycar.y,mycar.x-35+10,10,15);
	  
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
	}

	void draw_divider(float x1, float y1, float x2, float y2)
	{
	  float y=y1; 
	  float x= x1;  
	  float y_10;
	  float var_c;
	  float end_x = x2;
	  float end_y = y2;
	  if(Math.abs(x2-x1)!=0)
	  {
	    if(x > end_x)
	    {
	      x = x2;
	      end_x = x1;
	    }
	    for (; x <= end_x; x=x+20) {
	        var_c = (float) (y1 - ((y2-y1)*1.0/(x2-x1))*x1); 
	        y = (float) (((y2-y1)*1.0/(x2-x1))*x + var_c);
	        y_10 = (float) (((y2-y1)*1.0/(x2-x1))*(x+10) + var_c);
	        line(x, y, x+10, y_10);
	    }
	  }
	  else
	  {
	    if(y>y2)
	    {
	      y=y2;
	      end_y = y1;
	    }
	    while(y<=end_y)
	    {
	      if(y+10>end_y)
	        line(x, y, x, end_y);
	      else
	        line(x, y, x, y+10);
	      y = y+20;
	    }
	  }
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		PApplet.main(new String[] { "--present", "Traffic_car" });
//
//	}	

}
