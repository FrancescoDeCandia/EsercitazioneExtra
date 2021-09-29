package it.apulia.EsercitazioneExtra.services;

import it.apulia.EsercitazioneExtra.model.Brano;
import it.apulia.EsercitazioneExtra.model.BranoDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranoService {

    List<Brano> getAllBrani();
    void saveBrano(Brano brano);
    void updateBrano(Brano brano);
    void deleteBrano(String autore, String titolo);

    List<Brano> getBranoByVoto();
    List<Brano> getBranoByAutore();
    List<Brano> getBranoByTitolo();


}
