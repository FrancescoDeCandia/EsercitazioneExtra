package it.apulia.EsercitazioneExtra.controller;

import it.apulia.EsercitazioneExtra.model.Brano;
import it.apulia.EsercitazioneExtra.model.BranoDTO;
import it.apulia.EsercitazioneExtra.services.BranoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
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

    @PostMapping("/addSong")
    public ResponseEntity<Brano> addSong(@RequestBody @Valid BranoDTO brano){
        Brano temp = new Brano(brano.getTitolo(), brano.getAutore(), brano.getAlbum(), brano.getAnno(), brano.getVoto());
        branoService.saveBrano(temp);
        return ResponseEntity.ok().body(temp);

    }
}
