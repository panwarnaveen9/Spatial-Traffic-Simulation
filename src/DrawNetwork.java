import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import processing.core.*;


public class DrawNetwork {
	BasicSimulation parent; 
	
	public DrawNetwork(BasicSimulation p) {
		parent = p;
		display();
	}
	
	void display() {
		parent.pg.beginDraw();
		parent.pg.background(165,0);
		BufferedReader fileReader = null;
		// last two argument gap_size and line_type
		// gap size - distance between strokes 
		// line type 1 - road edge, 2 - lane, 3 - divider
		
		try
        {
            String[] edge_info = null;
            String line;
            fileReader = new BufferedReader(new FileReader("graph.csv"));
            while ((line = fileReader.readLine()) != null)
            {
            	edge_info = line.split(",");
            	if(edge_info[0]=="#New_road" || edge_info.length<6 || edge_info.length>6)
            		continue;
            	Float data[] = new Float[6];
            	for(int i=0; i<edge_info.length; i++)
            		data[i] = Float.parseFloat(edge_info[i]);
            	draw_road(data[0], data[1], data[2], data[3], data[4].intValue(), data[5].intValue());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		try {
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parent.pg.endDraw();
	}

	void draw_road(float x1, float y1, float x2, float y2, int gap_size, int line_type)
	{
		int color = parent.pg.color(0);
		parent.pg.strokeWeight(0);
		switch (line_type) {
		case 2:
			color = parent.pg.color(255);
			break;
		case 3:
			//parent.pg.strokeWeight(1);
			color = parent.pg.color(0, 0, 150);
			break;
		default:
			break;
		}
		parent.pg.stroke(color);
		if(gap_size==0)
			parent.pg.line(x1,y1,x2,y2);
		else
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
				for (; x <= end_x; x=x+gap_size) {
					var_c = (float) (y1 - ((y2-y1)*1.0/(x2-x1))*x1); 
					y = (float) (((y2-y1)*1.0/(x2-x1))*x + var_c);
					if((x+(gap_size/2))>end_x)
					{
						y_10 = (float) (((y2-y1)*1.0/(x2-x1))*(end_x) + var_c);
						parent.pg.line(x, y, end_x, y_10);
					}
					else
					{
						y_10 = (float) (((y2-y1)*1.0/(x2-x1))*(x+(gap_size/2)) + var_c);
						parent.pg.line(x, y, x+(gap_size/2), y_10);
					}
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
					if(y+(gap_size/2)>end_y)
						parent.pg.line(x, y, x, end_y);
					else
						parent.pg.line(x, y, x, y+(gap_size/2));
					y = y+gap_size;
				}
			}
		}
	}
}
