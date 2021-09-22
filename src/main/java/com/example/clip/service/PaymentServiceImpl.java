package com.example.clip.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clip.model.Payment;
import com.example.clip.model.User;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.repository.UserRepository;
import com.example.clip.request.PaymentRequest;
import com.example.clip.response.ReportResponse;
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public Payment savePayment(PaymentRequest paymentRequest) {
		Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setUserId(paymentRequest.getUserId());
        payment.setStatus("N");
      
        paymentRepository.save(payment);

	        
		return payment;
	}
	@Override
	public List<UserDto> getAllUsers() {
		
		List<String> userIds = paymentRepository.getAllUserId();
		List<UserDto> users = new ArrayList<>();
		for (String userID : userIds) {
			
			UserDto tmpUser = new UserDto();
			
			User user = userRepository.findById(userID);
			tmpUser.setId(user.getUser_id());
			tmpUser.setName(user.getName());
			users.add(tmpUser);
		}
		
		return users;
	}
	@Override
	public List<PaymentDto> disbursementProcess() {
		List<PaymentDto> paymentDisbursement = new ArrayList<>();
		for (Payment payment : paymentRepository.getPaymentsByStatus("N")) {
			PaymentDto tmpPayment = new PaymentDto();
			
			BigDecimal amount = payment.getAmount();
			BigDecimal porcentaje = new BigDecimal(3.5);
			BigDecimal tmpAmount = amount.multiply(porcentaje).divide(new BigDecimal(100));
			
			payment.setAmount_disbursment(amount.subtract(tmpAmount));
			payment.setStatus("P");
			paymentRepository.save(payment);
			tmpPayment.setUserId(payment.getUserId());
			tmpPayment.setPayment(amount);
			tmpPayment.setDisbursement(payment.getAmount_disbursment());
			
			paymentDisbursement.add(tmpPayment);
			
		}
		return paymentDisbursement;
	}
	@Override
	public ReportResponse report(String userId) {
		ReportResponse report = new ReportResponse();
		
		User user = userRepository.findById(userId);
		report.setUserName(user.getName());
		List<Payment> totalPayments = paymentRepository.findById(userId);
		
		List<Payment> totalNewPayments = paymentRepository.getPaymentsByStatus("N");
		BigDecimal new_payments_amount = new BigDecimal(0);
		
		for (Payment payment : totalNewPayments) {
			
			new_payments_amount = new_payments_amount.add(payment.getAmount());
		}
		report.setUserName(user.getName());
		report.setPaymentsSum(totalPayments.size());
		report.setNewPayments(totalNewPayments.size());
		report.setNewPaymentsAmount(new_payments_amount);
		return report;
	}
	
	

	
}
