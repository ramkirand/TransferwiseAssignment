package com.transferwise.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
	String comingFrom;
	String customerName;
	Date createDate;
	String correlationId;
	String mt103Msg;
}
