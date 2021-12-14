package com.assingment.hepsiburada.controller;

import com.assingment.hepsiburada.data.Data;
import com.assingment.hepsiburada.model.Fruit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroceryController {
    private static List<Fruit> myFruitStock = new ArrayList<>();
    private static Data data = new Data();

    static {

        Fruit apple = new Fruit(1L, "apple", 3, 100);
        myFruitStock.add(apple);

        Fruit grapes = new Fruit(2L, "grapes", 5, 50);
        myFruitStock.add(grapes);
        data.setData(myFruitStock);
    }

//    private String name;


    @RequestMapping(value = "/allGrocery", method = RequestMethod.GET)
    public Data allGrocery() {
        return data;
    }

    @RequestMapping(value = "/allGrocery/{name}", method = RequestMethod.GET)
    public ResponseEntity<Data> allGrocery(@PathVariable(value = "name") final String name) {
        Data resp = new Data();
        List<Fruit> fruitList = new ArrayList<>();
        for (Fruit fruit : data.getData()) {
            if (fruit.getName().equals(name)) {
                fruitList.add(fruit);
                resp.setData(fruitList);
                return new ResponseEntity<>(resp, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/add")
    ResponseEntity<Fruit> newFruit(@RequestBody Fruit fruit){
        Fruit newFruit= fruit;
        myFruitStock.add(newFruit);
        data.setData(myFruitStock);
        return new ResponseEntity<>(fruit, HttpStatus.CREATED);
    }
}
