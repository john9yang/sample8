package guava.collection;

import com.google.common.collect.ForwardingList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddLoggingListDemo {
    public static void main(String[] args) {
        AddLoggingList<String> languages = new AddLoggingList<String>();
        languages.add("java");
    }
}

class AddLoggingList<E> extends ForwardingList<E>{
    final List<E> delegate = new ArrayList<>();  //backing list

    @Override
    protected List<E> delegate() {
        return delegate;
    }

    @Override
    public void add(int index, E element) {
        System.out.println("add index:"+index);
        super.add(index, element);
    }

    @Override
    public boolean add(E element) {
        return standardAdd(element);
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        return standardAddAll(elements);
    }
}
