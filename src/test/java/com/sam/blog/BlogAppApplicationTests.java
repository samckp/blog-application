package com.sam.blog;

import com.sam.blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest(){

		String className = this.userRepository.getClass().getName();
		String pkgName = this.userRepository.getClass().getPackageName();

		System.out.println("class name : " + className);
		System.out.println("packageName : " + pkgName);

	}
}
