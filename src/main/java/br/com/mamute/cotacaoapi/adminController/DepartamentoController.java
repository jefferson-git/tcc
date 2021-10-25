package br.com.mamute.cotacaoapi.adminController;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Departamento;
import br.com.mamute.cotacaoapi.service.DepartamentoService;

@Controller
@RequestMapping("/dashboard-admin/departamento")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
    @GetMapping("/registrar")
	ModelAndView form(Departamento departamento, MultipartFile arquivo) {
		return departamentoService.form(departamento, arquivo);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(@Valid Departamento departamento, @RequestParam MultipartFile file,BindingResult result,RedirectAttributes attributes) {
		return departamentoService.salvar(departamento, file , result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return departamentoService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return departamentoService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return departamentoService.deletar(id, attributes);
	}
	
	@ResponseBody
	@GetMapping("/visualizar/{imagem}")
	byte[] visualizar(@PathVariable(name = "imagem") String imagem) throws IOException{
		return departamentoService.visualizar(imagem);
	}
}
