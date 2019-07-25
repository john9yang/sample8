package easyrule.proxy;

import model.Rule;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RuleProxy implements InvocationHandler {
    private Object target;

    private RuleProxy(final Object target){
       this.target = target;
    }

    public static Rule asRule(final Object rule){
        Rule result;
        if (rule instanceof  Rule){
            result = (Rule)rule;
        }
        else{
            result = (Rule) Proxy.newProxyInstance(Rule.class.getClassLoader(),new Class[]{Comparable.class},
                    new RuleProxy(rule));
        }
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();

        switch ( methodName ){
            case "compareTo":
                return compareToMethod(args);
            default:
                return null;
        }
    }

    private Object compareToMethod(final Object[] args) throws Exception {
        Method compareToMethod = getCompareToMethod();
        if (compareToMethod != null) {
            return compareToMethod.invoke(target, args);
        } else {
            Rule otherRule = (Rule) args[0];
            return compareTo(otherRule);
        }
    }

    private Method getCompareToMethod(){
         Method[] methods = getMethods();
         for( Method method : methods ){
             if (method.getName().equals("compareTo")) {
                 return method;
             }
         }
         return null;
    }

    private Method[] getMethods(){
        return getTargetClass().getMethods();
    }

    private Class<?> getTargetClass(){
       return target.getClass();
    }

    private int compareTo(final Rule otherRule) throws Exception {
        int otherPriority = otherRule.getPriority();
        int priority = 0;
        if (priority < otherPriority) {
            return -1;
        } else if (priority > otherPriority) {
            return 1;
        } else {
            String otherName = otherRule.getName();
            String name = "test";
            return name.compareTo(otherName);
        }
    }
}
