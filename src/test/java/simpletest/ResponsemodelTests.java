package simpletest;

import com.iwebirth.util.Jackson;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class ResponsemodelTests {
	@Test
	public void jacksonTest() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyone", "hello");
		map.put("keytwo", "world");
		map.put("keythree", true);
		System.out.println(Jackson.writeValueAsString(map));
	}
	

}
