package it.apulia.EsercitazioneExtra.repository;

import it.apulia.EsercitazioneExtra.model.Brano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUtente extends JpaRepository<Brano, String> {

    Brano findByUsername(String username);
    Boolean existsByUsername(String username);
}
