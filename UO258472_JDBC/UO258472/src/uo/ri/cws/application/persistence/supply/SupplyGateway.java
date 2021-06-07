package uo.ri.cws.application.persistence.supply;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;
import uo.ri.cws.application.persistence.sparePart.SparePartGateway;

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

	/**
	 * Return the providers who provide that sparePart
	 * @param id
	 * @return
	 */
	List<SupplyRecord> findProvidersBySparePartId(String id) throws SQLException;

	/**
	 * Return the supply that matches with the provider and sparepart
	 * @param idProvider
	 * @param idSparePart
	 * @return
	 */
    Optional<SupplyRecord> findSupplyByIdProviderAndIdSparePart(String idProvider, String idSparePart) throws SQLException;

	List<SupplyRecord> findSparePartIdsByIdProvider(String providerId) throws SQLException;
}
