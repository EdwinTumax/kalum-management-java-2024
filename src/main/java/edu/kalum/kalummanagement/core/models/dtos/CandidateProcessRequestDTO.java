package edu.kalum.kalummanagement.core.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProcessRequestDTO {
	@NotEmpty(message="El campo apellidos no debe ser vacio")
	private String apellidos;
	@NotEmpty(message="El campo nombres no debe ser vacio")
	private String nombres;
	@NotEmpty(message="El campo dirección no debe ser vacio")
	private String direccion;
	@NotEmpty(message="El campo telefono no debe ser vacio")
	private String telefono;
	@NotEmpty(message="El campo email no debe ser vacio")
	@Email(message="El correo electronico debe de ser valido")
	private String email;
	@NotEmpty(message="La carrera técnica no debe ser vacio")
	private String carreraId;
	@NotEmpty(message="La jornada no debe ser vacio")
	private String jornadaId;
	@NotEmpty(message="El examen de admisión no debe ser vacio")
	private String examenId;
}
