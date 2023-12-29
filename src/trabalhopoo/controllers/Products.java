package trabalhopoo.controllers;

import java.io.Serializable;

public class Products implements Serializable {
    
    public Integer code;
    public String name;
    public Integer quantity;

    public Products(Integer code, String name, Integer quantity) {

        this.code = code;
        this.name = name;
        this.quantity = quantity;
        
    }

    
    
}
