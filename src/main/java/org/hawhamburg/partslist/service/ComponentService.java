package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.Component;
import org.hawhamburg.partslist.persistence.ComponentRepository;

import java.util.List;

public class ComponentService {

    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /**
     * @return a list of all components that have been created so far.
     */
    public List<Component> getAll() {
        return componentRepository.getComponents();
    }

    /**
     * @return a list of the names of all components that have been created so far.
     */
    public List<String> getAllComponentNames() {
        return componentRepository.getComponentNames();
    }
}
