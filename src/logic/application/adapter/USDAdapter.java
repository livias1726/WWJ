package logic.application.adapter;

/**
 * Class for the conversion from a certain currency to US dollars
 */
public class USDAdapter implements Currency {

	private String usCurr;
	
	public USDAdapter(String currency) {
		this.usCurr = currency;
	}

	@Override
	public Float value(Float val) {
		Float res;

		switch(usCurr) {
			case "C$":
				res = new CDToUSDAdaptee().getValue(val);
				break;
			case "kr":
				res = new KrToUSDAdaptee().getValue(val);
				break;
			default:
				res = val;
				break;
		}

		return res;
	}

}
