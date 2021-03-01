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
				try {
				Tag tempTag= new Tag(tag, getClpData(tag).getType(), getClpData(tag).getNumber(0));
				tags.add(tempTag);	
				
				}catch(RuntimeException e) {
					System.err.println(e.getMessage());
				}
				
				

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
			System.err.println("Not possible to disconnect clp: " + e.getMessage());
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
	public CIPData getClpData(String tag) {

		try {
			CIPData cipData = clp.readTag(tag);
			return cipData;

		} catch (CipException e) {
			throw new RuntimeException("Tag reading error " + e.getMessage());
			
			
		} catch (TimeoutException e) {
			try {
				clp.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException("Loss of Connection");
		} catch (Exception e) {
			throw new RuntimeException("Unexpected tag reading error " +  e.getMessage() );
		}

	}

}
