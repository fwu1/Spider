
public class Arm {
	public static int nofSticks=5;
	public Stick[] s= new Stick[nofSticks];
	
	Arm() {
		for(int i=0;i<nofSticks;i++) 
			s[i]=new Stick();
	}
	
	void setBeams(double [] lengths) throws Exception {
		if(lengths.length<nofSticks)
			throw new Exception();
		for(int i=0;i<nofSticks;i++)
			s[i].length=lengths[i];
	}

	void setTurns(double [] turns)  throws Exception{
		if(turns.length<nofSticks)
			throw new Exception();
		for(int i=0;i<nofSticks;i++)
			s[i].turn=turns[i];
	}

	void setTurnsInDeg(double [] turns)  throws Exception{
		if(turns.length<nofSticks)
			throw new Exception();
		for(int i=0;i<nofSticks;i++)
			s[i].turn=turns[i]*Math.PI/180;
	}
	
	Position findPositions() {
		double[] r= new double[nofSticks];
		s[1].x=s[0].x+s[0].length*Math.cos(s[0].turn);
		s[1].y=s[0].y+s[0].length*Math.sin(s[0].turn);

		double kx=Math.cos(s[0].turn+s[1].turn);
		double ky=Math.sin(s[0].turn+s[1].turn);
		double turn=s[2].turn;

		s[2].x=s[1].x-s[1].length*kx;
		s[2].y=s[1].y-s[1].length*ky;
		r[2]=s[2].length*Math.cos(turn);
		
		s[3].x=s[2].x-r[2]*kx;
		s[3].y=s[2].y-r[2]*ky;
		s[3].z=s[2].z-s[2].length*Math.sin(turn);
		turn+=s[3].turn;
		r[3]=s[3].length*Math.cos(turn);

		s[4].x=s[3].x-r[3]*kx;
		s[4].y=s[3].y-r[3]*ky;
		s[4].z=s[3].z-s[3].length*Math.sin(turn);
		turn+=s[4].turn;
		r[4]=s[4].length*Math.cos(turn);
		
		Position p=new Position();
		p.x=s[4].x-r[4]*kx;
		p.y=s[4].y-r[4]*ky;
		p.z=s[4].z-s[4].length*Math.sin(turn);
		
		p.dx=(s[4].x-p.x)/s[4].length;
		p.dy=(s[4].y-p.y)/s[4].length;
		p.dz=(s[4].z-p.z)/s[4].length;
		return p;
	}
	
	TurnSet[] findTurns(Position p) {
		TurnSet[] turnList= new TurnSet[2];
		turnList[0]=null;
		turnList[1]=null;
		
		double th345=Math.asin(p.dz);
		double s12=p.dy/Math.cos(th345);
		double c12=p.dx/Math.cos(th345);
		double t6=p.x*c12+p.y*s12;
		double b1=s[0].length;
		double b2=s[1].length;
		double b5=s[4].length;
		double sp=b1*b1+t6*t6-p.x*p.x-p.y*p.y;
		double r5=b5*Math.sqrt(p.dx*p.dx+p.dy*p.dy);
		
		if(sp>=0) {
			sp=Math.sqrt(sp);
			double[] rs= {0,0};
			rs[0]=-t6+sp;
			rs[1]=-t6-sp;
			for (int ri=0;ri<2;ri++) {
				TurnSet ts=turnList[ri]=new TurnSet();
				double r=rs[ri];
				double s1=(p.y+r*s12)/b1;
				double c1=(p.x+r*c12)/b1;
				double th1=Math.atan2(s1, c1);
				double th12=Math.atan2(s12, c12);
				ts.turns[0]=th1;
				ts.turns[1]=th12-th1;
				double r34=r-r5-b2;
				double h5=-(p.z+p.dz*b5);
				TwoAngles[] a34=TwoSticks.getAngles(s[2].length,s[3].length,h5,r34);
				for(TwoAngles ta:a34) {
					if(ta!=null) {
						System.out.printf("as=%f, %f\n",ta.a1,ta.a2);
					}
				}
				//double b34=Math.sqrt(r34*r34+p.z*p.z);
				//double beta3=Math.atan(-p.z/r34);
				//double th
				
			}
		}
		
		return turnList;
	}
	
	
}
