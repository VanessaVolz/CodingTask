package volz.vanessa.codingtask.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import volz.vanessa.codingtask.model.Data;
import volz.vanessa.codingtask.repository.DataRepository;
import volz.vanessa.codingtask.service.DataService;

@Service
public class DataServiceImpl implements DataService {
	
	private DataRepository dataRepository;
	
	private static final String PK_HEADER_NAME = "PRIMARY_KEY";
	private static final String TIMESTAMP_HEADER_NAME = "UPDATED_TIMESTAMP";
		
	@Autowired
	public DataServiceImpl(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}
	
	@Override
	public Optional<Data> findById(Long id) {
		return dataRepository.findById(id);
	}
	
	/**
	 * Reads a MultipartFile plain text comma-separated file and saves all valid records to Data database. 
	 * <br>
	 * File headers: PRIMARY_KEY,NAME,DESCRIPTION,UPDATED_TIMESTAMP
	 * <br>
	 * PRIMARY_KEY should not be blank. <br>
	 * UPDATED_TIMESTAMP should have valid format.<br>
	 * 
	 * @param file MultipartFile
	 * @return List of Data with successfully saved records
	 * @throws IOException if MultipartFile file could not be read
	 */
	@Override
	public List<Data> save(MultipartFile file) throws IOException {
		try (var fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
				var parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());) {
			
			List<Data> dataList = parser.getRecords().stream()
				.filter(this::isValidRecord) // Using only valid records
				.map(this::mapValidCSVRecordToData) // Mapping valid records to Data object
				.collect(Collectors.toList()); // Returning as list
			
			// Saving all valid records to database, assuming we are not returning error if there is no valid records
			return dataRepository.saveAll(dataList);
		}
	}
	
	@Override
	public void deleteById(Long id) {
		dataRepository.deleteById(id);
	}
	
	private boolean isValidRecord(CSVRecord csvRecord) {
		// Record must be consistent to the Header (four values provided), and PRIMARY_KEY must be set
		if (!csvRecord.isConsistent() || !csvRecord.isSet(PK_HEADER_NAME)) return false;
		
		try {
			// Assuming we need valid timestamp format or a blank/empty as this field is not required
			if (csvRecord.isSet(TIMESTAMP_HEADER_NAME) && !StringUtils.isWhitespaceOrEmpty(csvRecord.get(TIMESTAMP_HEADER_NAME))) {
				LocalDateTime.parse(csvRecord.get(TIMESTAMP_HEADER_NAME));
			}
			// Assuming PRIMARY_KEY is Long, should be a valid long
			Long.parseLong(csvRecord.get(PK_HEADER_NAME));
		} catch (NumberFormatException | DateTimeParseException e) {
			//Should not store invalid format record, we can handle as needed, only logging with sysout for demonstration purposes
			System.out.println("Invalid format for record of line " + csvRecord.getRecordNumber() + ". Message: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	private Data mapValidCSVRecordToData(CSVRecord csvRecord) {
		return new Data(Long.parseLong(csvRecord.get(PK_HEADER_NAME)),
				csvRecord.get("NAME"),
				csvRecord.get("DESCRIPTION"),
				StringUtils.isWhitespaceOrEmpty(csvRecord.get(TIMESTAMP_HEADER_NAME)) ? null : LocalDateTime.parse(csvRecord.get(TIMESTAMP_HEADER_NAME)));
	}
	 
}
