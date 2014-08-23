
public class Test {

	public static void main(String[] args) {
		Arm a=new Arm();
		double [] beams= {5,0.2,1.5,1,1};
		double [] turnsInDeg= {10,45,10,-10,0};
		double [] turns= {0,0,0.1,0.2,0};
		try {
			a.setBeams(beams);
			//a.setTurnsInDeg(turnsInDeg);
			a.setTurns(turns);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Position p=a.findPositions();
		
		for(int i=1;i<a.nofSticks;i++) {
			System.out.printf("%d:[%f,%f,%f]\n", i,a.s[i].x,a.s[i].y,a.s[i].z);
		}
		System.out.printf("p=%s\n", p.toString());
		
		TurnSet[] tsList=a.findTurns(p);
		for(TurnSet ts:tsList) {
			if(ts!=null)
			System.out.printf("turns: %s\n", ts.toString());
		}
	}

}
