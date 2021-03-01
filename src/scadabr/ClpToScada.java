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

	public ClpToScada() {

	}

	public ClpToScada(Maquina maquina, ScadabrApi scadaApi, String path) {
		super();
		this.maquina = maquina;
		this.scadaApi = scadaApi;
		this.path = path;
	}

	public ClpToScada(String path) {
		this.scadaApi = new ScadabrApi();
		this.maquina = new ImplMaquina(path);
		this.path = path;
	}

	public void tryConnectClp() {
		maquina.connection();
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public ScadabrApi getScadaApi() {
		return scadaApi;
	}

	public String getPath() {
		return path;
	}

	public void trasnferDataToScada() {
		this.tags = maquina.getTagsFromclp();
		this.scadaApi.writeScadaDataPoint(tags);
	}

	@Override
	public String toString() {
		return "ClpToScada [maquina=" + maquina + ", scadaApi=" + scadaApi + ", path=" + path + ", tags=" + tags + "]";
	}

}
