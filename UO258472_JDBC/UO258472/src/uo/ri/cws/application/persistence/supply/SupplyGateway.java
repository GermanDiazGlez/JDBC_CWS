package uo.ri.cws.application.persistence.supply;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface SupplyGateway extends Gateway<SupplyRecord> {

	/**
	 * Return the SupplyRecord that matches with the id
	 * @param id
	 * @return SupplyRecord or null if none
	 */
	Optional<SupplyRecord> findBySparePartId(String id);

	/**
	 * Return the best provider for the sparePartId
	 * @param id
	 * @return SupplyRecord or null if none
	 */
	Optional<SupplyRecord> findBestProviderBySparePartId(String id);
}
