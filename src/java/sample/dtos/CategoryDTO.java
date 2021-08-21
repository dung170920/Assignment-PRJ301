/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dtos;

/**
 *
 * @author ASUS
 */
public class CategoryDTO {
    private String id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String id  , String name) {
        this.id = id;
        this.name = name;
    }
    
    public CategoryDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
