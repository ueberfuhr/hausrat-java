package de.sample.hausrat;

import de.sample.hausrat.domain.Product;

import java.util.Currency;

/**
 * The application environment.
 */
public interface Environment {

    /**
     * Returns the price of the given product per square meter.
     *
     * @param product the product
     * @return the price
     */
    int getProductPrice(Product product);

    /**
     * Returns the currency.
     */
    Currency getCurrency();

    /**
     * Returns the currency precision used for rounding.
     * @return the currency precision
     */
    default int getCurrencyPrecision() {
        return 2;
    }

}
