package com.zzizily.tech.spring.rest.event;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@AllArgsConstructor
public class EventController {

  private final EventRepository eventRepository;

  @PostMapping("/events")
  public ResponseEntity createEvent(@RequestBody Event event) {
    Event newEvent = eventRepository.save(event);
    URI createUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
    return ResponseEntity.created(createUri).body(newEvent);
  }
}
