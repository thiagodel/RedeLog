package com.redelog.api.repository;

import com.redelog.api.model.entities.Filial;

public interface FilialRepository extends BaseRepository<Filial, Long> {

    boolean existsByCnpj(String cnpj);

    boolean existsByNumeroFilial(String numeroFilial);
    boolean existsByNumeroFilialAndIdNot(String numeroFilial, Long id);
}
