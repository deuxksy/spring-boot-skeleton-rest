package com.zzizily.tech.spring.rest.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Class representing a person tracked by the application.")
public class EventDTO {

  @NotEmpty
  private String name;
  @NotBlank
  private String description;
  @NotNull
  private LocalDateTime beginEnrollmentDateTime;
  @NotNull
  private LocalDateTime closeEnrollmentDateTime;
  @NotNull
  private LocalDateTime beginEventDateTime;
  @NotNull
  private LocalDateTime endEventDateTime;

  private String location;
  @Min(0)
  private BigDecimal basePrice;
  @Min(0)
  private BigDecimal maxPrice;
  @Min(0)
  private Integer limitOfEnrollment;
}
