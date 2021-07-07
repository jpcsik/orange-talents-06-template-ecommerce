package br.com.zupacademy.jpcsik.mercadolivre.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExisteValidator.class})
public @interface Existe {
	 
	String message() default "Não existe!";
	 
	Class<?>[] groups() default {};
	 
	Class<? extends Payload>[] payload() default {};
	 
	String campo();
	 
	Class<?> classeDeDominio();
}