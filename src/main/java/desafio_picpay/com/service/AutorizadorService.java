package desafio_picpay.com.service;

import desafio_picpay.com.dto.transacao.AutorizacaoResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Log4j2
public class AutorizadorService {

    private final WebClient webClient;

    public AutorizadorService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://util.devi.tools").build();
    }

    public boolean autorizarTransacao() {

        try {
            AutorizacaoResponse response = webClient
                    .get()
                    .uri("/api/v2/authorize")
                    .retrieve()
                    .bodyToMono(AutorizacaoResponse.class)
                    .block();

            return response != null
                    && response.data() != null
                    && response.data().authorization();

        } catch (WebClientResponseException e) {
            log.debug("Autorizador retornou status: {}", e.getStatusCode());
            return false;
        }

    }
}

