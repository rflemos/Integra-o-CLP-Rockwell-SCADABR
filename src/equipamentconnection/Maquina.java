package equipamentconnection;

import java.util.List;

import etherip.types.CIPData;

public interface Maquina {

	void connection();

	CIPData getClpData(String tag) throws Exception;

	List<Tag> getTagsFromclp();

}
