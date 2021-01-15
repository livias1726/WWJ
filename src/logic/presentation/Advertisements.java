package logic.presentation;

import java.util.HashMap;
import java.util.Map;

public enum Advertisements {

	ADV1(0),
	ADV2(1),
	ADV3(2);

	private int value;	
	private static Map<Object, Object> map = new HashMap<>();

    private Advertisements(int value) {
        this.value = value;
    }

    static {
        for (Advertisements ad : Advertisements.values()) {
            map.put(ad.value, ad);
        }
    }

    public static Advertisements valueOf(Integer id) {
        return (Advertisements) map.get(id);
    }

    public static String toString(Integer id) {
    	switch(valueOf(id)) {
    	case ADV1:
    		return "https://freedesignfile.com/upload/2019/07/Healthy-eating-fruit-background-advertisement-vector.jpg";
    	case ADV2:
    		return "https://5.imimg.com/data5/MK/VP/TO/SELLER-2650952/commercial-advertisement-service-500x500.jpg";
    	case ADV3:
    		return "https://www.greentechmedia.com/assets/content/cache/made/assets/content/cache/remote/https_assets.greentechmedia.com/content/images/articles/green_advertisement_500_500_80_s_c1.jpg";
    	default:
    		return "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png";
    	}
    }
}
