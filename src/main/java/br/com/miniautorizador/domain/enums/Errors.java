package br.com.miniautorizador.domain.enums;

public enum Errors {
    SALDO_INSUFICIENTE("SALDO_INSUFICIENTE"),
    SENHA_INVALIDA("SENHA_INVALIDA"),
    CARTAO_INEXISTENTE("CARTAO_INEXISTENTE");

    private final String value;

    Errors(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
