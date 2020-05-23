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
}
