package uo.ri.cws.application.ui.manager.spares.sparepart.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.sparePart.SparePartReportDto;
import uo.ri.cws.application.business.sparePart.SparePartReportService;
import uo.ri.cws.application.ui.util.Printer;

public class ViewDetailAction implements Action {

	@Override
	public void execute() throws Exception {
		try{
			String code = Console.readString("Code: ");

			SparePartReportService service = BusinessFactory.forSparePartReportService();
			Optional<SparePartReportDto> op = service.findByCode( code );

			if ( op.isEmpty() ) {
				Console.println("There is no such spare part.");
				Console.println("Please mind the code and try again.");
				return;
			}

			Printer.print( op.get() );
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}

}
