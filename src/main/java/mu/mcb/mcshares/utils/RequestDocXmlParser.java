package mu.mcb.mcshares.utils;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import mu.mcb.mcshares.objects.xml.RequestDoc;

public class RequestDocXmlParser {

	public RequestDoc parse(InputStream data) {
		RequestDoc requestDoc = new RequestDoc();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RequestDoc.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			requestDoc = (RequestDoc) jaxbUnmarshaller.unmarshal(data);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return requestDoc;
	}
	
	public static void main(String[] args) {
		try {

			File file = new File("/Users/murachpersad/eclipse-workspace/mcshares/src/main/java/mu/mcb/mcshares/utils/McShares_2018.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RequestDoc.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			RequestDoc requestDoc = (RequestDoc) jaxbUnmarshaller.unmarshal(file);
			
			System.out.println(requestDoc);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
