package Storage;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.sound.sampled.Line;

import org.eclipse.swt.widgets.Item;

public class ProjectItem {
	private int num;
	private ArrayList<String> synitem;
	private ArrayList<Link> nexts;

	public ProjectItem(int num, ArrayList<String> synitem) {
		this.num = num;
		this.synitem = synitem;
		this.nexts = new ArrayList<Link>();
	}

	public ProjectItem() {
		num = 0;
		synitem = new ArrayList<String>();
		nexts = new ArrayList<Link>();
	}

	public void getClosure(SimpleInfo simpleinfo) {
		ArrayList<String> endsympols = simpleinfo.getEndsympols();
		ArrayList<String> notendsympols = simpleinfo.getNotendsympols();
		Dictionary<String, ArrayList<String>> production = simpleinfo
				.getProduction();
		Dictionary<String, Set<String>> firstset = simpleinfo.getFirstset();

		String[] closure = new String[100]; // modify
		int q = 0, h = 0;

		for (String item : synitem) {
			closure[h++] = item;
		}

		while (q < h) {

			int index = closure[q].indexOf(".");
			char at = closure[q].charAt(index + 1);
			char Ifir = closure[q].charAt(index + 2);
			int latindex = closure[q].indexOf(",");
			String suffix = closure[q].substring(latindex + 1);
			suffix = suffix.replace("ε","#");
			q++;
			if (at >= 'A' && at <= 'Z') {
				String sat = String.valueOf(at);
				ArrayList<String> ss = production.get(sat);
				if (Ifir != ',') {
					if (Ifir >= 'A' && Ifir <= 'Z') {
						Set<String> Ifirset = firstset
								.get(String.valueOf(Ifir));
						suffix = "";
						for (String ele : Ifirset) {
							if (ele != "") {
								if(ele.equals("ε"))
								{
									ele = "#";
								}
								suffix += ele + "/";
							}
						}
						suffix = suffix.substring(0, suffix.length() - 1);
					} else {
						suffix = String.valueOf(Ifir);
					}
				}
				for (String s : ss) {
					s = s.replace("ε", "");
					String e = sat + "→." + s + "," + suffix;
					int j;
					for (j = 0; j < h; j++) {
						if (e.equals(closure[j])) {
							break;
						}
					}
					if (j >= h)
						closure[h++] = e;
				}
			}
		}
		synitem.clear();
		int i;
		for (i = 0; i < h; i++) {
			synitem.add(closure[i]);
		}
	}

	public ArrayList<ProjectItem> move(SimpleInfo simpleinfo, int cnt,
			ArrayList<ProjectItem> pSet) {
		ArrayList<ProjectItem> nextItems = new ArrayList<ProjectItem>();
		ArrayList<String> traneles = new ArrayList<String>();
		traneles.addAll(simpleinfo.getEndsympols());
		traneles.addAll(simpleinfo.getNotendsympols());
		for (String tse : traneles) 
		{
			ArrayList<String> nextsynitem = new ArrayList<String>();
			for (String i : synitem) 
			{
				int index = i.indexOf(".");
				char next = i.charAt(index + 1);
				if (next == tse.charAt(0)) 
				{
					String nextstring = i.substring(0, index)
							+ String.valueOf(next) + "."
							+ i.substring(index + 2);
					nextsynitem.add(nextstring);
				}
				
			}
			if(nextsynitem.size() > 0)
			{
				ProjectItem nextItem = new ProjectItem(cnt, nextsynitem);
				nextItem.getClosure(simpleinfo);
				int hasCnt = hasItem(nextItem, pSet);
				Link link;
				if (hasCnt >=0) {
					link = new Link(tse, hasCnt);
				} else {
					link = new Link(tse, cnt++);
					nextItems.add(nextItem);
				}
				nexts.add(link);
			}
		}
		return nextItems;
	}

	public int hasItem(ProjectItem item, ArrayList<ProjectItem> pSet) {
		for (ProjectItem it : pSet) {
			if (it.isEqual(item))
				return it.getNum();
		}
		return -1;
	}

	public ArrayList<String> getProjectItem() {
		return synitem;
	}

	public ArrayList<Link> getNexts() {
		return nexts;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isEqual(ProjectItem pItem) {
		if (pItem.getProjectItem().equals(synitem)) {
			return true;
		}
		return false;
	}
}
