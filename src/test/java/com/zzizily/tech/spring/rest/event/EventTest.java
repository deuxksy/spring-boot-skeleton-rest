package com.zzizily.tech.spring.rest.event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {

  @Test
  public void builder() {
    Event event = Event.builder()
            .name("")
            .description("")
            .build();
    assertNotNull(event);
//    assertThat(event).isNotNull();
  }

  @Test
  public void javaBean() {
    Event event = new Event();
    event.setName("");
    event.setDescription("");
    assertNotNull(event.getName());
  }
}