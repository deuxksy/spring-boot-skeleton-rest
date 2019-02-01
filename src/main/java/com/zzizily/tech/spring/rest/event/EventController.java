package com.zzizily.tech.spring.rest.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@AllArgsConstructor
@Slf4j
public class EventController {

  private final EventRepository eventRepository;
  private final ModelMapper modelMapper;
  private final EventValidator eventValidator;

  @PostMapping("/events")
  public ResponseEntity createEvent(@RequestBody @Valid EventDTO eventDTO, Errors errors) {
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }
    eventValidator.validate(eventDTO, errors);
    if (errors.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }

    Event newEvent = eventRepository.save(modelMapper.map(eventDTO, Event.class));
    URI createUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
    return ResponseEntity.created(createUri).body(newEvent);
  }
}
