package de.sample.hausrat.control.config;

import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Use this annotation to your test to get the full-configured
 * {@link de.sample.hausrat.control.InsuranceCalculatorFactory}
 * injected.
 */
@ExtendWith(InsuranceCalculationParameterResolver.class)
public @interface InsuranceCalculationTest {

}
