package com.example.events.EventController;

import com.example.events.ApiResponse.ApiResponse;
import com.example.events.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    // Create a new event (ID , description , capacity, startDate , endDate)
    @PostMapping("/create/event")
    public ApiResponse createEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event created" );
    }

    //• Display all event .
    @GetMapping("/get")
    public ArrayList<Event> getEvents() {
        return events;
    }

    //• Update a event
    @PutMapping("/update/{index}")
    public ApiResponse updateEvents(@PathVariable int index, @RequestBody Event event) {
        if (index < 0 || index >= events.size()) {
            return new ApiResponse("Event index out of bounds", event);
        }
        events.set(index, event);
        return new ApiResponse("Event updated" );
    }

    //• Delete a event
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index) {
        if (index < 0 || index >= events.size()) {
            return new ApiResponse("Event index out of bounds");
        }
        events.remove(index);
        return new ApiResponse("Event deleted" );
    }

    //• Change capacity
    @PutMapping("/change/capacity/{index}")
public ApiResponse changeCapacity(@PathVariable int index , @RequestParam int newCapacity) {
        if (index < 0 || index >= events.size()) {
            return new ApiResponse("Event index out of bounds");
        }
        events.get(index).setCapacity(newCapacity);
        return new ApiResponse("Event Capacity changed");
}

//• Search for a event by given id
    @GetMapping("/search/{id}")
    public ApiResponse getEventById(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return new ApiResponse("Event found", event);
            }
        }
        return new ApiResponse("Event not found");
    }
}
