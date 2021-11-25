package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IAgenciaDAO;
import br.ufscar.dc.dsw.domain.Agencia;


@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {

	@Autowired
	private IAgenciaDAO agenciaDAO;

	@Override
	public boolean isValid(String cnpj, ConstraintValidatorContext context) {
		if (agenciaDAO != null) {
			Agencia agencia = agenciaDAO.findByCnpj(cnpj);
			return agencia == null;
		} else {
			return true;
		}

	}
}