package com.zzizily.tech.spring.rest.event;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {
  public void validate(EventDTO eventDTO, Errors errors) {
    if (eventDTO.getBasePrice().longValue() > eventDTO.getMaxPrice().longValue() && eventDTO.getMaxPrice().longValue() > 0) {
      errors.rejectValue("basePrice","wrongValue","기본 금액을 확인 해주세요.");
      errors.rejectValue("maxPrice","wrongValue","최대 금액을 확인 해주세요.");
    }

    LocalDateTime endEventDateTime = eventDTO.getEndEventDateTime();
    if (endEventDateTime.isBefore(eventDTO.getBeginEventDateTime())||
      endEventDateTime.isBefore(eventDTO.getCloseEnrollmentDateTime())||
      endEventDateTime.isBefore(eventDTO.getBeginEnrollmentDateTime())){
      errors.rejectValue("endEventDateTime", "wrongValue", "이벤트 시작 날짜를 학인 해주세요.");
    }
  }
}
