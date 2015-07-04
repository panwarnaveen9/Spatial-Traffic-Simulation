import processing.core.PApplet;

import processing.core.*;

public class Car {
	
		  int  c;
		  float x;
		  float y;
		  float speed;
		  PApplet parent; 
		  
		  Car(PApplet p,int x)
		  {
			parent = p;
		    c  = parent.color(x);
		    x = 0;
		    y = 115;
		    speed = 1;
		  }  
}
