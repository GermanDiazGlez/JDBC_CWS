package uo.ri.cws.application.business.util.command;

import java.sql.SQLException;

import uo.ri.cws.application.business.BusinessException;

public interface Command<T> {

	T execute() throws BusinessException, SQLException; 
}