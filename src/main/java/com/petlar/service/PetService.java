package com.petlar.service;

import com.petlar.model.Pet;
import com.petlar.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public Pet buscarPorId(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado!"));
    }

    public Pet salvar(@Valid Pet pet) {
        return petRepository.save(pet);
    }

    public Pet atualizar(Long id, @Valid Pet petAtualizado) {
        Pet petExistente = buscarPorId(id);

        petExistente.setNome(petAtualizado.getNome());
        petExistente.setTipo(petAtualizado.getTipo());
        petExistente.setDataNascimento(petAtualizado.getDataNascimento());
        petExistente.setValorAdocao(petAtualizado.getValorAdocao());
        petExistente.setTelefoneDono(petAtualizado.getTelefoneDono());

        return petRepository.save(petExistente);
    }

    public void deletar(Long id) {
        Pet pet = buscarPorId(id);
        petRepository.delete(pet);
    }
}
