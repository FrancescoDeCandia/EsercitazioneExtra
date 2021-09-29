package it.apulia.EsercitazioneExtra.repository;

import it.apulia.EsercitazioneExtra.model.Brano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranoRepository extends JpaRepository<Brano,String> {

    Brano findBranoByAutoreAndTitolo(String autore,String titolo);
    void deleteBranoByAutoreAndTitolo(String autore, String titolo);

    @Query
    List<Brano> findBranoByVoto();
    @Query
    List<Brano> findBranoByAutore();
    @Query
    List<Brano> findBranoByTitolo();

}
