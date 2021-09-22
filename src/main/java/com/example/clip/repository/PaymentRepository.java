package com.example.clip.repository;

import com.example.clip.model.Payment;
import com.example.clip.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query("SELECT DISTINCT userId FROM Payment")
	List<String> getAllUserId();
	
	List<Payment> getPaymentsByStatus(String status);
	
	@Query("SELECT p  FROM Payment p where user_id=?1")
	List<Payment> findById(String user_id);
	
	@Query(nativeQuery = true,value = "select * FROM Payment where user_id=?1")
	List<Payment>  findPayment(String user_id);

}
