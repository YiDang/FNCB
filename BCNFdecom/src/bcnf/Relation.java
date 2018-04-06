package bcnf;

import java.util.ArrayList;
import java.util.List;

public class Relation {
	private static final int len = 127;
	
	private int[] attrs = new int [len];
	
	private List<Relation>  powerSet= new ArrayList<>();
	
	public Relation(String str) {
		if(str==null) return;
		for(char a : str.toCharArray()) {
			attrs[(int)a] = 1;
		}
		
		char[] attrs = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		computePowerSet(attrs, sb, 0, false, this.powerSet);
		computePowerSet(attrs, sb, 1, false, this.powerSet);
	}
	
	private void computePowerSet(char[] attrs, StringBuilder sb, int position, boolean picked, List<Relation> powerSet) {
		if(position == attrs.length) {
			if(picked) {
			powerSet.add(new Relation(sb.toString()));
			}
			return;
		}
		
		if(picked) sb.append(attrs[position]);
		computePowerSet(attrs, sb, position + 1, false, powerSet);
		computePowerSet(attrs, sb, position + 1, true, powerSet);
		if(picked) sb.deleteCharAt(sb.length() - 1);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < len; i++) {
			if(attrs[i] == 1) sb.append((char)i);
		}
		return sb.toString();
	}
	
	public boolean equals(Relation r2) {
		int[] attr1 = this.attrs;
		int[] attr2 = r2.getAttrs();
		for(int i = 0; i < len; i++) {
			if(attr1[i] != attr2[i]) return false;
		}
		return true;
	}
	
	public boolean contains(char c) {
		if(this.attrs[(int)c] == 1) return true;
		else return false;
	}
	
	public boolean subset(Relation r2) {// check if this relation is subset of r2
		int[] attrs2 = r2.getAttrs();
		for(int i = 0 ; i < len; i++) {
			if(this.attrs[i] == 1 && attrs2[i] == 0) return false;
		}
		return true;
	}
	
	public Relation powerSetFirst() {
//		todo
		return null;
	}
	
	public Relation powerSetNext() {
//		todo
		return null;
	}
	
	public Relation union(Relation r2) {
		int[] attrs2 = r2.getAttrs();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < len; i++) {
			if(this.attrs[i] == 1 || attrs2[i] == 1) {
				sb.append((char) i);
			} 
		}
		return new Relation(sb.toString());
	}
	
	public Relation intersect(Relation r2) {
		int[] attrs2 = r2.getAttrs();
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < len; i++) {
			if(this.attrs[i] == 1 && attrs2[i] == 1) {
				sb.append((char) i);
			} 
		}
		return new Relation(sb.toString());
	}

	public int[] getAttrs() {
		return attrs;
	}
	
	public void printPowerSet() {
		System.out.println("???");
	}
	
}
