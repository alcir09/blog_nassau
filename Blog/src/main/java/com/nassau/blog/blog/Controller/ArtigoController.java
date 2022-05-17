package com.nassau.blog.blog.Controller;

import com.nassau.blog.blog.DTO.ArtigoDTO;
import com.nassau.blog.blog.model.Artigo;
import com.nassau.blog.blog.service.ArtigoLocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(path = "/blog")
public class ArtigoController {

    @Autowired
    private ArtigoLocalService artigoLocalService ; 

    @PostMapping(value="/artigo")
    public @ResponseBody Artigo adicionarArtigo(@RequestBody ArtigoDTO artigoDTO) {

        Artigo artigo = new Artigo();

        artigo.setAutor(artigoDTO.getTitulo());
        artigo.setAutor(artigoDTO.getAutor());
        artigo.setAutor(artigoDTO.getTexto());
        
        return artigoLocalService.criarArtigo(artigo);
    }
    
}
