import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Starfield extends PApplet {

Particle[] particles;
int numParticles  = 1000;
int scrnSz = 512;
int locationX = scrnSz/2; 
int locationY = scrnSz/2;

//key functions
boolean centerWrap = false;
boolean twist = false;

public void setup()
{
	size(scrnSz, scrnSz);

	particles = new Particle[numParticles];
	for(int i = 1; i < particles.length; i++)
	{
		if (i%100 == 0)
		{
			particles[i] = new JumboParticle(locationX, locationY);
		} 
		else
		{
			particles[i] = new NormalParticle(locationX, locationY);	
		}
		
	}
	particles[0] = new OddballParticle(locationX, locationY);
}

public void draw()
{
	background(0, 10);

	for(int i = 0; i < particles.length; i++)
	{
		if (twist == true)
		{
			particles[i].move(true);
		}
		else
		{
			particles[i].move(false);
		}
		if (centerWrap == true)
		{
			particles[i].centerWrap();
		}
		particles[i].show();
	}
}

public void mousePressed()
{
	locationX = mouseX;
	locationY = mouseY;
	setup();
	redraw();
}

public void keyReleased()
{
	if (key == 'w')
	{	
		centerWrap = !centerWrap;
	}
	if (key == 'e')
	{
		twist = !twist;
	}
}


//-------define classes-------
interface Particle
{
	public void move(boolean twist);
	public void centerWrap();
	public void show();
};

class NormalParticle implements Particle
{
	double dx, dy, dTheta, dSpeed;
	int myColor, partSize;

	NormalParticle(int myX, int myY)
	{
		dx = myX;
		dy = myY;
		dTheta = Math.random()*2*Math.PI;
		dSpeed = Math.random()*3;

		//appearance 
		myColor = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		partSize = (int)(Math.random()*3+1);
	}

	NormalParticle() //default constr
	{
		dx = width/2;
		dy = height/2;
		dTheta = Math.random()*2*Math.PI;
		dSpeed = Math.random()*3;

		//appearance 
		myColor = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		partSize = (int)(Math.random()*3+1);
	}

	public void move(boolean twist)
	{
		dx = dx + Math.cos(dTheta)*dSpeed;
		dy = dy + Math.sin(dTheta)*dSpeed;
		if (twist == true)
		{
			dTheta += 0.05f;	
		}
	}

	public void centerWrap() //comes back to center 
	{
		if (dx >= width || dx <= 0 || dy >= height || dy <= 0)
		{
			dx = locationX;
			dy = locationY;
		}
	}

	public void show()
	{
		fill(myColor); 
		noStroke();
		ellipse((float)dx, (float)dy, partSize, partSize);
	}
};

class JumboParticle extends NormalParticle
{
	JumboParticle(int myX, int myY)
	{
		partSize = (int)(Math.random()*3+10); //particle is simply larger
		dx = myX;
		dy = myY;
	}
};

class OddballParticle implements Particle
{
	double dx, dy, dTheta, dSpeed;
	int myColor, partSize;

	OddballParticle(int myX, int myY)
	{
		dx = myX;
		dy = myY;
		dTheta = Math.random()*2*Math.PI; //direction the particles moves
		dSpeed = 8; //Math.random()*3;

		//appearance 
		myColor = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		partSize = 10;
	}

	double index = 0;
	public void move(boolean twist)
	{
		dx = dx + Math.cos(dTheta)*dSpeed;
		dy = dy + Math.sin(dTheta)*dSpeed;

		if (index <= 0) //wobble, by alternating angle
		{
			dTheta = -dTheta;
		}
		else if (index >= 1)
		{
			dTheta = dTheta;
		}
		dTheta += 0.75f;
	}

	public void centerWrap() //comes back to center 
	{
		if (dx >= width || dx <= 0 || dy >= height || dy <= 0)
		{
			dx = locationX;
			dy = locationY;
		}
	}

	public void show()
	{
		fill(myColor); 
		noStroke();
		ellipse((float)dx, (float)dy, partSize, partSize);
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Starfield" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
