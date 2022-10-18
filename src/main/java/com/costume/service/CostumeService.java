package com.costume.service;

import com.costume.model.Costume;
import com.costume.repository.CostumeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostumeService {

    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int id) {
        return costumeRepository.getCostume(id);
    }

    public Costume save(Costume costume) {
        if (costume.getId() == null) {
            return costumeRepository.save(costume);
        } else {
            Optional<Costume> unDisfraz = costumeRepository.getCostume(costume.getId());

            if (unDisfraz.isEmpty()) {
                return costumeRepository.save(costume);
            } else {
                return costume;
            }
        }
    }

    public boolean deleteCostume(int id) {
        Optional<Costume> unDisfaz = costumeRepository.getCostume(id);

        if (unDisfaz.isEmpty()) {
            return false;
        } else {
            costumeRepository.delete(unDisfaz.get());
            return true;
        }
    }

    /*
        {"id":1,
        "brand":"DC Costumes",
        "name":"Superman",
        "description":"superman costume",
        "year":2021}
     */
    public Costume update(Costume costume) {
        if (costume.getId() != null) {
            Optional<Costume> e = costumeRepository.getCostume(costume.getId());
            if (!e.isEmpty()) {
                if (costume.getName() != null) {
                    e.get().setName(costume.getName());
                }
                if (costume.getBrand() != null) {
                    e.get().setBrand(costume.getBrand());
                }
                if (costume.getYear() != null) {
                    e.get().setYear(costume.getYear());
                }
                if (costume.getDescription() != null) {
                    e.get().setDescription(costume.getDescription());
                }
                if (costume.getCategory() != null) {
                    e.get().setCategory(costume.getCategory());
                }
                costumeRepository.save(e.get());
                return e.get();
            } else {
                return costume;
            }
        } else {
            return costume;
        }
    }
}
