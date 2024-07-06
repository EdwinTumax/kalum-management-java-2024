package edu.kalum.kalummanagement.core.models.domains;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
	private String errorCode;
	private String errorType;
	private String code;
	private String description;
	

}