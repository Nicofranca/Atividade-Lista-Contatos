package com.weg.ListaContato.repository;

import com.weg.ListaContato.model.Contato;
import com.weg.ListaContato.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContatoRepository {
    public Contato save(Contato contato) throws SQLException{
        String query = """
                INSERT INTO 
                Contato
                ( nome
                ,telefone) 
                VALUES
                (?, ?) 
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()){
                    contato.setId(rs.getInt(1));
                    return contato;
                }
            }
        }

        throw new RuntimeException("Erro ao cadastrar");

    }

    public List<Contato> findAll() throws SQLException {
        String query = """
                SELECT 
                id
                , nome
                , telefone 
                FROM 
                Contato
                """;

        List<Contato> listContatos = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");

                    Contato contato = new Contato(id, nome, telefone);
                    listContatos.add(contato);

                }
                return listContatos;
            }
        }

    }

    public void update(Contato contato) throws SQLException{
        String query = """
                UPDATE 
                Contato 
                SET nome = ?, telefone = ?
                 WHERE 
                 id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setInt(3, contato.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException{
        String query = """
                DELETE 
                FROM 
                Contato 
                WHERE 
                id = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }

    }
}


