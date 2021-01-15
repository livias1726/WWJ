package logic.application.adapter;

/**
 * Class for the conversion from a certain currency to pounds
 */
public class PoundAdapter implements Currency {

	private String currency;
	
	public PoundAdapter(String currency) {
		this.currency = currency;
	}

	@Override
	public Float value(Float val) {
		Float res;
		switch(currency) {
			case "C$":
				res = (float) (val*0.58);
				break;
			case "kr":
				res = (float) (val*0.0057);
				break;
			default:
				res = val;
				break;
		}
		
		return res;
	}

}
