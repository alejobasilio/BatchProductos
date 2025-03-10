package com.alejobasilio.batch2.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.alejobasilio.batch2.model.Producto;
import com.alejobasilio.batch2.model.ProductoDtoLG;

/**
 * Clase que implementa un escritor de archivos planos para escribir objetos de tipo ProductoDtoLG en un archivo CSV llamado "LGProductos.csv".
 */
@Component
public class CustomWriter2 extends FlatFileItemWriter<ProductoDtoLG> {

    public CustomWriter2() {
        setResource(new FileSystemResource(" LGProductos.csv"));
        setAppendAllowed(false);

        DelimitedLineAggregator<ProductoDtoLG> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(";");
        BeanWrapperFieldExtractor<ProductoDtoLG> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"id", "producto", "precio_inicial", "precio_final", "aleteracion_precio", "moneda"});
        aggregator.setFieldExtractor(extractor);

        setLineAggregator(aggregator);
    }
}