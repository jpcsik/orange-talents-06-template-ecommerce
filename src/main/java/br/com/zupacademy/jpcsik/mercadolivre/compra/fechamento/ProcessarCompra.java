package br.com.zupacademy.jpcsik.mercadolivre.compra.fechamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zupacademy.jpcsik.mercadolivre.compra.Compra;
import br.com.zupacademy.jpcsik.mercadolivre.compra.CompraRepository;
import br.com.zupacademy.jpcsik.mercadolivre.emails.Emails;
import br.com.zupacademy.jpcsik.mercadolivre.outrossistemasfake.NotaFiscal;
import br.com.zupacademy.jpcsik.mercadolivre.outrossistemasfake.Ranking;

@Service
public class ProcessarCompra {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private Emails emails;
	
	@Transactional
	public ResponseEntity<?> processar(Long id, RetornoPagamento retorno) {	
		
		Compra compra = compraRepository.findById(id).get();
		compra.adicionaTransacao(retorno);
		compraRepository.save(compra);
		
		if(compra.finalizada()) {
			NotaFiscal.processar(compra);
			Ranking.processar(compra);
			emails.mandarEmailCompraFizalizada(compra);
			return ResponseEntity.ok().build();
		}
		
		emails.mandarEmailCompraRejeitada(compra);
		return ResponseEntity.ok().body("Transação invalida!");
		
	}
	
}
