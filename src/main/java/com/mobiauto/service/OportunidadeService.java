package com.mobiauto.service;

import com.mobiauto.dao.*;

import com.mobiauto.dao.specs.OportunidadeSpecification;
import com.mobiauto.model.Oportunidade;
import com.mobiauto.model.Perfil;
import com.mobiauto.model.Usuario;
import com.mobiauto.model.dto.OportunidadeCreateDTO;
import com.mobiauto.model.dto.OportunidadeFilterDTO;
import com.mobiauto.model.dto.OportunidadeUpdateDTO;
import com.mobiauto.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OportunidadeService {

    @Autowired
    private OportunidadeRepository oportunidadeRepository;

    @Autowired
    private RevendaRepository revendaRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private OportunidadeSpecification oportunidadeSpecification;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Oportunidade salvar(OportunidadeCreateDTO oportunidadeDTO) {

        Oportunidade oportunidade = new Oportunidade();

        oportunidade.setVeiculo(veiculoRepository.findById(oportunidadeDTO.getIdVeiculo()).orElseThrow());

        oportunidade.setRevenda(usuarioService.getUsuarioLogado().getRevenda());

        oportunidade.setStatus(Status.NOVO);

        return oportunidadeRepository.save(oportunidade);
    }

    public List<Oportunidade> buscar() {
        return oportunidadeRepository.findAll();
    }

    public Oportunidade buscarUm(Long id) {
        return oportunidadeRepository.findById(id).orElse(null);
    }

    public List<Oportunidade> filtrar(OportunidadeFilterDTO oportunidadeFilterDTO) {
        Specification<Oportunidade> spec = oportunidadeSpecification.filtrar(oportunidadeFilterDTO);
        return oportunidadeRepository.findAll(spec);
    }

    public Oportunidade atualizar(OportunidadeUpdateDTO oportunidadeDTO) throws Exception {

        Oportunidade oportunidade = oportunidadeRepository.findById(oportunidadeDTO.getIdOportunidade()).orElseThrow();

        Usuario usuarioLogado = usuarioService.getUsuarioLogado();

        //Pegando o maior nivel de perfil do usuario
        int maxNivelPerfil = usuarioLogado.getPerfis().stream().map(Perfil::getNivel).max(Integer::compareTo).orElseThrow();

        if (maxNivelPerfil < 3 && !usuarioLogado.getId().equals(oportunidade.getUsuario().getId()) ) {
            throw new Exception("Usuário sem permissão de modificar");
        }


        if (maxNivelPerfil>=3 && oportunidadeDTO.getIdUsuario()!=null) {
            oportunidade.setDataAtribuicao(LocalDateTime.now());
            oportunidade.setUsuario(usuarioRepository.findById(oportunidadeDTO.getIdUsuario()).orElseThrow());
        }

        if (oportunidadeDTO.getIdCliente()!=null && oportunidade.getCliente() == null) {
            oportunidade.setCliente(clienteRepository.findById(oportunidadeDTO.getIdCliente()).orElseThrow());
        }

        if (oportunidadeDTO.getIdVeiculo()!=null) {
            oportunidade.setVeiculo(veiculoRepository.findById(oportunidadeDTO.getIdVeiculo()).orElseThrow());
        }

        if (oportunidadeDTO.getMotivoConclusao()!= null && oportunidade.getCliente()!=null) {
            oportunidade.setStatus(Status.CONCLUIDO);
            oportunidade.setMotivoConclusao(oportunidadeDTO.getMotivoConclusao());
        } else {
            oportunidade.setStatus(Status.EM_ANDAMENTO);
        }

        return oportunidadeRepository.save(oportunidade);
    }
}
