package minimarketdemo.api.rest.thumano;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import minimarketdemo.model.thumano.managers.ManagerTHumano;

@RequestScoped
@Path("thumano")
@Produces("application/json")
@Consumes("application/json")
public class ServicioRESTTHumano {
	@EJB
	private ManagerTHumano mTHumano;
	
	@POST
	@Path(value = "generarrol")
	public String generarRolPagosExterno() {
		return mTHumano.generarRolPagosExterno();
	}

}
