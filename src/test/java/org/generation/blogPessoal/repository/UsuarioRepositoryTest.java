package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario (0L, "Luiz Andrade", "https://i.imgur.com/Zn50qgQ.jpeg", "luiz@gmail.com", "12345678"));
		usuarioRepository.save(new Usuario (0L, "José Silva", "https://i.imgur.com/Bz2nddE.jpeg", "jose@gmail.com", "87654321"));
		usuarioRepository.save(new Usuario (0L, "Paulo Silva", "https://i.imgur.com/jKBRkIF.png", "paulo@gmail.com", "23456789"));
		usuarioRepository.save(new Usuario (0L, "Adriana Silva", "https://i.imgur.com/hT6fmSj.jpeg", "adriana@gmail.com", "98765432"));
	}
	
	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("luiz@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("luiz@gmail.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuario() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("José Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Paulo Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana Silva"));
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}