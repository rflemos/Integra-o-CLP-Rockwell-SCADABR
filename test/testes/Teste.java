package testes;

import java.rmi.RemoteException;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;

import org.apache.axis.message.SOAPHeaderElement;

import br.org.scadabr.api.APILocator;
import br.org.scadabr.api.APISoapBindingStub;
import br.org.scadabr.api.ScadaBRAPI;
import br.org.scadabr.api.constants.DataType;
import br.org.scadabr.api.constants.ErrorCode;
import br.org.scadabr.api.constants.QualityCode;
import br.org.scadabr.api.da.ReadDataOptions;
import br.org.scadabr.api.da.ReadDataParams;
import br.org.scadabr.api.da.ReadDataResponse;
import br.org.scadabr.api.da.WriteDataOptions;
import br.org.scadabr.api.da.WriteDataParams;
import br.org.scadabr.api.da.WriteDataResponse;
import br.org.scadabr.api.vo.APIError;
import br.org.scadabr.api.vo.ItemValue;

public class Teste {

	public static void main(String[] args) {
		
		
		
		APILocator locator = new APILocator();
		ScadaBRAPI service = null;
		

		
			try {
			service = (APISoapBindingStub) locator.getAPI();
			System.out.print((APISoapBindingStub) locator.getAPI() instanceof java.rmi.Remote );
			
			SOAPHeaderElement authentication = new SOAPHeaderElement("http://scadabr.org.br/api/","authentication");
			SOAPHeaderElement user = new SOAPHeaderElement("http://scadabr.org.br/api/","username", "admin");
			SOAPHeaderElement password = new SOAPHeaderElement("http://scadabr.org.br/api/","password", "admin");
			 
			 authentication.addChild(user);
			 authentication.addChild(password);
			 ((APISoapBindingStub)service).setHeader(authentication);

			} catch (ServiceException e) {}
			 
			 catch (SOAPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		boolean i=true;
		while(true) {
		WriteDataOptions writeDataOptions = new WriteDataOptions();
		writeDataOptions.setReturnItemValues(false);

		ItemValue itemValue = new ItemValue();
		itemValue.setItemName("bkk"); // Path da tag a receber a opera��o de escrita
		itemValue.setTimestamp(Calendar.getInstance());
		itemValue.setQuality(QualityCode.GOOD);
		itemValue.setDataType(DataType.BOOLEAN);
		itemValue.setValue(i);
		ItemValue[] itemValueList = new ItemValue[1]; // Para alterar mais de uma tag, basta acrescentar mais objetos ItemValue na lista
		itemValueList[0] = itemValue;

		WriteDataParams writeDataParams = new WriteDataParams();
		writeDataParams.setItemsList(itemValueList);

		WriteDataResponse writeDataResponse = new WriteDataResponse();

		try {
			
		Thread.sleep(2000);	
		 writeDataResponse = service.writeData(writeDataParams);
		 
		 String response = "";

			APIError[] errors = writeDataResponse.getErrors();
			if(errors[0].getCode() != ErrorCode.OK) {
			 response = "Error: " + errors[0].getDescription();
			} else {
			 response =  ":\n";
			}
		 
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
		
		
		
		if(i== true)
			i=false;
		else
			i=true;
		System.out.println("check");
		
		}
		
		
		
	
		
		
}
}
