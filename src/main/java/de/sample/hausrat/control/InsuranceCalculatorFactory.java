package de.sample.hausrat.control;

import de.sample.hausrat.Environment;
import de.sample.hausrat.domain.InsuranceCalculationRequest;
import de.sample.hausrat.domain.Price;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Set;

/**
 * A factory that provides the calculator.
 */
// might be extendable by further factory methods.
@RequiredArgsConstructor
public class InsuranceCalculatorFactory {

    private final Environment environment;
    private final Validator validator;

    private Price price(BigDecimal value) {
        int scale = environment.getCurrencyPrecision();
        Currency currency = environment.getCurrency();
        return new Price(value.setScale(scale, RoundingMode.HALF_UP), currency);
    }

    private Price price(double value) {
        return price(BigDecimal.valueOf(value));
    }

    // we need input validation using Bean Validation
    private InsuranceCalculator valid(InsuranceCalculator delegate) {
        return req -> {
            Set<ConstraintViolation<InsuranceCalculationRequest>> violations = validator.validate(req);
            if (!violations.isEmpty()) {
                throw new IllegalArgumentException(new ConstraintViolationException(violations));
            } else {
                return delegate.calculate(req);
            }
        };
    }

    /**
     * Creates the linear calculator.
     *
     * @return the linear calculator
     */
    public InsuranceCalculator createLinearCalculator() {
        return valid(req -> price(req.getLivingArea() * environment.getProductPrice(req.getProduct())));
    }

}
