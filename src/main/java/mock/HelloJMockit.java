package mock;

import java.util.Locale;

public class HelloJMockit {

  public String sayHello(){
    Locale locale = Locale.getDefault();
    if ( locale.equals(Locale.CHINA) ){
      return "您好,JMockit!";
    }
    else{
      return "Hello,JMockit!";
    }
  }
}
