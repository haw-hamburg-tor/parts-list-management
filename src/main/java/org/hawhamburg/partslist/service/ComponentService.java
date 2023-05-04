package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.Component;
import org.hawhamburg.partslist.persistence.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {
    public List<Component> getAll() {
        return ComponentRepository.getInstance().getComponents();
    }

    public List<String> getAllComponentNames() {
        return ComponentRepository.getInstance().getComponentNames();
    }
}
