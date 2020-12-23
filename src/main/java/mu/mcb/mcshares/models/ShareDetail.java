package mu.mcb.mcshares.models;

import javax.xml.bind.annotation.XmlElement;

public class ShareDetail { 
	private int numShares;
	private String sharePrice;
	
	@XmlElement(name = "Num_Shares")
	public int getNumShares() {
		return numShares;
	}
	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}
	
	@XmlElement(name = "Share_Price")
	public String getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
	}
}
