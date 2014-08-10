package Storage;

import java.nio.channels.Pipe;
import java.util.ArrayList;

public class ProjectSet 
{
	ArrayList<ProjectItem> projectset;
	
	public ProjectSet()
	{
		projectset = new ArrayList<ProjectItem>();
	}
	
	public ProjectSet(ArrayList<ProjectItem> projectset)
	{
		this.projectset = projectset;
	}
	
	public void run(SimpleInfo simpleinfo)
	{
		for(int i = 0 ; i < projectset.size() ; i ++)
		{
			ProjectItem pItem = projectset.get(i);
			pItem.getClosure(simpleinfo);
			System.out.println("search:" + pItem.getNum() + " " + pItem.getProjectItem());
			ArrayList<ProjectItem> nextItems = pItem.move(simpleinfo, projectset.size(),projectset);
			
			for(ProjectItem item:nextItems)
			{
				item.getClosure(simpleinfo);
				System.out.println(item.getNum());
				System.out.println(item.getProjectItem());
			}
			
			
			for(ProjectItem nxtItem:nextItems)
			{
				projectset.add(nxtItem);
			}
			
		}
	}

	public ArrayList<ProjectItem> getProjectset() {
		return projectset;
	}

	public void setProjectset(ArrayList<ProjectItem> projectset) {
		this.projectset = projectset;
	}
	
	
}
