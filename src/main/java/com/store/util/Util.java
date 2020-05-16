package com.store.util;

import com.store.DTO.UpdateCartItem;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    static public String hashPasssword(String password) {
        //TODO
        return password + "_HASHED";
    }

    static public List<UpdateCartItem> handleConvertLisUpdateItem(String s) {
        String[] subString = s.split(",");
        ObjectMapper mapper = new ObjectMapper(); //Jackson's JSON marshaller
        List<UpdateCartItem> updateCartItems = new ArrayList<UpdateCartItem>();
        UpdateCartItem temp = null;
        try {
            for (int i = 0; i < subString.length; i+=2) {
                temp = mapper.readValue(subString[i]+","+subString[i+1], UpdateCartItem.class);
                updateCartItems.add(temp);
            }
        } catch (IOException e) {
            throw new WebApplicationException();
        }

        return updateCartItems;
    }
}
