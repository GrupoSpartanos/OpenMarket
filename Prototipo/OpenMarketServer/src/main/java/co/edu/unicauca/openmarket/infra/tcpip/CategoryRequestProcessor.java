/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.infra.tcpip;

import co.edu.unicauca.openmarket.domain.Category;
import co.edu.unicauca.openmarket.domain.service.CategoryService;
import com.google.gson.Gson;
import com.ragjc.software.openmarketcommons.infra.Protocol;

/**
 *
 * @author RodAlejo
 */
public class CategoryRequestProcessor {
    private CategoryService categoryService;

    public CategoryRequestProcessor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String processRequest(Protocol protocolRequest) {
        String action = protocolRequest.getAction();

        switch (action) {
            case "get":
                return processGetCategory(protocolRequest);
            case "post":
                return Boolean.toString(processPostCategory(protocolRequest));
            case "put":
                return Boolean.toString(processPutCategory(protocolRequest));
            case "delete":
                return Boolean.toString(processDeleteCategory(protocolRequest));
            default:
                return "";
        }
    }

    private String processGetCategory(Protocol protocolRequest) {
        String id = protocolRequest.getParameters().get(0).getValue();
        Category category = categoryService.findCategoryById(Long.parseLong(id));
        if (category == null) {
            String errorJson = JsonUtils.generateNotFoundErrorJson("Categoría no encontrada. ID no existe");
            return errorJson;
        } else {
            return JsonUtils.objectToJSON(category);
        }
    }

    private boolean processPostCategory(Protocol protocolRequest) {
        Category category = new Category();
        category.setName(protocolRequest.getParameters().get(0).getValue());
        return categoryService.saveCategory(category.getName());
    }

    private boolean processPutCategory(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        Category category = new Category();
        category.setName(protocolRequest.getParameters().get(1).getValue());
        return categoryService.editCategory(id, category);
    }

    private boolean processDeleteCategory(Protocol protocolRequest) {
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        return categoryService.deleteCategory(id);
    }

    public String processGetCategories() {
        return new Gson().toJson(categoryService.findAllCategories());
    }
}
