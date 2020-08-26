package utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.management.RuntimeErrorException;

import etherip.EtherNetIP;
import etherip.data.CipException;
import etherip.types.CIPData;

public class Inicial {
	
	private EtherNetIP plc;
	protected String[] listDint;
	private Integration integration;
	
	public Inicial() throws IOException {
		plc = new EtherNetIP("192.168.1.2",0);
		listDint = Escreve.leitor().split(";");
		
	}
	
	
	public EtherNetIP getPlc() {
		return plc;
	}

	public String[] getList() {
		return listDint;
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
