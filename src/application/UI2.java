package application;

import java.nio.BufferUnderflowException;

import javax.print.attribute.standard.Sides;

import com.sun.org.apache.xml.internal.security.keys.storage.implementations.SingleCertificateResolver;

import etherip.data.CipException;
import scadabr.ClpToScada;
import utils.Inicial;
import utils.Integration;

public class UI2 {

	final synchronized public static void Conection(ClpToScada clpToScada)  {
		clpToScada.tryConnectClp();

	}

	final public static void readTags(Inicial ini) throws Exception {

		for (int i = 0; i < ini.getListDint().length; i++) {
			try {
				System.out.println(
						ini.getListDint()[i] + " " + ini.getData(ini.getListDint()[i]).getNumber(0).intValue());
			} catch (CipException e) {
				System.out.println(e.getMessage());
			} catch (BufferUnderflowException e) {
				System.out.println("Invalid variable type");

			}

		}

	}

	final synchronized public static void setScada(ClpToScada clpToScada){
		clpToScada.trasnferDataToScada();
		
	}

	final synchronized public static void readScada(Integration integration) throws Exception {

	}

}
