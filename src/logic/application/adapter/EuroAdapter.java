package logic.application.adapter;

/**
 * Class for the conversion from a certain currency to euros
 */
public class EuroAdapter implements Currency{
	
	private String currency;
	
	public EuroAdapter(String currency) {
		this.currency = currency;
	}

	@Override
	public Float value(Float val) {
		Float res;
		switch(currency) {
			case "C$":
				res = (float) (val*0.65);
				break;
			case "kr":
				res = (float) (val*0.0064);
				break;
			default:
				res = val;
				break;
		}
		
		return res;
	}

}
