package it.apulia.EsercitazioneExtra.services;

import it.apulia.EsercitazioneExtra.errors.MyNotAcceptableException;
import it.apulia.EsercitazioneExtra.errors.MyNotFoundException;
import it.apulia.EsercitazioneExtra.model.Brano;
import it.apulia.EsercitazioneExtra.model.BranoDTO;
import it.apulia.EsercitazioneExtra.repository.BranoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BranoServiceImpl implements BranoService {

    private final BranoRepository branoRepository;

    @Override
    public List<Brano> getAllBrani() {
        return branoRepository.findAll();
    }

    @Override
    public void saveBrano(Brano brano) {
        Brano temp = new Brano(brano.getAutore(), brano.getTitolo());
        if (!(temp == branoRepository.findBranoByAutoreAndTitolo(brano.getAutore(), brano.getTitolo()))) {
            branoRepository.save(brano);
                URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/songManager/ricerca/" + brano.getAutore() + "/" + brano.getTitolo()).toUriString());

             throw new MyNotAcceptableException("Il brano " + brano.getTitolo() + " è già presente all'interno del DB");
        }
            log.info("Brano SALVATO con successo!");
    }

    @Override
    public void updateBrano(Brano brano) {
        if(!this.branoRepository.existsById(brano.getBranoId())) {
            throw new MyNotFoundException("Il brano che vuoi modificare non esiste");
        } else
            log.info("Brano AGGIORNATO con successo!");
            this.branoRepository.save(brano);

    }

    @Override
    public void deleteBrano(String autore, String titolo) {
        log.info("Brano CANCELLATO con successo!");
        branoRepository.deleteBranoByAutoreAndTitolo(autore,titolo);

    }

    @Override
    public List<Brano> getBranoByVoto() {
        log.info("Lista dei brani ordinata per voto decrescente");
        return branoRepository.findBranoByVoto();
    }

    @Override
    public List<Brano> getBranoByAutore() {
        log.info("Lista dei brani in ordine alfabetico per autore crescente");
        return branoRepository.findBranoByAutore();
    }

    @Override
    public List<Brano> getBranoByTitolo() {
        log.info("Lista dei brani in ordine alfabetico per titolo crescente");
        return branoRepository.findBranoByTitolo();
    }

}
