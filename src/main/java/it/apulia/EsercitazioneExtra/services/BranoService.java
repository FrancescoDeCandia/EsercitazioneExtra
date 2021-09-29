package it.apulia.EsercitazioneExtra.services;

import it.apulia.EsercitazioneExtra.model.Brano;
import it.apulia.EsercitazioneExtra.model.BranoDTO;

import java.util.List;

public interface BranoService {

    List<Brano> getAllBrani();
    void saveBrano(Brano brano);
    void updateLibro(Brano brano);
    void deleteLibro(String autore, String titolo);

}
