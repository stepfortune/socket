package com.lab409.socket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


public class SocketApplicationTests {

	public static void main(String[] args) {
		List<Integer> integers = new ArrayList<>();
		integers.add(new Integer(1));
		integers.add(new Integer(2));
		System.out.println(integers.size());
		List<Integer> integers1 = new ArrayList<>();
		integers1.addAll(integers);
		integers1.clear();
		System.out.println(integers.size());

		List<Integer> i1 = integers;
		List<Integer> i2 =integers;
		if (i1 == i2){
			System.out.println("equal");
		}
	}

}
