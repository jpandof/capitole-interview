package com.inditex.interview.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.inditex.interview.domain.valueobjects.product.ProductId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ProductIdTest {

    @Test
    void notNull() {
        assertThrows(IllegalArgumentException.class, () -> ProductId.valueOf(null));
    }
}
