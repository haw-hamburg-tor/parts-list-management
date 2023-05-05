package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.Material;
import org.hawhamburg.partslist.model.Product;
import org.hawhamburg.partslist.persistence.ComponentRepository;

import java.util.List;

public class ProductService {

    private final ComponentRepository componentRepository;

    public ProductService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Product createProduct(String name, Integer price, List<String> componentNames, List<Integer> componentAmounts) {
        return componentRepository.createProduct(name, price, componentNames, componentAmounts);
    }

    public List<Material> getMaterialList(String productName) {
        return componentRepository.getProduct(productName).fetchMaterialList();
    }

    public List<String> getAllNames() {
        return componentRepository.getProductNames();
    }
}
