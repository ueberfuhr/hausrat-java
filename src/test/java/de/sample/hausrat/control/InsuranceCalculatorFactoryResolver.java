package de.sample.hausrat.control;

import de.sample.hausrat.ApplicationProperties;
import de.sample.hausrat.Environment;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;

// used for picocontainer
public class InsuranceCalculatorFactoryResolver {

    public InsuranceCalculatorFactory createFactory() throws Exception {
        final Environment env = ApplicationProperties.load();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return new InsuranceCalculatorFactory(env, validator);
    }
}
