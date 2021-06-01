package uo.ri.cws.application.persistence.mechanic;

import java.sql.SQLException;
import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface MechanicGateway extends Gateway<MechanicRecord> {

	/**
	 * @param dni
	 * @return the mechanic dto identified by the dni or null if none
	 */
	Optional<MechanicRecord> findByDni(String dni) throws SQLException;

}
