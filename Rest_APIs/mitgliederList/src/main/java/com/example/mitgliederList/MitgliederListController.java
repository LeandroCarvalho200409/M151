package com.example.mitgliederList;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class MitgliederListController {
    @Autowired
    DBConnection connection = new DBConnection();

    @GetMapping("/getMitgliederList")
    @CrossOrigin(origins = "*")
    public String getMitgliederList(
            @RequestParam (required = true) String searchText
    ){
        Connection conn = connection.getDBConnection();
        String returnString = "";
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM member WHERE last_name LIKE ?");
            System.out.println(searchText);
            query.setString(1, searchText+"%");
            ResultSet result = query.executeQuery();
            JSONArray memberArray = new JSONArray();
            while (result.next()){
                JSONObject memberObject = new JSONObject();
                memberObject.put("memberId", result.getInt("member_id"));
                memberObject.put("firstName", result.getString("first_name"));
                memberObject.put("lastName", result.getString("last_name"));
                memberObject.put("address", result.getString("address"));
                memberObject.put("city", result.getString("city"));
                memberObject.put("title", result.getString("title"));
                memberObject.put("phone", result.getString("phone"));
                memberObject.put("entryDate", result.getDate("entry_date"));
                memberArray.put(memberObject);
            }
            Gson gson = new Gson();
            String memberString = memberArray.toString();
            returnString = memberString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString;
    }
}
