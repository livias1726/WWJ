package logic.application.adapter;

/**
 * Class for the conversion from a certain currency to pounds
 */
public class PoundAdapter implements Currency {

	private String ukCurr;
	
	public PoundAdapter(String currency) {
		this.ukCurr = currency;
	}

	@Override
	public Float value(Float val) {
		Float res;
		switch(ukCurr) {
			case "C$":
				res = new CDToPoundAdaptee().getValue(val);
				break;
			case "kr":
				res = new KrToPoundAdaptee().getValue(val);
				break;
			default:
				res = val;
				break;
		}
		
		return res;
	}

}
