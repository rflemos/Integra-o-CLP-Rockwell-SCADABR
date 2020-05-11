package application;

import java.nio.BufferUnderflowException;

import etherip.data.CipException;
import utils.Inicial;

public class UI {
	
	
	
	final public static void Conection(Inicial ini) throws Exception {
	ini.Conect();
	}
	
	final public static void  readTags(Inicial ini) throws Exception   {

		
		for(int i =0; i<ini.getList().length; i++) {
			try {
			System.out.println(ini.getList()[i] +  " " + ini.getData(ini.getList()[i]).getBool());
			}
			catch(CipException e) {
				System.out.println(e.getMessage());
			}
			catch (BufferUnderflowException e) {
				System.out.println("Invalid variable type");
			
			}
			
			
		
		}
		
	}
	
	
	
	
	

}
