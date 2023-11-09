package br.edu.dombosco.dbcode.requisitos.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class RequisitosController {
    private int id;
    private String nome;
    private String qualificacao;
    private String descricao;
    private String file_especificacao;
    private String file_desenho;

    public RequisitosController(){
        this.id = 0;
        this.nome = "";
        this.qualificacao = "";
        this.descricao = "";
        this.file_especificacao = "";
        this.file_desenho = "";
    }

    public void salvar(int id, String nome, String qualificacao, String descricao, String fileEspecificacao, String fileDesenho) {
        this.id = id;
        this.nome = nome;
        this.qualificacao = qualificacao;
        this.descricao = descricao;
        this.file_especificacao = fileEspecificacao;
        this.file_desenho = fileDesenho;
    }

    public void editar(int id, String nome, String qualificacao, String descricao, String fileEspecificacao, String fileDesenho) {
        this.id = id;
        this.nome = nome;
        this.qualificacao = qualificacao;
        this.descricao = descricao;
        this.file_especificacao = fileEspecificacao;
        this.file_desenho = fileDesenho;
    }
    
    
}
