package uo.ri.cws.application.business.sparePart;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface SparePartCrudService {

	/**
	 * Registers a new spare part in the system with the information received
	 * @param dto
	 * @return new id generated
	 * @throws BusinessException in case of:
	 * 	- there is another spare part registered with the same code
	 * 	- the code or description are null or empty
	 * 	- any of the price, stock, min stock or max stock is negative
	 */
	String add(SparePartDto dto) throws BusinessException;

	/**
	 * Removes the spare part from the system if the spare part has not been
	 * 		used under any of its associations
	 * @param spare part code
	 * @throws BusinessException if case of:
	 * 	- there is no spare part with such code
	 * 	- the spare part has suppliers
	 * 	- the spare part is involved in any order
	 * 	- the spare part has substitutions
	 */
	void delete(String code) throws BusinessException;

	/**
	 * Updates the fields of the spare part identified by code (or id) except
	 * 	code, id and Version. The new values has to pass
	 * 	the same validation rules as for the add operation.
	 * @param dto
	 * @throws BusinessException in case of:
	 * 	- the spare part code does not exist
	 * 	- any of id or description is null or empty
	 * 	- any of stock, min stock or max stock is negative
	 * 	- the price is negative
	 */
	void update(SparePartDto dto) throws BusinessException;

	/**
	 * Note: this operation is quite similar to
	 * 		@see SparePartReportService.findByCode, but this one returns
	 * 		@see SparePartDto instead of
	 * 		@see SparePartReportDto (that).
	 * @param spare part code
	 * @return the spare part identified by the code or Optional.empty() if
	 * 		does not exist
	 * @throws BusinessException DOES NOT
	 */
	Optional<SparePartDto> findByCode(String code) throws BusinessException;

	/**
	 * @param spare part id
	 * @return the spare part identified by the id or Optional.empty() if
	 * 		does not exist
	 * @throws BusinessException DOES NOT
	 */
	Optional<SparePartDto> findById(String id) throws BusinessException;

	/**
	 *
	 * @return a list of spareparts whose stock is below the min stock
	 * @throws BusinessException
	 */
	List<SparePartDto> findAll() throws BusinessException;

	/**
	 * @return a list of sparePartsDto which are under stock and not in
	 * a current order
	 * @throws BusinessException DOES NOT
	 */
	List<SparePartDto> findUnderStock() throws BusinessException;
}
