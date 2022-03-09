package com.bulkemail.BulkEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;

@SpringBootApplication
public class BulkEmailApplication {

	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(BulkEmailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	private void sendEmail(){
		ArrayList<String> arrayList = new ArrayList<>();

		arrayList.add("escaper68@gmail.com");
		arrayList.add("gakash8860@gmail.com");
		arrayList.add("santosh20gtc279@student.gangatechnicalcampus.com");
		arrayList.add("shubham20gtc433@student.gangatechnicalcampus.com");

 		senderService.sendEmail(arrayList,"this is dummy Bulk Email","this is Bulk Email body");
	}

}
