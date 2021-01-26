package logic.application.adapter;

public class KrToUSDAdaptee {

	public Float getValue(Float val) {
		return (float) (val*0.0078);
	}
}
