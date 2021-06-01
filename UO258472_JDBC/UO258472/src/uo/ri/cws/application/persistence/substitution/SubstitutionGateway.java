package uo.ri.cws.application.persistence.substitution;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface SubstitutionGateway extends Gateway<SubstitutionRecord> {

	/**
	 * Return a SubstitutionRecord that matches with the id
	 * @param id
	 * @return SubstitutionRecord or null if none
	 */
	Optional<SubstitutionRecord> findBySparePartId(String id);

}
