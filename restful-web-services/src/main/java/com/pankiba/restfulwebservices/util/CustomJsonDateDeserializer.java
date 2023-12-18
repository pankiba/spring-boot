package com.pankiba.restfulwebservices.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String date = jsonParser.getText();

		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
