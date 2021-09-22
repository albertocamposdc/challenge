package com.example.clip.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.clip.model.Payment;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.request.RequestReport;
import com.example.clip.response.PaymentResponse;
import com.example.clip.response.ReportResponse;
import com.example.clip.service.PaymentService;

@Service
public class PaymentHandler {
	@Autowired
	@Qualifier("paymentService")
	PaymentService paymentService;

	public PaymentResponse generateResponseCreate(PaymentRequest paymentRequest) {
		PaymentResponse paymentResponse = new PaymentResponse();

		Payment payment = this.paymentService.savePayment(paymentRequest);

		paymentResponse.setId(payment.getId());
		paymentResponse.setUserId(payment.getUserId());
		paymentResponse.setAmount(payment.getAmount());

		return paymentResponse;
	}

	public List<UserDto> generateResponseAllUser() {

		return (this.paymentService.getAllUsers());
	}

	public List<PaymentDto> generateResponseDisbrusement() {

		return this.paymentService.disbursementProcess();

	}

	public ReportResponse generateResponseReport(RequestReport requestReport) {
		ReportResponse reportResponse = new ReportResponse();

		reportResponse = this.paymentService.report(requestReport.getUserId());

		return reportResponse;
	}

}
