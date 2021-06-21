package volz.vanessa.codingtask.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import volz.vanessa.codingtask.model.Data;
import volz.vanessa.codingtask.service.DataService;
import volz.vanessa.codingtask.util.FileUtil;

/**
 * 
 * @author Vanessa
 *
 */
@RestController
@RequestMapping("/api/data")
public class DataController {
	
	private final DataService dataService;
		
	@Autowired
	public DataController(DataService dataService) {
		this.dataService = dataService;		
	}

	@PostMapping
	public ResponseEntity<List<Data>> uploadFile(@RequestParam("file") MultipartFile dataFile) throws IOException {
		if (FileUtil.isTextFormat(dataFile)) {
			List<Data> dataList = dataService.save(dataFile);
			return ResponseEntity.ok().body(dataList);
		}
		
		// Sysout only for demonstration purposes
		System.out.println("Bad Request. Expected text/plain format file.");
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{primaryKey}")
	public ResponseEntity<Data> findById(@PathVariable("primaryKey") Long id) {
		Optional<Data> data = dataService.findById(id);
		
		if (data.isPresent()) {
			return ResponseEntity.ok().body(data.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{primaryKey}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("primaryKey") Long id) {
		dataService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
