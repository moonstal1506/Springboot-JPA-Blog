package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//사용자가 요청 ->응답 html
//@Controller


//사용자가 요청->응답(data)
@RestController
public class HttpController {
	
	private static final String TAG="HttpController:";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("sar@mail.com").build();
		System.out.println(TAG+"getter"+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter"+m.getUsername());
		return "lombok test 완료";
	}
	
	//select
	//인터넷 브라우저요청은 get만가능
//	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String username) {
//		return "get 요청:"+id+","+username;
//	}
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//insert
	@PostMapping("http/post")
	public String postTest(@RequestBody Member m) {//MessageConverter(스프링부트)
		return "post 요청: "+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	
	//update
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	//delete
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
