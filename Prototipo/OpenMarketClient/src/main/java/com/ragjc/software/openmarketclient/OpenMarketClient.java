/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ragjc.software.openmarketclient;

import com.ragjc.software.openmarketclient.access.Factory;
import com.ragjc.software.openmarketclient.access.ICategoryRepository;
import com.ragjc.software.openmarketclient.access.IProductRepository;
import com.ragjc.software.openmarketclient.domain.service.CategoryService;
import com.ragjc.software.openmarketclient.domain.service.ProductService;
import com.ragjc.software.openmarketclient.presentation.*;

/**
 *
 * @author RodAlejo
 */
public class OpenMarketClient {

    public static void main(String[] args) {
        GUILoginUser guiLogin = new GUILoginUser();
        guiLogin.setVisible(true);
        
    }
}
