package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.CartaoDeCredito;
import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CartaoDeCreditoRepository;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.ClienteRepository;
import br.com.mamute.cotacaoapi.repository.CompraRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class UsuarioMedotoPagamentoService {
		
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;	
	@Autowired private listaDesejosRepository desejosRepository;
	@Autowired private ClienteRepository clienteRepository;
	@Autowired private CartaoDeCreditoRepository cartaoDeCreditoRepository;
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private CompraRepository compraRepository;
		
	public ModelAndView MedotoPagamento(CartaoDeCredito cartaoDeCredito) {
		ModelAndView mvMedotoPagamento = new ModelAndView("ecommerce/usuario-metodo-pagamento");
		mvMedotoPagamento.addObject("cliente", new Cliente());
		mvMedotoPagamento.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvMedotoPagamento.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvMedotoPagamento.addObject("comprasRealizadas", compraRepository.listarCompras(usuarioService.ClienteLogado().getId()));
		mvMedotoPagamento.addObject("pedido",carrinhoService.compra);
		mvMedotoPagamento.addObject("departamentos", departamentoRepository.findAll());
		mvMedotoPagamento.addObject("categorias", categoriaRepository.findAll());
		mvMedotoPagamento.addObject("compras", carrinhoService.listaCompras);
		mvMedotoPagamento.addObject("cartao", cartaoDeCredito);
		mvMedotoPagamento.addObject("desejos", desejosRepository.findAll());
		return mvMedotoPagamento;
    }

	public String registrar(Cliente cliente) {
		cartaoDeCreditoRepository.saveAndFlush(cliente.getCartao());
		Cliente clienteLogado = usuarioService.ClienteLogado();
		clienteLogado.setCartao(cliente.getCartao());		
		clienteRepository.saveAndFlush(clienteLogado);

		return "redirect:/usuario/metodo-pagamento";
	}	
}
