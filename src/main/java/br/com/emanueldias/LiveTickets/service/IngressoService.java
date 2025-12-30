package br.com.emanueldias.LiveTickets.service;


import br.com.emanueldias.LiveTickets.dto.IngressoRequestDTO;
import br.com.emanueldias.LiveTickets.dto.IngressoResponseDTO;
import br.com.emanueldias.LiveTickets.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    public Flux<IngressoResponseDTO> getIngressosFromEventoId(Long id) {
        return ingressoRepository
                .findByEventoId(id)
                .switchIfEmpty(Mono.empty())
                .map(IngressoResponseDTO::toDto);
    }

    public Mono<IngressoResponseDTO> createIngresso(IngressoRequestDTO dto) {
        return ingressoRepository.save(dto.toEntity())
                .map(IngressoResponseDTO::toDto);
    }

    public Mono<IngressoResponseDTO> saleIngresso(Long id) {
        return ingressoRepository.decrementIfAvailable(id)
                .flatMap(rows -> {
                    if (rows == 0) {
                        return Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Ingresso esgotado"
                        ));
                    }
                    return ingressoRepository.findById(id);
                })
                .map(IngressoResponseDTO::toDto);
    }


}
