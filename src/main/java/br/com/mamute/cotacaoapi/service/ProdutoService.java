package br.com.mamute.cotacaoapi.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Categoria;
import br.com.mamute.cotacaoapi.model.Desconto;
import br.com.mamute.cotacaoapi.model.Imposto;
import br.com.mamute.cotacaoapi.model.Lucro;
import br.com.mamute.cotacaoapi.model.Marca;
import br.com.mamute.cotacaoapi.model.Produto;
import br.com.mamute.cotacaoapi.model.UnidadeDeMedida;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DescontoRepository;
import br.com.mamute.cotacaoapi.repository.ImpostoRepository;
import br.com.mamute.cotacaoapi.repository.LucroRepository;
import br.com.mamute.cotacaoapi.repository.MarcaRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;
import br.com.mamute.cotacaoapi.repository.UnidadeDeMedidaRepository;

@Service
public class ProdutoService {

	private static String caminhoDaImagem = "/cotacao_api/produto/";
	
	@Autowired
	private ImpostoRepository impostoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private LucroRepository lucroRepository;
	
	@Autowired
	private DescontoRepository descontoRepository;
	
	@Autowired
	private UnidadeDeMedidaRepository unidadeDeMedidaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ModelAndView form(Produto produto, MultipartFile arquivo, Imposto imposto,
		Categoria categoria, Marca marca, Lucro lucro, Desconto desconto, UnidadeDeMedida unidadeDeMedida) {
		
    	ModelAndView mvForm = new ModelAndView("dashboard-admin/produto/form-registrar-produto");
		mvForm.addObject("produto", produto);
		mvForm.addObject("arquivo", arquivo);
		mvForm.addObject("margem", lucro);
		mvForm.addObject("desconto", desconto);
		mvForm.addObject("impostos", impostoRepository.findAll());
		mvForm.addObject("categorias", categoriaRepository.findAll());
		mvForm.addObject("marcas", marcaRepository.findAll());
		mvForm.addObject("descontos", descontoRepository.findAll());
		mvForm.addObject("lucros", lucroRepository.findAll());
		mvForm.addObject("unidadeDeMedidas", unidadeDeMedidaRepository.findAll());
		return mvForm;
    }
	
	public ModelAndView salvar(Produto produto, @RequestParam("file") MultipartFile arquivo, Imposto imposto,
		Categoria categoria, Marca marca,Lucro margem, Desconto desconto, UnidadeDeMedida unidadeDeMedida,
		BindingResult result, RedirectAttributes attributes){	

		produto.setImagem(arquivo.getOriginalFilename());

		for (Desconto d : descontoRepository.findAll()) 
			if(d.getPorcentage().doubleValue() == desconto.getPorcentage().doubleValue() )				
				produto.setDesconto(d);
			
		for (Lucro lucro : lucroRepository.findAll()) 		
			if(lucro.getPorcentagem().doubleValue() == margem.getPorcentagem().doubleValue()) 
				produto.setLucro(lucro);
		 
		produto.setPrecoVenda(Double.parseDouble(produto.getPrecoFinal()));
		
		if(result.hasErrors()) 
			form(produto, arquivo, imposto, categoria, marca, margem, desconto, unidadeDeMedida);	
		
		if(produto.getNome() == null || produto.getNome().isBlank()) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos ou vazio.");	
			return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
		}		
		
		try {	
			
			Random random = new Random();
			float num = random.nextFloat();
			
			if(!arquivo.isEmpty() && produto.getId() == null) {
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoDaImagem+String.valueOf(num)+arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				produto.setImagem(String.valueOf(num)+arquivo.getOriginalFilename());
			}
			
			for (Produto p : produtoRepository.findAll()) {
				if (p.getId() == produto.getId()) {					
					produtoRepository.saveAndFlush(produto);
					attributes.addFlashAttribute("icone", "thumb_up");
					attributes.addFlashAttribute("menssagem", "Produto alterada com sucesso.");	
					return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
				}		
			}			
			
			produtoRepository.saveAndFlush(produto);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Produto salva com sucesso.");	
			return new ModelAndView("redirect:/dashboard-admin/produto/registrar");			
		
		//} catch (Exception e) {
			
			//attributes.addFlashAttribute("icone", "thumb_down");
			//attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
		//	return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		}
	
	public ModelAndView editado(Produto produto, Imposto imposto, Categoria categoria, Marca marca,
			Lucro margem, Desconto desconto, UnidadeDeMedida unidadeDeMedida, BindingResult result, RedirectAttributes attributes){	
		
			for (Desconto d : descontoRepository.findAll()) 
				if(d.getPorcentage().doubleValue() == desconto.getPorcentage().doubleValue())				
					produto.setDesconto(d);
				
			for (Lucro lucro : lucroRepository.findAll()) 		
				if(lucro.getPorcentagem().doubleValue() == margem.getPorcentagem().doubleValue()) 
					produto.setLucro(lucro);
			
			produto.setPrecoVenda(Double.parseDouble(produto.getPrecoFinal()));
			
			if(produto.getNome() == null || produto.getNome().isBlank()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Campos não podem ser nulos ou vazio.");	
				return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
			}		
			
			try {				
				
				for (Produto p : produtoRepository.findAll()) 
					if (p.getId() == produto.getId()) {	
						produto.setImagem(p.getImagem());
						produtoRepository.saveAndFlush(produto);
						attributes.addFlashAttribute("icone", "thumb_up");
						attributes.addFlashAttribute("menssagem", "Produto alterada com sucesso.");	
						return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
					}				
				
				produtoRepository.saveAndFlush(produto);
				attributes.addFlashAttribute("icone", "thumb_up");
				attributes.addFlashAttribute("menssagem", "Produto salva com sucesso.");	
				return new ModelAndView("redirect:/dashboard-admin/produto/registrar");			
			
			//} catch (Exception e) {
				
				//attributes.addFlashAttribute("icone", "thumb_down");
				//attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados.");	
			//	return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
				
			}catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
			}
	}		
	
