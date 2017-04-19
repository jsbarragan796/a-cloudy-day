package co.edu.uniandes.rest.corazon.config;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class WebApp extends ResourceConfig 
{

    public WebApp() 
    {
        packages("co.edu.uniandes.rest.corazon.resources");
    }
}
