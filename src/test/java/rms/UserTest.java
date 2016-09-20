package rms;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jack.rms.common.SpringContextUtil;
import com.jack.rms.dao.UserMapper;
import com.jack.rms.model.User;
import com.jack.rms.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application.xml"})
public class UserTest {
	
	@Autowired
	private IUserService userService;

	@Test
	public void getUser() {
		User user = userService.getUserById(1);

		System.out.println("username is " + user.getUsername() + "; nick name is " + user.getNickName());
		
	}
	
//	public static void main(String[] args) {
//		long startTime = System.currentTimeMillis();
//		
//		try {
//			Thread.sleep(2000);
//		} catch (Exception e) {
//		}
//		
//		Long endTime = System.currentTimeMillis();
//		
//		System.out.println((endTime-startTime)/1000);
//		
//		System.out.println(Runtime.getRuntime().availableProcessors());
//	}
	
	@Test
	public void saveUser() {
		User user = null;
		System.out.println("====== start save user =======");
		// 保存数量
		int count = 1;
		for (int i = 0; i < count; i++) {
			user = new User();
			user.setUsername("jack111" + i);
			user.setNickName("===jack===");
			user.setPassword("e10adc3949ba59abbe56e057f20f883e");
			user.setRoleId(1);
			
			int t = userService.saveUser(user);
			if (t <= 0) {
				System.out.println("=======save user error: number is " + i + "=========");
			}
		}
		System.out.println("====== end save user =======");
	}
	
	@Test
	public void saveUser4MultipleThread() {

		Long startTime = System.currentTimeMillis();
		System.out.println("====== start save user =======");
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		for (int loop = 0; loop < 2; loop++) {
			newFixedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println("--------1---------");
					try {
						for (int i = 0; i < 10; i++) {
							User user = new User();
							user.setUsername("jack111" + i);
							user.setNickName("===jack===");
							user.setPassword("e10adc3949ba59abbe56e057f20f883e");
							user.setRoleId(1);
	
							userService = SpringContextUtil.getBean("userService");
							int t = userService.saveUser(user);
							if (t <= 0) {
								System.out.println("=======save user error: number is " + i + "=========");
							}
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println("====== end save user =======");
		
		Long endTime = System.currentTimeMillis();
		System.out.println("Total time:" + (endTime-startTime)/1000);
	}
	
	@Test
	public void saveUser4SpringMultipleThread() {

		Long startTime = System.currentTimeMillis();
		System.out.println("====== start save user =======");
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		for (int loop = 0; loop < 2; loop++) {
			newFixedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println("--------1---------");
					try {
						for (int i = 0; i < 10; i++) {
							User user = new User();
							user.setUsername("jack111" + i);
							user.setNickName("===jack===");
							user.setPassword("e10adc3949ba59abbe56e057f20f883e");
							user.setRoleId(1);
	
							userService = SpringContextUtil.getBean("userService");
							UserMapper userMapper = userService.getUserMapper();
							
							int t = 0;
							try {
								t = userMapper.insert(user);
							}
							catch(Exception e) {
								System.out.println("------error-------------");
								e.printStackTrace();
								System.out.println("------error-------------");
							}
							System.out.println("--------6444---------" + t);
							// int t = userService.saveUser(user);
							System.out.println("=========charge record is " + t + "============");
							System.out.println("--------7---------" + t);
							if (t <= 0) {
								System.out.println("=======save user error: number is " + i + "=========");
							}
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		System.out.println("====== end save user =======");
		
		Long endTime = System.currentTimeMillis();
		System.out.println("Total time:" + (endTime-startTime)/1000);
	}

}

