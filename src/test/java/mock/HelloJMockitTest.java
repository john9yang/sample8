package mock;

import java.util.Locale;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

public class HelloJMockitTest {
  @Mocked
  Locale locale;

  @Test
  public void testSayHelloChina(){

     new Expectations(){
       {
         Locale.getDefault();
         result = Locale.CHINA;
       }
     };

    Assert.assertTrue("您好,JMockit!".equals((new HelloJMockit()).sayHello()));
  }

}
