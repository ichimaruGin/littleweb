package com.iwebirth.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class Jackson {
	private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper mapper = new ObjectMapper();
	public static void setJsonGenerator(Writer writer){
		try {
			jsonGenerator = mapper.getJsonFactory().createJsonGenerator(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ObjectMapper getMapper(){
			return mapper;		
	}
	public static JsonGenerator getJsonGenerator(Writer writer){
		if(jsonGenerator == null)
			setJsonGenerator(writer);
		return jsonGenerator;		
	}
	public static String writeValueAsString(Object obj) throws Exception{
		return mapper.writeValueAsString(obj);
	}
}
