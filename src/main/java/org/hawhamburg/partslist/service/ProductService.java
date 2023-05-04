package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.Material;
import org.hawhamburg.partslist.model.Product;
import org.hawhamburg.partslist.persistence.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public Product createProduct(String name, Integer price, List<String> componentNames, List<Integer> componentAmounts) {
        return ComponentRepository.getInstance().createProduct(name, price, componentNames, componentAmounts);
    }

    public List<Material> getMaterialList(String productName) {
        return ComponentRepository.getInstance().getProduct(productName).fetchMaterialList();
    }

    public List<String> getAllNames() {
        return ComponentRepository.getInstance().getProductNames();
    }
}
