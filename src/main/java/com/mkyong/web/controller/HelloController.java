package com.mkyong.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class HelloController {
	
	private static int count=0;

	@RequestMapping(value = "/staff", method = RequestMethod.GET, headers = "Accept=application/json")
	public StaffArray getStaff() {
		StaffArray list=new StaffArray();
		List<Staff> staffList = new ArrayList<Staff>();
		try {
			
			Staff staff = null;
			int a=0;
			JsonFactory factory = new JsonFactory();
			JsonParser parser = factory.createParser(new File("D:/My docs/file.json.txt"));

			while (!parser.isClosed()) {

				JsonToken jsonToken = parser.nextToken();
				if (JsonToken.START_OBJECT.equals(jsonToken)) {
					staff = new Staff();
				}

				if (JsonToken.END_OBJECT.equals(jsonToken)) {
					staffList.add(staff);
				}

				if (JsonToken.FIELD_NAME.equals(jsonToken)) {
					String fieldName = parser.getCurrentName();
					System.out.println(fieldName);

					jsonToken = parser.nextToken();

					if ("_id".equals(fieldName)) {
						staff.set_id(parser.getValueAsString());
					} else if ("index".equals(fieldName)) {
						staff.setIndex(parser.getValueAsInt());
					}else if ("guid".equals(fieldName)) {
						staff.setGuid(parser.getValueAsString());
					}else if ("balance".equals(fieldName)) {
						staff.setBalance(parser.getValueAsString());
					}else if ("age".equals(fieldName)) {
						staff.setIndex(parser.getValueAsInt());
					}else if ("eyeColor".equals(fieldName)) {
						staff.setEyeColor(parser.getValueAsString());
					}else if ("phone".equals(fieldName)) {
						staff.setPhone(parser.getValueAsString());
					}else if ("address".equals(fieldName)) {
						staff.setAddress(parser.getValueAsString());
					}
				}
			}

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(staffList.size());
		list.setMystaff(staffList);
		return list;
	}
	
	@RequestMapping(value = "/staff/{index}", method = RequestMethod.GET, headers = "Accept=application/json")
	public MyData getStaffById(@PathVariable int index) {
		
		MyData data= new MyData();
		data.setGuid("My GUID" + index);
		data.setId("My ID" +index+100);
		data.setIndex(index);
		count++;
		System.out.println("Response sent:"+ count);
		return data;
		
	}
}
