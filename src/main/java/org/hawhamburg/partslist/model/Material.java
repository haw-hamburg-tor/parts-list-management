package org.hawhamburg.partslist.model;

import java.util.Collections;
import java.util.List;

public class Material extends Component {

    public Material(String name, Integer price) {
        super(name, price);
    }

    @Override
    public boolean contains(Component component) {
        return this.equals(component);
    }

    @Override
    public Integer fetchTotalPrice() {
        return this.price;
    }

    @Override
    public List<Material> fetchMaterialList() {
        return Collections.singletonList(this);
    }

    @Override
    public String getType() {
        return "Material";
    }
}
