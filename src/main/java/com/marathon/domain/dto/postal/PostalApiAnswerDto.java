package com.marathon.domain.dto.postal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostalApiAnswerDto {

    @JsonProperty("miejscowosc")
    private String miejscowosc;
}
