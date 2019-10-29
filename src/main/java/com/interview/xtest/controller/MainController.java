package com.interview.xtest.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.xtest.entities.Item;
import com.interview.xtest.entities.RequestObject;
import com.interview.xtest.repositories.ItemRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/save")
    public String save(@RequestParam(value = "name", defaultValue = "world") String name) throws JsonProcessingException {
        //saving items to database
        itemRepository.save(new Item("Penguines", 20, 875));
        itemRepository.save(new Item("Horses", 25, 105));
        Random random = new Random();
        //creating random items
        for(int i =0; i<55;i++){
            itemRepository.save(new Item(Integer.toString(i), random.nextInt((50-0) +1)+0, random.nextInt((500-51)+1)+51));
        }
        return "Saved";
    }

    @RequestMapping("/getitem")
    public List<Item> getItem(@RequestParam(value = "page", defaultValue = "1") String page) {
        List<Item> items = (List<Item>) itemRepository.findAll();   //get all items from database
        int page_numer = Integer.parseInt(page);
        if (items.size() >= 50) {   //as ui should show 50 units
            //return items page by page limited to 50 items
            if(page_numer * 50 < items.size()){
                return items.subList((page_numer - 1) * 50, page_numer*50);
            }
            return items.subList((page_numer - 1) * 50, items.size()-1);
        }
        return items;
    }

    @GetMapping("/getItemCount")
    public long getItemCount() {
        //getting count of all items
        return itemRepository.count();
    }

    @PostMapping("/calculate")
    public double calculate(@RequestBody List<RequestObject> requestObjects) {

        double calculatedAmount = 0;
        if (requestObjects.size() >0) {
            for (RequestObject requestObject:requestObjects) {
                if((requestObject.getNumber_of_item() / requestObject.getTotal()) < 1 ){ //if user orders less than total items of a box
                    calculatedAmount = calculatedAmount + ((requestObject.getPrice()/requestObject.getTotal()) * 1.3 * requestObject.getNumber_of_item()); //adding 30% extra to price
                } else{
                    int boxCount = (int)Math.floor(requestObject.getNumber_of_item() / requestObject.getTotal());
                    if(boxCount >= 3){
                        calculatedAmount = calculatedAmount + boxCount*0.9 * requestObject.getPrice() ; //if he orders 3 or more boxes get 10% discount
                    }else{
                        calculatedAmount = calculatedAmount + boxCount * requestObject.getPrice();
                    }
                    calculatedAmount = calculatedAmount + (requestObject.getNumber_of_item() % requestObject.getTotal()) * 1.3 * (requestObject.getPrice()/requestObject.getTotal()); //other items will get 30% extra price
                }
                if(requestObject.getNumber_of_box() >= 3) {
                    calculatedAmount = calculatedAmount + requestObject.getNumber_of_box() * 0.9 * requestObject.getPrice();
                }
                else{
                    calculatedAmount = calculatedAmount + requestObject.getNumber_of_box() * requestObject.getPrice();
                }
            }


            return calculatedAmount;
        }
        return -1;

    }


}
