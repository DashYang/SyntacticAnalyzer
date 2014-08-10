package Main;

import GUI.Facade;
import GUI.TestFacade;

public class Test {
	public static void main(String[] args)
	{
		
		try {
			new Facade();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("closed");
	}
}
