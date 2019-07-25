package model;

public class Rule implements Comparable<Rule>{

    private String name;

    private int priority;

    public Rule(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Rule rule) {
         if (getPriority() < rule.getPriority() ){
             return -1;
         } else if ( getPriority() > rule.getPriority() ){
             return 1;
         }else{
             return getName().compareTo(rule.getName());
         }
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
