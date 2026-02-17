package com.weg.ListaContato.service;

import com.weg.ListaContato.model.Contato;
import com.weg.ListaContato.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository){
        this.contatoRepository = contatoRepository;
    }

    public Contato save(Contato contato){
        try {
            return contatoRepository.save(contato);

        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contato> findAll(){
        try {
            return contatoRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Contato contato, int id){

        contato.setId(id);

        try {
            contatoRepository.update(contato);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try {
            contatoRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
