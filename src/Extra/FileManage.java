package Extra;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManage {
	public void saveFile(String path,String file)
	{
		FileWriter fw;
		try {
			fw = new FileWriter(path);
			fw.write(file,0,file.length());  
			fw.flush();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public String openFile(String path)
	{
		FileReader fr;
		String file = "";
		try {
			fr = new FileReader(path);
			int ch = 0;
			while((ch = fr.read())!=-1 )  
			{  
				file += (char)ch;  
			}  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return file;
	}
	
}
