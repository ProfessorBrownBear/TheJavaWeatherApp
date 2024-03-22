To provide a complete, working example of the Weather Application, I'll include a main class to tie everything together. 
This class will create instances of the various components and demonstrate their use in the application.

java

public class Main {
    public static void main(String[] args) {
        // Creating instances of WeatherService implementations
        WeatherService localWeatherService = new LocalWeatherService();
        WeatherService remoteWeatherService = new RemoteWeatherService();

        // Creating instances of BaseWeatherFormatter implementations
        BaseWeatherFormatter simpleFormatter = new SimpleWeatherFormatter();
        BaseWeatherFormatter detailedFormatter = new DetailedWeatherFormatter();

        // Creating an instance of WeatherApp with dependency injection
        WeatherApp weatherApp = new WeatherApp(localWeatherService, simpleFormatter);

        // Displaying weather information
        System.out.println("Using Local Weather Service and Simple Formatter:");
        weatherApp.displayWeather("New York");

        // Switching to a different service and formatter
        weatherApp = new WeatherApp(remoteWeatherService, detailedFormatter);

        System.out.println("\nUsing Remote Weather Service and Detailed Formatter:");
        weatherApp.displayWeather("Los Angeles");
    }
}
In this Main class:

We create instances of LocalWeatherService, RemoteWeatherService, SimpleWeatherFormatter, and DetailedWeatherFormatter.
We then instantiate WeatherApp with different combinations of weather services and formatters.
Finally, we call the displayWeather method of WeatherApp to display the weather forecast for different locations.
This complete code provides a practical demonstration of interfaces, abstraction, and dependency injection in Java. Students can run this example to see how these concepts come together in a working application.

**Interface - WeatherService**
   ```java
   public interface WeatherService {
       String getWeatherForecast(String location);
   }
   ```

2. **Implementing Interfaces - LocalWeatherService and RemoteWeatherService**
   ```java
   public class LocalWeatherService implements WeatherService {
       @Override
       public String getWeatherForecast(String location) {
           return "Local Weather for " + location + ": Sunny";
       }
   }

   public class RemoteWeatherService implements WeatherService {
       @Override
       public String getWeatherForecast(String location) {
           return "Remote Weather for " + location + ": Rainy";
       }
   }
   ```

3. **Abstraction - BaseWeatherFormatter and its implementations**
   ```java
   public abstract class BaseWeatherFormatter {
       public abstract String formatWeather(String weatherData);
   }

   public class SimpleWeatherFormatter extends BaseWeatherFormatter {
       @Override
       public String formatWeather(String weatherData) {
           return "Simple Format: " + weatherData;
       }
   }

   public class DetailedWeatherFormatter extends BaseWeatherFormatter {
       @Override
       public String formatWeather(String weatherData) {
           return "Detailed Format: Weather data - " + weatherData;
       }
   }
   ```

4. **Dependency Injection - WeatherApp**
   ```java
   public class WeatherApp {
       private WeatherService weatherService;
       private BaseWeatherFormatter weatherFormatter;

       public WeatherApp(WeatherService weatherService, BaseWeatherFormatter weatherFormatter) {
           this.weatherService = weatherService;
           this.weatherFormatter = weatherFormatter;
       }

       public void displayWeather(String location) {
           String forecast = weatherService.getWeatherForecast(location);
           String formattedForecast = weatherFormatter.formatWeather(forecast);
           System.out.println(formattedForecast);
       }
   }
