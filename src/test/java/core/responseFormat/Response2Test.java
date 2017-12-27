package core.responseFormat;

import org.junit.Test;

import com.example.core.responseFormat.Response;
import com.example.model.hibernate.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Response2Test {

	@Test
	public void makeSuccess() throws JsonProcessingException{
		Response response2 = new Response().success();
		
	
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(response2);
		System.out.println(json);  
	}
}
