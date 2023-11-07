package org.damon.st.producer.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    @NotEmpty(message = "Type is required")
    private String type;
    @NotEmpty(message = "Value is required")
    private String value;
}