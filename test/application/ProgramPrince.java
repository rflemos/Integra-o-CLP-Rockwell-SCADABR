package application;

import java.io.IOException;
import etherip.data.CipException;
import utils.Integration;

public class ProgramPrince {
	
	
	public static void main(String[] args) {
		try {
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
		catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	
	
		
		}
		
		}
		catch (IOException e) {
			System.out.println("Init program problem: " + e.getMessage());}
	 
	}

}
