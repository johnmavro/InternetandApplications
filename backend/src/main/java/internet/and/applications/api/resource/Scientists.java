package internet.and.applications.api.resource;

import internet.and.applications.api.representation.JsonMapRepresentation;
import internet.and.applications.conf.Configuration;
import internet.and.applications.data.DataAccess;
import internet.and.applications.data.DataAccessException;
import internet.and.applications.data.Author;
import org.restlet.ext.json.JsonRepresentation;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.resource.Post;
import org.restlet.util.Series;
import org.restlet.data.Header;

import java.util.*;

public class Scientists extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post (Representation entity) throws ResourceException {
        //Create a new restlet form
        Form form = new Form(entity);
        Series headers =(Series) getRequestAttributes().get("org.restlet.http.headers");
        String disease = form.getFirstValue("disease");
        System.out.println(disease);
        if( disease==null ) throw new ResourceException(400,"Bad request");
        Optional<List<Author>> opt = dataAccess.searchquery(disease);
        if(opt.isPresent()){
          return new JsonMapRepresentation(Map.of("Author List",opt.get()));
        }else{
          throw new ResourceException(400,"Bad request");
        }
    }
}
