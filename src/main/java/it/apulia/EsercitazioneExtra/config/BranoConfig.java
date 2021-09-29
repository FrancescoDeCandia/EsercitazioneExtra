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
    CommandLineRunner commandLineRunner(BranoRepository repository, UtenteService utenteService) {
        return args -> {

            utenteService.resetAll();

            utenteService.saveRole(new Role("ROLE_USER"));
            utenteService.saveRole(new Role("ROLE_ADMIN"));

            utenteService.saveUtente(new Utente("mario.rossi@gmail.com", "password", new ArrayList<>()));
            utenteService.saveUtente(new Utente("matteo.bianchi@hotmail.it", "password1234", new ArrayList<>()));
            utenteService.saveUtente(new Utente("maria.verdi@live.com", "12345678", new ArrayList<>()));

            utenteService.addRoleToUtente("mario.rossi@gmail.com", "ROLE_USER");
            utenteService.addRoleToUtente("matteo.bianchi@hotmail.it", "ROLE_USER");
            utenteService.addRoleToUtente("matteo.bianchi@hotmail.it", "ROLE_ADMIN");
            utenteService.addRoleToUtente("maria.verdi@live.com", "ROLE_USER");

            Brano brano1 = new Brano( "Like a Rolling Stone","Bob Dylan","Almbum 1", 1965,9.3);
            Brano brano2 = new Brano( "Imagine","John Lennon","Album 2", 1971,9.1);
            Brano brano3 = new Brano( "What's Going On","Marvin Gaye","Album 3", 1971,8.3);
            Brano brano4 = new Brano( "Respect","Aretha Franklin","Album 4", 1967,8.0);
            Brano brano5 = new Brano( "Hey Jude","The Beatles","Album 5", 1968,8.7);
            Brano brano6 = new Brano( "Smells Like Teen Spirit","Nirvana","Album 6", 1991,8.2);
            Brano brano7 = new Brano( "My Generation","The Who","Album 7", 1965,9.1);
            Brano brano8 = new Brano( "Yesterday","The Beatles","Album 8", 1965,8.3);
            Brano brano9 = new Brano( "Purple Haze","Jimi Hendrix","Album 9", 1967,7.9);
            Brano brano10 = new Brano( "Maybellene","Chuck Berry","Album 10", 1955,8.6);

            List<Brano> temp= new ArrayList<>();
            temp.add(brano1);
            temp.add(brano2);
            temp.add(brano3);
            temp.add(brano4);
            temp.add(brano5);
            temp.add(brano6);
            temp.add(brano7);
            temp.add(brano8);
            temp.add(brano9);
            temp.add(brano10);

            repository.deleteAll();

            repository.saveAll(temp);

        };
    }
}
