package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.TaxaEntregaRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class EnvioService {

	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private TaxaEntregaRepository taxaEntregaRepository;
		
	public ModelAndView envio() {
		ModelAndView mvEnvio = new ModelAndView("ecommerce/envio");
		mvEnvio.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvEnvio.addObject("cliente", new Cliente());
		mvEnvio.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvEnvio.addObject("taxas", taxaEntregaRepository.findAll());
		mvEnvio.addObject("pedido",carrinhoService.compra);
		mvEnvio.addObject("departamentos", departamentoRepository.findAll());
		mvEnvio.addObject("categorias", categoriaRepository.findAll());
		mvEnvio.addObject("compras", carrinhoService.listaCompras);
		return mvEnvio;
    }	
}
