package org.hawhamburg.partslist.model;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComponentTests {

    /**
     * Test fetching the price of a Material.
     */
    @Test
    public void fetchPriceMaterial() {

        Material material = new Material("Material1", 100);
        assertEquals(100, material.fetchTotalPrice().intValue());

    }

    /**
     * Test fetching the price of a Product containing two Materials.
     */
    @Test
    public void fetchPriceProductWithTwoMaterials() throws CyclicStructureException {

        Material material1 = new Material("Material1", 10);
        Material material2 = new Material("Material2", 15);
        Product product = new Product("Product1", 0);
        product.addPart(material1, 1);
        product.addPart(material2, 1);
        assertEquals(25, product.fetchTotalPrice());

    }

    /**
     * Test fetching the price of a Product containing Products and Materials.
     */
    @Test
    public void fetchPriceProductWithMultipleSubProducts() throws CyclicStructureException {

        Material material1 = new Material("Material1", 10);
        Material material2 = new Material("Material2", 15);
        Product subProduct1 = new Product("SubProduct1", 0);
        subProduct1.addPart(material1, 1);
        subProduct1.addPart(material2, 1);
        Product subProduct2 = new Product("SubProduct2", 0);
        subProduct2.addPart(material1, 1);
        subProduct2.addPart(material2, 1);
        Product product = new Product("Product1", 0);
        product.addPart(subProduct1, 2);
        product.addPart(subProduct2, 3);
        product.addPart(material1, 1);
        assertEquals(135, product.fetchTotalPrice());
   

    }

    @Test
    public void fetchMaterialListProductWithMultipleSubProducts() throws CyclicStructureException {
        // Arrange
        var p1 = new Product("p1", 2);
        var p2 = new Product("p2", 5);
        var p3 = new Product("p3", 10);
        var p4 = new Product("p4", 1);
        var m1 = new Material("m1", 5);
        var m2 = new Material("m2", 10);
        var m3 = new Material("m3", 1);
        var m4 = new Material("m4", 15);

        p1.addPart(p2, 2);
        p1.addPart(p3, 1);
        p1.addPart(m3, 5);

        p2.addPart(m1, 10);
        p2.addPart(p4, 3);

        p3.addPart(m1, 3);
        p3.addPart(m2, 5);
        p3.addPart(p4, 2);

        p4.addPart(m1, 2);
        p4.addPart(m4, 1);

        var m1ExpectedResult = Collections.singletonList(m1);
        var m2ExpectedResult = Collections.singletonList(m2);
        var m3ExpectedResult = Collections.singletonList(m3);
        var m4ExpectedResult = Collections.singletonList(m4);

        var p4ExpectedResult = List.of(m1, m1, m4);
        var p3ExpectedResult = Stream.of(List.of(m1, m1, m1, m2, m2, m2, m2, m2), p4ExpectedResult, p4ExpectedResult)
                .flatMap(Collection::stream).toList();
        var p2ExpectedResult = Stream.of(List.of(m1, m1, m1, m1, m1, m1, m1, m1, m1, m1), p4ExpectedResult,
                p4ExpectedResult, p4ExpectedResult).flatMap(Collection::stream).toList();
        var p1ExpectedResult = Stream
                .of(p2ExpectedResult, p2ExpectedResult, p3ExpectedResult, List.of(m3, m3, m3, m3, m3))
                .flatMap(Collection::stream).toList();

        // Act & Assert
        assertEquals(m1ExpectedResult, m1.fetchMaterialList());
        assertEquals(m2ExpectedResult, m2.fetchMaterialList());
        assertEquals(m3ExpectedResult, m3.fetchMaterialList());
        assertEquals(m4ExpectedResult, m4.fetchMaterialList());

        assertEquals(p4ExpectedResult, p4.fetchMaterialList());
        assertEquals(p3ExpectedResult, p3.fetchMaterialList());
        assertEquals(p2ExpectedResult, p2.fetchMaterialList());
        assertEquals(p1ExpectedResult, p1.fetchMaterialList());
    }

    @Test
    public void addPartReflexiveCycle() {
        // Arrange
        var p1 = new Product("p1", 1);

        // Act & Assert
        assertThrows(CyclicStructureException.class, () -> p1.addPart(p1, 1));
    }

    @Test
    public void addPartDirectCycle() throws CyclicStructureException {
        // Arrange
        var p1 = new Product("p1", 1);
        var p2 = new Product("p2", 2);
        p1.addPart(p2, 1);

        // Act & Assert
        assertThrows(CyclicStructureException.class, () -> p2.addPart(p1, 1));
    }

    @Test
    public void addPartTransitiveCycle() throws CyclicStructureException {
        // Arrange
        var p1 = new Product("p1", 1);
        var p2 = new Product("p2", 2);
        var p3 = new Product("p3", 3);
        p1.addPart(p2, 1);
        p2.addPart(p3, 1);

        // Act & Assert
        assertThrows(CyclicStructureException.class, () -> p3.addPart(p1, 1));
    }
}
