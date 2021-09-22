package com.example.clip.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.clip.request.PaymentRequest;

@Component
public class PaymentValidatorRequest implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return PaymentRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PaymentRequest request = (PaymentRequest) target;
		
	}
	
}
