package br.com.aluraFlix.categorias;


import br.com.aluraFlix.domain.Categorias;
import br.com.aluraFlix.domain.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {




}
