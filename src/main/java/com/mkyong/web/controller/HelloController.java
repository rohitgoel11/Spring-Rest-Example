package com.mkyong.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	@RequestMapping(value = "/staff", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Staff> getCountries() {
		List<Staff> staffList = new ArrayList<Staff>();
		try {

			Staff staff = null;
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
		return staffList;
	}
}
