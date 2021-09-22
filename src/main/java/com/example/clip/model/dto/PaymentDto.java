package com.example.clip.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
	 private long id;

	    private BigDecimal payment;

	    private String userId;
	    
	    private BigDecimal disbursement;
}
