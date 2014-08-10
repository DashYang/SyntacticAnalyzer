package Storage;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class SimpleInfo {
	ArrayList<String> endsympols;
	ArrayList<String> notendsympols;
	Dictionary<String, ArrayList<String>>  production;
	Dictionary<String, Set<String>>  firstset;
	
	public SimpleInfo()
	{
		endsympols = new ArrayList<String>();
		notendsympols = new ArrayList<String>();
		production = new Hashtable<String, ArrayList<String>>();
		firstset = new Hashtable<String, Set<String>>();
	}

	public ArrayList<String> getEndsympols() {
		return endsympols;
	}

	public void setEndsympols(ArrayList<String> endsympols) {
		this.endsympols = endsympols;
	}

	public ArrayList<String> getNotendsympols() {
		return notendsympols;
	}

	public void setNotendsympols(ArrayList<String> notendsympols) {
		this.notendsympols = notendsympols;
	}

	public Dictionary<String, ArrayList<String>> getProduction() {
		return production;
	}

	public void setProduction(Dictionary<String, ArrayList<String>> production) {
		this.production = production;
	}

	public Dictionary<String, Set<String>> getFirstset() {
		return firstset;
	}

	public void setFirstset(Dictionary<String, Set<String>> firstset) {
		this.firstset = firstset;
	}
	
	
}
