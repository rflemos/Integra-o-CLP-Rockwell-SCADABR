package application;

import java.nio.BufferUnderflowException;

import etherip.data.CipException;
import scadabr.ClpToScada;
import utils.Inicial;
import utils.Integration;

public class UI2 {

	final public static void Conection(ClpToScada clpToScada) throws Exception {
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

	final public static void setScada(ClpToScada clpToScada) throws Exception {
		clpToScada.trasnferDataToScada();
		
	}

	final public static void readScada(Integration integration) throws Exception {

	}

}
