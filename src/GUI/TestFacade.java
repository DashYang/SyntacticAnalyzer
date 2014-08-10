
package GUI;
  
import org.eclipse.swt.SWT;   
import org.eclipse.swt.custom.CTabFolder;   
import org.eclipse.swt.custom.CTabFolder2Adapter;   
import org.eclipse.swt.custom.CTabFolderEvent;   
import org.eclipse.swt.custom.CTabItem;   
import org.eclipse.swt.graphics.Color;   
import org.eclipse.swt.graphics.Image;   
import org.eclipse.swt.layout.GridData;   
import org.eclipse.swt.layout.GridLayout;   
import org.eclipse.swt.widgets.Display;   
import org.eclipse.swt.widgets.Shell;   
import org.eclipse.swt.widgets.Text;   
  
public class TestFacade  
{   
    public static void main(String[] args)   
        {   
            final Display display = Display.getDefault();   
            final Shell shell = new Shell();   
            shell.setSize(296, 255);   
            shell.setText("CTabFolder 练习");   
            shell.setLayout(new GridLayout());   
            
            shell.open();   
  
            shell.setSize(800, 550);   
            shell.layout();   
            while (!shell.isDisposed()) {   
                if (!display.readAndDispatch())   
                    display.sleep();   
            }   
        }   
  
}