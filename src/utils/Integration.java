package utils;

import java.io.IOException;
import java.nio.BufferUnderflowException;
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
import etherip.data.CipException;

public class Integration {

	private ScadaBRAPI service;
	private Inicial inicial;
	
	public Integration() throws IOException {
		
		inicial = new Inicial();
		APILocator locator = new APILocator();
		
		try {
		 service = (APISoapBindingStub) locator.getAPI();
		} catch (ServiceException e) {
		 
		}
		
	}
	
	public void Conection() throws Exception {
		inicial.Conect();
	}


	
	public void writeScadaDataSource() throws Exception {
		
		
		WriteDataOptions writeDataOptions = new WriteDataOptions();
		writeDataOptions.setReturnItemValues(false);
		
		for(int i =0; i<inicial.listDint.length; i++) {
		try {
			
		ItemValue itemValue = new ItemValue();
		itemValue.setItemName(inicial.listDint[i]); // Path da tag a receber a opera��o de escrita
		itemValue.setTimestamp(Calendar.getInstance());
		itemValue.setQuality(QualityCode.GOOD);
		itemValue.setDataType(DataType.FLOAT);
	
		itemValue.setValue(inicial.getData(inicial.listDint[i]).getNumber(0).floatValue());
		ItemValue[] itemValueList = new ItemValue[1]; // Para alterar mais de uma tag, basta acrescentar mais objetos ItemValue na lista
		itemValueList[0] = itemValue;
		
		
		
		
		
		WriteDataParams writeDataParams = new WriteDataParams();
		WriteDataParams writeDataParamsFloat = new WriteDataParams();
		
		writeDataParams.setItemsList(itemValueList);
		
		
		
		
		WriteDataResponse writeDataResponse = new WriteDataResponse();

		try {
			
		
		 writeDataResponse = service.writeData(writeDataParams);
		 
		 
		 
		 String response = "";

			APIError[] errors = writeDataResponse.getErrors();
			if(errors[0].getCode() != ErrorCode.OK) {
			 response = "Error: " + errors[0].getDescription() + inicial.listDint[i];
			 System.out.println(response);
			} else {
			 response =  ":\n";
			}
		 
		} catch (RemoteException e) {
			
		} 
		
		
		
		
		}
		
		catch(CipException e) {
			System.out.println(e.getMessage());
		}
		catch (BufferUnderflowException e) {
			System.out.println("Invalid variable type");
		
		}
		
	}
	
		for(int i =0; i<inicial.listFloat.length; i++) {
			try {
				
			
			
			
			ItemValue itemValueFloat = new ItemValue();
			itemValueFloat.setItemName(inicial.listFloat[i]); // Path da tag a receber a opera��o de escrita
			itemValueFloat.setTimestamp(Calendar.getInstance());
			itemValueFloat.setQuality(QualityCode.GOOD);
			itemValueFloat.setDataType(DataType.FLOAT);
			itemValueFloat.setValue(inicial.getData(inicial.listFloat[i]).getNumber(0).floatValue());
			ItemValue[] itemValueListFloat = new ItemValue[1]; // Para alterar mais de uma tag, basta acrescentar mais objetos ItemValue na lista
			itemValueListFloat[0] = itemValueFloat;
			
			
			WriteDataParams writeDataParams = new WriteDataParams();
			WriteDataParams writeDataParamsFloat = new WriteDataParams();
			
			
			
			writeDataParamsFloat.setItemsList(itemValueListFloat);
			
			
			WriteDataResponse writeDataResponse = new WriteDataResponse();

			try {
				
			
			 
			 
			 writeDataResponse = service.writeData(writeDataParamsFloat);
			 
			 String response = "";

				APIError[] errors = writeDataResponse.getErrors();
				if(errors[0].getCode() != ErrorCode.OK) {
				 response = "Error: " + errors[0].getDescription() + inicial.listFloat[i];
				 System.out.println(response);
				} else {
				 response =  ":\n";
				}
			 
			} catch (RemoteException e) {
				
			} 
			
			
			
			
			}
			
			catch(CipException e) {
				System.out.println(e.getMessage());
			}
			catch (BufferUnderflowException e) {
				System.out.println("Invalid variable type");
			
			}
			
		}
		
	
	
	
	

}}
