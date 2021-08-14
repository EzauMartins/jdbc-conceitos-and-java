package com.digitalInovationEveris.bootcamp.jdbcconceitosConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    // DAO - DataBaseObject // Classe que acessa o banco

    //Consulta Gerar Lista Aluno de tabela | Select * from
    public List<Aluno> list() {
        //preparar lista que retona aluno pós consulta no bnaco

        List<Aluno> alunos = new ArrayList<>();

        try (Connection conect = ConnectionFactory.getConnection()) {
            PreparedStatement prep = conect.prepareStatement("select * from aluno");
            ResultSet rs = prep.executeQuery();      // executa query do select a cima e insere no resultset(rs)

            while (rs.next()) {   //Cursor percorre o select
                Aluno aluno = new Aluno(rs.getInt("Id")
                        , rs.getString("Nome")
                        , rs.getInt("Idade")
                        , rs.getString("Estado"));
                alunos.add(aluno);
            }
        } catch (Exception e) {

        }

        return alunos;


    }

    // Consulta DE aluno por id WHERE
    public Aluno getById(int id) {
        Aluno aluno = new Aluno();

        try (Connection conect = ConnectionFactory.getConnection()) {

            String sql = "select * from aluno where id = ?"; //Consulta
            PreparedStatement stmt = conect.prepareStatement(sql);  // Prepara Statement com os parametros
            stmt.setInt(1, id); // O numero 1 indica a posição da primeira "?"| o id é o valor passado no metodo

            ResultSet rs = stmt.executeQuery();

            //Guarda Valores retonados da tabela no objeto Aluno
            if (rs.next()) {
                aluno.setId(rs.getInt("Id"));           // Insere na LIST aluno os valores da query
                aluno.setNome(rs.getString("Nome"));    // que foram inseridos no REsultSet  "rs"
                aluno.setIdade(rs.getInt("Idade"));     // após a execução
                aluno.setEstado(rs.getString("Estado"));
            }


        } catch (SQLException e) {
            System.out.println("Listagem Falhou");
            e.printStackTrace();
        }

        return aluno;
    }

    // INSERT -- Inserindo Aluno/Registro
    public void create(Aluno aluno) {
        try (Connection conect = ConnectionFactory.getConnection()) {

            String sql = "INSERT INTO aluno(nome,idade,estado) VALUES(?,?,?)";

            PreparedStatement prepState = conect.prepareStatement(sql); //Prepara a query de inserção

            prepState.setString(1, aluno.getNome());
            prepState.setInt(2, aluno.getIdade());
            prepState.setString(3, aluno.getEstado());

            int rowsAfected = prepState.executeUpdate();

            System.out.println("Inserção bem sucedida! " + rowsAfected + " Linhas afetadas");

        } catch (SQLException e) {
            System.out.println("Insercao Falhou");
            e.printStackTrace();
        }
    }

    // Deletar
    public void delete(int id) {
        try (Connection conect = ConnectionFactory.getConnection()) {
            String sql = "delete from aluno where id = ?";
            PreparedStatement PreState = conect.prepareStatement(sql); // prepara o sql
            PreState.setInt(1, id); //para os valores para a interrogação a partir do id (1 posição da '?')

            int RowAffecteds = PreState.executeUpdate(); // Executa e retorna linhas afetadas

            System.out.println("Delete Complet Linhas Afetadas = " + RowAffecteds);


        } catch (SQLException e) {
            System.out.println("Delete Falhou");
            e.printStackTrace();
        }

    }

    //Atualizar

    public void update(Aluno aluno){
        try (Connection conect = ConnectionFactory.getConnection()) {
            String sql = "Update aluno Set nome = ? , idade = ? , estado = ? Where id = ?";
            PreparedStatement StatePrepare = conect.prepareStatement(sql);

            StatePrepare.setString(1,aluno.getNome());
            StatePrepare.setInt(2,aluno.getIdade());
            StatePrepare.setString(3,aluno.getEstado());
            StatePrepare.setInt(4,aluno.getId());

            Integer RowsAffecteds = StatePrepare.executeUpdate();

            System.out.println("Update OK!. Linhas Afetadas: "+RowsAffecteds);


        } catch (SQLException e) {
            System.out.println("Listagem Falhou");
            e.printStackTrace();
        }

    }
}
