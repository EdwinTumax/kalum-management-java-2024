package edu.kalum.kalummanagement.core.models.domains;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLog implements Serializable {
	private String apiName;
	private String hostName;
	private String apiKey;
	private String uri;
	private int responseCode;
	private String method;
	private Long responseTime;
	private String ip;
	private byte logLevel;
	private String message;
	private String dateTime;
	private String version;
}
