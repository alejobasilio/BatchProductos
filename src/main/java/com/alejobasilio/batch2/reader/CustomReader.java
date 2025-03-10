package com.alejobasilio.batch2.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.alejobasilio.batch2.model.Producto;

/**
 * Clase que implementa un lector de archivos planos para leer datos de un archivo CSV y mapearlos a objetos de tipo Producto.
 * El lector utiliza un archivo llamado "datos_entrada.csv" ubicado en el classpath, salta la primera línea del archivo (encabezados) y utiliza un delimitador ";" para separar los campos.
 * Los campos se mapean a las propiedades del objeto Producto utilizando un BeanWrapperFieldSetMapper.
 */

@Component
public class CustomReader extends FlatFileItemReader<Producto> {

    public CustomReader() {
        setResource(new ClassPathResource("datos_entrada.csv"));
        setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(";");
        tokenizer.setNames("id", "producto", "marca", "precio", "moneda");

        BeanWrapperFieldSetMapper<Producto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Producto.class);

        DefaultLineMapper<Producto> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        setLineMapper(lineMapper);
    }
}
