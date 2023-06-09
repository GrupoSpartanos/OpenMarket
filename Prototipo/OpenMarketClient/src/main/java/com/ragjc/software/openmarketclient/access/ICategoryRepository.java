/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;
import com.ragjc.software.openmarketcommons.domain.Category;
import java.util.List;

/**
 *
 * @author RodAlejo
 */
public interface ICategoryRepository {
    boolean save(Category category);
    boolean edit(Long id, Category category);
    
    boolean delete(Long id);

    Category findById(Long id);
    
    List<Category> findbyName(String name);
    
    List<Category> findAll();
}
