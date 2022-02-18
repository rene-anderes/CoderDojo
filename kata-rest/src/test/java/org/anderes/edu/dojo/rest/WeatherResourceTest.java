package org.anderes.edu.dojo.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Variant;

import org.anderes.edu.dojo.rest.persistence.WeatherData;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class WeatherResourceTest extends JerseyTest {

    private final static Integer EXIST_ID = 20987;
    private final static Integer EXIST_ID_TO_DELETE = 20999;
    private final static Integer NOT_EXIST_ID = 33333;
    private final static Variant DEFAULT_VARIANT = new Variant(APPLICATION_JSON_TYPE, "de_CH", "UTF-8");
    private final UriBuilder ResourcesBuilder = UriBuilder.fromResource(WeatherResource.class);
    
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(WeatherResource.class);
    }

    @Test
    public void shouldBeFindOneWeatherData() {
        
        // given
        final String path = ResourcesBuilder.path(EXIST_ID.toString()).toString();
        
        // when
        final Response response = target(path).request(APPLICATION_JSON_TYPE).buildGet().invoke();

        // then
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusInfo(), is(Status.OK));
        assertThat(response.hasEntity(), is(true));
        final WeatherData data = response.readEntity(WeatherData.class);
        assertThat(data, is(not(nullValue())));
        assertThat(data.getId(), is(EXIST_ID));
    }
    
    @Test
    public void shouldBeNotFindOneWeatherData() {
        
        // given
        final String path = ResourcesBuilder.path(NOT_EXIST_ID.toString()).toString();
        
        // when
        final Response response = target(path).request(APPLICATION_JSON_TYPE).buildGet().invoke();

        // then
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusInfo(), is(Status.NOT_FOUND));
        assertThat(response.hasEntity(), is(false));
    }
    
    @Test
    public void shouldBeStoreNewWeatherData() {
        
        // given
        final WeatherData data = new WeatherData();
        data.setTemperature(22.4).setAtmosphericPressure(1008.01).setDensity(2.45).setWindDirection(60).setWindSpeed(18.9);
        final Entity<WeatherData> entity = Entity.entity(data, DEFAULT_VARIANT);
        final String path = ResourcesBuilder.toString();
        
        // when
        final Response response = target(path).request().post(entity);
        
        // then
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusInfo(), is(Status.CREATED));
        assertThat(response.getLocation(), is(not(nullValue())));
        final String uriString = response.getLocation().toString();
        final String id = StringUtils.substringAfterLast(uriString, "/");
        assertThat(id.matches("[0-9]+"), is(true));
        assertThat(response.hasEntity(), is(false));
    }
    
    @Test
    public void shouldBeStoreWrongWeatherData() {
        
        // given
        final WeatherData data = new WeatherData(302);
        data.setTemperature(22.4).setAtmosphericPressure(1008.01).setDensity(2.45).setWindDirection(60).setWindSpeed(18.9);
        final Entity<WeatherData> entity = Entity.entity(data, DEFAULT_VARIANT);
        final String path = ResourcesBuilder.toString();
        
        // when
        final Response response = target(path).request().post(entity);
        
        // then
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusInfo(), is(Status.BAD_REQUEST));
    }
    
    @Test
    public void shouldBeUpdateWeatherData() {
        
        // given
        final WeatherData data = new WeatherData(EXIST_ID);
        data.setTemperature(20.4).setAtmosphericPressure(1000.67).setDensity(2.6).setWindDirection(45).setWindSpeed(12.45);
        final Entity<WeatherData> entity = Entity.entity(data, DEFAULT_VARIANT);
        final String path = ResourcesBuilder.path(EXIST_ID.toString()).toString();
        
        // when
        final Response response = target(path).request().put(entity);
        
        // then
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusInfo(), is(Status.OK));
        assertThat(response.getLocation(), is(nullValue()));
        assertThat(response.hasEntity(), is(false));
    }
    
    @Test
    public void shouldBeUpdateWrongWeatherData() {
        
        // given
        final WeatherData data = new WeatherData(EXIST_ID);
        data.setId(11111);
        final Entity<WeatherData> entity = Entity.entity(data, DEFAULT_VARIANT);
        final String path = ResourcesBuilder.path(EXIST_ID.toString()).toString();
        
        // when
        final Response response = target(path).request().put(entity);
        
        // then
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusInfo(), is(Status.BAD_REQUEST));
    }
    
    @Test
    public void shouldBeStoreNewWeatherDataViaPut() {

        // given
        final WeatherData data = new WeatherData(588);
        data.setTemperature(0.4).setAtmosphericPressure(1022.1).setDensity(2.9).setWindDirection(0).setWindSpeed(12.33);
        final Entity<WeatherData> entity = Entity.entity(data, DEFAULT_VARIANT);
        final String path = ResourcesBuilder.path("588").toString();
        
        // when
        final Response response = target(path).request().put(entity);
        
        // then
        assertThat(response.getStatusInfo(), is(Status.CREATED));
        assertThat(response.getLocation(), is(not(nullValue())));
        final String uriString = response.getLocation().toString();
        final String id = StringUtils.substringAfterLast(uriString, "/");
        assertThat(Integer.valueOf(id), is(588));
        assertThat(response.hasEntity(), is(false));
    }
    
    @Test
    public void shouldBeDeleteExistWeatherData() {
        
        // given
        final String path = ResourcesBuilder.path(EXIST_ID_TO_DELETE.toString()).toString();
        
        // when
        Response response = target(path).request().delete();
        
        // then
        assertThat(response.getStatusInfo(), is(Status.OK));
        
        // when
        response = target(path).request().delete();
        
        // then
        assertThat(response.getStatusInfo(), is(Status.NOT_FOUND));
    }
    
    @Test
    public void shouldBeFindAllWeatherData() {
        // given
        final String path = ResourcesBuilder.toString();
        
        // when
        final Response response = target(path).request().get();

        // then
        assertThat(response.getStatusInfo(), is(Status.OK));
        assertThat(response.hasEntity(), is(true));
    }
}
