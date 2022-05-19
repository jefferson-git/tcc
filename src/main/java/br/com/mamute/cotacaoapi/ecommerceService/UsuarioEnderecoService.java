package br.com.mamute.cotacaoapi.ecommerceService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.model.Pessoa;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.CompraRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.EnderecoRepository;
import br.com.mamute.cotacaoapi.repository.PessoaRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class UsuarioEnderecoService {
		
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;	
	@Autowired private listaDesejosRepository desejosRepository;
	@Autowired private UsuarioService usuarioService;
	@Autowired private EnderecoRepository enderecoRepository;
	@Autowired private PessoaRepository pessoaRepository;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private CompraRepository compraRepository;
			
	public ModelAndView endereco() {
		ModelAndView mvEndereco = new ModelAndView("ecommerce/usuario-endereco");
		mvEndereco.addObject("cliente", new Cliente());
		mvEndereco.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvEndereco.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvEndereco.addObject("comprasRealizadas", compraRepository.listarCompras(usuarioService.ClienteLogado().getId()));
		mvEndereco.addObject("pedido",carrinhoService.compra);
		mvEndereco.addObject("departamentos", departamentoRepository.findAll());
		mvEndereco.addObject("categorias", categoriaRepository.findAll());
		mvEndereco.addObject("compras", carrinhoService.listaCompras);
		mvEndereco.addObject("desejos", desejosRepository.findAll());
		return mvEndereco;
    }	
	
	public ModelAndView salvar(Cliente cliente, BindingResult result) {		
		if(result.hasErrors())
			endereco();			
		try {			
			enderecoRepository.save(cliente.getEndereco());			
			Pessoa pessoa = usuarioService.ClienteLogado();
			pessoa.setEndereco(cliente.getEndereco());			
			pessoaRepository.save(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/usuario/endereco");	
    }	
	
}
