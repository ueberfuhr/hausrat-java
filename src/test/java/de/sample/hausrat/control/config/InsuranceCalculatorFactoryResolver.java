package de.sample.hausrat.control.config;

import de.sample.hausrat.ApplicationProperties;
import de.sample.hausrat.Environment;
import de.sample.hausrat.control.InsuranceCalculatorFactory;
import lombok.Getter;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

// used for picocontainer
public class InsuranceCalculatorFactoryResolver {

    @Getter
    private final Environment environment;

    public InsuranceCalculatorFactoryResolver() throws Exception {
        this.environment = ApplicationProperties.load();
    }

    public InsuranceCalculatorFactory createFactory() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return new InsuranceCalculatorFactory(environment, validator);
    }
}
