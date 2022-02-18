package org.anderes.edu.dojo.rest.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;

/**
 * Repository für die CRUD Funktionalität.
 * <p/>
 * Einfache Lösung mit Singleton und In-Memory-Daten
 * 
 * @author René Anderes
 *
 */
public enum WeatherRepositoryStub implements WeatherRepository {
    /** Einzige Instanz dieser Klasse */
    INSTANCE;

    private final List<WeatherData> values = new CopyOnWriteArrayList<>();

    WeatherRepositoryStub() {
        values.addAll(defaultValues());
    }

    private Collection<WeatherData> defaultValues() {
        final ArrayList<WeatherData> defaultData = new ArrayList<>();
        final WeatherData data1 = new WeatherData(20987);
        data1.setTemperature(20.4).setAtmosphericPressure(1000.67).setDensity(2.6).setWindDirection(45).setWindSpeed(10.45);
        defaultData.add(data1);
        final WeatherData data2 = new WeatherData(20999);
        data2.setTemperature(16.4).setAtmosphericPressure(1050.22).setDensity(2.3).setWindDirection(90).setWindSpeed(0.0);
        defaultData.add(data2);
        return defaultData;
    }

    /* (non-Javadoc)
     * @see org.anderes.edu.dojo.rest.persistence.WeatherRepo#findWeatherDataById(java.lang.Integer)
     */
    @Override
    public Optional<WeatherData> findWeatherDataById(Integer id) throws RepositoryException {
        Validate.notNull(id, "Der Parameter 'id' darf nicht null sein.");
        return values.stream().filter(v -> v.getId().equals(id)).findAny();
    }

    /* (non-Javadoc)
     * @see org.anderes.edu.dojo.rest.persistence.WeatherRepo#storeData(org.anderes.edu.dojo.rest.persistence.WeatherData)
     */
    @Override
    public WeatherData storeData(WeatherData data) throws RepositoryException {
        Validate.notNull(data, "Der Parameter 'data' darf nicht null sein.");
        if (data.getId().equals(Integer.MIN_VALUE)) {
            int newId = createNewId();
            data.setId(newId);
        } else if (findWeatherDataById(data.getId()).isPresent()) {
            throw new IllegalArgumentException("Der Datensatz mit der ID '" + data.getId() + "' existiert schon.");
        }
        values.add(data);
        return data;
    }

    private int createNewId() throws RepositoryException {
        int newId = 0;
        do {
            newId = RandomUtils.nextInt(10000, 10000000);
        } while (findWeatherDataById(newId).isPresent());
        return newId;
    }

    /* (non-Javadoc)
     * @see org.anderes.edu.dojo.rest.persistence.WeatherRepo#updateData(org.anderes.edu.dojo.rest.persistence.WeatherData)
     */
    @Override
    public WeatherData updateData(WeatherData updatedData) throws RepositoryException {
        Validate.notNull(updatedData, "Der Parameter 'updatedData' darf nicht null sein.");
        final Optional<WeatherData> storedData = findWeatherDataById(updatedData.getId());
        if (storedData.isPresent()) {
            values.remove(storedData.get());
            values.add(updatedData);
            return updatedData;
        }
        throw new IllegalArgumentException();
    }

    /* (non-Javadoc)
     * @see org.anderes.edu.dojo.rest.persistence.WeatherRepo#deleteData(org.anderes.edu.dojo.rest.persistence.WeatherData)
     */
    @Override
    public boolean deleteData(WeatherData data) throws RepositoryException {
        Validate.notNull(data, "Der Parameter 'data' darf nicht null sein.");
        return values.remove(data);
    }

    /* (non-Javadoc)
     * @see org.anderes.edu.dojo.rest.persistence.WeatherRepo#findAllWeatherData()
     */
    @Override
    public Collection<WeatherData> findAllWeatherData() throws RepositoryException {
        return values;
    }
}
