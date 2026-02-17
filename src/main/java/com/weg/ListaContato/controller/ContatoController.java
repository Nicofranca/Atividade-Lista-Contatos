package com.weg.ListaContato.controller;

import com.weg.ListaContato.model.Contato;
import com.weg.ListaContato.repository.ContatoRepository;
import com.weg.ListaContato.service.ContatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/listacontatos")
@RestController
public class ContatoController {

    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService){
        this.contatoService = contatoService;
    }

    @PostMapping
    public Contato save(@RequestBody Contato contato){
        return contatoService.save(contato);
    }

    @GetMapping
    public List<Contato> findAll(){
        return contatoService.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Contato contato){
        contatoService.update(contato, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        contatoService.delete(id);
    }
}
