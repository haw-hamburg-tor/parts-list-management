package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.Material;
import org.hawhamburg.partslist.persistence.ComponentRepository;

public class MaterialService {

    private final ComponentRepository componentRepository;

    public MaterialService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /**
     * @return the created Material.
     */
    public Material createMaterial(String name, Integer price) {
        return componentRepository.createMaterial(name, price);
    }
}
