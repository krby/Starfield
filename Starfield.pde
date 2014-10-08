Particle[] particles;
int numParticles  = 1000;

int scrnSz = 512;

void setup()
{
	size(scrnSz, scrnSz);
	particles = new NormalParticle[numParticles];
	for(int i = 0; i < particles.length; i++)
	{
		particles[i] = new NormalParticle();
	}
}

void draw()
{
	background(0, 10);
	


	for(int i = 0; i < particles.length; i++)
	{
		particles[i].move();
		particles[i].centerWrap();
		particles[i].show();
	}
}

void mousePressed()
{
	for(int i = 0; i < particles.length; i++)
	{
		particles[i] = new NormalParticle();
	}
	redraw();
}

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

	void move()
	{
		dx = dx + Math.cos(dTheta)*dSpeed;
		dy = dy + Math.sin(dTheta)*dSpeed;
	}

	void centerWrap() //comes back to center 
	{
		if (dx >= scrnSz || dx <= 0 || dy >= scrnSz || dy <= 0)
		{
			dx = scrnSz/2;
			dy = scrnSz/2;
		}
	}

	void show()
	{
		fill(myColor); 
		noStroke();
		ellipse((float)dx, (float)dy, partSize, partSize);
	}
};

interface Particle
{
	public void move();
	public void centerWrap();
	public void show();
};

class OddballParticle
{
	//your code here
}