package de.sample.hausrat;

import de.sample.hausrat.control.InsuranceCalculator;
import de.sample.hausrat.control.InsuranceCalculatorFactory;
import de.sample.hausrat.domain.InsuranceRequest;
import de.sample.hausrat.domain.Price;
import de.sample.hausrat.domain.Product;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException {
        final Environment env = ApplicationProperties.load();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        final InsuranceCalculatorFactory calcFactory = new InsuranceCalculatorFactory(env, validator);
        final InsuranceCalculator calc = calcFactory.createLinearCalculator();
        Start.outputCase(new InsuranceRequest(Product.COMPACT, 100), calc);
        Start.outputCase(new InsuranceRequest(Product.OPTIMAL, 100), calc);
        Start.outputCase(new InsuranceRequest(Product.COMPACT, 150), calc);
        Start.outputCase(new InsuranceRequest(Product.OPTIMAL, 150), calc);
        Start.outputCase(new InsuranceRequest(null, -5), calc);
    }

    private static void outputCase(InsuranceRequest req, InsuranceCalculator calculator) {
        try {
            final Price price = calculator.calculate(req);
            System.out.printf("Produkt: %s, Wohnfläche: %.2fm² => %.2f %s%n", req.getProduct().name(), req.getLivingArea(), price.getValue(), price.getCurrency().getCurrencyCode());
        } catch (IllegalArgumentException e) {
            // Bean Validation should be the cause
            e.getCause().printStackTrace();
        }
    }

}
