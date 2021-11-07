package com.algaworks.algalog.domain.exceptions;

public class NegocioExeception extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NegocioExeception(String mensagem){
        super(mensagem);
    }
}
