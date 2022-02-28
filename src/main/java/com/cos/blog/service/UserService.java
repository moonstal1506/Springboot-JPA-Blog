package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //스프링이 컴포넌트 스캔해서 bean에 등록
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //1234 원문
		String encPassword=encoder.encode(rawPassword);//해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER); 
		userRepository.save(user);
	}
	@Transactional
	public void 회원수정(User user) {
		//수정시에는 영속성 컨텍스트 user 오브젝트를 영속화 시키고, 영속화된 user 오브젝트를 수정
		//select를 해서 user오브젝트를 db로부터 가져오는 이유는 영속화를 하기위해
		//영속화된 오브젝트를 변경하면 자동으로 db에 update문 날려중
		
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
		//회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = comit이 자동으로 됨
		//영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
	}

}
