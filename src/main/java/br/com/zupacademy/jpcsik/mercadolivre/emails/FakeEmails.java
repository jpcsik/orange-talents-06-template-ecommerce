package br.com.zupacademy.jpcsik.mercadolivre.emails;

import org.springframework.stereotype.Component;

@Component
public class FakeEmails implements Mailer{

	@Override
	public void send(String subject, String body, String nameFrom, String from, String to) {
		System.out.println(subject);
		System.out.println(body);
		System.out.println(nameFrom);
		System.out.println(from);
		System.out.println(to);
			
	}

}
