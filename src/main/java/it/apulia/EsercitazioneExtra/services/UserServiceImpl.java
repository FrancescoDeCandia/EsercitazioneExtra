package it.apulia.EsercitazioneExtra.services;

import it.apulia.EsercitazioneExtra.accessManager.model.Role;
import it.apulia.EsercitazioneExtra.accessManager.model.Utente;
import it.apulia.EsercitazioneExtra.repository.RepositoryUtente;
import it.apulia.EsercitazioneExtra.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UtenteService, UserDetailsService {

    private final RepositoryUtente repositoryUtente;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = this.repositoryUtente.findByUsername(username);
        if(utente == null) {
            log.error("Utente non trovato all'interno del db!");
            throw new UsernameNotFoundException("Utente non trovato all'interno del db!");
        } else {
            log.info("Utente richiesto trovato nel db: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            utente.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getNome()));
            });
            //ho potuto mettere il return seguente così perché ho distinto Utente custom e User
            return new User(utente.getUsername(), utente.getPassword(), authorities);
        }
    }

    @Override
    public Utente saveUtente(Utente utente) {
        log.info("Salvataggio dell'utente {} all'interno del database", utente.getUsername());
        //esempio di log, realisticamente da utilizzare in maniera sensata un po' ovunque
        utente.getRoles().forEach(
                role -> {
                    String nome = role.getNome();
                    role.setId(roleRepository.findByNome(nome).getId());
                }
        );
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return this.repositoryUtente.save(utente);
    }

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    //in questo caso l'operazione viene eseguita dato il tag @transactional definita all'inizio della classe
    //in casi reali si fa più attenzione sull'utilizzo di questa annotazione, ci saranno altri check da fare
    @Override
    public void addRoleToUtente(String username, String roleName) {
        Utente utente = this.repositoryUtente.findByUsername(username);
        Role role = this.roleRepository.findByNome(roleName);
        log.info("Aggiungo il ruolo {} all'utente {}.",role.getNome(),utente.getUsername());
        utente.getRoles().add(role);

        this.repositoryUtente.deleteById(utente.getId());
        this.repositoryUtente.save(utente);

    }

    @Override
    public Utente getUtente(String username) {
        this.repositoryUtente.findByUsername(username).getRoles().forEach(
                ruolo -> { System.out.println(ruolo.getNome());}
        );
        return this.repositoryUtente.findByUsername(username);
    }

    @Override
    public List<Utente> getUtenti() {
        return this.repositoryUtente.findAll();
    }

    public void resetAll() {
        this.repositoryUtente.deleteAll();
        this.roleRepository.deleteAll();
    }

    @Override
    public void deleteAll() {
        this.repositoryUtente.deleteAll();
    }
}
