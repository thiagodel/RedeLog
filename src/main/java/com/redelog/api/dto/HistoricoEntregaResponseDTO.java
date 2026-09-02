package com.redelog.api.dto;

import com.redelog.api.model.enums.StatusEntrega;

import java.time.LocalDateTime;

public record HistoricoEntregaResponseDTO(
        Long id,
        StatusEntrega status,
        String observacao,
        LocalDateTime data
) {
}
