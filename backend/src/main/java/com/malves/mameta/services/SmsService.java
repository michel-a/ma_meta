package com.malves.mameta.services;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.malves.mameta.entities.Sale;
import com.malves.mameta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleRepository saleRepository;

	public void sendSms(Long saleId) {

		Sale sale = saleRepository.findById(saleId).get();

		StringBuilder sb = new StringBuilder();
		sb.append("O(A) vendedor(a) ");
		sb.append(sale.getSellerName());
		sb.append(" foi destaque em ");
		sb.append(sale.getDate().getMonthValue());
		sb.append("/");
		sb.append(sale.getDate().getYear());
		sb.append(" com um total de R$");
		sb.append(new DecimalFormat("#,##0.00").format(sale.getAmount()));
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, sb.toString()).create();

		System.out.println(message.getSid());
	}
}
