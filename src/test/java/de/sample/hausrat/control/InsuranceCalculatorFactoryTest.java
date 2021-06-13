package de.sample.hausrat.control;

import de.sample.hausrat.Environment;
import de.sample.hausrat.domain.InsuranceRequest;
import de.sample.hausrat.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;
import java.util.Collections;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsuranceCalculatorFactoryTest {

    @Mock
    Environment env;
    @Mock
    Validator validator;
    @InjectMocks
    InsuranceCalculatorFactory factory;
    InsuranceCalculator calculator;

    @BeforeEach
    void setup() {
        this.calculator = factory.createLinearCalculator();
    }

    @Test
    void testCalculatorCreated() {
        assertThat(this.calculator).isNotNull();
    }

    @Test
    void testUsageOfEnvironmentAndValidation() {
        InsuranceRequest req = new InsuranceRequest(Product.COMPACT, 140);
        when(env.getProductPrice(req.getProduct())).thenReturn(100);
        when(env.getCurrencyPrecision()).thenReturn(2);
        when(env.getCurrency()).thenReturn(Currency.getInstance("EUR"));
        when(validator.validate(req)).thenReturn(Collections.emptySet());
        this.calculator.calculate(req);
    }
}
