package br.com.mamute.cotacaoapi.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConverterStringDouble implements Converter<String, Double>{

	@Override
	public Double convert(String salario) {
		salario = salario.trim();
		if(salario.length() > 0) 
			return  Double.parseDouble(salario.replace(".", "").replace(",", "."));			
		
		return 0.;
	}

}
