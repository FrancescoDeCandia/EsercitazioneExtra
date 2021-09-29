package it.apulia.EsercitazioneExtra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Brano {

    @Id
    @SequenceGenerator(
            name = "song_sequence",
            sequenceName = "song_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "song_sequence"
    )

    String branoId;
    String titolo;
    String autore;
    String album;
    Integer anno;
    Double voto;

    public Brano(String titolo, String autore, String album, Integer anno, Double voto) {
        this.titolo = titolo;
        this.autore = autore;
        this.album = album;
        this.anno = anno;
        this.voto = voto;
    }

    public Brano(String autore, String titolo) {
        this.titolo = titolo;
        this.autore = autore;
    }
}
