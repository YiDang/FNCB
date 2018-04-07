package bcnf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RelList {
	List<Relation> lrs = new ArrayList<>(); 
	Iterator<Relation> iterator;
	
	public RelList(){
		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		for (Relation r : lrs) {
			sb.append(r.toString()).append(',');
		}

		if(sb.indexOf(",") >= 0) sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("}");
		return sb.toString();
	}
	
	public void insert(Relation r) {
		this.lrs.add(r);
	}
	
	public boolean  hasNext() {
		return this.iterator.hasNext();
	}
	
	public Relation getFirst() {
		return lrs.size() == 0 ? null : lrs.get(0);
	} 
	
	public Relation getNext() {
		 return iterator.next();
		
	}
	
	public void resetIterator() {
		this.iterator = lrs.iterator();
	}
	public List<Relation> getLrs() {
		return lrs;
	}
}
