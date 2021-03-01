package application;

import etherip.data.CipException;
import scadabr.ClpToScada;

public class EquipmentConnect implements Runnable {
	
	private ClpToScada clpToScada;
	private String maquina;
	private String path;
	
	


	public EquipmentConnect(String maquina, String path) {
		this.clpToScada = new ClpToScada(path);
		this.maquina = maquina;
		this.path = path;
		new Thread(this,maquina).start();
	}




	@Override
	public void run() {
		
		while (true) {
			try {
				UI2.Conection(clpToScada);

				while (true) {
				
					UI2.setScada(clpToScada);
					
					
				

				}} catch (RuntimeException e) {
				System.err.println(e.getMessage());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
			
		}
		
		
		
		
	}
	
	

}
