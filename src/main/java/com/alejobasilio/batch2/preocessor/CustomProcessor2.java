package com.alejobasilio.batch2.preocessor;

import org.springframework.batch.item.ItemProcessor;

import com.alejobasilio.batch2.model.Producto;
import com.alejobasilio.batch2.model.ProductoDtoLG;

public class CustomProcessor2 implements ItemProcessor<Producto, ProductoDtoLG>{

	/**
     * Método que procesa un objeto Producto y devuelve un objeto ProductoDtoLG con la información procesada o null si no se cumple la condición.
     * 
     * @param item el objeto Producto a procesar
     * @return el objeto ProductoDtoLG procesado o null si la marca no es "LG" o la moneda no es "Euro", "Dollar" o "Pound"
     * @throws Exception si ocurre un error durante el procesamiento
     */
	@Override
	public ProductoDtoLG process(Producto item) throws Exception {
		if (item.getMarca().equals("LG") && (item.getMoneda().equals("Euro") || item.getMoneda().equals("Dollar") || item.getMoneda().equals("Pound"))) {
			ProductoDtoLG producto = new ProductoDtoLG();
			Double alteracion = 0.0;
            if(item.getMoneda().equals("Euro")) {
            	alteracion = (item.getPrecio()*20) / 100;
            	producto.setAleteracion_precio(alteracion);
            	producto.setPrecio_final(item.getPrecio() + alteracion);
            	
            }else if (item.getMoneda().equals("Dollar")) {
            	alteracion = (item.getPrecio()*15) / 100;
            	producto.setAleteracion_precio(alteracion);
            	producto.setPrecio_final(item.getPrecio() - alteracion);
            	
			}else if (item.getMoneda().equals("Pound")) {
				alteracion = (item.getPrecio()*5) / 100;
				if (item.getPrecio()<30) {
					producto.setAleteracion_precio(alteracion);
	            	producto.setPrecio_final(item.getPrecio() + alteracion);
				}else if (item.getPrecio()<80) {
					producto.setAleteracion_precio(alteracion);
	            	producto.setPrecio_final(item.getPrecio() - alteracion);
				}
			}
            producto.setId(item.getId());
            producto.setPrecio_inicial(item.getPrecio());
            producto.setMoneda(item.getMoneda());
            producto.setProducto(item.getProducto());
            
            return producto;
            
        } else {
            return null;
        }
	}

}
