package ro.fasttrackit.tema6exerc2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Profile("memory")
public class MemoryCountryReader implements CountryReader 
{
	private static long id = 1;
	
	@Override
	public List<Country> readCountries()
	{
		List<Country> countries = new ArrayList<Country>(10);
		
		countries.add( new Country(Long.valueOf(id++), "Aland Islands", "Mariehamn", Long.valueOf(28875),
					Integer.valueOf(1580), "Europe", new String[] {}) );
		countries.add( new Country(Long.valueOf(id++), "Austria", "Vienna", Long.valueOf(8725931),
				Integer.valueOf(83871), "Europe", new String[] {"CZE", "DEU", "HUN", "ITA", "LIE", "SVK", "SVN",  "CHE"}) );
		countries.add( new Country(Long.valueOf(id++), "Belgium", "Brussels", Long.valueOf(11319511),
				Integer.valueOf(30528), "Europe", new String[] {"FRA", "DEU", "LUX", "NLD"}) );
		countries.add( new Country(Long.valueOf(id++), "Belize", "Belmopan", Long.valueOf(370300),
				Integer.valueOf(22966), "Americas", new String[] {"GTM", "MEX"}) );
		countries.add( new Country(Long.valueOf(id++), "Colombia", "Bogota", Long.valueOf(48759958),
				Integer.valueOf(1141748), "Americas", new String[] {"BRA", "ECU", "PAN", "PER", "VEN"}) );
		countries.add( new Country(Long.valueOf(id++), "Denmark", "Copenhagen", Long.valueOf(5717014),
				Integer.valueOf(43094), "Europe", new String[] {"DEU"}) );
		countries.add( new Country(Long.valueOf(id++), "Finland", "Helsinki", Long.valueOf(5491817),
				Integer.valueOf(338424), "Europe", new String[] {"NOR", "SWE", "RUS"}) );
		countries.add( new Country(Long.valueOf(id++), "Gambia", "Banjul", Long.valueOf(1882450),
				Integer.valueOf(11295), "Africa", new String[] {"SEN"}) );
		countries.add( new Country(Long.valueOf(id++), "Indonesia", "Jakarta", Long.valueOf(258705000),
				Integer.valueOf(1904569), "Asia", new String[] {"TLS", "MYS", "PNG"}) );
		countries.add( new Country(Long.valueOf(id++), "Fiji", "Suva", Long.valueOf(867000),
				Integer.valueOf(18272), "Oceania", new String[] {}) );

		return countries;
	}

}
