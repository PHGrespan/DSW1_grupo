package br.ufscar.dc.dsw.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.service.spec.IAgenciaService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@RestController
public class PacoteRestController {
    
	@Autowired
	private IPacoteService service;

    @Autowired
    private IAgenciaService agenciaService;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Pacote pacote, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				pacote.setId(((Integer) id).longValue());
			} else {
				pacote.setId(((Long) id));
			}
		}

		pacote.setNome((String) json.get("nome"));
        pacote.setData((String) json.get("date"));
		pacote.setDuracao((Integer) json.get("duracao"));
		Double value = (Double) json.get("preco");
		pacote.setPreco(BigDecimal.valueOf(value));
		pacote.setDescricao((String) json.get("descricao"));
        pacote.setDestinos((String) json.get("destinos"));
        pacote.setFotos((String) json.get("fotos"));

        Integer idAgencia = (Integer) json.get("agencia");
		pacote.setAgencia(agenciaService.buscarPorId((idAgencia).longValue()));
	}

	@GetMapping(path = "/pacotes")
	public ResponseEntity<List<Pacote>> lista() {
		List<Pacote> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/pacotes/{id}")
	public ResponseEntity<Pacote> lista(@PathVariable("id") long id) {
		Pacote pacote = service.buscarPorId(id);
		if (pacote == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pacote);
	}
	
	@PostMapping(path = "/pacotes")
	@ResponseBody
	public ResponseEntity<Pacote> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Pacote pacote = new Pacote();
				parse(pacote, json);
				service.salvar(pacote);
				return ResponseEntity.ok(pacote);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/pacotes/{id}")
	public ResponseEntity<Pacote> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Pacote pacote = service.buscarPorId(id);
				if (pacote == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(pacote, json);
					service.salvar(pacote);
					return ResponseEntity.ok(pacote);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/pacotes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Pacote pacote = service.buscarPorId(id);
		if (pacote == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}