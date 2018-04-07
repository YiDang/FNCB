package bcnf;

public class Fd {
	private Relation in_lhs,  in_rhs;
	public Fd(Relation in_lhs,Relation in_rhs) {
		this.in_lhs = in_lhs;
		this.in_rhs = in_rhs;
	}
	
	public String toString() {
		return this.in_lhs.toString() + "->" + this.in_rhs.toString();
	}
	
	public boolean BCNFviolation(Relation s) {
		boolean res = !s.subset(this.in_lhs.union(this.in_rhs)) &&
				in_lhs.subset(s) &&
				!s.intersect(this.in_rhs).isEmpty();
//		if(res) System.out.println(this.toString() + " is violation over " + s.toString());
		return res;
	}

	public Relation getLHS() {
		return in_lhs;
	}

	public Relation getRHS() {
		return in_rhs;
	}

	
}
