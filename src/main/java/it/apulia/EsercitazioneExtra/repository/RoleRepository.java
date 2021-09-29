package it.apulia.EsercitazioneExtra.repository;

import it.apulia.EsercitazioneExtra.accessManager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByNome(String nome);
}
