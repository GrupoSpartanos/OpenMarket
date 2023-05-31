/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.access.CategoryRepository;
import co.edu.unicauca.openmarket.access.ProductRepository;
import co.edu.unicauca.openmarket.access.UserRepository;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import co.edu.unicauca.openmarket.domain.service.UserService;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;
import java.util.Scanner;

/**
 *
 * @author RodAlejo
 */
public class OpenMarketServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        
        // Crear instancias de los servicios y repositorios
        
        ProductService productService = new ProductService(new ProductRepository());
        CategoryService categoryService = new CategoryService(new CategoryRepository());
        UserService userService = new UserService(new UserRepository());
        
        ProductRequestProcessor productRequestProcessor  = new ProductRequestProcessor(productService);
        CategoryRequestProcessor categoryRequestProcessor = new CategoryRequestProcessor(categoryService);
        UserRequestProcessor userRequestProcessor = new UserRequestProcessor(userService);
        
        RequestProcessor requestProcessor = new RequestProcessor();
        
        
        requestProcessor.setProductRequestProcessor(productRequestProcessor);
        requestProcessor.setCategoryRequestProcessor(categoryRequestProcessor);
        requestProcessor.setUserRequestProcessor(userRequestProcessor);

        
        OpenMarketHandler myHandler = new OpenMarketHandler();
        myHandler.setRequestProcessor(requestProcessor);
        
        // Asignar los handlers al servidor
        myServer.setServerHandler(myHandler);
        
        // Iniciar el servidor
        myServer.startServer();
    }
    
}
