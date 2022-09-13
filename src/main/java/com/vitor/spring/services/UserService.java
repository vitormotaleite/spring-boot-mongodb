package com.vitor.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.spring.domain.User;
import com.vitor.spring.domain.repository.UserRepository;
import com.vitor.spring.dto.UserDTO;
import com.vitor.spring.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User finById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return this.repo.insert(obj);
	}
	
	public User fromDto(UserDTO userDto) {
		return new User(userDto.getId(),userDto.getName(),userDto.getEmail());
	}
	
	public void delete(String id) {
		this.finById(id);
		repo.deleteById(id);
	}

}
