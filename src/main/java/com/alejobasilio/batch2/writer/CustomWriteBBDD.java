package com.alejobasilio.batch2.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.alejobasilio.batch2.model.Producto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Clase que se utiliza para escribir objetos de tipo Producto en la base de datos.
 */
@Component
public class CustomWriteBBDD {

	/**
     * Fuente de datos que se utiliza para conectarse a la base de datos.
     */
	private final DataSource dataSource;

	
	public CustomWriteBBDD(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    /**
     * Método que devuelve un JdbcBatchItemWriter configurado para escribir objetos de tipo Producto en la base de datos.
     * 
     * @return un JdbcBatchItemWriter configurado para escribir objetos de tipo Producto en la base de datos.
     */
	public JdbcBatchItemWriter<Producto> writer() {
	    JdbcBatchItemWriter<Producto> writer = new JdbcBatchItemWriter<>();
	    writer.setItemPreparedStatementSetter(new CustomItemPreparedStatementSetter());
	    writer.setSql("INSERT INTO productos (id, producto, marca, precio, moneda) VALUES (?, ?, ?, ?, ?)");
	    writer.setDataSource(dataSource);
	    return writer;
	}
	
	/**
     * Clase interna que implementa la interfaz ItemPreparedStatementSetter para establecer los valores de los parámetros en la sentencia SQL.
     */
	public class CustomItemPreparedStatementSetter implements ItemPreparedStatementSetter<Producto> {
	    @Override
	    public void setValues(Producto item, PreparedStatement ps) throws SQLException {
	        ps.setLong(1, item.getId());
	        ps.setString(2, item.getProducto());
	        ps.setString(3, item.getMarca());
	        ps.setDouble(4, item.getPrecio());
	        ps.setString(5, item.getMoneda());
	    }
	    }

}
