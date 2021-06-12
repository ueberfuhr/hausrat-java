package de.sample.hausrat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * A request to the {@link de.sample.hausrat.control.InsuranceCalculator}.
 */
@Data
@AllArgsConstructor
public class InsuranceRequest {

    @NotNull
    private final Product product;
    @Positive
    private final double livingArea;

}
