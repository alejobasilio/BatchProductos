package com.alejobasilio.batch2.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.alejobasilio.batch2.model.Producto;

/**
 * Clase que implementa un escritor de archivos planos para escribir objetos de tipo Producto en un archivo CSV llamado "EuroProductos.csv".
 */
@Component
public class CustomWriter extends FlatFileItemWriter<Producto> {

    public CustomWriter() {
        setResource(new FileSystemResource("EuroProductos.csv"));
        setAppendAllowed(false);

        DelimitedLineAggregator<Producto> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(";");
        BeanWrapperFieldExtractor<Producto> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"id", "producto", "marca", "precio", "moneda"});
        aggregator.setFieldExtractor(extractor);

        setLineAggregator(aggregator);
    }
}