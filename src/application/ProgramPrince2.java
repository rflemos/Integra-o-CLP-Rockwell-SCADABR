package application;

import java.io.IOException;
import etherip.data.CipException;
import scadabr.ClpToScada;
import utils.Integration;

public class ProgramPrince2 {

	public static void main(String[] args) {

		ClpToScada clpToScada = new ClpToScada("BKL4.properties");

		while (true) {
			try {
				UI2.Conection(clpToScada);
				try {
					while (true) {

						UI2.setScada(clpToScada);

					}
				} catch (CipException e) {
					System.out.println(e.getMessage() + "CipExcepetion");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}

		}

	}

}
