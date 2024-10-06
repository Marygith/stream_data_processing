# Weather data processing
Stream data processing project, based on weather data

## Data source
As a data source will serve custom server, sending weather data every second.

## Data structure

### Basic attributes
    private double temperature;          // Temperature in Celsius
    private double humidity;             // Humidity as a percentage
    private double pressure;             // Atmospheric pressure in hPa (hectopascal)
    private double windSpeed;            // Wind speed in meters per second

### Geographic and temporal attributes
    private double latitude;             // Latitude of the location
    private double longitude;            // Longitude of the location
    private String observationTime;      // Time of the observation (ISO 8601 format)

## Desired results
Goal of data processing is calculating predictability of weather for specific location, based on incoming weather data and forecast.

## Machine learning usage
Machine learning can be used to create forecast and to make it more precise.

## Latency resistance && data loss concerns
As weather changes not so frequently (not a matter of seconds), data loss and latency for weather data receiving are tolerable.
However, it is crucial to secure data till the moment of comparison real data with forecast.

For data receiving at-most-one semantics seems to be the most suitable. Replicas must be configured for temporary data storage.