package com.zzizily.tech.spring.rest.commons;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
@Slf4j
public class ErrorSerializer extends JsonSerializer<Errors> {

  @Override
  public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeStartArray();
    errors.getFieldErrors().stream().forEach(error -> {
      try {
        gen.writeStartObject();
        gen.writeStringField("field", error.getField());
        gen.writeStringField("objectName", error.getObjectName());
        gen.writeStringField("code", error.getCode());
        gen.writeStringField("defaultMessage", error.getDefaultMessage());
        Object rejectedValue = error.getRejectedValue();
        if (null != rejectedValue) {
          gen.writeStringField("rejectedValue", rejectedValue.toString());
        }
        gen.writeEndObject();
      } catch (IOException e) {
        log.error("{}", e);
      }
    });

    errors.getGlobalErrors().stream().forEach(error -> {
      try {
        gen.writeStartObject();
        gen.writeStringField("objectName", error.getObjectName());
        gen.writeStringField("code", error.getCode());
        gen.writeStringField("defaultMessage", error.getDefaultMessage());
        gen.writeEndObject();
      } catch (IOException e) {
        log.error("{}", e);
      }
    });
    gen.writeEndArray();
  }
}
