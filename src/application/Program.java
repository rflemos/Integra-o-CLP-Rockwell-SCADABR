package application;

import java.util.concurrent.TimeoutException;

import javax.swing.event.TreeWillExpandListener;

import etherip.data.CipException;
import utils.Inicial;
import utils.Integration;

public class Program {
	
	
	public static void main(String[] args) throws Exception {
	 
		Integration integration = new Integration();
		
		
		while(true) {
		try {
		UI.Conection(integration);
		try {
		while(true) {
		UI.setScada(integration);
		}
		}
		catch (CipException e) {
			System.out.println(e.getMessage());
			
		}
		
		
		}
		catch (TimeoutException e) {
			System.out.println(e.getMessage());
			Thread.sleep(2000);
			
		}
		
	
	
		
		}
		
	 
	 
	}

}
