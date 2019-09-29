package mock;

import mockit.*;
import org.junit.Test;

public class StockServiceTest {
    @Mocked
    StockService stockService;

    @Test
    public void getStockPrice(){
        new Expectations(){
            {
                stockService.getStockPrice();
                result = 1.0d;
            }
        };

        System.out.println(stockService.getStockPrice());
    }
}
