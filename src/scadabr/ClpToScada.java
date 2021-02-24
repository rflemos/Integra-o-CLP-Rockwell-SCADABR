package scadabr;

import java.util.List;

import equipamentconnection.ImplMaquina;
import equipamentconnection.Maquina;
import equipamentconnection.Tag;

public class ClpToScada {
	
	private Maquina maquina;
	private ScadabrApi scadaApi;
	private String path;
	private List<Tag> tags;
	
	public ClpToScada(String path) {
		this.scadaApi = new ScadabrApi();
		this.maquina = new ImplMaquina(path);
		this.path = path;
	}
	
	public void tryConnectClp() throws Exception {
		maquina.connection();		
	}
	
	public void trasnferDataToScada()  {
		
		this.tags = maquina.getTagsFromclp();
		try {
		this.scadaApi.writeScadaDataPoint(tags);	
		}
		catch(RuntimeException e) {
			System.err.println(e.getLocalizedMessage());
		}
		
		
	}
	
	

}
