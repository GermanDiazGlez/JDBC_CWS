package uo.ri.cws.application.persistence.supply;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface SupplyGateway extends Gateway<SupplyRecord> {

	/**
	 * Return the SupplyRecord that matches with the id
	 * @param id
	 * @return SupplyRecord or null if none
	 */
	Optional<SupplyRecord> findBySparePartId(String id) throws SQLException;

	/**
	 * Return the best provider for the sparePartId
	 * @param id
	 * @return SupplyRecord or null if none
	 */
	List<SupplyRecord> findBestProviderBySparePartId(String id) throws SQLException;

	/**
	 * Return an SupplyRecord if can match one with the provider nif
	 * @param nif
	 * @return
	 */
    Optional<SupplyRecord> findProviderById(String id) throws SQLException;
}
