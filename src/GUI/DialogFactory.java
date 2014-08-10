package GUI;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


public class DialogFactory {
	
	/**
	 * @param shell
	 * @return path
	 */
	public String getSaveDiaglog(Shell shell)
	{
		FileDialog dialog = new FileDialog(shell, SWT.SAVE);// 设置为保存对话框  
	    dialog.setFilterNames(new String[] { "CPP", "All Files (*.*)" });
	    dialog.setFilterExtensions(new String[] { "*.cpp", "*.*" });
	    dialog.setFilterPath("c:\\"); // Windows path
	    dialog.setFileName("syn.txt");
	    String path = dialog.open();
		return path;
	}
	
	/**
	 * @param shell
	 * @return path
	 */
	public String getOpendialog(Shell shell)
	{
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
        fd.setText("Open");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.txt", "*.cpp", "*.*" };
        fd.setFilterExtensions(filterExt);
	    String path = fd.open();
		return path;
	}
	
	/**
	 * overwrite file
	 * @param shell
	 * @return path
	 */
	public boolean getOverwriteCmd(Shell shell)
	{
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION |SWT.YES | SWT.NO);
		messageBox.setMessage("文件存在，是否覆盖？");
		if(messageBox.open() == SWT.YES)
			return true;
		return false;
	}
	
	/**
	 *  new and save
	 * @param shell
	 * @return boolean
	 */
	public boolean getNewandSave(Shell shell)
	{
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION |SWT.YES | SWT.NO);
		messageBox.setMessage("是否保存原文件?");
		if(messageBox.open() == SWT.YES)
			return true;
		return false;
	}
	
	/**
	 *  how
	 * @param shell
	 * @return boolean
	 */
	public void getHow(Shell shell)
	{
		MessageBox messageBox = new MessageBox(shell, SWT.YES);
		String res = "a cpp likely Text editor";
		messageBox.setText("how");
		messageBox.setMessage(res);
		messageBox.open();
	}
	
	/**
	 *  about
	 * @param shell
	 * @return boolean
	 */
	public void getAbout(Shell shell)
	{
		MessageBox messageBox = new MessageBox(shell, SWT.YES);
		String res = "Dash's work for compile theory\n LexicalAnalyzer";
		messageBox.setText("how");
		messageBox.setMessage(res);
		messageBox.open();
	}
	
	/**
	 *  wrong syn
	 * @param shell
	 * @return boolean
	 */
	public void getWrongSyn(Shell shell)
	{
		MessageBox messageBox = new MessageBox(shell, SWT.YES);
		String res = "错误的文法,请确认为以S为起始符,且不含“.”，“,”，“#”）的2型文法";
		messageBox.setText("how");
		messageBox.setMessage(res);
		messageBox.open();
	}
}
