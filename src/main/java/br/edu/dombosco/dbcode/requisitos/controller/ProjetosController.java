package br.edu.dombosco.dbcode.requisitos.controller;

/**
 *
 * @author user
 */
public class ProjetosController {
    
private String titulo;
    private String descricao;
    private String parcipantes;
    private String datainicio;
    private String datafim;
    private String estimativas;

    public ProjetosController(){
        titulo = "";
        descricao = "";
        parcipantes = "";
        datainicio = "";
        datafim = "";
        estimativas = "";
    }
    public void salvar(String titulo, String descricao, String parcipantes, String datainicio, String datafim, String estimativas){
        this.titulo = titulo;
        this.descricao = descricao;
        this.parcipantes = parcipantes;
        this.datainicio = datainicio;
        this.datafim = datafim;
        this.estimativas = estimativas;
    }
    
}
