package com.demo.alb_um.Modulos.Alumno;

import com.demo.alb_um.DTOs.AlumnoDTO;
import com.demo.alb_um.DTOs.CitaDTO;
import com.demo.alb_um.DTOs.TallerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumno")
public class UsuarioAlumnoControlador {

    @Autowired
    private UsuarioAlumnoServicio usuarioAlumnoServicio;

    @GetMapping("/{userName}")
    public String obtenerInformacionAlumnoPorUserName(@PathVariable String userName, Model model) {
        Optional<AlumnoDTO> alumnoOpt = usuarioAlumnoServicio.obtenerInformacionAlumnoPorUserName(userName);
        if (alumnoOpt.isPresent()) {
            AlumnoDTO alumno = alumnoOpt.get();
            List<CitaDTO> citasPendientes = usuarioAlumnoServicio.obtenerCitasPendientes(userName);
            List<TallerDTO> talleresPendientes = usuarioAlumnoServicio.obtenerTalleresPendientes(userName);

            model.addAttribute("alumno", alumno);
            model.addAttribute("citasPendientes", citasPendientes);
            model.addAttribute("talleresPendientes", talleresPendientes);
            return "alumno";
        }
        return "error"; // Página de error si no se encuentra el alumno
    }
}
