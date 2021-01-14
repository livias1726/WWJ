package logic.application.control.adapter;

/**
 * Class for the conversion from a certain currency to US dollars
 */
public class USDAdapter implements Currency {

	private String currency;
	
	public USDAdapter(String currency) {
		this.currency = currency;
	}

	@Override
	public Float value(Float val) {
		Float res;

		switch(currency) {
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
