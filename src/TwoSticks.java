/*
 * 
 * 2 connected sticks, S1,S2, length b1,b2, tilted as a1,a2,
 * 
 *                /*
 *            b2/- |
 *           /-/   |
 *         /- a2   | p
 *   b1/--*        |
 *   /- a1         |
 *  *---------------
 *          q
 */
public class TwoSticks {
	
	/*
	 *  Know a1,a2, return the position of end point as value p,q
	 */
	static double[] getEndPos(double b1,double b2,double a1,double a2) {
		double[] pq= {0,0};
		pq[0]=b1*Math.sin(a1)+b2*Math.sin(a2);
		pq[1]=b1*Math.cos(a1)+b2*Math.cos(a2);
		return pq;
	}
	
	/*
	 * know 
	 * 
	 */
	static TwoAngles[] getAngles(double b1,double b2,double p,double q) {
		TwoAngles[] rsts=new TwoAngles[2];
		rsts[0]=null;
		rsts[1]=null;
		double a0=Math.atan2(p,q);
		double pqs=p*p+q*q;
		double dbs=b1*b1-b2*b2;
		double h=Math.sqrt(pqs);
		double gv1=(pqs+dbs)/(2*b1*h);
		double g1=0;
		if(gv1<1.0001) {
			if(gv1<1.0)
				g1=Math.acos(gv1);
			for(int i=0;i<2;i++) {
				TwoAngles ta=new TwoAngles();
				ta.a1=(i==0)?(a0+g1):(a0-g1);
				ta.a2=Math.atan2(p-b1*Math.sin(ta.a1),q-b1*Math.cos(ta.a1));
				rsts[i]=ta;
			}
		}
		else {
			System.out.printf("ERRoR gv1=%f; b1, b2, p, q=%f, %f, %f, %f\n",gv1,b1, b2, p, q );
		}
		return rsts;
	}


	public static void basicTest() {
		double b1=2,b2=2;
		double[] pq=getEndPos(b1,b2,1.2*Math.PI/2,1.2*Math.PI/2);
		System.out.printf("p,q=%f, %f\n",pq[0],pq[1]);
		TwoAngles[] as=getAngles(b1,b2,pq[0],pq[1]);
		for(TwoAngles ta:as) {
			System.out.printf("as=%f, %f\n",ta.a1,ta.a2);
			pq=getEndPos(b1,b2,ta.a1,ta.a2);
			System.out.printf("p,q=%f, %f\n",pq[0],pq[1]);
		}
	}
	
	public static boolean equal(double a1,double a2,double b1,double b2) {
		return (a1-b1)*(a1-b1)+(a2-b2)*(a2-b2)<1e30;
	}
	
	public static void loopTest() {
		double b1=1,b2=2;
		int n1=360;
		int n2=360;
		for(double a1=-Math.PI;a1<=Math.PI;a1+=Math.PI/n1) {
			for(double a2=-Math.PI;a2<=Math.PI;a2+=Math.PI/n2) {
				double[] pq=getEndPos(b1,b2,a1,a1);
				//System.out.printf("p,q=%f, %f\n",pq[0],pq[1]);
				
				TwoAngles[] as=getAngles(b1,b2,pq[0],pq[1]);
				for(TwoAngles ta:as) {
					//System.out.printf("as=%f, %f\n",ta.a1,ta.a2);
					if(ta==null) {
						System.out.printf("Failed for a1,a2=%f, %f\n",a1,a2);
						continue;
					}
					
					double[] pq1=getEndPos(b1,b2,ta.a1,ta.a2);
					if(!equal(pq[0],pq[1],pq1[0],pq1[1])) {
						System.out.printf("p,q=%f, %f\n",pq[0],pq[1]);
						System.out.printf("p1,q1=%f, %f\n",pq[0],pq[1]);
					}
				}
			}
		}
		System.out.printf("End\n");
	}
	
	public static void main_0(String[] args) {
		basicTest();
		//loopTest();
	}

}
