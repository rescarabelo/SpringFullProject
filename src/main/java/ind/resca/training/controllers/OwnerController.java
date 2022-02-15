package ind.resca.training.controllers;

import ind.resca.training.models.Owner;
import ind.resca.training.repos.OwnerRepository;
import ind.resca.training.utils.dtos.RegisterOwnerDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
    final OwnerRepository ownerRepository;

    @GetMapping("/list")
    public Page<Owner> list(
            @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(name = "id", required = false) UUID ownerId
    ) {
        return ownerId == null ? ownerRepository.findAll(pageable) : ownerRepository.findById(ownerId, pageable);
    }

    @PostMapping("/register")
    public ResponseEntity<Owner> save(
            @Valid RegisterOwnerDto owner,
            UriComponentsBuilder uriBuilder
    ) {
        Owner newOwner = ownerRepository.save(owner.convert());

        URI uri = uriBuilder.path("owners/list/owner").queryParam("id", newOwner.getId()).buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(newOwner);
    }

    @DeleteMapping("/delete")
    public void delete(
            @RequestParam("id") UUID ownerId
    ) {
        ownerRepository.deleteById(ownerId);
    }
}
