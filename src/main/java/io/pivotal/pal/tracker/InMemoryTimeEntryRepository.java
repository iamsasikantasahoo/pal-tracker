package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{


    HashMap<Long,TimeEntry> repo=new HashMap<>();
    Long counter=0L;



    public TimeEntry create(TimeEntry timeEntry) {
       timeEntry.setId(++counter);
       repo.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {

        return repo.get(id);
    }

    public List<TimeEntry> list() {



        return new ArrayList<>(repo.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(repo.containsKey(id)) {
            timeEntry.setId(id);
            repo.put(id, timeEntry);

            return timeEntry;
        }else{
            return null;
        }
    }

    public void delete(long id) {

        repo.remove(id);

    }
}
