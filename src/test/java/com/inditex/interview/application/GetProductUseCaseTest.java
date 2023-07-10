package com.inditex.interview.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.inditex.interview.domain.entities.Product;
import com.inditex.interview.infrastructure.configurations.DataLoaderConfiguration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class GetProductUseCaseTest {

    @Autowired
    private GetProducts getProducts;

    @Autowired
    private DataLoaderConfiguration dataLoaderConfiguration;

    @AfterEach
    public void tearDown() {
        dataLoaderConfiguration.cleanData();
    }

    @Test
    void test1Base() {
        loadDataBaseTest("test-1-base");
        try {
            List<Product> products = getProducts.execute();
            assertEquals(3, products.size());
            assertEquals(5, products.get(0).getId().getValue());
            assertEquals(1, products.get(1).getId().getValue());
            assertEquals(3, products.get(2).getId().getValue());
        } catch (Exception ex) {
            fail(ex);
        }

    }



    @Test
    void test2WithNoAvailableProducts() {
        loadDataBaseTest("test-2-NoAvailableProducts");
        try {
            List<Product> products = getProducts.execute();
            assertTrue(products.isEmpty(), "No deberia haber productos disponibles");
        } catch (Exception ex) {
            fail(ex);
        }
    }

    @Test
    void test3AvailableProductsOnlyBackSoon() {
        loadDataBaseTest("test-3-NoAvailableProductsOnlyBackSoon");
        try {
            List<Product> products = getProducts.execute();
            assertEquals(1, products.size(), "Solo debe haber un producto con only back soon");
        } catch (Exception ex) {
            fail(ex);
        }
    }
    @Test
    void test4NoAvailableProductsOnlyOneSpecialSize() {
        loadDataBaseTest("test-4-NoAvailableProductsOnlyOneSpecialSize");
        try {
            List<Product> products = getProducts.execute();
            assertEquals(0, products.size(), "No debe haber ningun producto que tenga disponible una talla normal");
        } catch (Exception ex) {
            fail(ex);
        }
    }

    @Test
    void test5NoAvailableProductsOnlyOneSpecialSizeAndBackSoon() {
        loadDataBaseTest("test-5-NoAvailableProductsOnlyOneSpecialSizeAndBackSoon");
        try {
            List<Product> products = getProducts.execute();
            assertEquals(1, products.size(), "Debe haber un producto que tenga marcado backsoon y tiene una talla especial");
        } catch (Exception ex) {
            fail(ex);
        }
    }

    private void loadDataBaseTest(String testFolder) {
        dataLoaderConfiguration.loadProducts(getFilePathProduct(testFolder));
        dataLoaderConfiguration.loadSizes(getFilePathSize(testFolder));
        dataLoaderConfiguration.loadStocks(getFilePathStock(testFolder));
    }

    private static String getFilePathProduct(String folderTest, String fileCSV) {
        return "src/test/resources/data/" + folderTest + "/" + fileCSV;
    }
    private static String getFilePathProduct(String folderTest) {
        return getFilePathProduct(folderTest, "product.csv");
    }
    private static String getFilePathStock(String folderTest) {
        return getFilePathProduct(folderTest, "stock.csv");
    }
    private static String getFilePathSize(String folderTest) {
        return getFilePathProduct(folderTest, "size-1.csv");
    }

}
