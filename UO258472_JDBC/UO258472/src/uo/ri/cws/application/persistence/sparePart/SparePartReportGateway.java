package uo.ri.cws.application.persistence.sparePart;

import uo.ri.cws.application.persistence.Gateway;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SparePartReportGateway extends Gateway<SparePartReportRecord> {
	
	Optional<SparePartReportRecord> findByCode(String code) throws SQLException;

	Optional<SparePartReportRecord> isPresent(String code) throws SQLException;
	
	Optional<SparePartReportRecord> findById(String id);

	List<SparePartReportRecord> findByDescription(String description) throws SQLException;

}
