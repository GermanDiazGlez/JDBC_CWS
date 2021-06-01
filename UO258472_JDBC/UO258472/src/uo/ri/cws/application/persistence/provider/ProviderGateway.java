package uo.ri.cws.application.persistence.provider;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface ProviderGateway extends Gateway<ProviderRecord> {

	/**
	 * Return a ProviderRecord that matches with the nif
	 * @param nif
	 * @return the ProviderRecord or null if none
	 */
	Optional<ProviderRecord> findProviderByNif(String nif);

	/**
	 * Return a ProviderRecord that matches with the code
	 * @param code
	 * @return the ProviderRecord or null if none
	 */
	Optional<ProviderRecord> findProviderNameByOrderCode(String code);

	/**
	 * Return a ProviderRecord that matches with the name
	 * @param name
	 * @return the ProviderRecord or null if none
	 */
	Optional<ProviderRecord> findProviderNifByName(String name);

}
