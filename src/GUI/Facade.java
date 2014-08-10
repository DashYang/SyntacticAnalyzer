package GUI;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;
import java.util.SimpleTimeZone;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.internal.image.GIFFileFormat;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import Extra.FileManage;
import Storage.Link;
import Storage.ProjectItem;
import Storage.ProjectSet;
import Storage.Ritem;
import Storage.SimpleInfo;
import Syntool.FirstSetFactory;
import Syntool.SympolProcess;

public class Facade {
	private static  Shell    	shell;							
	private static  Display  	display;				
	private static 	CTabFolder 	tabFolder;
	
	private static SimpleInfo simpleinfo;
	
	private static 	List 		listnes;
	private static 	List 		listes;
	private static 	List 		listpro;
	private static 	List 		listfir;
	private static 	List 		listprojectset;
	private static 	Text synText;
	private static	List stepList;
	private static	List stateList;
	private static	List symList;
	private static 	List inList;
	private static	List actList;
	private static	List gotoList;
	private static ArrayList<List> projectList;
	
	private static CTabItem synItem;
	private static CTabItem GrammerItem;
	private static CTabItem	simpleItem;
	private static CTabItem projectinfoItem;
	private static CTabItem actionItem;
	
	private static ProjectSet 	pSet;
	private CTabFolder aitem;
	
	
	public Facade()
	{
		simpleinfo = new SimpleInfo();
		
		
		display = new Display();
		shell = new Shell(display,SWT.CLOSE);
		shell.setText("MySyn");
		shell.setSize(800, 550);
		shell.setLayout(new GridLayout());   
        shell.open();   
        
        tabFolder = new CTabFolder(shell, SWT.NONE|SWT.BORDER);   
        tabFolder.setTabHeight(20);   
        tabFolder.marginHeight = 5;   
        tabFolder.marginWidth = 5;   
        tabFolder.setSize(700,540);
        tabFolder.setLayout(new RowLayout());
        tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));   
        tabFolder.setSimple(false);//设置圆角   
        tabFolder.setUnselectedCloseVisible(true); 
        
        tabFolder.setMaximized(true);   
        tabFolder.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));   
        shell.layout(true);   
        
        setGrammerItem();
        setSimpleInfo();
        setProjectInfo();
        
        //给shell加上布局管理器    
        shell.layout(true);
        while (!shell.isDisposed()) {  
            if (!display.readAndDispatch())  
                display.sleep();  
        }  
        display.dispose();  
	}
	
	private void setGrammerItem()
	{
		GrammerItem = new CTabItem(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL);
		tabFolder.setSelection(GrammerItem);
		GrammerItem.setText("输入文法" );
	
        Composite composite = new Composite(tabFolder, SWT.NONE);
        composite.setLayout(new RowLayout());
        composite.setLayoutData(new RowData(700,500));
        
        //composite1
        Composite composite1 = new Composite(composite, SWT.BORDER);
        composite1.setLayout(new RowLayout());
        composite1.setLayoutData(new RowData(760,40));
      
        //composite1.setLayoutData(gd);
        Button btn1 = new Button(composite1,SWT.PUSH);
        btn1.setLayoutData(new RowData(40,30));
        btn1.setText("→");
        
        Button btn2 = new Button(composite1,SWT.PUSH);
        btn2.setLayoutData(new RowData(40,30));
        btn2.setText("ε");
        
        Button btn3 = new Button(composite1,SWT.PUSH);
        btn3.setLayoutData(new RowData(40,30));
        btn3.setText("保存");
        
        Button btn4 = new Button(composite1,SWT.PUSH);
        btn4.setLayoutData(new RowData(40,30));
        btn4.setText("分析");
        
        Button btn5 = new Button(composite1,SWT.PUSH);
        btn5.setLayoutData(new RowData(40,30));
        btn5.setText("读取");
        
        //composite2
        Composite composite2 = new Composite(composite, SWT.BORDER);
        composite2.setLayout(new RowLayout());
        composite2.setLayoutData(new RowData(760,420));
        
        final Text t = new Text(composite2, SWT.None|SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL|SWT.WRAP);   
        t.setText("输入文法"); 
        t.setLayoutData(new RowData(720,410));
        
        GrammerItem.setControl(composite);
        
        btn1.addListener(SWT.Selection, new Listener() {
			// apend a sympol
			@Override
			public void handleEvent(Event arg0) {
				int index = t.getCaretPosition();
				String content = t.getText();
				t.setText(content.substring(0,index) + "→" + content.substring(index));
				t.setSelection(index+1);
				t.setFocus();
			}
		});
        
        btn2.addListener(SWT.Selection, new Listener() {
			// apend a sympol
			@Override
			public void handleEvent(Event arg0) {
				int index = t.getCaretPosition();
				String content = t.getText();
				t.setText(content.substring(0,index) + "ε" + content.substring(index));
				t.setSelection(index+1);
				t.setFocus();
			}
		});
        
        btn3.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				DialogFactory df = new DialogFactory();
		        FileManage fm = new FileManage();
			    if(t.getText() != null || t.getText() != "")
			    {
			    	if(df.getNewandSave(shell))
			    	{
			    		goSave(t);
			    	}
			    }
			}
		});
        
        btn5.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				DialogFactory df = new DialogFactory();
		        FileManage fm = new FileManage();
			    String path = df.getOpendialog(shell);
			    if(path == null || path =="")
			    	return;
			    t.setText(fm.openFile(path));
			}
		});
        
        btn4.addListener(SWT.Selection, new Listener() {
			//click to analysis
			@Override
			public void handleEvent(Event arg0) {
				if(actionItem != null)
				{
					actionItem.dispose();
				}
				if(synItem != null)
				{
					synItem.dispose();
				}
					
				//projectinfoItem.dispose();
				
				SympolProcess syp = new SympolProcess(t.getText().trim());
				
				if(!syp.isAllowed())
				{
					DialogFactory df = new DialogFactory();
					df.getWrongSyn(shell);
					return;
				}
				ArrayList<String> es = syp.getEndSympol(t.getText());
				ArrayList<String> nes = syp.getNotEndSympol(t.getText());
				Dictionary<String, ArrayList<String>> pro = syp.getproRows();
				FirstSetFactory fsf = new FirstSetFactory(pro,nes);
				fsf.run();
				
				Dictionary<String, Set<String>> firsetSet = fsf.getFirstSet();
						
				simpleinfo.setEndsympols(es);
				System.out.println(nes);
				simpleinfo.setNotendsympols(nes);
				simpleinfo.setProduction(pro);
				simpleinfo.setFirstset(firsetSet);
				
				
				ArrayList<String> item = new ArrayList<String>();
				item.add("S'→.S,#");
				ProjectItem pItem = new ProjectItem(0,item);
				ArrayList<ProjectItem> pItems = new ArrayList<ProjectItem>();
				pItems.add(pItem);
				pSet = new ProjectSet(pItems);
				pSet.run(simpleinfo);
				updateEveryinfo();
			}
		});
	}
	
	public void goSave(Text t)
	{
		FileManage fm = new FileManage();
	    DialogFactory df = new DialogFactory();
	    String path = df.getSaveDiaglog(shell);
	    if(path == null || path == "")
	    	return;
	    String file = t.getText();
	    if(new File(path).exists())
	    {
	    	if(df.getOverwriteCmd(shell))
	    	{
	    		fm.saveFile(path, file);
	    	}
	    }else
	    	fm.saveFile(path, file);
	}
	
	public void updateEveryinfo()
	{
		listnes.removeAll();
		listes.removeAll();
		listpro.removeAll();
		listfir.removeAll();
		listprojectset.removeAll();
		
		for(String word:simpleinfo.getNotendsympols())
	     {
	    	 //System.out.println(word); debug
	    	 listnes.add(word);
	     }
		System.out.println(simpleinfo.getEndsympols());
		for(String word:simpleinfo.getEndsympols())
	     {
	    	 listes.add(word);
	     }
		Dictionary<String, ArrayList<String>> dic = simpleinfo.getProduction();
		int cnt = 0;
		listpro.add("S'→S");
		for(String word:simpleinfo.getNotendsympols())
	     {
			ArrayList<String> set = dic.get(word);
			if(set != null )
			{	
				for(String right:set)
				{
					listpro.add(word+"→"+right);
				}
			}
	     }
		
		Dictionary<String, Set<String>> firstset = simpleinfo.getFirstset();
		for(String word:simpleinfo.getNotendsympols())
	     {
			Set<String> set = firstset.get(word);
			if(set != null )
			{	
				String aset = word + ":{";
				for(String right:set)
				{
					aset  += right+"," ;
				}
				listfir.add(aset.substring(0,aset.length()-1) + "}");
				System.out.println(aset); //debug
			}
	     }
		
		ArrayList<ProjectItem> arrProjectItems = pSet.getProjectset(); 
		for(ProjectItem pItem:arrProjectItems )
		{
			ArrayList<String> arrItems = pItem.getProjectItem();
			listprojectset.add("I"+pItem.getNum()+"\n");
			for(String word: arrItems)
			{
				listprojectset.add(word);
			}
			listprojectset.add("可以去到的项目集\n");
			ArrayList<Link> nextLinks = pItem.getNexts();
			for(Link l: nextLinks)
			{
				listprojectset.add(l.getSympol() + " to I" + String.valueOf(l.getItem()));
			}
			listprojectset.add("\n");
		}
		
		setActionTable();
	}
	
	private void setSimpleInfo()
	{
		simpleItem = new CTabItem(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL);   
		simpleItem.setText("基本信息");
		
		 Composite composite = new Composite(tabFolder, SWT.NONE);
	     composite.setLayout(new RowLayout());
	     composite.setLayoutData(new RowData(700,500));
	     
	     RowData rd = new RowData(150,420);
	     RowData rd2 = new RowData(180,468);
	     
	     Composite cp1 = new Composite(composite, SWT.BORDER);
	     cp1.setLayout(new RowLayout());
	     cp1.setLayoutData(rd2);
	     Label Lnendsym = new Label(cp1, SWT.NONE);
	     Lnendsym.setText("非终结符");
	     listnes = new List(cp1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	     listnes.setLayoutData(rd);
	     
	     
	     Composite cp2 = new Composite(composite, SWT.BORDER);
	     cp2.setLayout(new RowLayout());
	     cp2.setLayoutData(rd2);
	     Label Lendsym = new Label(cp2, SWT.NONE);
	     Lendsym.setText("终结符");
	     listes = new List(cp2, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL|SWT.MULTI);
	     listes.setLayoutData(rd);
	     
	     Composite cp3 = new Composite(composite, SWT.BORDER);
	     cp3.setLayout(new RowLayout());
	     cp3.setLayoutData(rd2);
	     Label Lpro = new Label(cp3, SWT.NONE);
	     Lpro.setText("产生式");
	     listpro = new List(cp3, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL|SWT.MULTI);
	     listpro.setLayoutData(rd);
	     
	     Composite cp4 = new Composite(composite, SWT.BORDER);
	     cp4.setLayout(new RowLayout());
	     cp4.setLayoutData(rd2);
	     Label Lfir = new Label(cp4, SWT.NONE);
	     Lfir.setText("first集");
	     listfir = new List(cp4, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL|SWT.MULTI);
	     listfir.setLayoutData(rd);
	     
	     simpleItem.setControl(composite);
	}
	
	private void setProjectInfo()
	{
		projectinfoItem = new CTabItem(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL);   
		projectinfoItem.setText("项目集");
		
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    composite.setLayout(new RowLayout());
	    composite.setLayoutData(new RowData(760,480));
	    
	    listprojectset = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    listprojectset.setLayoutData(new RowData(740,450));
	    
	    projectinfoItem.setControl(composite);
	}
	
	private void setActionTable()
	{
		actionItem = new CTabItem(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL);   
		actionItem.setText("分析表");
		
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    composite.setLayout(new RowLayout());
	    composite.setLayoutData(new RowData(780,480));
	    
	    int Tnum = simpleinfo.getEndsympols().size();
	    int Nnum = simpleinfo.getNotendsympols().size();
	    int tid = pSet.getProjectset().size();
	    projectList = new ArrayList<List>();
	    int width = 700/(Tnum + Nnum + 2);
	    System.out.println(width);
	    RowData rd = new RowData(width-20,450);
	    
	    List idlist = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    idlist.setLayoutData(rd);
	    idlist.add("ID");
	    for(int i = 0 ; i < tid ; i ++ )
	    {
	    	idlist.add(String.valueOf(i));
	    }
	    projectList.add(idlist);
	    System.out.println(Tnum + " " + Nnum);
	    for(int i=0;i < Tnum ;i ++)
	    {
	    	List act = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    	act.setLayoutData(rd);
	    	String mark = simpleinfo.getEndsympols().get(i).toString();
	    	act.add(mark);
	    	projectList.add(act);
	    }
	    List sharp = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
    	sharp.setLayoutData(rd);
    	String smark = "#";
    	sharp.add(smark);
    	projectList.add(sharp);
	    for(int i=0;i < Nnum ;i ++)
	    {
	    	List act = new List(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    	act.setLayoutData(rd);
	    	String mark = simpleinfo.getNotendsympols().get(i).toString();
	    	act.add(mark);
	    	projectList.add(act);
	    }
	    displayProjectSet(projectList);
	    actionItem.setControl(composite);
	    setSynTable();
	}
	
	private void setSynTable()
	{
		synItem = new CTabItem(tabFolder, SWT.None|SWT.MULTI|SWT.V_SCROLL);   
		synItem.setText("输入语法");
		
	    Composite composite = new Composite(tabFolder, SWT.NONE);
	    composite.setLayout(new RowLayout());
	    composite.setLayoutData(new RowData(780,480));
	    
	    Composite ctrlpannel = new Composite(composite, SWT.BORDER);
	    ctrlpannel.setLayout(new RowLayout());
	    ctrlpannel.setLayoutData(new RowData(780,38));
	    
	    Button btn = new Button(ctrlpannel,SWT.PUSH);
        btn.setLayoutData(new RowData(40,30));
        btn.setText("分析");
	    
        Composite txtpannel = new Composite(composite, SWT.BORDER);
	    txtpannel.setLayout(new RowLayout());
	    txtpannel.setLayoutData(new RowData(780,25));
	    synText = new Text(txtpannel, SWT.SINGLE);
	    synText.setLayoutData(new RowData(750,20));
	    
	    Composite analysis = new Composite(composite, SWT.BORDER);
	    analysis.setLayout(new RowLayout());
	    analysis.setLayoutData(new RowData(780,480));
	    
	    RowData rd = new RowData(100,360);
	    stepList = new List(analysis, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    stepList.setLayoutData(rd);
	    stepList.add("步骤");
	    
	    stateList = new List(analysis, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    stateList.setLayoutData(rd);
	    stateList.add("状态栈");
	    
	    symList = new List(analysis, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    symList.setLayoutData(rd);
	    symList.add("符号栈");
	    
	    inList = new List(analysis, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    inList.setLayoutData(rd);
	    inList.add("输入串");
	    
	    actList = new List(analysis, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    actList.setLayoutData(rd);
	    actList.add("ACTION");
	    
	    gotoList = new List(analysis, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
	    gotoList.setLayoutData(rd);
	    gotoList.add("GOTO");
	    
	    btn.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				
				updateActionTable();
			}
				
		});
	    synItem.setControl(composite);
	}
	
	public void updateActionTable()
	{
		stepList.removeAll();
		stepList.add("步骤");
		
		stateList.removeAll();
		stateList.add("状态栈");
		
		symList.removeAll();
		symList.add("符号栈");
		
		inList.removeAll();
		inList.add("输入串");
		
		actList.removeAll();
		actList.add("ACTION");
		
		gotoList.removeAll();
		gotoList.add("GOTO");
		
		int cnt = 1;
		int states[] = new int[pSet.getProjectset().size() + 10];
		int statenum = 0;
		String step , sympol ,input, action,go2 = "";
		int state;
		step = String.valueOf(cnt);
		state = 0;
		
		states[statenum] = state;
		statenum ++;
		
		sympol = "#";
		input = synText.getText() + "#";
		System.out.print(state + " " + sympol + " " + input);				
		stepList.add(step);
		stateList.add(String.valueOf(state));
		symList.add(sympol);
		inList.add(input);
		action = getAction(state,input,projectList);
		String mm = action.substring(0, 1);
		actList.add(action);
		if(action.equals("acc") || action.equals("error!"))
		{
			return;
		}
		if(mm.equals("r"))
		{
			cnt ++;
			Ritem res = getR(states,sympol,input,action,projectList);
			states = res.getStates();
			sympol = res.getSympol();
			statenum = sympol.length();
			go2 = res.getGo2();
			gotoList.add(go2);
		}else
		{
			cnt ++;
			state = Integer.valueOf(action.substring(1));
			states[statenum]  = state;
			statenum ++;
			sympol += input.substring(0,1);
			input = input.substring(1);
			gotoList.add("");
		}
		System.out.println(" " +action + " " + go2);	
		boolean f = true;
		while(f)
		{
			step = String.valueOf(cnt);
			stepList.add(step);
			String Sstate = "";
			for(int i = 0 ;i < statenum ; i++)
			{
				Sstate += String.valueOf(states[i]) + " , ";
			}
			Sstate.substring(0, Sstate.length()-1);
			stateList.add(Sstate);
			symList.add(sympol);
			inList.add(input);
			System.out.print(state + " " + sympol + " " + input);		
			
			state = states[statenum-1];
			action = getAction(state, input, projectList);
			actList.add(action);
			if(action.equals("acc") || action.equals("error!"))
			{
				break;
			}
			String xx = action.substring(0, 1);
			if(xx.equals("r"))
			{
				cnt ++;
				Ritem res = getR(states,sympol,input,action,projectList);
				states = res.getStates();
				sympol = res.getSympol();
				statenum = sympol.length();
				go2 = res.getGo2();
				gotoList.add(go2);
			}else
			{
				cnt ++;
				state = Integer.valueOf(action.substring(1));
				states[statenum]  = state;
				statenum ++;
				sympol += input.substring(0,1);
				input = input.substring(1);
				gotoList.add("");
			}
			System.out.println(action + " " + go2);	
		}
	}
	
	public String getAction(int state,String input,ArrayList<List> projectList)
	{
		int row = state + 1;
		System.out.println();
		String sym = input.substring(0,1);
		System.out.println("row :" + row + " sym:" + sym);
		for(List list:projectList)
		{
			if(!list.getItem(0).equals("ID"))
			{
				String act = list.getItem(row);
				if(!act.equals("") && list.getItem(0).equals(sym))
				{
					return act;
				}	
			}
		}
		return "error!";
	}
	
	public Ritem getR(int[] statestack,String symstack,String instream,String action
			,ArrayList<List> projectList)
	{
		System.out.println(statestack);
		System.out.println("符号" + symstack);
		System.out.println("输入" + instream);
		Ritem reERitem = new Ritem();
		int newstate[] = statestack;
		String newsympol = "";
		String gotw = "";
		
		int r = Integer.valueOf(action.substring(1));
		String pro = listpro.getItem(r);
		String ns = symstack;
		
		
		String[] tmp = pro.split("→");
		System.out.println(tmp[1]);
		String jb = tmp[1].replace("ε", "");
		
		int index = ns.lastIndexOf(jb);
		newsympol = ns.substring(0,index) + tmp[0];
		
		int row = newstate[index-1] + 1;
		for(List list:projectList)
		{
			if(!list.getItem(0).equals("ID"))
			{
				String act = list.getItem(row);
				if(!act.equals("") && list.getItem(0).equals(tmp[0]))
				{
					gotw = act;
					break;
				}
			}
		}
		newstate[index] = Integer.valueOf(gotw.substring(1));
		reERitem.setStates(newstate);
		reERitem.setSympol(newsympol);
		reERitem.setGo2(gotw);
		return reERitem;	
	}
	
	public void displayProjectSet(ArrayList<List> projectList)
	{
		Dictionary<String, ArrayList<String>> proStrings = simpleinfo.getProduction();
		int rowNum = pSet.getProjectset().size();
		int colNum = projectList.size();
		ArrayList<ProjectItem> pItemspSet = pSet.getProjectset();
		System.out.println(rowNum + " " + colNum);
		for(int row = 1; row <= rowNum ; row++ )
		{
			int rowid = Integer.valueOf(projectList.get(0).getItem(row)); 
			ProjectItem pItem = null;
			for(ProjectItem pi:pItemspSet)
			{
				if(pi.getNum() == rowid)
				{
					pItem = pi;
					break;
				}
			}
			
			for(int col = 1; col < projectList.size(); col ++)
			{
				String mark = projectList.get(col).getItem(0);
				System.out.println("mark:" + mark);
				ArrayList<Link> links = pItem.getNexts();
				boolean f = false;
				for(Link l1:links)
				{
					if(l1.getSympol().equals(mark))
					{
						f = true;
						System.out.println("col: " + col + " row" + row);  //debug
						projectList.get(col).add("S"+l1.getItem());
						break;
					}
				}
				
				ArrayList<String> arrItems = pItem.getProjectItem();
				for(String it:arrItems)
				{
					int index = it.indexOf(",");
					String suffix = it.substring(index+1);
					System.out.println(suffix);
					String sympol = String.valueOf(it.charAt(index-1));
					System.out.println(sympol + "  " + suffix + " " + mark);
					if(sympol.equals(".") && suffix.contains(mark))
					{
						for(int id = 0; id < listpro.getItemCount() ;id ++)
						{
							String pro = listpro.getItem(id);
							String tstring = it.substring(0,index-1);
							System.out.println(tstring);
							pro = pro.replace("ε", "");
							if(pro.equals(tstring))
							{
								f = true;
								if(id == 0)
								{
									projectList.get(col).add("acc");
								}else
								{
									projectList.get(col).add("r"+id);
								}
								break;
							}
						}
					}
				}
					
				if( f == false )
					projectList.get(col).add("");
			}
		}
	}
}
