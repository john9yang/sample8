package com.sample.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ReduceTest {

    @Test
    public void sumTest(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        int result = numbers.stream().reduce(0,(subTotal,element) -> subTotal+element);
        assertThat(result).isEqualTo(21);
    }
}
