
public class Stick {
	double x=0,y=0,z=0;
	double length=1;
	double turn=0;
	
	public String toString() {
		return String.format("[%f, %f,%f] %f %f",x,y,z,length,turn);
	}
}
