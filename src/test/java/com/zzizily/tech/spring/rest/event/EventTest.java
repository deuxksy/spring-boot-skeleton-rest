package com.zzizily.tech.spring.rest.event;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
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
  @Parameters(method = "parametersForTestFree")
  public void testFree(int basePrice, int maxPrice, boolean isFree) {
    Event event = Event.builder()
            .basePrice(new BigDecimal(basePrice))
            .maxPrice(new BigDecimal(maxPrice))
            .build();
    event.update();
    assertThat(event.isFree()).isEqualTo(isFree);
  }

  private Object[] parametersForTestFree() {
    return new Object[]{
      new Object[]{0, 0, true},
      new Object[]{100, 0, false},
      new Object[]{0, 1000, false},
    };
  }

  @Test
  @Parameters(method = "parametersForOnOffline")
  public void testOnOffline(String location, boolean isOffLine) {
    Event event = Event.builder()
            .location(location)
            .build();
    event.update();
    assertThat(event.isOffline()).isEqualTo(isOffLine);
  }

  private Object[] parametersForOnOffline() {
    return new Object[]{
      new Object[]{"교대역 동익성봉빌딩 5층", true},
      new Object[]{"", false},
      new Object[]{"   ", false},
      new Object[]{null, false}
    };
  }
}