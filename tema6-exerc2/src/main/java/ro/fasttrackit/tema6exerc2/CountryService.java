package ro.fasttrackit.tema6exerc2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CountryService
{
	List<Country> countries;
	
	public CountryService(CountryReader countryReader)
	{
		countries = countryReader.readCountries();
	}
	
	// metoda ce returneaza un singur obiect 'Country', dupa ID
	public Optional<Country> getReferenceById(long id)
	{
		// pentru cautarea dupa ID se poate folosi o bucla while sau stream-uri
		// pt. ca se folosesc stream-uri, rezultatul va fi de tip Optional<Country>, nu de tip Country
		// se cauta obiectul de tip Country dupa ID, folosind 'filter()' si 'findFirst()'
		Optional<Country> optCountry = countries.stream()
												.filter((Country country) -> country.id() == id)
												.findFirst();
		return optCountry;
	}
	
	// metoda ce returneaza toate obiectele 'Country" din repozitoriu
	public List<Country> findAll() { return countries; }
	
	public List<String> getAllCountryNames()
	{
		return countries.stream()
						.map((Country country) -> country.name())
						.collect(Collectors.toList());
	}
	
	public Optional<String> getCapitalById(long id)
	{
		// se cauta tara dupa ID-ul repectiv
		Optional<Country> optCountry = getReferenceById(id);
		
		// daca tara a fost gasita
		if(optCountry.isPresent())
			return Optional.of(optCountry.get().capital());
		else
			return Optional.ofNullable(null);
	}
	
	public long getPopulationById(long id)
	{
		// se cauta tara dupa ID-ul repectiv
		Optional<Country> optCountry = getReferenceById(id);
		
		// daca tara a fost gasita
		if(optCountry.isPresent())
			return optCountry.get().population();
		else
			return 0;
	}
	
	public List<String> getNeighboursById(long id)
	{
		// se cauta tara dupa ID-ul repectiv
		Optional<Country> optCountry = getReferenceById(id);
		
		// daca tara a fost gasita
		if(optCountry.isPresent())
			return Arrays.asList(optCountry.get().neighbours());
		else
			return new ArrayList<String>(0);
		
	}
	
	public List<Country> getAllByIncludeExcludeNeighbours(String includedNeighbourCode, String excludedNeighbourCode)
	{
		if(includedNeighbourCode != null && excludedNeighbourCode != null)
			return  countries.stream()
						.filter((Country country) -> Arrays.asList(country.neighbours()).contains(includedNeighbourCode))
						.filter((Country country) -> Arrays.asList(country.neighbours()).contains(excludedNeighbourCode) == false)
						.collect(Collectors.toList());
		else
			// ca sa nu se returneze null se initializeaza un List<Country> gol (si doar la nevoie (lazy))
			return new ArrayList<Country>();
	}
	
	public Map<String, Long> getCountryToPopulationMap()
	{
		Map<String, Long> map = countries.stream()
									.collect(Collectors.toMap(Country::name, Country::population));
		return map;
	}
	
	public List<Country> getAllBy(String continent)
	{
		if(continent != null)
			// cu stream, se gasesc toate tarile ce apartin continentul trimis ca argument
			return countries.stream()
							.filter((Country country) -> country.continent().equals(continent))
							.collect(Collectors.toList());
		else
			// ca sa nu se returneze null se initializeaza un List<Country> gol (si doar la nevoie (lazy))
			return new ArrayList<Country>();
	}
	
	public List<Country> getAllBy(String continent, long minPopulation)
	{
		if(continent != null)
			// cu stream, se gasesc toate tarile ce apartin continentul trimis ca argument
			// si au populatia >= cu minPopulation
			return countries.stream()
								.filter((Country country) -> country.continent().equals(continent))
								.filter((Country country) -> country.population() >= minPopulation)
								.collect(Collectors.toList());
		else
			// ca sa nu se returneze null se initializeaza un List<Country> gol (si doar la nevoie (lazy))
			return new ArrayList<Country>();
	}
	
	public Map<String, List<Country>> getContinentToCountriesMap()
	{
		// se grupeaza tarile dupa continent; pentru grupare se foloseste 'groupingBy()', iar gruparea se face
		// dupa rezultatul metodei 'continent()', ce returneaza field-ul cu acelasi nume
		return countries.stream()
						.collect(Collectors.groupingBy(Country::continent));
	}
}
