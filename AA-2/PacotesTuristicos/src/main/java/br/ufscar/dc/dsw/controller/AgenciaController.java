package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.service.spec.IAgenciaService;

@Controller
@RequestMapping("/agencias")
public class AgenciaController {
    
	@Autowired
	private IAgenciaService service;


	@GetMapping("/cadastrar")
	public String cadastrar(Agencia agencia) {
		return "agencia/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("agencias",service.buscarTodos());
		return "agencia/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Agencia agencia, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "agencia/cadastro";
		}
		
		service.salvar(agencia);
		attr.addFlashAttribute("sucess", "Agencia inserida com sucesso.");
		return "redirect:/agencias/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("agencia", service.buscarPorId(id));
		return "agencia/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Agencia agencia, BindingResult result, RedirectAttributes attr) {
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("cnpj") == null) {
			return "agencia/cadastro";
		}

		service.salvar(agencia);
		attr.addFlashAttribute("sucess", "Agencia editada com sucesso.");
		return "redirect:/agencias/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.temPacotes(id)) {
			model.addAttribute("fail", "Agencia não excluída. Possui pacote(s) vinculado(s).");
		} else {
			service.excluir(id);
			model.addAttribute("sucess", "Agencia excluída com sucesso.");
		}
		return listar(model);
	}
}
