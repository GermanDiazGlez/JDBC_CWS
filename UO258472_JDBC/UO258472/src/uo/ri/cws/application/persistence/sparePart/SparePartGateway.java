package uo.ri.cws.application.persistence.sparePart;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface SparePartGateway extends Gateway<SparePartRecord> {

	/**
	 * Return the SparePartRecord that matches with the code
	 * @param code
	 * @return SparepartRecord or null if none
	 */
	Optional<SparePartRecord> findByCode(String code) throws SQLException;

    @Override
    default void update(SparePartRecord partRecord) throws SQLException {

    }

    /**
	 * Return the SparepartRecord that matches with the id
	 * @param id
	 * @return SparePartRecord or null if none
	 */
	Optional<SparePartRecord> findById(String id) throws SQLException;

	/**
	 * Returns the SparePart that matches with the code if exists
	 * @param code
	 * @return
	 */
    Optional<SparePartRecord> isPresent(String code) throws SQLException;

	/**
	 * @return the spare parts which stock is under the minimum
	 */
    List<SparePartRecord> findUnderStock() throws SQLException;
}
