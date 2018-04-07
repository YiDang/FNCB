package bcnf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Relation {
	private static final int len = 127;

	private int[] attrs = new int[len];

	private List<Relation> powerSet = new ArrayList<>();
	Iterator<Relation> iterator;

	public Relation(String str) {
		if (str == null)
			return;
		for (char a : str.toCharArray()) {
			attrs[(int) a] = 1;
		}

	}

	public void computePowerSet() {
		char[] attrs = this.toString().toCharArray();
		StringBuilder sb = new StringBuilder();
		computePS(attrs, sb, 0, false, this.powerSet);
		computePS(attrs, sb, 0, true, this.powerSet);
	}

	private void computePS(char[] attrs, StringBuilder sb, int position, boolean picked, List<Relation> powerSet) {
		if (position >= attrs.length) {
			if (picked) {
				powerSet.add(new Relation(sb.toString()));
			}
			return;
		}

		if (picked)
			sb.append(attrs[position]);
		computePS(attrs, sb, position + 1, false, powerSet);
		computePS(attrs, sb, position + 1, true, powerSet);
		if (picked)
			sb.deleteCharAt(sb.length() - 1);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (this.attrs[i] == 1) {
				sb.append((char) i);
				count++;
			}
		}
		return sb.toString();
	}

	public boolean equals(Relation r2) {
		int[] attr1 = this.attrs;
		int[] attr2 = r2.getAttrs();
		for (int i = 0; i < len; i++) {
			if (attr1[i] != attr2[i])
				return false;
		}
		return true;
	}

	public boolean contains(char c) {
		if (this.attrs[(int) c] == 1)
			return true;
		else
			return false;
	}

	public boolean subset(Relation r2) {// check if this relation is subset of r2
		int[] attrs2 = r2.getAttrs();
		for (int i = 0; i < len; i++) {
			if (this.attrs[i] == 1 && attrs2[i] == 0)
				return false;
		}
		return true;
	}

	public Relation powerSetFirst() {
		return powerSet.size() == 0 ? null : powerSet.get(0);
	}

	public Relation powerSetNext() {
		return iterator.next();
	}

	public boolean powerSethasNext() {	
		return this.iterator.hasNext();
	}
	
	public void resetPowerSetIterator() {
		this.iterator = powerSet.iterator();
	}

	public Relation union(Relation r2) {
		int[] attrs2 = r2.getAttrs();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (this.attrs[i] == 1 || attrs2[i] == 1) {
				sb.append((char) i);
			}
		}
		return new Relation(sb.toString());
	}

	public Relation intersect(Relation r2) {
		int[] attrs2 = r2.getAttrs();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (this.attrs[i] == 1 && attrs2[i] == 1) {
				sb.append((char) i);
			}
		}
		return new Relation(sb.toString());
	}
	
	public Relation diff(Relation r2) {//this - r2
		int[] attrs2 = r2.getAttrs();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (this.attrs[i] == 1 && attrs2[i] == 0) {
				sb.append((char) i);
			}
		}
		return new Relation(sb.toString());
	}
	
	public boolean isEmpty() {
		for(int i = 0 ; i < len; i++) {
			if(attrs[i] == 1) return false;
		}
		return true;
	}
	public int[] getAttrs() {
		return attrs;
	}

	public void printPowerSet() {
		for (Relation r : this.powerSet) {
			System.out.println("{" + r.toString() + "}");
		}
	}

}
