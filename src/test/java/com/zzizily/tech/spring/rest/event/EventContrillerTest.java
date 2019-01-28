package com.zzizily.tech.spring.rest.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventContrillerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void createEvnet() throws Exception {
    Event event = Event.builder()
      .id(100)
      .name("")
      .description("")
      .beginEnrollmentDateTime(LocalDateTime.of(2019, 1, 27, 11, 34))
      .closeEnrollmentDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .build();

    mockMvc.perform(
      post("/api/events")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaTypes.HAL_JSON)
        .content(objectMapper.writeValueAsString(event)
      )
    )
    .andDo(print())
    .andExpect(status().isCreated())
    .andExpect(jsonPath("id").exists())
    .andExpect(header().exists(HttpHeaders.LOCATION))
    .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
    ;
  }
}