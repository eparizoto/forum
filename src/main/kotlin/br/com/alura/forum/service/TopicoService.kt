package br.com.alura.forum.service

import br.com.alura.forum.dto.NovoTopicoDto
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService ) {


    fun listar(): List<Topico> {
        return topicos;
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream()
            .filter({t -> t.id == id})
            .findFirst()
            .get()
    }

    fun cadastrar(dto: NovoTopicoDto) {

        topicos = topicos.plus(Topico(
            id = topicos.size.plus(1L),
            titulo = dto.titulo,
            mensagen = dto.mensagem,
            curso = cursoService.buscarPorId(dto.idCurso),
            autor = usuarioService.buscarPorId(dto.idAutor),
        ))
    }
}