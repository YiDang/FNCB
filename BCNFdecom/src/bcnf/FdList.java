package bcnf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FdList {
	List<Fd> fds = new ArrayList<>();
	Iterator<Fd> iterator;

	public FdList() {
	}
	
	public FdList(FdList fl) {
		this.fds = new ArrayList<>(fl.getFds());
	}
	
	public List<Fd> getFds() {
		return fds;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		for (Fd fd : fds) {
			sb.append(fd.toString()).append(',');
		}

		if (sb.indexOf(",") >= 0)
			sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("}");
		return sb.toString();
	}

	public void insert(Fd f) {
		this.fds.add(f);
	}

	public Fd getFirst() {
		return fds.size() == 0 ? null : fds.get(0);
	}

	public Fd getNext() {
		return iterator.next();
	}

	public boolean hasNext() {
		return this.iterator.hasNext();
	}
	
	public void resetIterator() {
		this.iterator = fds.iterator();
	}

	public Relation closure(Relation r) throws CloneNotSupportedException {
		boolean flag = true;
		Relation before = r;
		while (flag) {
			flag = false;
			Relation after = before;
			for (Fd fd : fds) {
				if (fd.getLHS().subset(after)) {
					after = after.union(fd.getRHS());
				}
			}
			flag = !after.equals(before);
			before = after;
		}
		return before;
	}

}
