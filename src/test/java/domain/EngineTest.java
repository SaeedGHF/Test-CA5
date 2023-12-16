package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class EngineTest {
    private Engine engine;

    @Before
    public void setUp() {
        engine = new Engine();
    }

    @Test
    public void shouldAddOrder() {
        Order o1 = new Order();
        o1.setId(1);
        o1.setCustomer(1);
        o1.setPrice(1000);
        o1.setQuantity(50);
        int quantity = engine.addOrderAndGetFraudulentQuantity(o1);
        assertEquals(quantity, 50);
    }


    @Test
    public void shouldAddMultipleOrder() {
        Order o1 = new Order();
        o1.setId(1);
        o1.setCustomer(1);
        o1.setPrice(1000);
        o1.setQuantity(50);
        int quantity = engine.addOrderAndGetFraudulentQuantity(o1);
        assertEquals(quantity, 50);

        Order o2 = new Order();
        o2.setId(2);
        o2.setCustomer(1);
        o2.setPrice(1000);
        o2.setQuantity(500);
        int q2 = engine.addOrderAndGetFraudulentQuantity(o2);

        Order o3 = new Order();
        o3.setId(3);
        o3.setCustomer(1);
        o3.setPrice(1000);
        o3.setQuantity(5000);
        int q3 = engine.addOrderAndGetFraudulentQuantity(o3);

        Order o4 = new Order();
        o4.setId(4);
        o4.setCustomer(1);
        o4.setPrice(1000);
        o4.setQuantity(5);
        int q4 = engine.addOrderAndGetFraudulentQuantity(o4);
    }

    @Test
    public void shouldNotAddSameOrder() {
        Order o1 = new Order();
        o1.setId(1);
        o1.setCustomer(1);
        o1.setPrice(1000);
        o1.setQuantity(50);
        engine.addOrderAndGetFraudulentQuantity(o1);
        int quantity = engine.addOrderAndGetFraudulentQuantity(o1);
        assertEquals(quantity, 0);
    }

    @Test
    public void shouldNotAddZeroQuantityOrder() {
        Order o1 = new Order();
        o1.setId(1);
        o1.setQuantity(0);

        int quantity = engine.addOrderAndGetFraudulentQuantity(o1);
        assertEquals(quantity, 0);
    }

    @Test
    public void shouldReturnQuantityPatternByPrice() {
        Order o1 = new Order();
        o1.setId(4);
        o1.setPrice(100);
        o1.setCustomer(1);
        o1.setQuantity(10);

        Order o2 = new Order();
        o2.setId(5);
        o2.setPrice(100);
        o2.setCustomer(1);
        o2.setQuantity(15);

        Order o3 = new Order();
        o3.setId(6);
        o3.setPrice(100);
        o3.setCustomer(1);
        o3.setQuantity(20);

        Order o4 = new Order();
        o4.setId(4);
        o4.setPrice(200);
        o4.setCustomer(1);
        o4.setQuantity(15);

        Order o5 = new Order();
        o5.setId(5);
        o5.setPrice(200);
        o5.setCustomer(1);
        o5.setQuantity(20);

        engine.addOrderAndGetFraudulentQuantity(o1);
        engine.addOrderAndGetFraudulentQuantity(o2);
        engine.addOrderAndGetFraudulentQuantity(o3);
        engine.addOrderAndGetFraudulentQuantity(o4);
        engine.addOrderAndGetFraudulentQuantity(o5);

        assertEquals(engine.getQuantityPatternByPrice(100), 5);
        assertEquals(engine.getQuantityPatternByPrice(200), 0);
    }

    @Test
    public void testGetAverageOrderQuantityByCustomer() {
        Engine engine = new Engine();

        // Add sample orders to the engine
        Order order1 = new Order();
        order1.setCustomer(1);
        order1.setQuantity(35);
        engine.addOrderAndGetFraudulentQuantity(order1);

        Order order2 = new Order();
        order2.setCustomer(1);
        order2.setQuantity(65);
        engine.addOrderAndGetFraudulentQuantity(order2);

        // Test the average order quantity for customer 1
        int averageQuantity = engine.getAverageOrderQuantityByCustomer(1);
        assertEquals(35, averageQuantity);
    }

    @Test
    public void testGetQuantityPatternByPrice() {
        Engine engine = new Engine();

        // Add sample orders to the engine
        Order order1 = new Order();
        order1.setId(1);
        order1.setPrice(100);
        order1.setQuantity(5);
        engine.addOrderAndGetFraudulentQuantity(order1);

        Order order2 = new Order();
        order2.setId(2);
        order2.setPrice(100);
        order2.setQuantity(8);
        engine.addOrderAndGetFraudulentQuantity(order2);

        Order order3 = new Order();
        order3.setId(3);
        order3.setPrice(100);
        order3.setQuantity(12);
        engine.addOrderAndGetFraudulentQuantity(order3);

        // Test the quantity pattern for price 100
        int quantityPattern = engine.getQuantityPatternByPrice(100);
        assertEquals(0, quantityPattern);
    }

    @Test
    public void testGetCustomerFraudulentQuantity() {
        Engine engine = new Engine();

        // Add sample orders to the engine
        Order order1 = new Order();
        order1.setCustomer(1);
        order1.setQuantity(5);
        engine.addOrderAndGetFraudulentQuantity(order1);

        Order order2 = new Order();
        order2.setCustomer(1);
        order2.setQuantity(8);
        engine.addOrderAndGetFraudulentQuantity(order2);

        // Test the fraudulent quantity for customer 1 with quantity 10
        Order order3 = new Order();
        order3.setCustomer(1);
        order3.setQuantity(10);
        int fraudulentQuantity = engine.getCustomerFraudulentQuantity(order3);
        assertEquals(5, fraudulentQuantity);
    }

    @Test
    public void testAddOrderAndGetFraudulentQuantity() {
        Engine engine = new Engine();

        Order order1 = new Order();
        order1.setId(1);
        order1.setCustomer(1);
        order1.setPrice(100);
        order1.setQuantity(5);
        int fraudulentQuantity1 = engine.addOrderAndGetFraudulentQuantity(order1);
        assertEquals(5, fraudulentQuantity1);

        Order order2 = new Order();
        order2.setId(2);
        order2.setCustomer(2);
        order2.setPrice(100);
        order2.setQuantity(10);
        // int fraudulentQuantity2 = engine.addOrderAndGetFraudulentQuantity(order2);
        // assertEquals(0, fraudulentQuantity2);

        Order order3 = new Order();
        order3.setId(3);
        order3.setCustomer(1);
        order3.setPrice(100);
        order3.setQuantity(10);
        int fraudulentQuantity3 = engine.addOrderAndGetFraudulentQuantity(order3);
        assertEquals(5, fraudulentQuantity3);
    }
}
