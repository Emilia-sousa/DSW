package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.math.BigDecimal;
import javax.validation.Valid;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.PacoteTuristico;
import br.ufscar.dc.dsw.service.spec.IAgenciaService;

//import java.io.IOException;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class AgenciaRestController {

    @Autowired
    private IAgenciaService aservice;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
     }

    private void parse(Agencia agencia, JSONObject json) {

        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                agencia.setId(((Integer) id).longValue());
            } else {
                agencia.setId((Long) id);
            }
        }
        agencia.setDescricao((String) json.get("descricao"));
        agencia.setCNPJ((String) json.get("cnpj"));
        agencia.setEmail((String) json.get("email"));
        agencia.setNome((String) json.get("nome"));
        agencia.setPassword((String) json.get("password"));

    }

    @GetMapping(path = "/agencias")
    public ResponseEntity<List<Agencia>> lista() {
        List<Agencia> lista = aservice.buscarTodos();

        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        for (Agencia a : lista) {
            List<PacoteTuristico> pacotes = a.getPacotes();
            for (PacoteTuristico p : pacotes) {
                p.setAgencia(null);
            }
        }


        return ResponseEntity.ok(lista);

    }

    @GetMapping(path = "/agencias/{id}")
    public ResponseEntity<Agencia> lista(@PathVariable("id") long id) {
        Agencia a = aservice.buscarPorId(id);
        if(a == null) {
            return ResponseEntity.notFound().build();
        }

        a.setPassword(null);
        List<PacoteTuristico> pacotes = a.getPacotes();
        for (PacoteTuristico p : pacotes) {
            p.setAgencia(null);
        }
        return ResponseEntity.ok(a);

    }


    @PostMapping(path = "/agencias")
    @ResponseBody
    public ResponseEntity<Agencia> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Agencia agencia = new Agencia();
                parse(agencia, json);

                aservice.salvar(agencia);
                return ResponseEntity.ok(agencia);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @PutMapping(path = "/agencias/{id}")
    public ResponseEntity<Agencia> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Agencia agencia = aservice.buscarPorId(id);
                if(agencia == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(agencia, json);
                    aservice.salvar(agencia);
                    return ResponseEntity.ok(agencia);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
 }

    @DeleteMapping(path = "/agencias/{id}")
 public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

        Agencia agencia = aservice.buscarPorId(id);
        if(agencia == null) {
            return ResponseEntity.notFound().build();
        } else {
            aservice.excluir(id);
            return ResponseEntity.ok(true);
        }
    }
}

