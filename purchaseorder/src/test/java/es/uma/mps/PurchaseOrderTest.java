package es.uma.mps;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class PurchaseOrderTest {
    @Test
    void shouldPurchaseWorkPropertyWhenThereAreEnoughProducts() {
        // Step 1. Create the mock object
        Warehouse warehouse = mock(Warehouse.class);

        // Step 2. Define behaviour
        Mockito.when(warehouse.thereAreProducts("Bread", 15)).thenReturn(true);

        // Step 3. Execute method
        PurchaseOrder purchaseOrder = new PurchaseOrder("Bread", 15);
        purchaseOrder.purchase(warehouse);

        // Step 4. Verify
        verify(warehouse).remove("Bread", 15);
    }

    @Test
    void shouldPurchaseWorkPropertyWhenThereAreNOTEnoughProducts() {
        // Step 1. Create the mock object
        Warehouse warehouse = mock(Warehouse.class);

        // Step 2. Define behaviour
        when(warehouse.thereAreProducts("PlayStation", 9)).thenReturn(false);

        // Step 3. Execute method
        PurchaseOrder purchaseOrder = new PurchaseOrder("PlayStation", 9);
        purchaseOrder.purchase(warehouse);

        // Step 4. Verify
        // verify(warehouse, times(9)).remove("PlayStation", 9);
        verify(warehouse, never()).remove("PlayStation", 9);
    }
}