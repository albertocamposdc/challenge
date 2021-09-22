package com.example.clip.service;

import java.util.List;

import com.example.clip.model.Payment;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.response.ReportResponse;

public interface PaymentService {
	
	public Payment savePayment(PaymentRequest paymentRequest);
	
	
	public List<UserDto> getAllUsers();
	
	public List<PaymentDto> disbursementProcess();
	
	public ReportResponse report(String userId);

}
