package simpletest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.iwebirth.controller.responsemodel.EVTreeResponseLeaf;
import com.iwebirth.controller.responsemodel.EVTreeResponseNode;
import com.iwebirth.controller.responsemodel.TreeResponseRoot;
import com.iwebirth.util.Jackson;


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
