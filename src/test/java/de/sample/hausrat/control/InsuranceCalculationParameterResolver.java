package de.sample.hausrat.control;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class InsuranceCalculationParameterResolver implements ParameterResolver {

    private final InsuranceCalculatorFactoryResolver delegate = new InsuranceCalculatorFactoryResolver();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == InsuranceCalculatorFactory.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        try {
            return delegate.createFactory();
        } catch (Exception e) {
            throw new ParameterResolutionException("Unable to resolve InsuranceCalculatorFactory.", e);
        }
    }

}
