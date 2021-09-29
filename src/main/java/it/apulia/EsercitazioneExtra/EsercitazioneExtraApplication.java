package it.apulia.EsercitazioneExtra;

/*
	Esercitazione extra
	Tramite l’utilizzo di un database relazionale a supporto (PostgreSQL o quello che volete voi), montato su Docker ( vedere esempio EsRESTJPADocker) si implementi la gestione di una raccolta di brani musicali
	Le classi interessate saranno
	Brano, contenente informazioni come Titolo, Cantante, Album di riferimento, Anno di uscita (come numero), voto complessivo da 1 a 10 in formato double o float (inventatevi voi una decina di brani)
	Come richieste verranno effettuate, oltre le operazioni CRUD, delle ricerche relative a:
	-        Restituzione della lista dei brani per voto in ordine decrescente;
	-        Restituzione della lista dei brani per ordine alfabetico crescente del Cantante
	-        Restituzione della lista dei brani per ordine alfabetico crescente del titolo del brano
	Anche in questa esercitazione c’è la parte di registrazione utente e gestione tramite jwt.
	In questo caso, nessun utente può effettuare le richieste se non è registrato (quindi potete fargli fare una registrazione chiamando l’add user, ma tramite questa richiesta si potrà inserire soltanto username e password, e all’utente verrà assegnato poi il ruolo ROLE_USER)
	L’utente registrato può fare solo le operazioni di GET e di ricerca sui brani
	 ---
	Per l’utilizzo di PostgreSQL su docker, verrà utilizzato pgadmin4 che sarà accessibile sulla localhost:5050
	all’accesso vi verrà chiesto la password dell’admin di pgadmin4, root, che è indicata nel docker-compose
	Nel momento in cui aprirete pgadmin4, dovrete creare il server

 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EsercitazioneExtraApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsercitazioneExtraApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
