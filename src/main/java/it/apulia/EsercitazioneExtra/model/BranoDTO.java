package it.apulia.EsercitazioneExtra.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class BranoDTO {

    String titolo;
    String autore;
    String album;
    Integer anno;
    @Min(1) @Max(10)
    Double voto;

}
