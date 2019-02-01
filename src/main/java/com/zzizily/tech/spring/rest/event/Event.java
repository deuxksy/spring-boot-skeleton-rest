package com.zzizily.tech.spring.rest.event;

import lombok.*;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Event {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String description;
  private LocalDateTime beginEnrollmentDateTime;
  private LocalDateTime closeEnrollmentDateTime;
  private LocalDateTime beginEventDateTime;
  private LocalDateTime endEventDateTime;
  private String location;
  private BigDecimal basePrice;
  private BigDecimal maxPrice;
  private Integer limitOfEnrollment;
  private boolean offline;
  private boolean free;
  @Enumerated(EnumType.STRING)
  private EventStatus eventStatus = EventStatus.DRAFT;

  public Event update() {

    if (new BigDecimal(0).equals(this.basePrice) && new BigDecimal(0).equals(this.maxPrice)) {
      this.free = true;
    } else {
      this.free = false;
    }

    if (Strings.isNotBlank(this.location)) {
      offline = true;
    } else {
      offline = false;
    }
    return this;
  }
}
