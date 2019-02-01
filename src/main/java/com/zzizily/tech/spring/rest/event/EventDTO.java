package com.zzizily.tech.spring.rest.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing a person tracked by the application.")
public class EventDTO {
  @NotBlank
  @Size(min=3, max=20)
  @ApiModelProperty(notes = "First name of the person.", example = "John", required = true, position = 1)
  private String firstName;
  private String name;
  private String description;
  private LocalDateTime beginEnrollmentDateTime;
  private LocalDateTime closeEnrollmentDateTime;
  private LocalDateTime beginEventDateTime;
  private LocalDateTime endEventDateTime;
  private String location;
  private int basePrice;
  private int maxPrice;
  private int limitOfEnrollment;
}
