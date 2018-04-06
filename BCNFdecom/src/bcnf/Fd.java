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
//		todo
		return false;
	}

	public Relation getLHS() {
		return in_lhs;
	}

	public Relation getRHS() {
		return in_rhs;
	}

	
}
