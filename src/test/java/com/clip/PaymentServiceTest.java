package com.clip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.clip.model.Payment;
import com.example.clip.model.User;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.repository.UserRepository;
import com.example.clip.request.PaymentRequest;
import com.example.clip.response.ReportResponse;
import com.example.clip.service.PaymentServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {
	@InjectMocks PaymentServiceImpl paymentServiceImpl;
	
	@Mock
	PaymentRequest paymentRequest;
	
	@Mock
	PaymentRepository paymentRepository;
	@Mock
	UserRepository userRepository;
	
	@Test
	public void testSavePayment() {
		when(paymentRequest.getUserId()).thenReturn("1");
		when(paymentRequest.getAmount()).thenReturn(new BigDecimal(1000));
		
		Payment resp = paymentServiceImpl.savePayment(paymentRequest);
		assertNotNull(resp);
	}
	
	@Test
	public void getAllUsersTest() {
		User user = new User("1","Julio");
		when(paymentRepository.getAllUserId()).thenReturn(List.of("1"));
		 Mockito.doReturn(user).when(userRepository).findById("1");
		List<UserDto> listUsers = paymentServiceImpl.getAllUsers();
		assertNotNull(listUsers);
		assertEquals(1, listUsers.size());;
	}
	
	@Test
	public void disbursementProcessTest() {
		
		Payment payment = new Payment(1, new BigDecimal(1000),"1", "N", null);
		List<Payment>  paymentList = new ArrayList<>();
		paymentList.add(payment);
		
		Mockito.doReturn(paymentList).when(paymentRepository).getPaymentsByStatus("N");
		List<PaymentDto>result =  paymentServiceImpl.disbursementProcess();
		assertNotNull(result);
		assertEquals(1, result.size());;
	}
	
	
	@Test
	public void reportTest() {
		User user = new User("1","Julio");
		Mockito.doReturn(user).when(userRepository).findById("1");
		
		
		
		List<Payment>  paymentList = new ArrayList<>();
		paymentList.add(new Payment(1, new BigDecimal(1000),"1", "P", new BigDecimal(965)));
		when(paymentRepository.findById("1")).thenReturn(paymentList);
		
		paymentList.clear();
		
		paymentList.add(new Payment(1, new BigDecimal(1000),"1", "N", null));
		when(paymentRepository.getPaymentsByStatus("N")).thenReturn(paymentList);
		
		ReportResponse reporte = paymentServiceImpl.report("1");
		
		assertEquals(1, reporte.getNewPayments());
		assertEquals(1, reporte.getPaymentsSum());
		assertEquals("Julio", reporte.getUserName());
		assertEquals(new BigDecimal(1000), reporte.getNewPaymentsAmount());
		
		
	}
	
	
	
}
