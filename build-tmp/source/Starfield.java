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

public void setup()
{
	size(scrnSz, scrnSz);
	particles = new Particle[numParticles];
	for(int i = 1; i < particles.length; i++)
	{
		particles[i] = new NormalParticle();
	}
	particles[0] = new OddballParticle();
}

public void draw()
{
	background(0, 10);

	for(int i = 0; i < particles.length; i++)
	{
		particles[i].move();
		//particles[i].centerWrap();
		particles[i].show();
	}
}

public void mousePressed()
{
	for(int i = 1; i < particles.length; i++)
	{
		particles[i] = new NormalParticle();
	}
	particles[0] = new OddballParticle();

	redraw();
}


//-------define classes-------
interface Particle
{
	public void move();
	public void centerWrap();
	public void show();
};

class NormalParticle implements Particle
{
	double dx, dy, dTheta, dSpeed;
	int myColor, partSize;

	NormalParticle()
	{
		dx = scrnSz/2;
		dy = scrnSz/2;
		dTheta = Math.random()*2*Math.PI;
		dSpeed = Math.random()*3;

		//appearance 
		myColor = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		partSize = (int)(Math.random()*3+1);
	}

	public void move()
	{
		dx = dx + Math.cos(dTheta)*dSpeed;
		dy = dy + Math.sin(dTheta)*dSpeed;
	}

	public void centerWrap() //comes back to center 
	{
		if (dx >= scrnSz || dx <= 0 || dy >= scrnSz || dy <= 0)
		{
			dx = scrnSz/2;
			dy = scrnSz/2;
		}
	}

	public void show()
	{
		fill(myColor); 
		noStroke();
		//ellipse((float)dx, (float)dy, partSize, partSize);
	}
};

class OddballParticle implements Particle
{
	double dx, dy, dTheta, dSpeed;
	int myColor, partSize;

	OddballParticle()
	{
		dx = scrnSz/2;
		dy = scrnSz/2;
		dTheta = Math.random()*2*Math.PI; //direction the particles moves
		dSpeed = 40; //Math.random()*3;

		//appearance 
		myColor = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		partSize = 10;
	}

	public void move()
	{
		dx = dx + Math.cos(dTheta)*dSpeed;
		dy = dy + Math.sin(dTheta)*dSpeed;

		dTheta++;
	}

	public void centerWrap() //comes back to center 
	{
		if (dx >= scrnSz || dx <= 0 || dy >= scrnSz || dy <= 0)
		{
			dx = scrnSz/2;
			dy = scrnSz/2;
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
