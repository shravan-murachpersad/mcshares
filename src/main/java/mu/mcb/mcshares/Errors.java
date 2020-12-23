package mu.mcb.mcshares;

public enum Errors {
	SUCCESS("000", "Success"),
	INVALID_CUSTOMER_TYPE("100", "The type of the customer should be either Individual or Corporate.");
	
	private final String responseCode;
	private final String responseDescription;
    
    private Errors(String code, String description) {
        this.responseCode = code;
        this.responseDescription = description;
    }

	public String getResponseCode() {
		return responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

}
