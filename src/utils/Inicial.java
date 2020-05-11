package utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.management.RuntimeErrorException;

import etherip.EtherNetIP;
import etherip.data.CipException;
import etherip.types.CIPData;

public class Inicial {
	
	private EtherNetIP plc;
	private String[] list;
	
	public Inicial() throws IOException {
		plc = new EtherNetIP("100.0.0.135",0);
		list = Escreve.leitor().split(";");
	}
	
	
	public EtherNetIP getPlc() {
		return plc;
	}

	public String[] getList() {
		return list;
	}
	
	final public CIPData getData(String tag) throws Exception  {
		CIPData cipData = null;
		try {
		cipData = plc.readTag(tag);
		}catch(TimeoutException e) {
			throw new TimeoutException("Lost connection");
		}
		
		return cipData;
		
		}
	
	final public void Conect() throws Exception   {
		try {
		plc.connectTcp();
		} catch (TimeoutException e) {
			throw new TimeoutException("Not Possible Conect");
		}
			
	}
	}
