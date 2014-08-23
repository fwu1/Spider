
public class Position {
	double x=0,y=0,z=0;
	double dx=0,dy=0,dz=0;
	
	public String toString() {
		return String.format("[%f,%f,%f] (%f,%f,%f)", x,y,z,dx,dy,dz);
	}
}
