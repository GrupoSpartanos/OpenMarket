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
public interface ICategoryAccess {
    boolean save(Category category) throws Exception;;
    boolean edit(Long id, Category category) throws Exception;;
    
    boolean delete(Long id) throws Exception;;

    Category findById(Long id) throws Exception;;
    
    List<Category> findbyName(String name) throws Exception;;
    
    List<Category> findAll() throws Exception;;
}
