package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.Material;
import org.hawhamburg.partslist.persistence.ComponentRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService {
    public Material createMaterial(String name, Integer price) {
        return ComponentRepository.getInstance().createMaterial(name, price);
    }
}
