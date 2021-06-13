package de.sample.hausrat;

import de.sample.hausrat.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationPropertiesTest {

    @Mock
    Properties props;
    @InjectMocks
    ApplicationProperties env;

    @ParameterizedTest
    @EnumSource(Product.class)
    void testProductPrice(Product product) {
        String propertyName = String.format("product.%s.price", product.name().toLowerCase(Locale.ROOT));
        when(props.getProperty(propertyName)).thenReturn("100");
        int price = env.getProductPrice(product);
        assertThat(price).isEqualTo(100);
    }

    @Test
    void testProductPriceForNull() {
        assertThatThrownBy(() -> env.getProductPrice(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
