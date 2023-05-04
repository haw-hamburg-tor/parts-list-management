package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.CyclicStructureException;
import org.hawhamburg.partslist.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductServiceTests {

    @Test
    public void addPartReflexiveCycle() {
        // Arrange
        var p1 = new Product("p1", 1);

        // Act & Assert
        assertThrows(CyclicStructureException.class, () -> p1.addPart(p1, 1));
    }

}
