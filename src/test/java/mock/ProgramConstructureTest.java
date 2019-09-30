package mock;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

public class ProgramConstructureTest{

  @Mocked
  HelloJMockit helloJMockit;

  @Test
  public void test1(){
    new Expectations(){
      {
         helloJMockit.sayHello();
         result = "hello,john";
      }
    };

    String msg = helloJMockit.sayHello();
    assertThat(msg,equalTo("hello,john"));

    String msg2 = helloJMockit.sayHello();
    assertThat(msg2,equalTo("hello,john"));

    new Verifications(){
      {
        helloJMockit.sayHello();
        times =2 ;
      }
    };
  }

}
