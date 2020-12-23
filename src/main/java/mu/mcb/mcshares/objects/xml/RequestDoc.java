package mu.mcb.mcshares.objects.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RequestDoc")
public class RequestDoc {
	
	private String docDate;
	private String docRef;
	private DocData docData;
	  
	@XmlElement(name = "Doc_Date")
	public String getDocDate() {
		return docDate;
	}
	public void setDocDate(String doc_Date) {
		docDate = doc_Date;
	}
	
	@XmlElement(name = "Doc_Ref")
	public String getDocRef() {
		return docRef;
	}
	public void setDocRef(String doc_Ref) {
		docRef = doc_Ref;
	}
	
	@XmlElement(name = "Doc_Data")
	public DocData getDocData() {
		return docData;
	}
	public void setDocData(DocData doc_Data) {
		docData = doc_Data;
	}
}
