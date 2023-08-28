public class Planet{
	public double xxPos;//Its current x position
	public double yyPos;//Its current y position
	public double xxVel;//Its current velocity in the x direction
	public double yyVel;//Its current velocity in the y direction
	public double mass;//Its mass
	public String imgFileName;/*The name of the file that corresponds to the image that depicts the planet*/
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
                yyPos=p.yyPos;
                xxVel=p.xxVel;
                yyVel=p.yyVel;
                mass=p.mass;
                imgFileName=p.imgFileName;
	}
	public double calcDistance(Planet p){
		double xdistance=this.xxPos-p.xxPos;
		double ydistance=this.yyPos-p.yyPos;
		double distance=Math.sqrt(xdistance*xdistance+ydistance*ydistance);
		return distance;
	}
	public double calcForceExertedBy(Planet p){
		double G=6.67e-11;
		double force=G*this.mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
		return force;
	}
	public double calcForceExertedByX(Planet p){
		double xforce;
		double force = this.calcForceExertedBy(p);
		double dx=p.xxPos-this.xxPos;
		xforce=dx*force/this.calcDistance(p);
		return xforce;
	}
	public double calcForceExertedByY(Planet p){
		double yforce;
		double force = this.calcForceExertedBy(p);
		double dy=p.yyPos-this.yyPos;
		yforce=dy*force/this.calcDistance(p);
		return yforce;
	}
	public double calcNetForceExertedByX(Planet[] planets){
		double sum=0;
		for(int i=0;i<planets.length;i=i+1){
			if (this.equals(planets[i]))
					{
						continue;
					}
			sum=sum+this.calcForceExertedByX(planets[i]);
		}
		return sum;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		double sum=0;
		for(int i=0;i<planets.length;i=i+1){
			if (this.equals(planets[i]))
					{
						continue;
					}
			sum=sum+this.calcForceExertedByY(planets[i]);
		}
		return sum;
	}
	public void update(double dt,double xforce,double yforce){
		double ax=xforce/this.mass;
		double ay=yforce/this.mass;
		this.yyVel=this.yyVel+ay*dt;
		this.xxVel=this.xxVel+ax*dt;
		this.xxPos=this.xxPos+dt*this.xxVel;
		this.yyPos=this.yyPos+dt*this.yyVel;
	}
	public void draw(){
		String image="images/"+this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,image);
		StdDraw.show();
	}
}
