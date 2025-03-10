package com.alejobasilio.batch2.Config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.alejobasilio.batch2.model.Producto;
import com.alejobasilio.batch2.model.ProductoDtoLG;
import com.alejobasilio.batch2.preocessor.CustomProcessor;
import com.alejobasilio.batch2.preocessor.CustomProcessor2;
import com.alejobasilio.batch2.preocessor.CustomProcessorBBDD;
import com.alejobasilio.batch2.reader.CustomReader;
import com.alejobasilio.batch2.writer.CustomWriteBBDD;
import com.alejobasilio.batch2.writer.CustomWriter;
import com.alejobasilio.batch2.writer.CustomWriter2;

/**
 *  Clase de configuración para la aplicación de procesamiento por lotes.
 * 
 * 
 * @author Alejo Basilio Alfonso
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class BatchConfig {

	 /**
     * Crea un objeto DataSource que se utiliza para conectarse a la base de datos.
     * 
     * @return el objeto DataSource configurado.
     */
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/batch");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("Lahojarota_1324");
		return dataSourceBuilder.build();
	}

	 /**
     * Crea un objeto Job que se utiliza para definir el flujo de trabajo de la aplicación de procesamiento por lotes.
     * 
     * @param jobRepository el repositorio de trabajos que se utiliza para almacenar la información de los trabajos.
     * @param transactionManager el administrador de transacciones que se utiliza para gestionar las transacciones de la base de datos.
     * @return el objeto Job configurado.
     */
	@Bean
	Job createJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("job", jobRepository).flow(stepProductos(jobRepository, transactionManager))
				.next(stepProductosLG(jobRepository, transactionManager))
				.next(createStepBBDD(jobRepository, transactionManager))
				.end().build();
	}

	  /**
     * Crea un objeto Step que se utiliza para definir el primer paso del flujo de trabajo (Guardar productos).
     * 
     * @return el objeto Step configurado.
     */
	@Bean
	Step stepProductos(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step", jobRepository).<Producto, Producto>chunk(1, transactionManager)
				.allowStartIfComplete(true).reader(new CustomReader()).processor(new CustomProcessor())
				.writer(new CustomWriter()).build();
	}

	  /**
     * Crea un objeto Step que se utiliza para definir el segundo paso del flujo de trabajo (Guardar productos de LG).
     * 
     * @return el objeto Step configurado.
     */
	@Bean
	Step stepProductosLG(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("otroStep", jobRepository).<Producto, ProductoDtoLG>chunk(1, transactionManager)
				.allowStartIfComplete(true).reader(new CustomReader()).processor(new CustomProcessor2())
				.writer(new CustomWriter2()).build();
	}

	  /**
     * Crea un objeto Step que se utiliza para definir el tercer paso del flujo de trabajo (Guardar Productos en BBDD).
     * 
     *  @return el objeto Step configurado.
     */
	@Bean
	Step createStepBBDD(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	    CustomWriteBBDD customWriteBBDD = new CustomWriteBBDD(dataSource());
	    return new StepBuilder("StepBBDD", jobRepository)
	            .<Producto, Producto> chunk(1, transactionManager)
	            .allowStartIfComplete(true)
	            .reader(new CustomReader())
	            .processor(new CustomProcessorBBDD())
	            .writer(new CustomWriteBBDD(dataSource()).writer())
	            .build();

	}
}
