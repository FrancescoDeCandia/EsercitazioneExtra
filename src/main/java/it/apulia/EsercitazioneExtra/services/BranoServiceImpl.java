package it.apulia.EsercitazioneExtra.services;

import it.apulia.EsercitazioneExtra.errors.MyNotFoundException;
import it.apulia.EsercitazioneExtra.model.Brano;
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
        log.info("Ecco la lista di tutti i brani presenti!");
        return branoRepository.findAll();
    }

    // SAVE BRANO DA OTTIMIZZARE CON UN CHECK SULLA GIA' ESISTENZA NEL DATABASE (PER AUTORE E TITOLO)
    @Override
    public void saveBrano(Brano brano) {
            branoRepository.save(brano);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/songManager/ricerca/" + brano.getAutore() + "/" + brano.getTitolo()).toUriString());

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
