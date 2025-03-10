package com.alejobasilio.batch2.preocessor;

import org.springframework.batch.item.ItemProcessor;

import com.alejobasilio.batch2.model.Producto;

/**
 * Método que procesa un objeto Producto y devuelve un objeto Producto para insertarlo en la BBDD.
 * 
 * @param item el objeto Producto a procesar
 * @return el objeto Producto
 */
public class CustomProcessorBBDD implements ItemProcessor<Producto, Producto> {

	@Override
	public Producto process(Producto item) throws Exception {

		return item;
	}

}
