package de.sample.hausrat;

import de.sample.hausrat.domain.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Currency;
import java.util.Locale;
import java.util.Properties;

/**
 * An environment that is read from a properties file.
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ApplicationProperties implements Environment {

    public static final String PROPERTIES_FILE = "application.properties";
    private static final String PRODUCT_COMPACT_PRICE = "product.compact.price";
    private static final String PRODUCT_OPTIMAL_PRICE = "product.optimal.price";

    private final Properties properties;

    /**
     * Loads the environment from the properties file.
     *
     * @return the environment
     * @throws IOException if loading the properties failed
     * @see #PROPERTIES_FILE
     */
    public static ApplicationProperties load() throws IOException {
        final Properties p = new Properties();
        try (final InputStream in = Start.class.getResourceAsStream("/" + ApplicationProperties.PROPERTIES_FILE)) {
            p.load(in);
        }
        return new ApplicationProperties(p);
    }

    @Override
    public int getProductPrice(Product product) {
        final String propertyName;
        if(null == product) {
            throw new IllegalArgumentException();
        } else {
            switch (product) {
                case COMPACT:
                    propertyName = ApplicationProperties.PRODUCT_COMPACT_PRICE;
                    break;
                case OPTIMAL:
                    propertyName = ApplicationProperties.PRODUCT_OPTIMAL_PRICE;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return Integer.parseInt(properties.getProperty(propertyName));
        }
    }

    @Override
    public Currency getCurrency() {
        return Currency.getInstance(Locale.GERMANY);
    }
}
