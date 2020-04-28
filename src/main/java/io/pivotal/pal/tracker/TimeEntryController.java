package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
   // @Autowired
    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }
    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);

    }
@GetMapping("{timeEntryId}")
    public ResponseEntity read(@PathVariable long timeEntryId) {
    TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
    if(timeEntry==null)
        return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
    else
        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }
@GetMapping()
    public ResponseEntity list() {
    List<TimeEntry> timeEntryList = timeEntryRepository.list();
    return new ResponseEntity<>(timeEntryList, HttpStatus.OK);

    }
    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId, expected);
        if(timeEntry!=null) {
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
         timeEntryRepository.delete(timeEntryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
