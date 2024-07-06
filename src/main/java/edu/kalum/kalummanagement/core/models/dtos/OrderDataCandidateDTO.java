package edu.kalum.kalummanagement.core.models.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDataCandidateDTO implements Serializable {
	private String apellidos;
	private String nombres;
	private String direccion;
	private String telefono;
	private String email;
	private String carreraId;
	private String jornadaId;
	private String examenId;
}
