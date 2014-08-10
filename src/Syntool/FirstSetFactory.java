package Syntool;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;


public class FirstSetFactory {
	Dictionary<String, ArrayList<String>> proStrings;
	ArrayList<String> lefts;
	Dictionary<String, Set<String>> firstSet = new Hashtable<String, Set<String>>();
	boolean[] leftflag;
	public FirstSetFactory()
	{
		proStrings = new Hashtable<String, ArrayList<String>>();
		lefts = new ArrayList<String>();
		leftflag = new boolean[30];
	}
	
	public FirstSetFactory(Dictionary<String, ArrayList<String>> proStrings,ArrayList<String> left)
	{
		this.proStrings = proStrings;
		this.lefts = left;
		this.leftflag = new boolean[30];
	}
	
	public void run()
	{
		for(int i = 0 ; i < leftflag.length ; i ++ )
		{
			leftflag[i] = false;
		}
		
		for(String left: lefts)
		{
			if(leftflag[left.charAt(0)-'A'] == false)
			{
				leftflag[left.charAt(0)-'A'] = true;
				dfs(left);
			}
		}
	}
	
	public void dfs(String left)
	{
		ArrayList<String> rights = proStrings.get(left);
		Set<String> resfirset = new HashSet<String>();
		firstSet.put(left,resfirset);
		for(String right:rights)
		{	
			int j;
			for( j = 0 ; j < right.length() ; j++)
			{
				Set<String> tmpfirset = new HashSet<String>();
				char at = right.charAt(j);
				String sat = String.valueOf(at);
				if(!(at>='A' && at <= 'Z'))
				{
					tmpfirset.add(sat);
				}else
				{
					if(leftflag[at-'A'] == false)
					{
						leftflag[at-'A'] = true;
						dfs(sat);
					}
					Set<String> nset = firstSet.get(sat);
					tmpfirset.addAll(nset);
				}
				resfirset.addAll(tmpfirset);
				System.out.println(resfirset);
				if(!tmpfirset.contains("ε"))
				{
					break;
				}
			}
			if(j < right.length())
			{
				resfirset.remove("ε");
				System.out.println(resfirset);
			}
		}
		firstSet.put(left,resfirset);
	}
	
	public Dictionary<String, Set<String>> getFirstSet()
	{
		return firstSet;
	}
}
