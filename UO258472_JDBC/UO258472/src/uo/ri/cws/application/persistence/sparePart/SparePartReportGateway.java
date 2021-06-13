package uo.ri.cws.application.persistence.sparePart;

import uo.ri.cws.application.persistence.Gateway;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SparePartReportGateway extends Gateway<SparePartReportRecord> {

	/**
	 * Not used
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	Optional<SparePartReportRecord> findByCode(String code) throws SQLException;

	/**
	 * @param code
	 * @return The sparePart if exists and Optional.empty if not
	 * @throws SQLException
	 */
	Optional<SparePartReportRecord> isPresent(String code) throws SQLException;

	/**
	 * Not used
	 * @param id
	 * @return
	 */
	Optional<SparePartReportRecord> findById(String id);

	/**
	 * @param description
	 * @return a list of SpareParts which match total or partially with the description
	 * @throws SQLException
	 */
	List<SparePartReportRecord> findByDescription(String description) throws SQLException;

}
