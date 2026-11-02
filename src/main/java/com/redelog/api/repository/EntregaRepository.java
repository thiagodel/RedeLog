package com.redelog.api.repository;

import com.redelog.api.model.entities.Entrega;
import com.redelog.api.model.enums.StatusEntrega;

import java.util.List;

public interface EntregaRepository extends BaseRepository<Entrega, Long> {

    boolean existsByEntregadorIdAndStatusIn(
            Long entregadorId,
            List<StatusEntrega> status
    );
}
