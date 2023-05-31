/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ragjc.software.openmarketclient.access;

import com.ragjc.software.openmarketcommons.domain.Category;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RodAlejo
 */
public class CategoryRemoteRepository implements ICategoryRepository {
    private CategoryAccessImplSockets categoryAccess;
    
    public CategoryRemoteRepository(){
        categoryAccess = new CategoryAccessImplSockets();
    }
    
    @Override
    public boolean save(Category category) {
        try {
            return categoryAccess.save(category);
            
        } catch (Exception ex) {
            Logger.getLogger(CategoryRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean edit(Long id, Category category) {
        try {
            return categoryAccess.edit(id, category);
            
        } catch (Exception ex) {
            Logger.getLogger(CategoryRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            return categoryAccess.delete(id);
            
        } catch (Exception ex) {
            Logger.getLogger(CategoryRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Category findById(Long id) {
        try {
            return categoryAccess.findById(id);
            
        } catch (Exception ex) {
            Logger.getLogger(CategoryRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Category> findbyName(String name) {
        try {
            return categoryAccess.findbyName(name);
            
        } catch (Exception ex) {
            Logger.getLogger(CategoryRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        try {
            return categoryAccess.findAll();
            
        } catch (Exception ex) {
            Logger.getLogger(CategoryRemoteRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}