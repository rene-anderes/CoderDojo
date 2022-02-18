package org.anderes.edu.dojo.rest.persistence;

import java.util.Collection;
import java.util.Optional;

public interface WeatherRepository {

    /**
     * Liefert, wenn vorhanden, die entsprechenden Wetterdaten anhand der ID zurück.
     * 
     * @param id
     *            Eindeutiger Identifikator
     * @return Wetterdaten oder Empty
     */
    Optional<WeatherData> findWeatherDataById(Integer id) throws RepositoryException;

    /**
     * Speichert neue Wetterdaten.
     * <p\>
     * Falls keine ID existiert, wird eine neue erstellt. Ist die vorhandene ID
     * nicht eindeutig, wird eine Exception ausgelöst.
     * 
     * @param data
     *            Neue Wetterdaten
     * @return Wetterdaten die gespeichert wurden
     * @throws IllegalArgumentException
     *             falls die Wetterdaten nicht eindeutig sind.
     */
    WeatherData storeData(WeatherData data) throws RepositoryException;

    /**
     * Aktualisiert bestehende Wetterdaten.
     * 
     * @param updatedData
     *            Aktualisierte Wetterdaten
     * @return Wetterdaten die gespeichert wurden
     * @throws IllegalArgumentException
     *             falls keine entsprechenden Wetterdaten zum aktualisieren vorhanden sind
     */
    WeatherData updateData(WeatherData updatedData) throws RepositoryException;

    /**
     * Löscht bestehende die Wetterdaten
     * 
     * @param data
     *            Wetterdaten zum löschen
     * @return {@code true}, wenn die Daten gelöscht werden konnten
     */
    boolean deleteData(WeatherData data) throws RepositoryException;

    /**
     * Liefert alle Wetterdaten als Liste zurück.
     * 
     * @return Liste der Wetterdaten
     */
    Collection<WeatherData> findAllWeatherData() throws RepositoryException;
}
