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
				res = (float) (val*0.79);
				break;
			case "kr":
				res = (float) (val*0.0078);
				break;
			default:
				res = val;
				break;
		}

		return res;
	}

}
