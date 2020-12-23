package mu.mcb.mcshares;

public enum Errors {
	SUCCESS("000", "Success"),
	INVALID_CUSTOMER_TYPE("100", "The type of the customer should be either Individual or Corporate."),
	INVALID_CUSTOMER_AGE("101", "The customer should be above 18 years old."),
	INVALID_CUSTOMER_AGE_MISSING("101", "The field birth date is missing."),
	INVALID_CUSTOMER_SHARES_AMOUNT("102", "The customer must have at least 1 share."),
	INVALID_CUSTOMER_SHARES_PRICE("102", "The price of the share is invalid. Share price should be greater than 0.");
	
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
