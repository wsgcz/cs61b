public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		in.readInt();
		double radius=in.readDouble();
		return radius;
	}
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int length=in.readInt();
		in.readDouble();
		Planet[] planets=new Planet[length];
		for (int i=0;i<length;i++){
			double xp=in.readDouble();
			double yp=in.readDouble();
			double xv=in.readDouble();
			double yv=in.readDouble();
			double mass=in.readDouble();
			String name = in.readString();
			Planet tmp=new Planet(xp,yp,xv,yv,mass,name);
			planets[i]=tmp;
		}
		return planets;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double radius=readRadius(filename);
		Planet[] planets=readPlanets(filename);
		double time =0;
		StdDraw.setScale(-1*radius,radius);
		StdDraw.clear();
		double size=2*radius;
		String universe="images/starfield.jpg";
		StdDraw.picture(0,0,universe,size,size);
		StdDraw.show();
		for(int i=0;i<planets.length;i++){
			planets[i].draw();
		}
		for (;time<=T;time=time+dt){
			StdDraw.enableDoubleBuffering();
			double[] xForces=new double[planets.length];
			double[] yForces=new double[planets.length];
			for (int j=0;j<planets.length;j++){
				xForces[j]=planets[j].calcNetForceExertedByX(planets);
				yForces[j]=planets[j].calcNetForceExertedByY(planets);
			}
			for (int j=0;j<planets.length;j++){
				planets[j].update(dt,xForces[j],yForces[j]);
			}
			StdDraw.clear();
                	StdDraw.picture(0,0,universe,size,size);
                	StdDraw.show();
                	for(int i=0;i<planets.length;i++){
                        planets[i].draw();
                	}
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}

