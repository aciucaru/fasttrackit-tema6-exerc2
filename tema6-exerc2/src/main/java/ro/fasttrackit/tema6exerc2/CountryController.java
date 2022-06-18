package ro.fasttrackit.tema6exerc2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// controller pentru subdomeniul "/countries/"
@RestController
@RequestMapping("countries") // subdomeniu la nivel de clasa (controller), toate metodele vor fi relative la acest subdomeniu
public class CountryController
{
	private final CountryService countryService;
	
	public CountryController(CountryService countryService)
	{
		this.countryService = countryService;
	}
	
	@GetMapping("")
	public List<Country> getAllCountries() { return countryService.findAll(); }
	
	@GetMapping("/names")
	public List<String> getAllCountryNames()
	{
		return countryService.getAllCountryNames();
	}
	
	@GetMapping("/{id}/capital")
	public String getOneCountryCapital(@PathVariable long id)
	{
		// se cauta tara dupa ID-ul repectiv
		Optional<String> optCapital = countryService.getCapitalById(id);
		
		// daca tara a fost gasita
		if(optCapital.isPresent())
			return optCapital.get();
		else
			return ""; // nu se returneaza null
	}
	
	@GetMapping("/{id}/population")
	public Long getOneCountryPopulation(@PathVariable long id)
	{
		// se cauta tara dupa ID-ul repectiv
		Optional<Country> optCountry = countryService.getReferenceById(id);
		
		// daca tara a fost gasita
		if(optCountry.isPresent())
			return optCountry.get().population();
		else
			return Long.valueOf(0);
	}

	@GetMapping("/{id}/neighbours")
	public List<String> getOneCountryNeighbours(@PathVariable long id)
	{
		return countryService.getNeighboursById(id);
	}
	
	@GetMapping(value = "", params = "includeNeighbour, excludeNeighbour")
	public List<Country> getAllCountriesByIncludeExcludeNeighbours
							(@RequestParam("includeNeighbour") String includedNeighbourCode,
							 @RequestParam("excludeNeighbour") String excludedNeighbourCode)
	{
		if(includedNeighbourCode != null && excludedNeighbourCode != null)
			return countryService.getAllByIncludeExcludeNeighbours(includedNeighbourCode, excludedNeighbourCode);
		else
			// ca sa nu se returneze null se initializeaza un List<Country> gol (si doar la nevoie (lazy))
			return new ArrayList<Country>();
	}
	
	@GetMapping("/population")
	public Map<String, Long> getCountryToPopulationMap()
	{
		return countryService.getCountryToPopulationMap();
	}
}
