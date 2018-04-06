package bcnf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FdList {
	List<Fd> fds = new ArrayList<>();
	Iterator<Fd> iterator;
	public FdList() {
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{") ;
		for (Fd fd : fds) {
			sb.append(fd.toString()).append(',');
		}

		if(sb.indexOf(",") >= 0) sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("}"); 
		return sb.toString();
	}
	
	public void insert(Fd f) {
		this.fds.add(f);
	}
	
	public Fd getFirst() {
		this.iterator = this.fds.iterator();
		if(this.iterator.hasNext()) return this.iterator.next();
		else return null;
	} 
	
	public Fd getNext() {
		if(null == this.iterator) this.iterator = fds.iterator();
		if(this.iterator.hasNext()) return iterator.next();
		else return null;
	}
	
	public boolean  hasNext() {
		return this.iterator.hasNext();
	}
	
	public Relation closure(Relation r) throws CloneNotSupportedException{
		boolean flag = true;
		Relation before =  r;
		while(flag) {
			flag = false;
			Relation after = before;
			for(Fd fd : fds) {
				if(fd.getLHS().subset(after)) {
					after = after.union(fd.getRHS());
				}
			}
			flag = !after.equals(before);
			before = after;
		}
		return before;
	}
	

}
