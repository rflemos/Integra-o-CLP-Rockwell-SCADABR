package testes;

import java.util.concurrent.TimeoutException;

import etherip.EtherNetIP;
import etherip.data.CipException;
import etherip.types.CIPData;

public class EthernetTeste {

	public static void main(String[] args) throws Exception {

		/*
		 * while(x==0) {
		 * 
		 * CIPData value =plc.readTag(tag); z=value.getDint();
		 * System.out.println(value); }
		 */
		EtherNetIP plc2 = new EtherNetIP("192.168.1.2",0);
		
		try 
		
		{
			
			while (true) {
				try {
					int xb=0;
					Thread.sleep(2000);

					plc2.connectTcp();

					String tag = "UTIL_1T";
					System.out.println("\n*\n* bool '" + tag + "':\n*\n");
					
					float z;
					boolean a;
					try {
						while (true) {

							CIPData value = plc2.readTag(tag);
							z =  value.getNumber(0).intValue();
							
							

							System.out.println("Original Value: " + z);
						}
					} catch (CipException e) {
						System.out.println(e.getMessage());
					}

				} catch (final TimeoutException  e) {
					System.out.println("Not possible to conect");
				}

			}
		}
		finally {}
	}
}