package com.digitalInovationEveris.bootcamp.jdbcconceitosConnection;

import java.util.List;

public class QueriesExecution {
    public static void main(String[] args) {

        AlunoDAO alunoDAO = new AlunoDAO();
        // ===== RETORNA LISTA ALUNO ===================

        List<Aluno> alunos = alunoDAO.list();
        // alunos.stream().forEach(System.out::println);

        //==============================================

        //  ===== RETORNA ALUNO ESPECIFICO WHERE========

        Aluno alunoConsulta = alunoDAO.getById(3);

        // System.out.println(alunoConsulta);

        //==============================================

        //  ====== Inserindo REGISTRO/Aluno================================================

        Aluno insert = new Aluno();      // Cria a classe do aluno com os valores que deseja inserirr
            insert.setNome("Andressa");
            insert.setEstado("AM");
            insert.setIdade(28);

        //alunoDAO.create(insert); //Chama o metodo create do 'alunoDAO', e passa o state 'insert' com os valos inseridos

        // =================================================================================

        //========== Deleta Registro ==============
        alunoDAO.delete(5);

        // =======================================

        //ATUALIZAR ALUNO

        alunoDAO.list().stream().forEach(System.out::println);

        Aluno alunoParaAulizar = alunoDAO.getById(2);
        alunoParaAulizar.setNome("EzauEu");
        alunoParaAulizar.setIdade(25);
        alunoParaAulizar.setEstado("GR");

        alunoDAO.update(alunoParaAulizar);

        alunoDAO.list().stream().forEach(System.out::println);

        }
    }

