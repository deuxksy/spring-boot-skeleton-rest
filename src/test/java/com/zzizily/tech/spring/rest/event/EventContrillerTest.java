package com.zzizily.tech.spring.rest.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

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
    EventDTO eventDTO = EventDTO.builder()
      .name("Spring")
      .description("REST API Development with Spring")
      .beginEnrollmentDateTime(LocalDateTime.of(2019, 1, 27, 11, 34))
      .closeEnrollmentDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .beginEventDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .endEventDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .basePrice(new BigDecimal(1000))
      .maxPrice(new BigDecimal(10000))
      .limitOfEnrollment(100)
      .location("강남역 D2 스타텁 팩토리")
      .build();

      mockMvc.perform(post("/api/events")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaTypes.HAL_JSON)
        .content(objectMapper.writeValueAsString(eventDTO)
      ))
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("id").exists())
      .andExpect(header().exists(HttpHeaders.LOCATION))
      .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
      .andExpect(jsonPath("free").value(false))
      .andExpect(jsonPath("offline").value(true))
      .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT))
    ;
  }

  @Test
  public void createEvnetBadRequest() throws Exception {
    Event event = Event.builder()
      .id(10)
      .name("설 이벤트")
      .description("좀 사주세요 매출 채워야 해요")
      .beginEnrollmentDateTime(LocalDateTime.of(2019, 1, 27, 11, 34))
      .closeEnrollmentDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .beginEventDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .endEventDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .basePrice(new BigDecimal(100))
      .maxPrice(new BigDecimal(1000))
      .limitOfEnrollment(100)
      .location("서울시 강남구 토즈")
      .build();

    mockMvc.perform(post("/api/events")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .accept(MediaTypes.HAL_JSON)
      .content(objectMapper.writeValueAsString(event)
    ))
    .andDo(print())
    .andExpect(status().isBadRequest())
    ;
  }

  @Test
  public void createEvnetBadRequestEmptyInput() throws Exception {
    EventDTO eventDTO = EventDTO.builder()
      .build();

    mockMvc.perform(post("/api/events")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(eventDTO)
      ))
      .andDo(print())
      .andExpect(status().isBadRequest())
    ;
  }

  @Test
  public void createEvnetBadRequestWrongInput() throws Exception {
    EventDTO eventDTO = EventDTO.builder()
      .name("설 이벤트")
      .description("좀 사주세요 매출 채워야 해요")
      .beginEnrollmentDateTime(LocalDateTime.of(2019, 1, 27, 11, 34))
      .closeEnrollmentDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .beginEventDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .endEventDateTime(LocalDateTime.of(2019, 3, 1, 1, 1))
      .basePrice(new BigDecimal(10000))
      .maxPrice(new BigDecimal(1000))
      .limitOfEnrollment(100)
      .location("서울시 강남구 토즈")
      .build();

    mockMvc.perform(post("/api/events")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .content(objectMapper.writeValueAsString(eventDTO)
      ))
      .andDo(print())
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$[0].objectName").exists())
//      .andExpect(jsonPath("$[0].field").exists())
      .andExpect(jsonPath("$[0].defaultMessage").exists())
      .andExpect(jsonPath("$[0].code").exists())
//      .andExpect(jsonPath("$[0].rejectValue").exists())
    ;
  }
}