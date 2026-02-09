package desafio_picpay.com.dto.transacao;

public record AutorizacaoResponse(String status, Data data) {

    public record Data(boolean authorization){}

    public boolean isAuthorized(){
        return data != null && data.authorization();
    }
}
