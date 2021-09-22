package com.example.clip.controller;

import com.example.clip.handler.PaymentHandler;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.request.RequestReport;
import com.example.clip.response.PaymentResponse;
import com.example.clip.response.ReportResponse;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clip.repository.PaymentRepository;

import org.springframework.http.HttpHeaders;

@Slf4j
@RestController
@RequestMapping("/api/clip")
public class TransactionController {

	protected HttpHeaders headers = new HttpHeaders();

	@Autowired
	PaymentRepository paymentRepository;
	@Autowired
	PaymentHandler paymentHandler;

	public TransactionController() {
		this.headers.add("Access-Control-Allow-Origin", "*");
		this.headers.add("Content-Type", "application/json;charset=UTF-8");
		this.headers.add("Access-Control-Allow-Methods", "POST, GET");
		this.headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
	}

	@PostMapping(value = "/createPayload", produces = "application/json")
	public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {

		PaymentResponse response = paymentHandler.generateResponseCreate(paymentRequest);

		return new ResponseEntity<>(response, this.headers, HttpStatus.OK);

	}

	@GetMapping(value = "/allUserPayment", produces = "application/json")
	public ResponseEntity<List<UserDto>> queryAllUsers() {

		List<UserDto> response = paymentHandler.generateResponseAllUser();

		return new ResponseEntity<>(response, this.headers, HttpStatus.OK);

	}

	@GetMapping(value = "/disbursementProcess", produces = "application/json")
	public ResponseEntity<List<PaymentDto>> disbrusement() {

		List<PaymentDto> response = paymentHandler.generateResponseDisbrusement();

		return new ResponseEntity<>(response, this.headers, HttpStatus.OK);

	}

	@PostMapping(value = "/report", produces = "application/json")
	public ResponseEntity<ReportResponse> report(@RequestBody RequestReport requestReport) {

		ReportResponse reportResponse = paymentHandler.generateResponseReport(requestReport);

		return new ResponseEntity<>(reportResponse, this.headers, HttpStatus.OK);

	}

}
