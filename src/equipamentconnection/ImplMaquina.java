package equipamentconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import etherip.EtherNetIP;
import etherip.data.CipException;
import etherip.types.CIPData;

public class ImplMaquina implements Maquina {

	private EtherNetIP clp;
	private String[] nameTags;
	private String path;

	public ImplMaquina() {
	}

	public ImplMaquina(EtherNetIP clp, String path) {
		this.clp = clp;
		this.path = path;
	}

	public ImplMaquina(String path) {
		this.path = path;
		this.clp = setClp(loadProperties(path));
		this.nameTags = loadProperties(path).getProperty("NAME_TAGS").split(";");
	}

	@Override
	public List<Tag> getTagsFromclp() {
		List<Tag> tags = new ArrayList<>();
		try {
			for (String tag : nameTags) {
				
				tags.add(new Tag(tag, getClpData(tag).getType(), getClpData(tag).getNumber(0)));

			}

			return tags;
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	// metodo auxliar que busca as configurações no arquivo
	public static Properties loadProperties(String path) {
		try (FileInputStream fs = new FileInputStream(path)) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public void connection() {
		try {
			
			clp.connectTcp();
		}

		catch (TimeoutException e) {
			
			throw new RuntimeException("Not possible connect clp");
		} catch (Exception e) {
			
			throw new RuntimeException("Error in conncetion: " + e.getMessage());
		}

	}

	public void closeConnection() {
		try {
			clp.close();
		} catch (Exception e) {
			System.err.println("Not possible to disconnect clp: ");
		}

	}

	// metodo auxliar que seta as informações da maquina
	public EtherNetIP setClp(Properties props) {
		try (EtherNetIP plc = new EtherNetIP(props.getProperty("IP"), Integer.parseInt(props.getProperty("SLOT")));) {
			return plc;

		} catch (NumberFormatException e) {
			throw new RuntimeException("Please enter with a number in value slot" + e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error set clp: " + e.getMessage());
		}

	}

	//
	@Override
	public CIPData getClpData(String tag) throws Exception {

		try {
			CIPData cipData = clp.readTag(tag);
			return cipData;

		} catch (CipException e) {
			throw new RuntimeException(e.getMessage() + "CIPEX");
		} catch (TimeoutException e) {
			clp.close();
			throw new RuntimeException("Lost Conection ");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage() + "exeption2");
		}

	}

}
