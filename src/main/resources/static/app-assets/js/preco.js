function calcValor(){	  
			  		 
	var V_CUSTO = parseFloat(document.getElementById("custo").value.trim());
    var V_IMPOSTO = parseFloat(document.getElementById("imposto").value.trim());		  
	var V_DESCONTO = parseFloat(document.getElementById("desconto").value.trim());
	var V_LUCRO = parseFloat(document.getElementById("lucro").value.trim());
	
	document.getElementById("margem_lucro").value = V_LUCRO.toFixed(0).trim();
	document.getElementById("margem_desconto").value = V_DESCONTO.toFixed(1).trim();
			  
	var PRECO_VENDA = parseFloat(V_CUSTO + V_CUSTO * V_LUCRO/100);
	var VALOR_DESCONTO = parseFloat(PRECO_VENDA * V_DESCONTO/100);
    PRECO_VENDA = parseFloat(PRECO_VENDA - VALOR_DESCONTO);	  
			  
	document.getElementById("preco_venda").value = PRECO_VENDA.toFixed(5);			  
}