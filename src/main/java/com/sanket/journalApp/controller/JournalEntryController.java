package com.sanket.journalApp.controller;

import com.sanket.journalApp.entity.JournalEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    private Map<Long,JournalEntry> journalEntries = new HashMap<>();

    @GetMapping()
    public List<JournalEntry> getAll(){ // localhost:8080/journal GET
        return new ArrayList<>(journalEntries.values());
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getOne(@PathVariable Long myId) {
        JournalEntry entry = journalEntries.get(myId);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entry);
    }


    @PostMapping()
    public boolean createEntry(@RequestBody JournalEntry myEntry){// localhost:8080/journal POST
        journalEntries.put(myEntry.getId(), myEntry);
        return true;


    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<String> modify(@PathVariable Long myId, @RequestBody JournalEntry myEntry) {
        if (!journalEntries.containsKey(myId)) {
            return ResponseEntity.notFound().build();
        }
        journalEntries.put(myId, myEntry);
        return ResponseEntity.ok("Entry updated");
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<String> delete(@PathVariable Long myId,@RequestBody JournalEntry myEntry){
        if (!journalEntries.containsKey(myId)) {
            return ResponseEntity.notFound().build();
        }
        journalEntries.remove(myId);
        return ResponseEntity.ok("Entry deleted");

    }


}
