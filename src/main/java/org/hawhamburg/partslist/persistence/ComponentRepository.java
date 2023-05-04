package org.hawhamburg.partslist.persistence;

import org.hawhamburg.partslist.model.Component;
import org.hawhamburg.partslist.model.Material;
import org.hawhamburg.partslist.model.Product;

import java.util.List;

public class ComponentRepository {

    public ComponentRepository() {
    }


    public Material createMaterial(String name, Integer price) {
        throw new UnsupportedOperationException("Not implemented Yet!");
    }

    public Product createProduct(String name, Integer price, List<String> componentNames, List<Integer> componentAmounts) {
        throw new UnsupportedOperationException("Not implemented Yet!");
    }


    public Product getProduct(String name) {
        throw new UnsupportedOperationException("Not implemented Yet!");
    }

    public List<String> getProductNames() {
        throw new UnsupportedOperationException("Not implemented Yet!");
    }

    public List<Component> getComponents() {
        throw new UnsupportedOperationException("Not implemented Yet!");
    }

    public List<String> getComponentNames() {
        throw new UnsupportedOperationException("Not implemented Yet!");
    }
}
