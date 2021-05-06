package com.transferwise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScreeningResponse {
	String comment;
	String messageId;
	int code;
}
