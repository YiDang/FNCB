package bcnf;

import java.io.IOException;

public class test {
	public static void main(String args[]) throws IOException, CloneNotSupportedException {
		Prob prob;
		if(args.length == 0) prob = new Prob("input2");
		else prob = new Prob(args[0]);
		
		prob.p1ans();
		prob.p2ans();
		prob.p3ans();
		
	}
}
