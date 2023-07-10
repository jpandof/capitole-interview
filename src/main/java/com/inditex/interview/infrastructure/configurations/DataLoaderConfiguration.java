package com.inditex.interview.infrastructure.configurations;

import com.inditex.interview.infrastructure.persistance.entities.ProductDAO;
import com.inditex.interview.infrastructure.persistance.entities.SizeDAO;
import com.inditex.interview.infrastructure.persistance.entities.StockDAO;
import com.inditex.interview.infrastructure.persistance.repository.JPAProductRepository;
import com.inditex.interview.infrastructure.persistance.repository.JPASizeRepository;
import com.inditex.interview.infrastructure.persistance.repository.JPAStockRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Initialize the repository with some data
 */
@Configuration
public class DataLoaderConfiguration {

    private final JPAProductRepository productRepository;
    private final JPASizeRepository sizeRepository;
    private final JPAStockRepository stockRepository;
    private final Environment environment;

    @Autowired
    public DataLoaderConfiguration(JPAProductRepository productRepository, JPASizeRepository sizeRepository, JPAStockRepository stockRepository, Environment environment) {
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.stockRepository = stockRepository;
        this.environment = environment;
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner loadData() {
        return args -> {
            boolean dataLoadingEnabled = environment.getProperty("app.data-loader.enabled", Boolean.class, false);

            if (!dataLoadingEnabled) {
                return;
            }

            loadProducts("src/main/resources/data/product.csv");
            loadSizes("src/main/resources/data/size-1.csv");
            loadStocks("src/main/resources/data/stock.csv");
        };
    }

    @FunctionalInterface
    interface LineParser<T> {
        T parseLine(List<String> line);
    }

    public <T> void loadFromCsv(String filePath, LineParser<T> parser, JpaRepository<T, Long> repository) {
        List<T> list = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                List<String> lineList = Arrays.asList(nextLine);
                T dao = parser.parseLine(lineList);
                list.add(dao);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        repository.saveAll(list);
    }

    public void loadProducts(String filePath) {
        loadFromCsv(filePath, lineList -> new ProductDAO(
            Long.valueOf(lineList.get(0).trim()),
            Long.valueOf(lineList.get(1).trim()),
            new ArrayList<>()
        ), productRepository);
    }

    public void loadSizes(String filePath) {
        loadFromCsv(filePath, lineList -> new SizeDAO(
            Long.valueOf(lineList.get(0).trim()),
            new ProductDAO(Long.valueOf(lineList.get(1).trim()), null, null),
            Boolean.parseBoolean(lineList.get(2).trim()),
            Boolean.parseBoolean(lineList.get(3).trim()),
            new ArrayList<>()
        ), sizeRepository);
    }

    public void loadStocks(String filePath) {
        loadFromCsv(filePath, lineList -> new StockDAO(
            null,
            new SizeDAO(Long.valueOf(lineList.get(0).trim()), null, null, null, null),
            Long.valueOf(lineList.get(1).trim())
        ), stockRepository);
    }

    public void cleanData(){
        stockRepository.deleteAll();
        sizeRepository.deleteAll();
        productRepository.deleteAll();
    }

/*
    public void loadProducts(String filePath) {

        List<ProductDAO> list = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                List<String> lineList = Arrays.asList(nextLine);
                ProductDAO productDAO = new ProductDAO(
                    Long.valueOf(lineList.get(0).trim()),
                    Long.valueOf(lineList.get(1).trim()),
                    new ArrayList<>()
                );
                list.add(productDAO);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        productRepository.saveAll(list);
    }

    public void loadSizes(String filePath) {
        List<SizeDAO> list = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                List<String> lineList = Arrays.asList(nextLine);
                SizeDAO productDAO = new SizeDAO(
                    Long.valueOf(lineList.get(0).trim()),
                    new ProductDAO(Long.valueOf(lineList.get(1).trim()), null, null),
                    Boolean.parseBoolean(lineList.get(2).trim()),
                    Boolean.parseBoolean(lineList.get(3).trim()),
                    new ArrayList<>()
                );
                list.add(productDAO);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        sizeRepository.saveAll(list);
    }

    public void loadStocks(String filePath) {
        List<StockDAO> list = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                List<String> lineList = Arrays.asList(nextLine);
                StockDAO productDAO = new StockDAO(
                    null,
                    new SizeDAO(Long.valueOf(lineList.get(0).trim()), null, null,null,null),
                    Long.valueOf(lineList.get(1).trim())
                );
                list.add(productDAO);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        stockRepository.saveAll(list);
    }*/

}
