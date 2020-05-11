package application;

import java.util.concurrent.TimeoutException;

import javax.swing.event.TreeWillExpandListener;

import etherip.data.CipException;
import utils.Inicial;

public class Program {
	
	
	public static void main(String[] args) throws Exception {
	 
		Inicial inicio = new Inicial();
		
		
		while(true) {
		try {
		UI.Conection(inicio);
		try {
		while(true) {
		UI.readTags(inicio);
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
