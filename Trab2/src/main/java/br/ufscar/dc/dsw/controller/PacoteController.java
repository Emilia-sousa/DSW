package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@Controller
@RequestMapping("/pacotes")
public class PacoteController {

	@Autowired
	private IPacoteService pacoteService;

	@Autowired
	private IAgenciaService agenciaService;

	@GetMapping("/cadastrar")
	public String cadastrar(ModelMap model) {
        PacoteTuristico pacote = new PacoteTuristico();
        model.addAttribute("pacote", pacote);
		return "pacote/cadastro";
	}


	@RequestMapping(path = {"/search"})
	public String listar(PacoteTuristico pacoteturistico, ModelMap model, String keyword) {
		if(keyword!=null) {
			List<PacoteTuristico> list = pacoteService.getByKeyword(keyword);
			model.addAttribute("pacotes", list);
		}else {
			model.addAttribute("pacotes", pacoteService.buscarTodos());
		}
		return "pacote/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid PacoteTuristico pacote, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "pacote/cadastro";
		}

		pacoteService.salvar(pacote);
		attr.addFlashAttribute("sucess", "pacote.create.sucess");
		return "redirect:/pacotes/search";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("pacote", pacoteService.buscarPorId(id));
		return "pacote/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid PacoteTuristico pacote, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "pacote/cadastro";
		}

		pacoteService.salvar(pacote);
		attr.addFlashAttribute("sucess", "pacote.edit.sucess");
		return "redirect:/pacotes/search";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		pacoteService.excluir(id);
		attr.addFlashAttribute("sucess", "pacote.delete.sucess");
		return "redirect:/pacotes/search";
	}

	@ModelAttribute("agencias")
	public List<Agencia> listaAgencias() {
		return agenciaService.buscarTodos();
	}
	
}
