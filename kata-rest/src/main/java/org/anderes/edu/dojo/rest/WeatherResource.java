package org.anderes.edu.dojo.rest;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.anderes.edu.dojo.rest.persistence.RepositoryException;
import org.anderes.edu.dojo.rest.persistence.WeatherData;
import org.anderes.edu.dojo.rest.persistence.WeatherRepository;
import org.anderes.edu.dojo.rest.persistence.WeatherRepositoryStub;

@Path("weatherdata")
public class WeatherResource {

    private WeatherRepository repository = WeatherRepositoryStub.INSTANCE;
    
    @GET
    @Produces(APPLICATION_JSON)
    public Response findAllWeatherData() throws RepositoryException {
        final Collection<WeatherData> data = repository.findAllWeatherData();
        final GenericEntity<Collection<WeatherData>> genericList = new GenericEntity<Collection<WeatherData>>(data) {};
        return Response.ok().encoding(UTF_8.name()).entity(genericList).build();
    }
    
	@GET
	@Path("{id: [0-9]+}")
	@Produces(APPLICATION_JSON)
	public Response findWeatherData(@PathParam("id") Integer id) throws RepositoryException {
	    final Optional<WeatherData> data = repository.findWeatherDataById(id);
	    if (data.isPresent()) {
	        return Response.ok().encoding(UTF_8.name()).entity(data.get()).build();
	    }
	    throw new NotFoundException();
	}

	@POST
	@Consumes(APPLICATION_JSON)
	public Response insertNewWeatherData(WeatherData data) throws RepositoryException {
	    if (!data.getId().equals(Integer.MIN_VALUE)) {
	        throw new BadRequestException();
	    }
	    final WeatherData storedData = repository.storeData(data);
	    final URI location = UriBuilder.fromResource(WeatherResource.class).path(storedData.getId().toString()).build();
	    return Response.created(location).build();
	}
	
	@PUT
	@Path("{id: [0-9]+}")
	@Consumes(APPLICATION_JSON)
	public Response updateWeatherData(@PathParam("id") Integer id, WeatherData data) throws RepositoryException {
	    if (!id.equals(data.getId())) {
	        throw new BadRequestException();
	    }
	    final Optional<WeatherData> findData = repository.findWeatherDataById(data.getId());
	    if (findData.isPresent()) {
	        repository.updateData(data);
	        return Response.ok().build();
	    } else {
	        final WeatherData storedData = repository.storeData(data);
	        final URI location = UriBuilder.fromResource(WeatherResource.class).path(storedData.getId().toString()).build();
            return Response.created(location).build();
	    }
    }
	
	@DELETE
	@Path("{id: [0-9]+}")
	public Response deleteWeatherData(@PathParam("id") Integer id) throws RepositoryException {
	    final Optional<WeatherData> data = repository.findWeatherDataById(id);
	    if (data.isPresent()) {
	        repository.deleteData(data.get());	        
	        return Response.ok().build();
	    }
	    throw new NotFoundException();
	}
}
