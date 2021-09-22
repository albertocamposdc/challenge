package com.example.clip.response;

import java.math.BigDecimal;
import java.util.List;

import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
	String userName;
	int paymentsSum;
	int newPayments;
	BigDecimal newPaymentsAmount;
	

}
