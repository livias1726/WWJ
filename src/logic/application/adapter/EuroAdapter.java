package logic.application.adapter;

/**
 * Class for the conversion from a certain currency to euros
 */
public class EuroAdapter implements Currency{
	
	private String euCurr;
	
	public EuroAdapter(String currency) {
		this.euCurr = currency;
	}

	@Override
	public Float value(Float val) {
		Float res;
		switch(euCurr) {
			case "C$":
				res = new CDToEuroAdaptee().getValue(val);
				break;
			case "kr":
				res = new KrToEuroAdaptee().getValue(val);
				break;
			default:
				res = val;
				break;
		}
		
		return res;
	}

}
