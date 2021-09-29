package it.apulia.EsercitazioneExtra.services;

import it.apulia.EsercitazioneExtra.accessManager.model.Role;
import it.apulia.EsercitazioneExtra.accessManager.model.Utente;

import java.util.List;

public interface UtenteService {

    Utente saveUtente(Utente utente);
    Role saveRole(Role role);
    void addRoleToUtente(String username, String roleName);
    Utente getUtente(String username);
    List<Utente> getUtenti();
    void resetAll();
    void deleteAll();

}
