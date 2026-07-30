package com.redelog.api.repository;

import com.redelog.api.model.entities.Filial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilialRepository extends JpaRepository<Filial, Long> {

    boolean existsByCnpj(String cnpj);

    boolean existsByNumeroFilial(String numeroFilial);
}
