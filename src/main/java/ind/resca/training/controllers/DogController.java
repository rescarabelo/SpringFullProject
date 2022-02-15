package ind.resca.training.controllers;

import ind.resca.training.models.Dog;
import ind.resca.training.utils.dtos.RegisterDogDto;
import ind.resca.training.repos.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/dogs")
public class DogController {

    final DogRepository dogRepository;

    @GetMapping("/list")
    @Cacheable(value = "dogList")
    public Page<Dog> list(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(name = "id", required = false) UUID dogId
    ) {
        return dogId == null ? dogRepository.findAll(pageable) : dogRepository.findById(dogId, pageable);
    }

    @GetMapping("list/owner")
    public Page<Dog> findByOwnerId(
            @RequestParam(name = "id") UUID ownerId,
            Pageable pageable
    ) {
        return dogRepository.findByOwnerId(ownerId, pageable);
    }

    @PostMapping("/register")
    @Transactional
    @CacheEvict(value = "dogList", allEntries = true)
    public ResponseEntity<Dog> save(
            @Valid RegisterDogDto dog,
            UriComponentsBuilder uriBuilder
    ) {

        Dog newDog = dogRepository.save(dog.convert());

        URI uri = uriBuilder.path("dogs/list/dog").queryParam("id", newDog.getId()).buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(newDog);
    }

    @PutMapping("/register")
    @CacheEvict(value = "dogList", allEntries = true)
    public Dog edit(
            Dog dog
    ) {
        return dogRepository.save(dog);
    }

    @DeleteMapping("/delete")
    @CacheEvict(value = "dogList", allEntries = true)
    public void delete(
            @RequestParam("id") UUID dogId
    ) {
        dogRepository.deleteById(dogId);
    }
}
