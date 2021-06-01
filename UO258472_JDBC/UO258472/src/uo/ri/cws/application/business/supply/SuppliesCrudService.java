package uo.ri.cws.application.business.supply;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

public interface SuppliesCrudService {

	/**
	 * Registers a new supply in the system for the indicated provider and spare part
	 * @param dto
	 * @return the id of the new registered supply
	 * @throws BusinessException in case of:
	 * 	- there already exist a supply for the provider and the spare part
	 * 	- the delivery term or the price are negative
	 * 	- does not exist the provider
	 * 	- does not exist the spare part
	 */
	String add(SupplyDto dto) throws BusinessException;

	/**
	 * Removes from the system the supply uniquely indicated by the nif and code
	 * (a supply can be removed with no other considerations)
	 * @param providers nif
	 * @param spare part code
	 * @throws BusinessException if the indicated supply does not exist
	 */
	void delete(String nif, String code) throws BusinessException;

	/**
	 * Updates the supply with the new data from the incoming dto
	 * @param dto
	 * @throws BusinessException in case of:
	 * 	- it does not exist a supply for the provider and the spare part
	 * 	- the delivery term or the price are negative
	 */
	void update(SupplyDto dto) throws BusinessException;

	/**
	 * @param nif
	 * @param code
	 * @return the supply dto uniquely identified by the provider nif and
	 * 		spare part code or Optional.empty() if does not exist
	 * @throws BusinessException DOES NOT
	 */
	Optional<SupplyDto> findByNifAndCode(String nif, String code) throws BusinessException;

	/**
	 * @param provider nif
	 * @return the list (might be empty) of supply dto served by the provider
	 * 		identified by nif
	 * @throws BusinessException DOES NOT
	 */
	List<SupplyDto> findByProviderNif(String nif) throws BusinessException;

	/**
	 * @param spare part code
	 * @return the list (might be empty) of supply dto identified by the
	 * 		spare part code
	 * @throws BusinessException DOES NOT
	 */
	List<SupplyDto> findBySparePartCode(String code) throws BusinessException;

}
