package internet.and.applications.api;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class RestfulApp extends Application {

  @Override
  public synchronized Restlet createInboundRoot(){

    Router router = new Router(getContext());
    return router;
  }
}
