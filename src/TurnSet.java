
public class TurnSet {
	double[] turns=new double[5];
	
	public String toString() {
		String txt="";
		for (int i=0;i<turns.length;i++) {
			txt+=String.format("%s%f", (i==0)?"":" ,",turns[i]);
		}
		return txt;
	}

	public String toDegString() {
		String txt="";
		for (int i=0;i<turns.length;i++) {
			txt+=String.format("%s%f", (i==0)?"":" ,",Util.toDeg(turns[i]));
		}
		return txt;
	}
	
}
