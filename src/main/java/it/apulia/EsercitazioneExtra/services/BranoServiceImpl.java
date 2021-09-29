package it.apulia.EsercitazioneExtra.services;

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
        branoRepository.save(brano);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/songManager/ricerca/" +brano.getAutore() + "/" + brano.getTitolo()).toUriString());

        log.info("Brano {} salvato all'interno della libreria musicale raggiungibile al link {} ",
                brano.getTitolo(), uri.toString());

    }

    @Override
    public void updateLibro(Brano brano) {

    }

    @Override
    public void deleteLibro(String autore, String titolo) {

    }
}
