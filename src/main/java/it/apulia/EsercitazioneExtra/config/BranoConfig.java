package it.apulia.EsercitazioneExtra.config;

import it.apulia.EsercitazioneExtra.accessManager.model.Role;
import it.apulia.EsercitazioneExtra.accessManager.model.Utente;
import it.apulia.EsercitazioneExtra.model.Brano;
import it.apulia.EsercitazioneExtra.repository.BranoRepository;
import it.apulia.EsercitazioneExtra.services.UtenteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BranoConfig {

    @Bean
    CommandLineRunner commandLineRunnerPrenotazioni(BranoRepository repository, UtenteService utenteService) {
        return args -> {

            utenteService.resetAll();

            utenteService.saveRole(new Role("ROLE_USER"));
            utenteService.saveRole(new Role("ROLE_ADMIN"));

            utenteService.saveUtente(new Utente(null, "mario.rossi@gmail.com", "password", new ArrayList<>()));
            utenteService.saveUtente(new Utente(null, "matteo.bianchi@hotmail.it", "password1234", new ArrayList<>()));
            utenteService.saveUtente(new Utente(null, "maria.verdi@live.com", "12345678", new ArrayList<>()));

            utenteService.addRoleToUtente("mario.rossi@gmail.com", "ROLE_USER");
            utenteService.addRoleToUtente("matteo.bianchi@hotmail.it", "ROLE_USER");
            utenteService.addRoleToUtente("matteo.bianchi@hotmail.it", "ROLE_ADMIN");
            utenteService.addRoleToUtente("maria.verdi@live.com", "ROLE_USER");

            Brano brano1 = new Brano("Bob Dylan", "Like a Rolling Stone","Almbum 1", 1965,9.3);
            Brano brano2 = new Brano("John Lennon", "Imagine","Album 2", 1971,9.1);
            Brano brano3 = new Brano("Marvin Gaye", "What's Going On","Album 3", 1971,8.3);
            Brano brano4 = new Brano("Aretha Franklin", "Respect","Album 4", 1967,8.0);
            Brano brano5 = new Brano("The Beatles", "Hey Jude","Album 5", 1968,8.7);
            Brano brano6 = new Brano("Nirvana", "Smells Like Teen Spirit","Album 6", 1991,8.2);
            Brano brano7 = new Brano("The Who", "My Generation","Album 7", 1965,9.1);
            Brano brano8 = new Brano("The Beatles", "Yesterday","Album 8", 1965,8.3);
            Brano brano9 = new Brano("Jimi Hendrix", "Purple Haze","Album 9", 1967,7.9);
            Brano brano10 = new Brano("Chuck Berry", "Maybellene","Album 10", 1955,8.6);

            repository.deleteAll();

            repository.saveAll(
                    List.of(brano1,brano2,brano3,brano4,brano5,brano6,brano7,brano8,brano9,brano10));
        };
    }
}
