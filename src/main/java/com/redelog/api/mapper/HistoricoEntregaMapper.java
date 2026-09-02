package com.redelog.api.mapper;

import com.redelog.api.dto.HistoricoEntregaResponseDTO;
import com.redelog.api.model.entities.HistoricoEntrega;

public final class HistoricoEntregaMapper {

    private HistoricoEntregaMapper() {
    }

    public static HistoricoEntregaResponseDTO toDTO(HistoricoEntrega historico) {
        return new HistoricoEntregaResponseDTO(
                historico.getId(),
                historico.getStatus(),
                historico.getObservacao(),
                historico.getData()
        );
    }
}
