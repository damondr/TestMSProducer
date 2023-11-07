package org.damon.st.producer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {
    @NotEmpty(message = "Type is required")
    private String type;
    @NotEmpty(message = "Value is required")
    private String value;
}