package it.apulia.EsercitazioneExtra.repository;

import it.apulia.EsercitazioneExtra.model.Brano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranoRepository extends JpaRepository<Brano,Long> {

    Brano findBranoByAutoreAndTitolo(String autore,String titolo);
    void deleteBranoByAutoreAndTitolo(String autore, String titolo);

    // (NATIVE QUERY = TRUE) CONSENTE DI SCRIVERE QUERY IN LINGUAGGIO PROPRIO SQL
    // PIUTTOSTO CHE CON SINTASSI JPA (UTILIZZO DI ALIAS, DOT NOTATION E ?1
    @Query(value = "SELECT * FROM Brano ORDER BY voto DESC", nativeQuery = true)
    List<Brano> findBranoByVoto();

    @Query(value = "SELECT * FROM Brano ORDER BY autore ASC", nativeQuery = true)
    List<Brano> findBranoByAutore();

    @Query(value = "SELECT * FROM Brano ORDER BY titolo ASC", nativeQuery = true)
    List<Brano> findBranoByTitolo();

}