	public ModelAndView listar(RedirectAttributes attributes) {	
		
		List<Produto> produtos = produtoRepository.findAll();
		try {
			if(produtos.size() == 0) {			
				attributes.addFlashAttribute("icone", "visibility_off");
				attributes.addFlashAttribute("menssagem", "No momento a lista está vazia, realize um registro!");
				return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
			}
			
			ModelAndView mvLista = new ModelAndView("dashboard-admin/produto/lista-produto");
			return mvLista.addObject("produtos", produtos);
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Produto não pode ser deletada!");
			return new ModelAndView("redirect:/dashboard-admin/produto/registrar");
		}
	}
	
	public ModelAndView formEditar(Produto produto, String imagem, Imposto imposto,
			Categoria categoria, Marca marca, Lucro lucro, Desconto desconto, UnidadeDeMedida unidadeDeMedida) {
			
	    	ModelAndView mvForm = new ModelAndView("dashboard-admin/produto/form-registrar-produto");
			mvForm.addObject("produto", produto);
			mvForm.addObject("imagem", imagem);
			mvForm.addObject("margem", lucro);
			mvForm.addObject("desconto", desconto);
			mvForm.addObject("impostos", impostoRepository.findAll());
			mvForm.addObject("categorias", categoriaRepository.findAll());
			mvForm.addObject("marcas", marcaRepository.findAll());
			mvForm.addObject("descontos", descontoRepository.findAll());
			mvForm.addObject("lucros", lucroRepository.findAll());
			mvForm.addObject("unidadeDeMedidas", unidadeDeMedidaRepository.findAll());
			return mvForm;
	    }
	
	public ModelAndView editar(Long id, String imagem, Imposto imposto, Categoria categoria,
			Marca marca,Lucro margem, Desconto desconto, UnidadeDeMedida unidadeDeMedida,
			BindingResult result, RedirectAttributes attributes){
		
		Optional<Produto> produto = produtoRepository.findById(id);
		try {	
			if(produto.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/produto/listar");
			}
			return formEditar(produto.get(), produto.get().getImagem(), imposto, categoria, marca, margem, desconto, unidadeDeMedida);
						
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/produto/listar");
		}
	}
	
	public ModelAndView deletar(Long id, RedirectAttributes attributes) {		
		
		try {
			if(produtoRepository.findById(id).isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/produto/listar");
			}				
			
			produtoRepository.deleteById(id);
			attributes.addFlashAttribute("icone", "thumb_up");
			attributes.addFlashAttribute("menssagem", "Produto deletada!");
			return new ModelAndView("redirect:/dashboard-admin/produto/listar");
			
			
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Produto não pode ser deletado!");
			return new ModelAndView("redirect:/dashboard-admin/produto/listar");
		}		
	}

	public byte[] visualizar(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagem+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

	public ModelAndView descricao(Long id, RedirectAttributes attributes) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		try {	
			if(produto.isEmpty()) {
				attributes.addFlashAttribute("icone", "thumb_down");
				attributes.addFlashAttribute("menssagem", "Erro, id inesistente!");
				return new ModelAndView("redirect:/dashboard-admin/produto/listar");
			}
			
			ModelAndView mvDesc = new ModelAndView("dashboard-admin/produto/descricao-produto");
			return mvDesc.addObject("produto", produto.get());
						
		} catch (Exception e) {
			attributes.addFlashAttribute("icone", "thumb_down");
			attributes.addFlashAttribute("menssagem", "Erro ao estabelecer contato com o banco de dados!");
			return new ModelAndView("redirect:/dashboard-admin/produto/listar");
		}
	}
}
