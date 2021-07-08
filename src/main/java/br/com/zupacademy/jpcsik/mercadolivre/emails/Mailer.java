package br.com.zupacademy.jpcsik.mercadolivre.emails;

import javax.validation.constraints.NotBlank;

public interface Mailer {

	/**
	 * 
	 * @param subject assunto do email
	 * @param body corpo do email
	 * @param nameFrom nome que aparece no provedor de email
	 * @param from email de origem
	 * @param to email de destino
	 */
	void send(@NotBlank String subject, @NotBlank String body, @NotBlank String nameFrom, @NotBlank String from, @NotBlank String to);
}
