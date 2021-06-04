package uo.ri.cws.application.business.sparePart;

import uo.ri.cws.application.business.BusinessException;

import java.util.List;
import java.util.Optional;

public interface SparePartReportService {

	/**
	 * @param spare part description or a part from it
	 * @return a list with the sparePartsReport whose description matches with the param, parcial or totally
	 * @throws BusinessException DOES NOT
	 */
	List<SparePartReportDto> findByDescription(String desc) throws BusinessException;

	/**
	 * @param spare part code
	 * @return the spare part identified by the code or Optional.empty() if
	 * 		does not exist
	 * @throws BusinessException DOES NOT
	 */
	Optional<SparePartReportDto> findByCode(String code) throws BusinessException;

}
