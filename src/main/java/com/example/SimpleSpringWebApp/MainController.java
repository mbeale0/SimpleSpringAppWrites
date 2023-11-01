package com.example.SimpleSpringWebApp;

import java.net.URI;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.log.Log;

@Controller
@RequestMapping(path="/") 
public class MainController {
  
  @Autowired
  private MessageRepo messageRepository;

  @GetMapping("/GetMessage")
  public ResponseEntity<Object> readLog() throws JsonProcessingException{
    ArrayList<Message> messages = (ArrayList) messageRepository.findAll();
    
    ObjectMapper objectMapper = new ObjectMapper();
    String result = objectMapper.writeValueAsString(messages);
    
    if (messages == null) {
        return ResponseEntity.notFound().build();
    } else {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result).toUri();
        //return ResponseEntity.notFound().build();
        return ResponseEntity.created(uri).body(result);
    }
  }


  @GetMapping("/LogWriter")
  public String getMessageInput(Model model){
    model.addAttribute("message", new Message());
    
    return "LogWriter";
  }

  @PostMapping("/LogWriter")
  public String saveMessage(@ModelAttribute Message message, Model model){
    model.addAttribute("message", message);
    messageRepository.save(message);
    return "http://localhost:8080/Log";
  }


}