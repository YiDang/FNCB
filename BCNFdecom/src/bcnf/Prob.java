package bcnf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Prob {
	private static Relation relation;
	private static FdList fl;
	private static FdList fClosure;
	private static FdList bcViolations;
	private static HashSet<String> relationSet;
	private static RelList rl;
	
	private String path = "src\\bcnf\\input1";
	
	public Prob(String path) {
		this.path = path;
	}
	
	public void p1ans() throws IOException, CloneNotSupportedException {
		System.out.println("Part 1");
		readFile();
		
		FdList res = new FdList();
		
		relation.computePowerSet();
		relation.resetPowerSetIterator();
		while(relation.powerSethasNext()) {
			Relation lhs = relation.powerSetNext();
			
			Relation clo = fl.closure(lhs);
			
			Relation rhs = clo.diff(lhs);
			if(!rhs.isEmpty()) {
				res.insert(new Fd(lhs, rhs));
			}
		}
		
		System.out.println(res.toString());
		fClosure = res;
	}

	private  void readFile() throws IOException {
		File file = new File(this.path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		st = br.readLine();
		relation = new Relation(st.replaceAll(" ", ""));
		fl = new FdList();
		while ((st = br.readLine()) != null) {
			String[] strs = st.split("->");
			Fd fd = new Fd(new Relation(strs[0].replaceAll(" ", "")), new Relation(strs[1].replaceAll(" ", "")));
			fl.insert(fd);
		}
	}
	
	public void p2ans() throws IOException, CloneNotSupportedException {
		System.out.println("\nPart 2");
		FdList res2 = new FdList();

		
		fClosure.resetIterator();
		while(fClosure.hasNext()) {
			Fd fd = fClosure.getNext();
			if(fd.BCNFviolation(relation)) {
				res2.insert(fd);
			}
		}
		
		
		System.out.println(res2.toString());
		bcViolations = res2;
	}
	
	public void p3ans() throws CloneNotSupportedException {
		System.out.println("\nPart 3");
		rl = new RelList();
		relationSet = new HashSet<>();
		recursiveDecomposition(relation);
		
		Iterator<String> itrator = relationSet.iterator();
		while(itrator.hasNext()) {
			rl.insert(new Relation(itrator.next()));
		}
		
		System.out.println("\nDecomposed Relations:");
		System.out.println(rl.toString());
		
		List<FdList> correspondingFds = new ArrayList<>();
		rl.resetIterator();
		while(rl.hasNext()) {
			Relation curRel  = rl.getNext();
			FdList curFdList = new FdList();
			
			fClosure.resetIterator();
			while(fClosure.hasNext()) {
				Fd fd = fClosure.getNext();
				if(fd.getLHS().subset(curRel)) {
					Relation rhs = curRel.intersect(fd.getRHS());
					if(!rhs.isEmpty()) {
						curFdList.insert(new Fd(fd.getLHS(), rhs));
					}
				}
			}
			correspondingFds.add(curFdList);
		}
		
		System.out.println("\nCorresponding FDs:");
		for(FdList fdlist : correspondingFds) {
			System.out.println(fdlist.toString());
		}
	}
	
	private void recursiveDecomposition(Relation curRelation) throws CloneNotSupportedException {
		FdList curfClosure = new FdList(fClosure);
		curfClosure.resetIterator();
		boolean flag = true;
		while(curfClosure.hasNext()) {
			Fd fd = curfClosure.getNext();
			if(fd.BCNFviolation(curRelation)) {
				flag = false;
				Relation x = fd.getLHS();
				Relation y = fl.closure(x).diff(x);
				Relation z = curRelation.diff(x).diff(y);
//				System.out.println(curRelation + " become " + x.union(y) + " , " + x.union(z));
				recursiveDecomposition(x.union(y));
				recursiveDecomposition(x.union(z));
				break;
			}
		}
		
		if(flag) {
			relationSet.add(curRelation.toString());
		}
		
	}
}
