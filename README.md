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
    private String windDirection;        // Wind direction as compass point (e.g., "N", "NE")
    private double precipitation;        // Precipitation in mm (rainfall/snowfall)
    private String precipitationType;    // Type of precipitation (e.g., "rain", "snow", "hail")
    private double visibility;           // Visibility distance in kilometers
    private String condition;            // General weather condition (e.g., "sunny", "cloudy", "fog")

### Extended atmospheric attributes
    private double dewPoint;             // Dew point temperature in Celsius
    private double uvIndex;              // UV Index (0 to 11+)
    private double airQualityIndex;      // Air Quality Index (AQI)
    private double particulateMatter25;  // PM2.5 level in µg/m³ (fine particulate matter)
    private double particulateMatter10;  // PM10 level in µg/m³ (larger particulate matter)
    
### Environmental attributes
    private String cloudCoverage;        // Cloud coverage as a percentage or description (e.g., "75% cloudy")
    private String weatherAlert;         // Description of any active weather alerts (e.g., "tornado warning")
    private String dayOrNight;           // "day" or "night", based on current time and location

### Astronomical attributes
    private double solarElevationAngle;  // Solar elevation angle in degrees (above horizon)
    private double lunarPhase;           // Moon phase as a percentage of illumination (0 = new moon, 100 = full moon)
    private String lunarPhaseDescription;// Textual description of the moon phase (e.g., "waxing crescent")
    
### Wind and storm conditions
    private double gustSpeed;            // Maximum wind gust speed in meters per second
    private String stormStatus;          // Description of any storm activity (e.g., "no storm", "tropical storm")
    private double lightningActivity;    // Number of lightning strikes in the vicinity (per km²)

### Geographic and temporal attributes
    private String location;             // City or region where the weather is measured
    private double latitude;             // Latitude of the location
    private double longitude;            // Longitude of the location
    private String observationTime;      // Time of the observation (ISO 8601 format)

## Desired results
Goal of data processing is creating weather forecast, for today, three days, 10 days and 30 days.

## Machine learning usage
Machine learning can be used to create forecast and to make it more precise.

## Latency resistance && data loss concerns
As weather changes not so frequently (not a matter of seconds), data loss and latency are tolerable.
That is why at-most-one semantics seems to be the most suitable.