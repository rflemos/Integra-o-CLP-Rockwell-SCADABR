package application;

import java.nio.BufferUnderflowException;

import etherip.data.CipException;
import utils.Inicial;
import utils.Integration;

public class UI {
	
	
	
	final public static void Conection(Integration integration) throws Exception {
	integration.Conection();
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
	final public static void setScada(Integration integration) throws Exception {
		integration.writeScadaDataSource();
	}
	
	
	
	
	

}
