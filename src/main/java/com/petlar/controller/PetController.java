package com.petlar.controller;

import com.petlar.model.Pet;
import com.petlar.service.PetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public String listarPets(Model model) {
        model.addAttribute("pets", petService.listarTodos());
        return "pet/lista";
    }

    @GetMapping("/novo")
    public String novoPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "pet/form";
    }

    @PostMapping
    public String salvarPet(@ModelAttribute("pet") @Valid Pet pet) {
        petService.salvar(pet);
        return "redirect:/pets";
    }

    @GetMapping("/editar/{id}")
    public String editarPetForm(@PathVariable Long id, Model model) {
        model.addAttribute("pet", petService.buscarPorId(id));
        return "pet/form";
    }

    @PostMapping("/{id}")
    public String atualizarPet(@PathVariable Long id, @ModelAttribute("pet") @Valid Pet pet) {
        petService.atualizar(id, pet);
        return "redirect:/pets";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPet(@PathVariable Long id) {
        petService.deletar(id);
        return "redirect:/pets";
    }
}
