package scadabr;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;

import org.apache.axis.message.SOAPHeaderElement;

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
import equipamentconnection.Tag;
import etherip.data.CipException;
import etherip.types.CIPData.Type;

public class ScadabrApi {

	private APILocator locator;
	private ScadaBRAPI service;
	private WriteDataOptions writeDataOptions;
	private WriteDataParams writeDataParams;
	private WriteDataResponse writeDataResponse;
	private ItemValue itemValue;
	private List<ItemValue> itemValueList;

	public ScadabrApi() {
		try {

			this.locator = new APILocator();
			this.service = (APISoapBindingStub) locator.getAPI();

			SOAPHeaderElement authentication = new SOAPHeaderElement("http://scadabr.org.br/api/", "authentication");
			SOAPHeaderElement user = new SOAPHeaderElement("http://scadabr.org.br/api/", "username", "RAFAEL");
			SOAPHeaderElement password = new SOAPHeaderElement("http://scadabr.org.br/api/", "password", "1234");
			authentication.addChild(user);
			authentication.addChild(password);
			((APISoapBindingStub) this.service).setHeader(authentication);
			
			
			
			

		} catch (ServiceException e) {
			e.printStackTrace();
		}

		catch (SOAPException e) {
			e.printStackTrace();
		}
	}

	public void writeScadaDataPoint(List<Tag> tags)  {
		writeDataOptions = new WriteDataOptions();
		writeDataOptions.setReturnItemValues(false);
		itemValueList = new ArrayList<ItemValue>();
		
		for (Tag tag : tags) {
			itemValue = new ItemValue();
			itemValue.setItemName(tag.getName());
			itemValue.setTimestamp(Calendar.getInstance());
			itemValue.setQuality(QualityCode.GOOD);

			if (tag.getType() == Type.DINT || tag.getType() == Type.INT || tag.getType() == Type.SINT) {
				itemValue.setDataType(DataType.INTEGER);
			}
			else if (tag.getType() == Type.REAL) {
				itemValue.setDataType(DataType.FLOAT);
			}
			
			else {
				throw new IllegalArgumentException("O tipo da variavel informada não é aceito");
			}

			itemValue.setValue(tag.getValue());
			
			
			itemValueList.add(itemValue);
			
		}
		
		ItemValue[] itens = new ItemValue[this.itemValueList.size()];
		itemValueList.toArray(itens);
		
		writeDataParams = new WriteDataParams();
		writeDataParams.setItemsList(itens);
		
		

		try {
			writeDataResponse = new WriteDataResponse();
			writeDataResponse = service.writeData(writeDataParams);
			APIError[] errors = writeDataResponse.getErrors();
			if (errors[0].getCode() != ErrorCode.OK) {
				throw new RuntimeException(errors[0].getDescription());
			} 
			} catch (RemoteException e) {
			System.out.println("Remote error e1 " + e.getMessage());
			
		
		}

	
	}

	

}
