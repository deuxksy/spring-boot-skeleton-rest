package com.zzizily.tech.spring.rest.event;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


public class EventTest {

  @Test
  public void builder() {
    String name = "Event";
    String description = "Spring";

    Event event = Event.builder()
            .name(name)
            .description(description)
            .build();
    assertThat(event);
    assertThat(event).isNotNull();
  }

  @Test
  public void javaBean() {
    String name = "Event";
    String description = "Spring";

    Event event = new Event();
    event.setName(name);
    event.setDescription(description);
    assertThat(event.getName()).isEqualTo(name);
    assertThat(event.getName()).isEqualTo(description);
  }

  @Test
  public void testFree() {
    Event event = Event.builder()
            .basePrice(new BigDecimal(0))
            .maxPrice(new BigDecimal(0))
            .build();
    event.update();
    assertThat(event.isFree()).isTrue();

    event = Event.builder()
            .basePrice(new BigDecimal(100))
            .maxPrice(new BigDecimal(0))
            .build();
    event.update();
    assertThat(event.isFree()).isFalse();

    event = Event.builder()
            .basePrice(new BigDecimal(0))
            .maxPrice(new BigDecimal(1000))
            .build();
    event.update();
    assertThat(event.isFree()).isFalse();
  }

  @Test
  public void testOnOffline() {
    Event event = Event.builder()
            .location("교대역 동익성봉빌딩 5층")
            .build();
    event.update();
    assertThat(event.isOffline()).isTrue();

    event = Event.builder()
            .build();
    event.update();
    assertThat(event.isOffline()).isFalse();
  }
}