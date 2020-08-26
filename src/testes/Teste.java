package testes;

import java.rmi.RemoteException;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import br.org.scadabr.api.APILocator;
import br.org.scadabr.api.APISoapBindingStub;
import br.org.scadabr.api.ScadaBRAPI;
import br.org.scadabr.api.constants.DataType;
import br.org.scadabr.api.constants.ErrorCode;
import br.org.scadabr.api.constants.QualityCode;
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
		} catch (ServiceException e) {
		 
		}
		boolean i=true;
		while(true) {
		WriteDataOptions writeDataOptions = new WriteDataOptions();
		writeDataOptions.setReturnItemValues(false);

		ItemValue itemValue = new ItemValue();
		itemValue.setItemName("BKL4_OEE_API_1"); // Path da tag a receber a operação de escrita
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
