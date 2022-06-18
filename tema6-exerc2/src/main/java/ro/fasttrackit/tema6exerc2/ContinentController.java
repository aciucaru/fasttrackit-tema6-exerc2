package ro.fasttrackit.tema6exerc2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//controller pentru subdomeniul "/continents/"
@RestController
@RequestMapping("continents") // subdomeniu la nivel de clasa (controller), toate metodele vor fi relative la acest subdomeniu
public class ContinentController
{
	private final CountryService countryService;
	
	public ContinentController(CountryService countryService)
	{
		this.countryService = countryService;
	}
	
	@GetMapping("/{continent}/countries")
	public List<Country> getAllCountriesByContinent(@PathVariable String continent)
	{
		if(continent != null)
			// cu stream, se gasesc toate tarile ce apartin continentul trimis ca argument
			return countryService.getAllBy(continent);
		else
			// ca sa nu se returneze null se initializeaza un List<Country> gol (si doar la nevoie (lazy))
			return new ArrayList<Country>();
	}
	
	@GetMapping(value = "/{continent}/countries", params = "minPopulation")
	public List<Country> getAllCountriesByContinentAndMinPopulation(@PathVariable String continent,
																	@RequestParam long minPopulation)
	{
		if(continent != null)
			return countryService.getAllBy(continent, minPopulation);
		else
			// ca sa nu se returneze null se initializeaza un List<Country> gol (si doar la nevoie (lazy))
			return new ArrayList<Country>();
	}
	
	@GetMapping("/countries")
	public Map<String, List<Country>> getContinentToCountriesMap()
	{
		return countryService.getContinentToCountriesMap();
	}
}
