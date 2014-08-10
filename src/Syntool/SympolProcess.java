package Syntool;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;


public class SympolProcess 
{
	private ArrayList<String> rows;
	
	public SympolProcess()
	{
		rows = new ArrayList<String>();
	}
	
	public SympolProcess(String text)
	{
		rows = new ArrayList<String>();
		setRows(text);
	}
	
	public ArrayList<String> getNotEndSympol(String text)
	{
		ArrayList<String> NotEndSympols = new ArrayList<String>();
		for(int i = 0 ; i  < text.length() ; i ++)
		{
			char tmp = text.charAt(i);
			if( tmp>='A' && tmp <= 'Z' )
			{
				String stmp = String.valueOf(tmp);
				if(!NotEndSympols.contains(stmp))
					NotEndSympols.add(stmp);
			}
		}
		return NotEndSympols;
	}
	
	public ArrayList<String> getEndSympol(String text)
	{
		ArrayList<String> EndSympols = new ArrayList<String>();
		for(int i = 0 ; i  < text.length() ; i ++)
		{
			char tmp = text.charAt(i);
			if( !(tmp>='A' && tmp <= 'Z') && tmp !='|' && tmp != '→'
					&& tmp != 'ε' && tmp != ' ' && tmp != '\n' 
					&& tmp != '\t' && tmp != '\r')
			{
				String stmp = String.valueOf(tmp);
				if(!EndSympols.contains(stmp))
				{
					//System.out.println("T:"+stmp);
					EndSympols.add(stmp);
				}
			}
		}
		return EndSympols;
	}
	
	public void setRows(String text)
	{
		String nrow = "";
		for(int i = 0 ; i  < text.length() ; i ++)
		{
			char tmp = text.charAt(i);
			if(tmp == '\r')
			{
				i++;
				rows.add(nrow);
				nrow = "";
				continue;
			}
			nrow += String.valueOf(tmp);
		}
		rows.add(nrow);
	}
	
	public ArrayList<String> getRows()
	{
		return rows;
	}
	
	public boolean isAllowed()
	{
		boolean f = false;
		for(String row:rows)
		{
			if(row.length() < 3)
				return false;
			if(row.contains("#") || row.contains(".") || row.contains(","))
				return false;
			char f1 = row.charAt(0);
			String f2 = row.substring(1,2);
			if(f1 == 'S')
				f = true;
			if(!(f1 >= 'A' && f1 <= 'Z') || !f2 .equals("→"))
			{
				return false;
			}
		}
		if(f == true)
			return true;
		else
			return false;
	}
	
	public Dictionary<String, ArrayList<String>> getproRows()
	{
		Dictionary<String, ArrayList<String>> proRows = new Hashtable<String, ArrayList<String>>();
		for(String row:rows)
		{
			ArrayList<String> set = new ArrayList<String>(); 
			String left = row.substring(0,1);
			if(proRows.get(left) != null)		
			{
				set = proRows.get(left);
			}else
			{
				set = new ArrayList<String>();
			}
			String res = "";
			for(int i = 2 ; i < row.length() ; i++)
			{
				char tmp = row.charAt(i);
				if(tmp == '|')
				{
					set.add(res);
					res = "";
				}else
				{
					res += String.valueOf(tmp); 
				}
			}
			set.add(res);
			proRows.put(left, set);
		}
		return proRows;
	}
}
