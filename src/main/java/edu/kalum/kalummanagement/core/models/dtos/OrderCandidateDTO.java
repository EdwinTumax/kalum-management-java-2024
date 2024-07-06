package edu.kalum.kalummanagement.core.models.dtos;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCandidateDTO implements Serializable {
	private String orderId;
	private Date orderDate;
	private String status;
	private OrderDataCandidateDTO data;
}
