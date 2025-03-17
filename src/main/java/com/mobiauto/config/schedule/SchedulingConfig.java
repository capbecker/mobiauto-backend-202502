package com.mobiauto.config.schedule;

import com.mobiauto.model.Oportunidade;
import com.mobiauto.model.Usuario;
import com.mobiauto.service.OportunidadeService;
import com.mobiauto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    OportunidadeService oportunidadeService;

    @Autowired
    UsuarioService usuarioService;

    @Scheduled(fixedRate = 10000)
    public void executarPeriodicamente() {
        List<Oportunidade> oportunidades = oportunidadeService.buscarOportunidadesSemResponsavel();
        List<Usuario> usuarios = usuarioService.buscarUsuariosOrdenadoPorQuantidadeOportunidade();
        int j = 0;
        for (int i = 0; i<oportunidades.size(); i++) {
            Oportunidade proximaOportunidade = oportunidades.get(i);
            Usuario proximoUsuario = null;
            int jInicial = j; //para evitar loop infinito
            while(proximoUsuario==null) {
                if(usuarios.get(j).getRevenda().getId().equals(proximaOportunidade.getRevenda().getId())) {
                    proximoUsuario = usuarios.get(j);
                }
                j++;
                if (jInicial == j) {
                    break;
                }
                if (j>=usuarios.size()) {
                    j = 0;
                }
            }
            oportunidadeService.atribuiUsuario(proximaOportunidade, proximoUsuario);
        }
    }
}
