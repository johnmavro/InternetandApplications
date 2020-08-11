package internet.and.applications.api;

import internet.and.applications.api.resource.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class RestfulApp extends Application {

  @Override
  public synchronized Restlet createInboundRoot(){

    Router router = new Router(getContext());
    //Paths
    //we only need one
    router.attach("/Scientists/diseases",Scientists.class);
    return router;
  }
}
