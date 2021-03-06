package de.sample.hausrat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
public class Price {

    private BigDecimal value;
    private Currency currency;

}
