package br.com.zupacademy.jpcsik.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteValidator implements ConstraintValidator<Existe, Object>{

	private String campo;
	private Class<?> classe;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(Existe anotacao) {
		campo = anotacao.campo();
		classe = anotacao.classeDeDominio();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("SELECT 1 FROM "+classe.getName()+" WHERE "+campo+"=:value");
		query.setParameter("value", value);
		List<?> resultList = query.getResultList();
		return !resultList.isEmpty();
	}

}