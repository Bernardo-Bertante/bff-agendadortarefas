package com.bffagendadortarefas.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTOResponse {

    private Long id;
    private String numero;
    private String ddd;
}
