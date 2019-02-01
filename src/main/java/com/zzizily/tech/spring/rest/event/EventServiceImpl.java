package com.zzizily.tech.spring.rest.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  @Override
  public Event save(Event event) {
    event.update();
    return eventRepository.save(event);
  }
}
