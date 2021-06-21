package volz.vanessa.codingtask.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import volz.vanessa.codingtask.model.Data;

public interface DataService {
	
	/**
	 * Searches for given id in database to return Data.
	 * 
	 * @param id Long for searching on database
	 * @return Optional of Data from database
	 */
	Optional<Data> findById(Long id);
	
	/**
	 * Reads a MultipartFile plain text comma-separated file and saves records to Data database. 
	 * 
	 * @param file MultipartFile
	 * @return List of Data with successfully saved records
	 * @throws IOException if MultipartFile file could not be read
	 */
	List<Data> save(MultipartFile file) throws IOException;
	
	/**
	 * Deletes corresponding Data record in database for given id.
	 * 
	 * @param id Long for deletion on database
	 */
	void deleteById(Long id);
	
}
