package uo.ri.cws.application.persistence.provider;

import java.sql.SQLException;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface ProviderGateway extends Gateway<ProviderRecord> {

	/**
	 * Return a ProviderRecord that matches with the nif
	 * @param nif
	 * @return the ProviderRecord or null if none
	 */
	Optional<ProviderRecord> findProviderByNif(String nif) throws SQLException;

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

	/**
	 * Find if there is a provider matching with 3 at the same time
	 * @param name
	 * @param email
	 * @param phone
	 * @return
	 */
	Optional<ProviderRecord> findProviderByOthers(String name, String email, String phone) throws SQLException;
}
