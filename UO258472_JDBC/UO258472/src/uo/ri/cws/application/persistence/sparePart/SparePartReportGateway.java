package uo.ri.cws.application.persistence.sparePart;

import uo.ri.cws.application.persistence.Gateway;

import java.util.List;
import java.util.Optional;

public interface SparePartReportGateway extends Gateway<SparePartReportRecord> {
	
	Optional<SparePartReportRecord> findByCode(String code);

	Optional<SparePartReportRecord> isPresent(String code);
	
	Optional<SparePartReportRecord> findById(String id);

	List<SparePartReportRecord> findByDescription(String description);

}
