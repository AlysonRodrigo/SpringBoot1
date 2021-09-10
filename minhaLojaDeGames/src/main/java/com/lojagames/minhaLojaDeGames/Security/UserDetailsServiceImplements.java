package com.lojagames.minhaLojaDeGames.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lojagames.minhaLojaDeGames.model.usuarioModel;
import com.lojagames.minhaLojaDeGames.repository.usuarioRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {
	private @Autowired usuarioRepository repositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<usuarioModel> objetoOptional = repositorio.findByEmail(username);

		if (objetoOptional.isPresent()) {
			return new UserDetailsImplements(objetoOptional.get());
		} else {
			throw new UsernameNotFoundException(username + "Não existe!");
		}
	}

}
