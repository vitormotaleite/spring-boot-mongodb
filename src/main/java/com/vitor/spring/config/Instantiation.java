package com.vitor.spring.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vitor.spring.domain.Post;
import com.vitor.spring.domain.User;
import com.vitor.spring.domain.repository.PostRepository;
import com.vitor.spring.domain.repository.UserRepository;
import com.vitor.spring.dto.AuthorDTO;
import com.vitor.spring.dto.CommentDTO;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		this.userRepository.deleteAll();
		this.postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		this.userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null,sdf.parse("21/08/2018"),"partiu viagem!","vou viajar para sao paulo, abracos!",new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/08/2018"),"bom dia","acordei feliz hoje",new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("boa viagem mano",sdf.parse("21/03/2018"),new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("aproveite",sdf.parse("22/03/2018"),new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("tenha um otimo dia",sdf.parse("23/03/2018"),new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		this.postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		this.userRepository.save(maria);

	}

}
