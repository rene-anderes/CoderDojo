package org.anderes.edu.dojo.rest.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;

public class WeatherRepositoryTest {

    private final static Integer EXIST_ID = 20987;
    private final static Integer EXIST_ID_TO_DELETE = 20999;
    private final static Integer NOT_EXIST_ID = 33333;
    private final WeatherRepository repository = WeatherRepositoryStub.INSTANCE;
    
    @Test
    public void shouldBeFindWeatherDataById() throws RepositoryException {
        // when
        final Optional<WeatherData> data = repository.findWeatherDataById(EXIST_ID);
        
        // then
        assertThat(data, is(not(nullValue())));
        assertThat(data.isPresent(), is(true));
        final WeatherData weatherData = data.get();
        assertThat(weatherData.getId(), is(EXIST_ID));
        assertThat(weatherData.getAtmosphericPressure(), is(1000.67));
        assertThat(weatherData.getDensity(), is(2.6));
        assertThat(weatherData.getWindDirection(), is(45));
        assertThat(weatherData.getWindSpeed(), is(10.45));
    }
    
    @Test
    public void shouldBeNotFindWeatherDataById() throws RepositoryException {
        // when
        final Optional<WeatherData> data = repository.findWeatherDataById(NOT_EXIST_ID);
        
        // then
        assertThat(data, is(not(nullValue())));
        assertThat(data.isPresent(), is(false));
    }
    
    @Test
    public void shouldBeStoreDataWithoutId() throws RepositoryException {
        // given
        final WeatherData data = new WeatherData();
        data.setTemperature(22.4).setAtmosphericPressure(1008.01).setDensity(2.45).setWindDirection(60).setWindSpeed(18.9);
        
        // when
        final WeatherData weatherData = repository.storeData(data);
        
        // then
        assertThat(weatherData, is(not(nullValue())));
        assertThat(weatherData.getId(), is(not(nullValue())));
        assertThat(weatherData.getId(), is(not(Integer.MIN_VALUE)));
        assertThat(weatherData.getAtmosphericPressure(), is(1008.01));
        assertThat(weatherData.getDensity(), is(2.45));
        assertThat(weatherData.getTemperature(), is(22.4));
        assertThat(weatherData.getWindDirection(), is(60));
        assertThat(weatherData.getWindSpeed(), is(18.9));
    }
    
    @Test
    public void shouldBeStoreDataWithId() throws RepositoryException {
        // given
        final WeatherData data = new WeatherData(500);
        data.setTemperature(22.5).setAtmosphericPressure(1018.01).setDensity(2.55).setWindDirection(45).setWindSpeed(0);
        
        // when
        final WeatherData weatherData = repository.storeData(data);
        
        // then
        assertThat(weatherData, is(not(nullValue())));
        assertThat(weatherData.getId(), is(not(nullValue())));
        assertThat(weatherData.getId(), is(500));
        assertThat(weatherData.getAtmosphericPressure(), is(1018.01));
        assertThat(weatherData.getDensity(), is(2.55));
        assertThat(weatherData.getTemperature(), is(22.5));
        assertThat(weatherData.getWindDirection(), is(45));
        assertThat(weatherData.getWindSpeed(), is(0.0));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldBeStoreDataWithExistId() throws RepositoryException {
        // given
        final WeatherData data = new WeatherData(EXIST_ID);
        data.setTemperature(20.5);
        
        // when
        repository.storeData(data);
    }  
    
    @Test
    public void shouldBeUpdateData() throws RepositoryException {

        // when
        final Optional<WeatherData> value = repository.findWeatherDataById(EXIST_ID);
        assertThat(value, is(not(nullValue())));
        assertThat(value.isPresent(), is(true));
        final WeatherData data = value.get();
        data.setTemperature(20.5);
        final WeatherData weatherData = repository.updateData(data);
        
        // then
        assertThat(weatherData.getId(), is(EXIST_ID));
        assertThat(weatherData.getAtmosphericPressure(), is(1000.67));
        assertThat(weatherData.getDensity(), is(2.6));
        assertThat(weatherData.getTemperature(), is(20.5));
        assertThat(weatherData.getWindDirection(), is(45));
        assertThat(weatherData.getWindSpeed(), is(10.45));
    }
    
    @Test
    public void shouldBeDeleteData() throws RepositoryException {
        // given
        final WeatherData data = new WeatherData(EXIST_ID_TO_DELETE);
        data.setTemperature(16.4).setAtmosphericPressure(1050.22).setDensity(2.3).setWindDirection(90).setWindSpeed(0.0);
       
        // when
        boolean ok = repository.deleteData(data);
        
        // then 
        assertThat(ok, is(true));
    }
    
    @Test
    public void shouldBeFindAll() throws RepositoryException {
        
        // when
        final Collection<WeatherData> data = repository.findAllWeatherData();
        
        // then
        assertThat(data, is(not(nullValue())));
        assertThat(data.isEmpty(), is(false));
    }
}
