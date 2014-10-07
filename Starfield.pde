NormalParticle[] particles;
int numParticles  = 100;

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

class NormalParticle
{
	double dx, dy, dTheta, dSpeed;
	int myColor, partSize;

	NormalParticle()
	{
		dx = scrnSz/2;
		dy = scrnSz/2;
		dTheta = Math.random()*2*Math.PI;
		dSpeed = Math.random()*2+1;

		//appearance 
		myColor = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
		partSize = (int)(Math.random()*3+1);
	}

	void move()
	{
		dx = dx + Math.cos(dTheta)*dSpeed;
		dy = dy + Math.sin(dTheta)*dSpeed;
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
	//your code here
};

class OddballParticle
{
	//your code here
}