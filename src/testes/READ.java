package testes;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import br.org.scadabr.api.APILocator;
import br.org.scadabr.api.APISoapBindingStub;
import br.org.scadabr.api.ScadaBRAPI;
import br.org.scadabr.api.constants.ErrorCode;
import br.org.scadabr.api.da.ReadDataOptions;
import br.org.scadabr.api.da.ReadDataParams;
import br.org.scadabr.api.da.ReadDataResponse;
import br.org.scadabr.api.vo.APIError;
import br.org.scadabr.api.vo.ItemValue;

public class READ {
public static void main(String[] args) {
		
		
		
		APILocator locator = new APILocator();
		ScadaBRAPI service = null;
		try {
		 service = (APISoapBindingStub) locator.getAPI();
		} catch (ServiceException e) {
		 
		}
		
		String [] itemPathList = {"BKL4_OEE_API_WRITE"};
		
		ReadDataOptions readDataOptions = new ReadDataOptions();
		ReadDataParams readDataParams = new ReadDataParams();
		readDataParams.setOptions(readDataOptions);
		readDataParams.setItemPathList(itemPathList); // lista com todas as tags que se deseja ler
		ReadDataResponse readDataResponse = new ReadDataResponse();

		try {
		    readDataResponse = service.readData(readDataParams);
		} catch (RemoteException e) {
		    e.printStackTrace();
		}

		ItemValue[] itemsValue = readDataResponse.getItemsList();
		String response = "";

		APIError[] errors = readDataResponse.getErrors();
		if(errors[0].getCode() != ErrorCode.OK)
		    response = "Error: " + errors[0].getDescription();
		else
		    response ="" + itemsValue[0].getValue();
		int z = (int) Double.parseDouble(response);
		
		System.out.println(z);

}}
