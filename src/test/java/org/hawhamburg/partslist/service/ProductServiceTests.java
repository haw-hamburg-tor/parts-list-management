package org.hawhamburg.partslist.service;

import org.hawhamburg.partslist.model.CyclicStructureException;
import org.hawhamburg.partslist.model.Material;
import org.hawhamburg.partslist.model.Product;
import org.hawhamburg.partslist.persistence.ComponentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTests {


    private ProductService productService;
    private ComponentRepository componentRepository;

    @BeforeEach
    void setUp() {
        componentRepository = Mockito.mock(ComponentRepository.class);
        productService = new ProductService(componentRepository);
    }

    @Test
    public void createProduct() {
        // Arrange
        var name = "p2";
        var price = 10;
        var componentNames = Arrays.asList("m1", "p1");
        var componentAmounts = Arrays.asList(2, 5);

        var expectedProduct = new Product(name, price);
        when(componentRepository.createProduct(name, price, componentNames, componentAmounts)).thenReturn(expectedProduct);

        // Act
        var actualProduct = productService.createProduct(name, price, componentNames, componentAmounts);

        // Assert
        assertEquals(expectedProduct, actualProduct);
        verify(componentRepository, times(1)).createProduct(name, price, componentNames, componentAmounts);
    }

    @Test
    public void getMaterialList() throws CyclicStructureException {
        // Arrange
        var p1 = new Product("p1", 2);
        var m1 = new Material("m1", 5);
        var m2 = new Material("m2", 10);
        p1.addPart(m1, 1);
        p1.addPart(m2, 2);

        var expectedMaterialList = List.of(m1, m2, m2);

        when(componentRepository.getProduct(p1.getName())).thenReturn(p1);

        // Act
        var actualMaterialList = productService.getMaterialList(p1.getName());

        // Assert
        assertEquals(expectedMaterialList, actualMaterialList);
        verify(componentRepository, times(1)).getProduct(p1.getName());
    }

    @Test
    public void getAllNames() {
        // Arrange
        var expectedComponentNames = Arrays.asList("m1", "m2", "p1");
        when(componentRepository.getProductNames()).thenReturn(expectedComponentNames);

        // Act
        var actualComponentNames = productService.getAllNames();

        // Assert
        assertEquals(expectedComponentNames, actualComponentNames);
        verify(componentRepository, times(1)).getProductNames();
    }
}
