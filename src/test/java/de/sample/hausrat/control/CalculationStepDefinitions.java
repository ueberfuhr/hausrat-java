package de.sample.hausrat.control;

import de.sample.hausrat.domain.InsuranceRequest;
import de.sample.hausrat.domain.Price;
import de.sample.hausrat.domain.Product;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@RequiredArgsConstructor
public class CalculationStepDefinitions {

    private final InsuranceCalculatorFactoryResolver resolver;
    private InsuranceCalculator calculator;
    private Product product;
    private double livingArea;

    @Given("the linear calculation")
    public void setup() throws Exception {
        this.calculator = this.resolver.createFactory().createLinearCalculator();
    }

    @When("the product is \\\"([^\\\"]*)\\\"$")
    public void the_product_is(Product product) {
        this.product = product;
    }

    @When("the living area is {float} m²")
    public void the_living_area_is(double livingArea) {
        this.livingArea = livingArea;
    }

    private Price executeCalculation() {
        return this.calculator.calculate(new InsuranceRequest(this.product, this.livingArea));
    }

    @Then("the sum insured is {float} {string}")
    public void the_sum_insured_is(double value, String currencyCode) {
        Price result = executeCalculation();
        assertAll(
                () -> assertThat(result.getValue().doubleValue()).isEqualTo(value),
                () -> assertThat(result.getCurrency().getCurrencyCode()).isEqualTo(currencyCode)
        );
    }

    @Then("the validation will fail")
    public void the_validation_will_fail() {
        assertThatThrownBy(this::executeCalculation)
                .isInstanceOf(IllegalArgumentException.class)
                .hasCauseInstanceOf(ConstraintViolationException.class);
    }

}
