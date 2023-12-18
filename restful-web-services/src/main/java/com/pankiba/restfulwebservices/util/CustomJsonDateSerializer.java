package com.pankiba.restfulwebservices.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomJsonDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = format.format(date);
		jsonGenerator.writeString(dateString);
	}

}
