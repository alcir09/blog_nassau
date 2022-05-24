package com.nassau.blog.blog.Controller;

import com.nassau.blog.blog.DTO.ArtigoDTO;
import com.nassau.blog.blog.Helper.ArtigoHelper;
import com.nassau.blog.blog.exception.ArtigoInexistenteException;
import com.nassau.blog.blog.model.Artigo;
import com.nassau.blog.blog.service.ArtigoLocalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bytebuddy.asm.Advice.Return;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping(path = "/blog")
public class ArtigoController {

    @Autowired
    private ArtigoLocalService artigoLocalService ; 

    @PostMapping(value="/artigo")
    public @ResponseBody Artigo adicionarArtigo(@RequestBody ArtigoDTO artigoDTO) {

        Artigo artigo = new Artigo();

        artigo.setTitulo(artigoDTO.getTitulo());
        artigo.setAutor(artigoDTO.getAutor());
        artigo.setTexto(artigoDTO.getTexto());
        artigo.setData( ArtigoHelper.converterStringParamDate(artigoDTO.getData()));
        
        return artigoLocalService.criarArtigo(artigo);
    }

    @GetMapping(path = "/artigo")
    public @ResponseBody Page<Artigo> buscarArtigos(
           @RequestParam(value = "page", defaultValue = "0") String page,
           @RequestParam(value = "size", defaultValue = "0") String size) {

            Pageable pagging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));

            return artigoLocalService.buscarArtigos(pagging);
           }

    @PutMapping(path = "/artigo/{id}")
    public @ResponseBody Object buscarArtigoPeloID(@PathVariable("id") String id) {
        try {
            return artigoLocalService.buscarArtigoPeloId(Integer.parseInt(id));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/artigo/{id}")
        public @ResponseBody Object atualizarArtigo(
            @PathVariable("id") String id, @RequestBody ArtigoDTO artigoDTO){
            try{
                  artigoLocalService.atulizarArtigo(Integer.parseInt(id),
                  artigoDTO.getTitulo(), artigoDTO.getAutor(),
                  ArtigoHelper.converterStringParamDate(artigoDTO.getData()), artigoDTO.getTexto());
               
               return "Artigo atualizado com Sucesso!";
            }catch(ArtigoInexistenteException e){
                return e.getMessage();
            }
        }

    @DeleteMapping(path = "artigo/{id}")
    public @ResponseBody String deletarArtigo(@PathVariable("id") String id){

        try {
        artigoLocalService.deletarArtigo(Integer.parseInt(id));

        return "artigo deletado com sucesso";

        } catch (ArtigoInexistenteException e) {
            return e.getMessage();
        }

    }
    
}
