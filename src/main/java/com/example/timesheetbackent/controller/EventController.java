package com.example.timesheetbackent.controller;


import com.example.timesheetbackent.model.Event;
import com.example.timesheetbackent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;



    @PostMapping("/addEventByManager/{ids}/{id}")
    public Event addEvent(@PathVariable("ids") Long[] ids,@PathVariable("id") Long id,@RequestBody Event event){
        return eventService.addEvent(id,ids,event);
    }


    @GetMapping("/getAllEventById/{id}")
    public List<Event> eventList(@PathVariable("id") Long id){
        return eventService.getAllEvent(id);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/deleteEventById/{id}",method = RequestMethod.DELETE)
    public void deleteEventById(@PathVariable("id") Long id){

      eventService.deleteEventById(id);
    }


}
