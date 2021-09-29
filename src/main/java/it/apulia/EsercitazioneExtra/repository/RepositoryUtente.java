package it.apulia.EsercitazioneExtra.repository;

import it.apulia.EsercitazioneExtra.accessManager.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUtente extends JpaRepository<Utente, Long> {

    Utente findByUsername(String username);
    Boolean existsByUsername(String username);
}
