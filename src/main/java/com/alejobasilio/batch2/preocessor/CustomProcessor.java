package com.alejobasilio.batch2.preocessor;

import org.springframework.batch.item.ItemProcessor;

import com.alejobasilio.batch2.model.Producto;

public class CustomProcessor implements ItemProcessor<Producto,Producto>{

	  /**
     * Método que procesa un objeto Producto y devuelve el objeto procesado o null si no se cumple la condición.
     * 
     * @param item el objeto Producto a procesar
     * @return el objeto Producto procesado o null si la moneda no es "Euro"
     * @throws Exception si ocurre un error durante el procesamiento
     */
	@Override
	public Producto process(Producto item) throws Exception {
		if (item.getMoneda().equals("Euro")) {
            return item;
        } else {
            return null;
        }
	}

}
