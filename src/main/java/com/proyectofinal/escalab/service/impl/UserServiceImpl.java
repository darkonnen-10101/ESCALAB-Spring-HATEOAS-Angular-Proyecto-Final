package com.proyectofinal.escalab.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyectofinal.escalab.dao.IUserRepository;
import com.proyectofinal.escalab.service.IUserService;
import com.proyectofinal.escalab.entity.AppUser;
import org.springframework.security.core.userdetails.User;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public AppUser registrar(AppUser obj) {
		return userRepository.save(obj);
	}

	@Override
	public AppUser modificar(AppUser obj) {
		return userRepository.save(obj);
	}

	@Override
	public List<AppUser> listar() {
		return userRepository.findAll();
	}

	@Override
	public AppUser leerPorId(Integer id) {
		Optional<AppUser> op = userRepository.findById(id);
		return op.isPresent() ? op.get() : new AppUser(); 
	}

	@Override
	public boolean eliminar(Integer id) {
		userRepository.deleteById(id);
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser usuario = userRepository.findOneByUsername(username);		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getRoleName()));
		});
		
		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);

		return ud;
	}

}
