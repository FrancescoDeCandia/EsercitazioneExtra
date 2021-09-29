package it.apulia.EsercitazioneExtra.controller;

import it.apulia.EsercitazioneExtra.model.Brano;
import it.apulia.EsercitazioneExtra.model.BranoDTO;
import it.apulia.EsercitazioneExtra.services.BranoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/songManager")
public class BranoController {

    private final BranoService branoService;

    @Autowired
    public BranoController(BranoService branoService) {
        this.branoService = branoService;
    }

    @GetMapping("/listaBrani")
    public ResponseEntity<List<Brano>> getBrani(){
        return ResponseEntity.ok().body(branoService.getAllBrani());
    }

    // QUERY: VOTI DAL PIU' ALTO AL PIU' BASSO
    @GetMapping("/listaPerVoti")
    public ResponseEntity<List<Brano>> getBraniByVoti(){
        return ResponseEntity.ok().body(branoService.getBranoByVoto());
    }

    // QUERY: AUTORI IN ORDINE ALFABETICO
    @GetMapping("/listaPerAutori")
    public ResponseEntity<List<Brano>> getBraniByAutori(){
        return ResponseEntity.ok().body(branoService.getBranoByAutore());
    }

    // QUERY: TITOLI IN ORDINE ALFABETICO
    @GetMapping("/listaPerTitoli")
    public ResponseEntity<List<Brano>> getBraniByTitoli(){
        return ResponseEntity.ok().body(branoService.getBranoByTitolo());
    }

    @PostMapping("/addSong")
    public ResponseEntity<?> addSong(@RequestBody @Valid BranoDTO brano){
        Brano temp = new Brano(brano.getTitolo(), brano.getAutore(), brano.getAlbum(), brano.getAnno(), brano.getVoto());
        branoService.saveBrano(temp);
        return ResponseEntity.ok().body(temp);
    }

    @PutMapping("/{branoId}")
    public ResponseEntity<?> updateBrano(@PathVariable String branoId, @RequestBody Brano brano){
        branoService.updateBrano(brano);
        return ResponseEntity.ok().body(brano);
    }

    @DeleteMapping("/{autore}/{titolo}")
    public ResponseEntity<?> deleteBrano(@PathVariable String autore, @PathVariable String titolo){
        branoService.deleteBrano(autore, titolo);
        return ResponseEntity.ok().build();
    }

}
